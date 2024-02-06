package clipboard.services;

import AWT.WindowEventMenu;

import javax.swing.*;

public class JButtonI extends JButton {
	public WindowEventMenu wem;
	public JButtonI(String label, WindowEventMenu wem) {
		super(label);
		this.wem= wem;
		this.addActionListener(this.wem);
		//this.setBorderPainted(false);
		this.setFocusPainted(false);
		//this.setContentAreaFilled(false);
	}
}
