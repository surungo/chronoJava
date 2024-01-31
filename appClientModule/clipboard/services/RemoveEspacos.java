package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class RemoveEspacos extends ButtonI {

	public RemoveEspacos(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = RemoveEspacos(Clipboard.getClipboard());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

	public String RemoveEspacos(String string) {
		String out = "";
		out = string;
		out = UrlEncodeClip.encode(out);
		out = out.replace("+", "");
		out = UrlDecodeClip.decode(out);

		return out;
	}

}
