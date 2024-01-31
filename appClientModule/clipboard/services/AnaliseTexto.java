package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

import java.util.HashMap;

public class AnaliseTexto extends ButtonI {

	public AnaliseTexto(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try {
			String clip = Clipboard.getClipboard();
			HashMap<String,Integer> lista = new HashMap();
			clip=MyStringUtils.replaceSpecialToSpace(clip);
			clip=MyStringUtils.removeEspacosDuplicados(clip);

			String[] splits = clip.split(" ");
			for (String palavra: splits){
				palavra=palavra.trim();
				if(!" ".equals(palavra)){
					int count = lista.containsKey(palavra)?lista.get(palavra)+1:1;
					lista.put(palavra,count);
				}
			}

			clip = String.valueOf(lista);
			clip = clip.replace("{","");
			clip = clip.replace("}","");
			clip = clip.replaceAll(",","\n");
			clip = clip.replaceAll("=",";");
			clip = clip.replaceAll(" ","");
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch(Exception e) {
			wem.setTextErro(e.getMessage());
		}
		return "";
	}
	
}

