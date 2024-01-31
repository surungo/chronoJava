package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class DecToBin extends ButtonI {

	public DecToBin(String label, WindowEventMenu wem) {
		super(label, wem);
	}
	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		if(!Useful.testBin(clip)) {
			wem.setTextErro("Não é numero");
			return "";
		}
		int numero = Integer.parseInt(clip);
		clip = Integer.toBinaryString(numero);//Converter um valor int para binario e atribui o valor a um tipo string
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
	
	
}

