package clipboard.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class BarraDupla extends ButtonI {

	public  BarraDupla(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip =  Clipboard.getClipboard();
		clip = clip.replaceAll(Pattern.quote("\\"), Matcher.quoteReplacement("\\\\"));
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}



}
