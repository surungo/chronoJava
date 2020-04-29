package chrono;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.Timer;

public class ChronoService extends Thread {

	private ChronoView chronoview;
	private Timer timer;
	private boolean rodando = false;
	private DecimalFormat timeFormatter;

	// Properties of Program.
	private long centiseconds_var = 0;
	private long seconds_var = 0;
	private long minutes_var = 3;
	private long centiseconds = 0;
	private long seconds = 0;
	private long minutes = 3;
	private long centisecondsPrec = 0;
	private long centisecondsPrec_var = 0;
	
	public HashMap<String, String> config;
	
	private long startTimeLocalNS = 0;
	private long stopTimeLocalNS = 0;
	private long diffTime = 0;
	private long timePause = 0;
	

	private long timePauseInicio = 0;
	
	public ChronoService() {

		loadConfig();

		timeFormatter = new DecimalFormat("00");
		
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeUpdate(e);
			}

			private void timeUpdate(ActionEvent e) {
				stopTimeLocalNS = System.nanoTime();
				diffTime = convertNanotimeCentiseconds((stopTimeLocalNS - startTimeLocalNS));
				
				atualizaTimes(diffTime);
				if (chronoview != null)
					chronoview.atualizaTempo(minutes_var, seconds_var, centiseconds_var, getValores());
				if (centisecondsPrec_var<=0) {
					zerar();
					timer.stop();
				}
				
			}
			private void timeUpdateBasic(ActionEvent e) {
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
				if (chronoview != null)
					chronoview.atualizaTempo(minutes_var, seconds_var, centiseconds_var, getValores());
				
			}
		});
		chronoview = new ChronoView(this);
		chronoview.frame.setVisible(true);
		chronoview.reset();

	}

	protected void atualizaTimes(long disconnectTime2) {
		centisecondsPrec_var = (getTimePause() + centisecondsPrec - disconnectTime2 );
		minutes_var =  convertCentisecondsMinutos(centisecondsPrec_var);
		long minutosCentiseconds_aux = convertMinutosCentiseconds(minutes_var);
		long secondsCentiseconds_aux = centisecondsPrec_var-minutosCentiseconds_aux;
		seconds_var =   convertCentisecondsSeconds(secondsCentiseconds_aux);
		secondsCentiseconds_aux = convertSecondsCentiseconds(seconds_var);
		centiseconds_var = centisecondsPrec_var-secondsCentiseconds_aux-minutosCentiseconds_aux;
		
	}

	private long convertSecondsCentiseconds(long valor) {
		return (valor*100);
	}

	private long convertCentisecondsSeconds(long valor) {
		return (valor/100);
	}

	private long convertMinutosCentiseconds(long valor) {
		return (valor*100)*60;
	}

	private long convertCentisecondsMinutos(long valor) {
		return (valor/100)/60;
	}

	protected long convertNanotimeCentiseconds(long valor) {
		
		return valor/10000000;
	}

	public long getTimePause() {
		return convertNanotimeCentiseconds(timePause);
	}
	
	private void loadConfig() {
		config = new HashMap<>();
		config.put("view.showCentesimos", "true");
		config.put("time.minutos", "3");
		config.put("time.segundos", "0");
		config.put("time.centesimos", "0");
		config.put("view.opacity", "0.9");
		config.put("view.foreground", "back");
		config.put("view.background", "lightgray");
		config.put("view.pauseforeground", "lightgray");
		config.put("view.pausebackground", "white");
		config.put("view.alertaforeground", "white");
		config.put("view.alertabackground", "red");
		config.put("view.alertaminutos", "0");
		config.put("view.alertasegundos", "10");
		config.put("view.alertacentesimos", "0");

		String filename = "config.properties";
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream(filename);
			prop.load(input);
			prop.forEach((key, value) -> config.put((String) key, (String) value));

		} catch (IOException io) {
			try {
				System.out.println("Unable to find " + filename + ". Create a " + filename);
				OutputStream output = new FileOutputStream(filename);
				// set the properties value
				config.forEach((key, value) -> prop.setProperty(key, value));
				// save properties to project root folder
				prop.store(output, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// io.printStackTrace();
		}

		System.out.println(prop);

	}

	public void pause() {
		if (rodando) {
			timePauseInicio = System.nanoTime();
			this.stopChrono();
		} else {
			this.start();
			timePause += System.nanoTime()-timePauseInicio;
		}
	}

	public void reset() {

		try {
			centiseconds_var = Short.parseShort(this.config.get("time.centesimos"));
		} catch (Exception e) {
			centiseconds_var = centiseconds;
		}
		try {
			seconds_var = Short.parseShort(this.config.get("time.segundos"));
		} catch (Exception e) {
			seconds_var = seconds;
		}

		try {
			minutes_var = Short.parseShort(this.config.get("time.minutos"));
		} catch (Exception e) {
			minutes_var = minutes;
		}
		inicializacentisecondsPrec();
		
		chronoview.atualizaTempo(minutes_var, seconds_var, centiseconds_var, getValores());

		timer.start();
		rodando = true;
		stopTimeLocalNS = System.nanoTime();
		startTimeLocalNS = System.nanoTime();
	}
	

	protected void zerar() {
		centiseconds_var = 0;
		seconds_var = 0;
		minutes_var = 0;
		centisecondsPrec_var = 0;
		chronoview.atualizaTempo(minutes_var, seconds_var, centiseconds_var, getValores());
	}

	private void inicializacentisecondsPrec() {
		long auxminutes_var = minutes_var*60*100;
		long auxseconds_var = seconds_var*100;
		long auxcentiseconds_var = centiseconds_var;
		centisecondsPrec = (auxminutes_var+auxseconds_var+auxcentiseconds_var);
	}

	public void start() {
		timer.start();
		rodando = true;
	}
	
	public void stopChrono() {
		timer.stop();
		rodando = false;
	}

	public boolean estaRodando() {
		return rodando;
	}

	public long getMinutes_var() {
		return minutes_var;
	}

	public void setMinutes_var(long l) {
		this.minutes_var = l > 99 ? 99 : l;
	}

	public void setMinutes(String minutes) {
		setMinutes_var(Long.parseLong("".contentEquals(minutes) ? "0" : minutes));
	}

	public long getSeconds_var() {
		return seconds_var;
	}

	public void setSeconds_var(long seconds) {
		this.seconds_var = (seconds > 59) ? 59 : seconds;
	}

	public void setSeconds(String seconds) {
		setSeconds_var(Long.parseLong("".contentEquals(seconds) ? "0" : seconds));
	}

	public long getCentiseconds_var() {
		return centiseconds_var;
	}

	public void setCentiseconds_var(long s) {
		this.centiseconds_var = s > 99 ? 99 : s;
	}

	public void setCentiseconds(String centiseconds) {
		setCentiseconds_var(Long.parseLong("".contentEquals(centiseconds) ? "0" : centiseconds));
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

	public HashMap<String, String> getValores() {
		HashMap<String, String> valores = new HashMap<String, String>();
		valores.put("minutes", getMinutesString());
		valores.put("seconds", getSecondsString());
		valores.put("centiseconds", getCentisecondsString());
		return valores;
	}

	public String getTime() {
		return getTime(false);
	}

	public String getTime(boolean showDecimos) {
		return getMinutesString() + ":" + getSecondsString() + (showDecimos ? ("." + getCentisecondsString()) : "");
	}

}
