package chrono;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.Timer;

public class ChronoService extends Thread {

	private ChronoView chronoview;
	private Timer timer;
	private boolean rodando = false;
	private DecimalFormat timeFormatter;

	// Properties of Program.
	private byte centiseconds = 0;
	private byte seconds = 0;
	private short minutes = 3;
	
	public ChronoService() {
		timeFormatter = new DecimalFormat("00");
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (centiseconds > 0) {
					centiseconds--;
				} else {
					if (seconds == 0 && minutes == 0) {
						timer.stop();
					} else if (seconds > 0) {
						seconds--;
						centiseconds = 99;
					} else if (minutes > 0) {
						minutes--;
						seconds = 59;
						centiseconds = 99;
					}
				}
				if(chronoview!=null)
					chronoview.atualizaTempo(minutes,seconds,centiseconds,getValores());
			}
		});
		chronoview = new ChronoView(this);
		chronoview.atualizaTempo(minutes,seconds,centiseconds,getValores());
		chronoview.frame.setVisible(true);
		chronoview.rodar();
	}

	public void rodar() {
		if (rodando) {
			timer.stop();
			rodando = false;
		} else {
			timer.start();
			rodando = true;
		}
	}

	public void reset() {
		timer.stop();
		rodando = false;
	}

	public void vai() {
		timer.start();
		rodando = true;
	}

	public boolean estaRodando() {
		return rodando;
	}

	public short getMinutes() {
		return minutes;
	}

	public void setMinutes(short minutes) {
			this.minutes = minutes>99?99:minutes;
	}
	
	public void setMinutes(String minutes) {
		setMinutes(Byte.parseByte("".contentEquals(minutes)?"0":minutes));
	}
	
	public byte getSeconds() {
		return seconds;
	}

	public void setSeconds(byte seconds) {
		this.seconds = (seconds>59)?59:seconds;
	}
	
	public void setSeconds(String seconds) {
		setSeconds(Byte.parseByte("".contentEquals(seconds)?"0":seconds));
	}

	public byte getCentiseconds() {
		return centiseconds;
	}

	public void setCentiseconds(byte centiseconds) {
		this.centiseconds = centiseconds>99?99:centiseconds;
	}
	
	public void setCentiseconds(String centiseconds) {
		setCentiseconds(Byte.parseByte("".contentEquals(centiseconds)?"0":centiseconds));
	}

	public String getMinutesString() {
		return timeFormatter.format(minutes);
	}

	public String getSecondsString() {
		return timeFormatter.format(seconds);
	}

	public String getCentisecondsString() {
		return timeFormatter.format(centiseconds);
	}
	
	public  HashMap<String, String> getValores() {
		HashMap<String, String> valores = new HashMap<String, String>();
		valores.put("minutes", getMinutesString());
		valores.put("seconds", getSecondsString());
		valores.put("centiseconds", getCentisecondsString());
		return valores;
	}

	public String getTime() {
		return getTime( false ) ;		
	}

	public String getTime(boolean showDecimos) {
		return getMinutesString() + ":"
                + getSecondsString() +
                (showDecimos? ("."
                + getCentisecondsString() ):"");
	}
	
}
