package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class RemoveEspacosDuplicados extends ButtonI {

	public RemoveEspacosDuplicados(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = MyStringUtils.removeEspacosDuplicados(Clipboard.getClipboard());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

	

}
