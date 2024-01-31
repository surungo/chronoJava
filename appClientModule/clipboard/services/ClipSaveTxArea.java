package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipSaveTxArea extends ButtonI {

	public ClipSaveTxArea(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		this.wem.setCommentTextArea(clip);
		return "";
	}

}
