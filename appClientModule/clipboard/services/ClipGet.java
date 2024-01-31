package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipGet extends ButtonI {

	public ClipGet(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		this.wem.setTextDe(clip);
		return "";
	}

}
