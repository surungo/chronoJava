package clipboard.services;

import java.awt.Button;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClearTextArea extends Button {
	
	private WindowEventMenu wem;
	
	public ClearTextArea(String label) {
		super(label);
	}
	
	public ClearTextArea(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		wem.setCommentTextArea("");
		return "";
	}
	
	
}

