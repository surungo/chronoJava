package clipboard.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class RemoveDuplicadosChar extends ButtonI {

	public  RemoveDuplicadosChar(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip =  Clipboard.getClipboard();
		clip = MyStringUtils.removeCharDuplicados(clip);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
}
