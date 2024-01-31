package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class GeraCpfComPontClip extends GeraCpfCnpj  {

	public GeraCpfComPontClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = this.cpf(true);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}


}