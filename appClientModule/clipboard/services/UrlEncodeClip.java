package clipboard.services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class UrlEncodeClip extends ButtonI {

	public UrlEncodeClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try{
			String clip = encode(Clipboard.getClipboard());
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch (Exception e){
			wem.setTextErro(e.getMessage());
		}
		return "";
	}

	public static String encode(String string) {
		String out = "";
		try {
			out = URLEncoder.encode(string, StandardCharsets.UTF_8.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return out;
	}

}
