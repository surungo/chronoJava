package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipLoadTxArea extends ButtonI {

	public ClipLoadTxArea(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = wem.getCommentTextArea();
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		return "";
	}

}
