package clipboard.services;

import java.awt.Button;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class CutRight extends Button {
	
	private WindowEventMenu wem;
	
	public CutRight(String label) {
		super(label);
	}
	
	public CutRight(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		clip = MyStringUtils.cutRight(clip, wem.getIntPara());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return clip;
	}
}