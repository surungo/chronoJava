package clipboard.services;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class UrlDecodeClip extends ButtonI {

	public UrlDecodeClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try{
			String clip = decode(Clipboard.getClipboard());
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch (Exception e){
			wem.setTextErro(e.getMessage());
		}
		return "";
	}

	public static String decode(String string) {
		String out = "";
		try {
			out = URLDecoder.decode(string, StandardCharsets.UTF_8.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return out;
	}

}
