package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class GeraCnpjComPontClip extends GeraCpfCnpj {

	public GeraCnpjComPontClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = this.cnpj(true);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}