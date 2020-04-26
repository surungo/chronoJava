package chrono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import AWT.MoveMouseListener;

public class ChronoView {

	public JFrame frame;
	private JLabel lblNewLabel;
	private ChronoService chronoService;
	private boolean showDecimos;

	/**
	 * Create the application.
	 * @param chronoService 
	 */
	public ChronoView(ChronoService chronoService_in) {
		this.chronoService = chronoService_in;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setBounds(100, 100, 117, 59);
		frame.setLocationRelativeTo(null);
		frame.setLocation(100,100);
        frame.setAlwaysOnTop( true );
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNewLabel = new JLabel("03:00.00");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		MoveMouseListener mml = new MoveMouseListener(frame);
        frame.addMouseListener(mml);
        frame.addMouseMotionListener(mml);
	        
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		frame.setUndecorated(true);
		frame.pack();
        
	}

	public void atualizaTempo(short minutes, byte seconds, byte centiseconds, HashMap<String, String> valores) {
    	if(minutes==0  && seconds<10 )colorAlerta();
    	lblNewLabel.setText(valores.get("minutes")+":"+valores.get("seconds")+(showDecimos?("."+valores.get("centiseconds")):"") );
	}

	public void rodar() {
		this.chronoService.rodar();
		
	}

    public void colorInicial() {
    	lblNewLabel.setForeground(Color.black);
		frame.setBackground(new Color(0xeeeeee));
	}
	
	public void colorAlerta() {
		lblNewLabel.setForeground(Color.white);
		frame.setBackground(Color.red);
	}

}
