package chrono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import AWT.MoveMouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChronoView {

	public JFrame frame;
	private JLabel lblTime;
	private JButton btnReset;
	private ChronoService chronoService;
	private boolean showCentesimos = true;
	public JPanel panel;

	/**
	 * Create the application.
	 * 
	 * @param chronoService
	 */
	public ChronoView(ChronoService chronoService_in) {
		this.chronoService = chronoService_in;
		showCentesimos = "true".equals(this.chronoService.config.get("view.showCentesimos"));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// frame.setBounds(100, 100, 117, 59);
		frame.setLocationRelativeTo(null);
		frame.setLocation(0, 0);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MoveMouseListener mml = new MoveMouseListener(this);
		frame.addMouseListener(mml);
		frame.addMouseMotionListener(mml);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		frame.getContentPane().add(btnReset, BorderLayout.SOUTH);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		lblTime = new JLabel(showCentesimos ? " 00:00.00 " : " 00:00 ");
		panel.add(lblTime);
		lblTime.setFont(new Font("Consolas", Font.BOLD, 14));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		frame.setUndecorated(true);
		frame.pack();
	}

	public void atualizaTempo(long minutes_var, long seconds_var, long centiseconds_var,
			HashMap<String, String> valores) {

		if (minutes_var <= convertStringLong(this.chronoService.config.get("view.alertaminutos"))
				&& seconds_var <= convertStringLong(this.chronoService.config.get("view.alertasegundos"))
				&& centiseconds_var <= convertStringLong(this.chronoService.config.get("view.alertacentesimos"))) {
			colorAlerta();
		}
		lblTime.setText(valores.get("minutes") + ":" + valores.get("seconds")
				+ (showCentesimos ? ("." + valores.get("centiseconds")) : ""));
	}

	private long convertStringLong(String string) {
		long aux = 0;
		try {
			aux = Long.parseLong(string);
		} catch (Exception e) {
			aux = 0;
		}
		return aux;
	}

	public void pause() {
		this.chronoService.pause();
		alterarTela(this.chronoService.estaRodando());
	}

	public void reset() {
		this.chronoService.reset();
		alterarTela(this.chronoService.estaRodando());
	}

	private void alterarTela(boolean estaRodando) {
		if (this.chronoService.estaRodando()) {
			colorInicial();
			btnResetsetVisible(false);
		} else {
			colorPause();
			btnResetsetVisible(true);
		}
	}

	private void btnResetsetVisible(boolean b) {

		btnReset.setVisible(b);
		int height = frame.getHeight() + (((b) ? 1 : -1) * btnReset.getHeight());
		frame.setSize(frame.getWidth(), height);
	}

	private Color getColor(String string) {
		Color color;
		try {
			Field field = Class.forName("java.awt.Color").getField(string);
			color = (Color) field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return color;
	}

	public void colorInicial() {
		lblTime.setForeground(getColor(this.chronoService.config.get("view.foreground")));
		panel.setBackground(getColor(this.chronoService.config.get("view.background")));
		frame.setOpacity(Float.parseFloat(this.chronoService.config.get("view.opacity")));

	}

	public void colorPause() {
		lblTime.setForeground(getColor(this.chronoService.config.get("view.pauseforeground")));
		panel.setBackground(getColor(this.chronoService.config.get("view.pausebackground")));
		frame.setOpacity(1.0f);
	}

	public void colorAlerta() {
		lblTime.setForeground(getColor(this.chronoService.config.get("view.alertaforeground")));
		panel.setBackground(getColor(this.chronoService.config.get("view.alertabackground")));
		frame.setOpacity(1.0f);
	}

}
