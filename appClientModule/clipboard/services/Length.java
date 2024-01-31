package clipboard.services;

import java.awt.Button;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class Length extends Button {
	
	private WindowEventMenu wem;
	
	public Length(String label) {
		super(label);
	}
	
	public Length(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		clip=String.valueOf( clip.length() );
		//Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		wem.setTextPara(clip);
	    return clip;
	}
	
	
}

