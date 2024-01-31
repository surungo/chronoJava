package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.DateTimeUtils;

import java.util.Date;

public class DataConvert1Clip extends ButtonI {

	public DataConvert1Clip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		//de
		//2023-10-04 16:52:47.193
		//para
		//Oct 4, 2023 @ 09:04:05.912
		//MMMMM dd, yyyy @ HH:mm:ss.SSS

		String clip = Clipboard.getClipboard();
		Date data = DateTimeUtils.stringToDate(clip);
		clip = DateTimeUtils.dateToStringUS( data,DateTimeUtils.FORMAT_006);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}