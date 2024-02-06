package clipboard.services;

import AWT.WindowEventMenu;

import java.awt.*;

public class ExitService  extends JButtonI{
	public ExitService(String label, WindowEventMenu wem) {
		super(label,wem);
		this.setBackground(Color.RED);
	}
	public String getActionCommand() {
		System.exit(0);
		return "";
	}
}
