package clipboard.services;

import java.awt.Button;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipSet extends ButtonI {
	
	public ClipSet(String label, WindowEventMenu wem ) {
		super(label,wem);
	}

	public String getActionCommand() {
		String clip = wem.getTextDe();
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
	
	
}

