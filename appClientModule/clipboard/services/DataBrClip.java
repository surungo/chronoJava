package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.DateTimeUtils;

import java.util.Date;

public class DataBrClip extends ButtonI {

	public DataBrClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = DateTimeUtils.toStringDefault(new Date());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}