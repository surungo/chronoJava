package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipLoad1 extends ButtonI {

	public ClipLoad1(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = wem.getSave1TextArea();
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		return "";
	}

}
