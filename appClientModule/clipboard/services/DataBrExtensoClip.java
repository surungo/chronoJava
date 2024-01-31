package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.DateTimeUtils;

import java.util.Date;

public class DataBrExtensoClip extends ButtonI {

	public DataBrExtensoClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = DateTimeUtils.getDataPorExetenso(new Date());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}