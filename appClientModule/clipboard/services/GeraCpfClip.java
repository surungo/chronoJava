package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class GeraCpfClip extends GeraCpfCnpj {

	public GeraCpfClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = this.cpf(false);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

}