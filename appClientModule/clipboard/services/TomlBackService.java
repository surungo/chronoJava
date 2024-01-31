package clipboard.services;

import java.awt.Button;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class TomlBackService extends Button {
	
	private WindowEventMenu wem;
	
	public TomlBackService(String label) {
		super(label);
	}
	
	public TomlBackService(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		String porta = wem.getTextPara();
		if("".equals(porta)) {
			wem.setTextPara("preencha porta aqui");
		} else {
			clip = "\r\n\r\n  [backends."+clip+"]\r\n"
				+ "      [backends."+clip+".LoadBalancer]\r\n"
				+ "      method = \"drr\"\r\n"
				+ "      [backends."+clip+".servers.poa01dck07]\r\n"
				+ "      url = \"http://poa01dck07.pucrs.br:"+porta+"\"\r\n"
				+ "      weight = 1\r\n"
				+ "      [backends."+clip+".servers.poa01dck08]\r\n"
				+ "      url = \"http://poa01dck08.pucrs.br:"+porta+"\"\r\n"
				+ "      weight = 1\r\n"
				+ "      [backends."+clip+".servers.poa01dck09]\r\n"
				+ "      url = \"http://poa01dck09.pucrs.br:"+porta+"\"\r\n"
				+ "      weight = 1\r\n";
		
			Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		}
	    return clip;
	}
	
	
}


