package chrono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import AWT.MoveMouseListener;
import javax.swing.JButton;

public class ChronoView {

	public JFrame frame;
	private JLabel lblTime;
	private JButton btnReset;
	private ChronoService chronoService;
	private boolean showDecimos = true;

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
		frame.setLocation(0,0);
        frame.setAlwaysOnTop( true );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblTime = new JLabel("03:00.00");
		lblTime.setFont(new Font("Consolas", Font.BOLD, 14));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		MoveMouseListener mml = new MoveMouseListener(this);
        frame.addMouseListener(mml);
        frame.addMouseMotionListener(mml);
	        
		frame.getContentPane().add(lblTime, BorderLayout.NORTH);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	reset();
            }
	    });
		frame.getContentPane().add(btnReset, BorderLayout.SOUTH);
		frame.setUndecorated(true);
		frame.pack();
	}

	public void atualizaTempo(short minutes, byte seconds, byte centiseconds, HashMap<String, String> valores) {
    	if(minutes==0  && seconds<10 )colorAlerta();
    	lblTime.setText(valores.get("minutes")+":"+valores.get("seconds")+(showDecimos?("."+valores.get("centiseconds")):"") );
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
		if(this.chronoService.estaRodando()) {
			colorInicial();
			btnResetsetVisible(false);
		}else {
			colorPause();
			btnResetsetVisible(true);
		}
	}

    private void btnResetsetVisible(boolean b) {
    	
    	btnReset.setVisible(b);
    	int height = frame.getHeight()+ ( ((b)?1:-1)*btnReset.getHeight());
		frame.setSize(frame.getWidth(),height);
	}

	public void colorInicial() {
    	lblTime.setForeground(Color.black);
		frame.setBackground(new Color(0xeeeeee));
	}
	
    public void colorPause() {
    	lblTime.setForeground(new Color(0x999999));
		frame.setBackground(new Color(0xeeeeee));
	}
	
	public void colorAlerta() {
		lblTime.setForeground(Color.white);
		frame.setBackground(Color.red);
	}

}
