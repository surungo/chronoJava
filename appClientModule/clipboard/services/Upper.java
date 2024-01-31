package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class Upper extends ButtonI {

	public  Upper(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		try{
			String clip = Clipboard.getClipboard();
			wem.fdebug(clip);
			clip =  MyStringUtils.Upper(clip);
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch (Exception e){
			wem.setTextErro(e.getMessage());
		}
		return "";
	}



}
