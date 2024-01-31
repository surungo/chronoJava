package clipboard.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class Md5 extends ButtonI {

	public Md5(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = md5(Clipboard.getClipboard());
		int size = clip.length();
		clip= MyStringUtils.cutRight(clip, wem.getIntPara());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

	public String md5(String string) {
		String out = "";
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(string.getBytes(), 0, string.length());
			out = new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return out;
		}
		return out;
	}
}
