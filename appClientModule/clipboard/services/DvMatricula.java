package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class DvMatricula extends ButtonI {

	public DvMatricula(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try {
			int vmatricula = Integer.parseInt( Clipboard.getClipboard());
			vmatricula  = MyStringUtils.calcularDV(vmatricula);
			String clip = String.valueOf(vmatricula); 
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch(Exception e) {
			wem.setTextErro(e.getMessage());
		}
		return "";
	}
	
}

