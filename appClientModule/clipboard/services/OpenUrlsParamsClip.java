package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class OpenUrlsParamsClip extends ButtonI {

	public OpenUrlsParamsClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		// basic
		OpenUrlsClip.openUrlsList(clip, true);
		return "";
	}

}
