package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class SpringYmlToBasicBase64Clip extends ButtonI {

	public SpringYmlToBasicBase64Clip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		wem.setTextErro("");
		String clip = Clipboard.getClipboard();
		try {
			wem.addCommentTextArea(clip);
			clip = convert(clip);
			wem.addCommentTextArea(clip);
		}catch (Exception e){
			wem.setTextErro(e.getMessage());
		}
		Clipboard.setClipboard("Authorization");
		Clipboard.setClipboard("Basic "+clip);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);

		return "";
	}

	private String convert(String clip) {
		/*
		  name: usuario 
		  password: d950ef3d5a3a3d6b11046b8ee324c921 
		  Basic
		  dXN1YXJpbzpkOTUwZWYzZDVhM2EzZDZiMTEwNDZiOGVlMzI0YzkyMQ==
		 */
		String strName = "name: ";
		int strNameSize = strName.length();
		int strNamePos = clip.indexOf(strName) + strNameSize;
		String name = clip.substring(strNamePos);
		name = name.substring(0, name.indexOf("\n"));

		String strPassword = "password: ";
		int strPasswordSize = strPassword.length();
		int strPasswordPos = clip.indexOf(strPassword) + strPasswordSize;
		String password = clip.substring(strPasswordPos);
		try {
			password = password.substring(0, password.indexOf("\n"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		clip = name.trim() + ":" + password.trim();
		return Base64EncodeClip.encode(clip);
	}
}
