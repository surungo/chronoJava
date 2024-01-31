package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipSave2 extends ButtonI {

	public ClipSave2(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		this.wem.setSave2TextArea(clip);
		return "";
	}

}
