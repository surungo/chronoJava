package clipboard.services;

import java.util.Base64;

import AWT.WindowEventMenu;

import clipboard.Clipboard;

public class Base64DecodeClip extends ButtonI {

	public Base64DecodeClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}
	
	public String getActionCommand() {
		String clip = decode(Clipboard.getClipboard());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
	    return "";
	}
	
	public static String decode(String string) {
		String out = "";
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(string);
			out = new String(decodedBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return out;
	}
	
}

