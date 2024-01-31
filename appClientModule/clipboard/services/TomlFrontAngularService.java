package clipboard.services;

import java.awt.Button;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class TomlFrontAngularService extends Button {
	
	private WindowEventMenu wem;
	
	public TomlFrontAngularService(String label) {
		super(label);
	}
	
	public TomlFrontAngularService(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		clip = "\r\n"
					+ "\r\n"
					+ "  [frontends."+clip+"]\r\n"
					+ "      backend = \""+clip+"\"\r\n"
					+ "      passHostHeader = true\r\n"
					+ "      [frontends."+clip+".routes.cluster]\r\n"
					+ "      rule = \"Host:webapp.pucrs.br; PathPrefixStrip: /"+clip+"\"\r\n"
					+ "      [frontends."+clip+".headers]\r\n"
					+ "      SSLRedirect = true\r\n"
					+ "      SSLForceHost = true\r\n"
					+ "";
		
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		
	    return clip;
	}
	
	
}


