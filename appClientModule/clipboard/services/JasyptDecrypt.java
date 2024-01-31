package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import org.jasypt.encryption.StringEncryptor;
import util.JasyptConfiguration;

public class JasyptDecrypt extends ButtonI {

	public JasyptDecrypt(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try {
			JasyptConfiguration jasyptConfiguration = new JasyptConfiguration();
			String password = wem.getTextDe();
			if ("".equals(password)) {
				wem.addCommentTextArea("password fill in De textfield, default 'senha'");
				password = "senha";
			}
			StringEncryptor encryptor = jasyptConfiguration.getEncryptor(password);
			String clip = Clipboard.getClipboard();
			clip = encryptor.decrypt(clip);
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch (Exception e){
			wem.setTextErro(e.getMessage());
		}
		return "";
	}
}
