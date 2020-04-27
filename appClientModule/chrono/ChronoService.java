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
	private byte centiseconds_var = 0;
	private byte seconds_var = 0;
	private short minutes_var = 3;
	private byte centiseconds = 0;
	private byte seconds = 0;
	private short minutes = 3;
	
	public ChronoService() {
		timeFormatter = new DecimalFormat("00");
		
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (centiseconds_var > 0) {
					centiseconds_var--;
				} else {
					if (seconds_var == 0 && minutes_var == 0) {
						timer.stop();
					} else if (seconds_var > 0) {
						seconds_var--;
						centiseconds_var = 99;
					} else if (minutes_var > 0) {
						minutes_var--;
						seconds_var = 59;
						centiseconds_var = 99;
					}
				}
				if(chronoview!=null)
					chronoview.atualizaTempo(minutes_var,seconds_var,centiseconds_var,getValores());
			}
		});
		chronoview = new ChronoView(this);
		chronoview.frame.setVisible(true);
		chronoview.reset();
		
	}

	public void pause() {
		if (rodando) {
			timer.stop();
			rodando = false;
		} else {
			timer.start();
			rodando = true;
		}
	}

	public void reset() {
		centiseconds_var=centiseconds;
		seconds_var=seconds;
		minutes_var=minutes;
		chronoview.atualizaTempo(minutes_var,seconds_var,centiseconds_var,getValores());
		
		timer.start();
		rodando = true;
	}

	public void start() {
		timer.start();
		rodando = true;
	}

	public boolean estaRodando() {
		return rodando;
	}

	public short getMinutes_var() {
		return minutes_var;
	}

	public void setMinutes_var(short minutes) {
			this.minutes_var = minutes>99?99:minutes;
	}
	
	public void setMinutes(String minutes) {
		setMinutes_var(Byte.parseByte("".contentEquals(minutes)?"0":minutes));
	}
	
	public byte getSeconds_var() {
		return seconds_var;
	}

	public void setSeconds_var(byte seconds) {
		this.seconds_var = (seconds>59)?59:seconds;
	}
	
	public void setSeconds(String seconds) {
		setSeconds_var(Byte.parseByte("".contentEquals(seconds)?"0":seconds));
	}

	public byte getCentiseconds_var() {
		return centiseconds_var;
	}

	public void setCentiseconds_var(byte centiseconds) {
		this.centiseconds_var = centiseconds>99?99:centiseconds;
	}
	
	public void setCentiseconds(String centiseconds) {
		setCentiseconds_var(Byte.parseByte("".contentEquals(centiseconds)?"0":centiseconds));
	}

	public String getMinutesString() {
		return timeFormatter.format(minutes_var);
	}

	public String getSecondsString() {
		return timeFormatter.format(seconds_var);
	}

	public String getCentisecondsString() {
		return timeFormatter.format(centiseconds_var);
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
