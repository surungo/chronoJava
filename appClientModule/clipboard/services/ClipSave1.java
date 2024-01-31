package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipSave1 extends ButtonI {

	public ClipSave1(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		this.wem.setSave1TextArea(clip);
		return "";
	}

}
