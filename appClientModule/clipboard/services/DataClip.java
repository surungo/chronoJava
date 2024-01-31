package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.DateTimeUtils;

import java.util.Date;

public class DataClip extends ButtonI {

	public DataClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = DateTimeUtils.dateyyyyMMddhhmmss(new Date());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}