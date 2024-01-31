package clipboard.services;

import java.awt.Button;

import AWT.WindowEventMenu;

public class ButtonI extends Button {
	
	public WindowEventMenu wem;
	
	public ButtonI(String label, WindowEventMenu wem) {
		super(label);
		this.wem = wem;
	}
}
