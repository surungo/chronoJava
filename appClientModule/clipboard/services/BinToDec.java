package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class BinToDec extends ButtonI {

	public BinToDec(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		wem.setTextErro("");
		String clip = Clipboard.getClipboard();
		if(!testBin(clip)) {
			wem.setTextErro("Não é binario");
			return "";
		}
		if(clip.length()>31) {
			wem.setTextErro("Binario não pode ser maior que 31 chars");
			return "";
		}
		try {
		// basic
			int numero = Integer.parseInt(clip, 2);//Nome da variavel e tipo, 2 = binary. Converte o binario para int
			clip=numero+"";
		}catch (Exception e) {
			wem.setTextErro(e.getMessage());
		}
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
	
	public boolean testBin(String in) {
		return  in.replace("0", "").replace("1", "").length()==0;
	}
}

