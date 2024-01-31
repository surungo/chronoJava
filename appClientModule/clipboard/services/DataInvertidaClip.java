package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.DateTimeUtils;

import java.util.Date;

public class DataInvertidaClip extends ButtonI {

	public DataInvertidaClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = DateTimeUtils.dateyyyyMMddhhmmssS(new Date());
		clip = Long.toString( 99999999999999999L  - Long.parseLong(clip)  );
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}