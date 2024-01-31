package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipLoad2 extends ButtonI {

	public ClipLoad2(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = wem.getSave2TextArea();
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		return "";
	}

}
