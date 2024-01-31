package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class GeraCnpjClip extends GeraCpfCnpj {

	public GeraCnpjClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = this.cnpj(false);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
	
}