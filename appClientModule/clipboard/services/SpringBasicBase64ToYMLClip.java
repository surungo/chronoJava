package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class SpringBasicBase64ToYMLClip extends ButtonI {

	public SpringBasicBase64ToYMLClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try {
			wem.setTextErro("");
			String clip = Clipboard.getClipboard();
			clip=Base64DecodeClip.decode(clip);
			String[] clips = clip.split(":");

			String user = clips[0];
			String password = clips[1];
			clip = "  security:" +
					"\n    user:" + user +
					"\n    password:" + password;
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch (Exception e){
			wem.setTextErro(e.getMessage());
		}


		return "";
	}
}
