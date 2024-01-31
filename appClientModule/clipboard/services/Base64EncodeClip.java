package clipboard.services;

import java.util.Base64;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class Base64EncodeClip extends ButtonI {

	public Base64EncodeClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = encode(Clipboard.getClipboard());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
	    return "";
	}
	
	public static String encode(String string) {
		String out = "";
		try {
			out = Base64.getEncoder().encodeToString(string.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return out;
	}
	
}

