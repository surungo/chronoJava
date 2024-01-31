package clipboard.services;

import java.awt.Button;
import java.util.regex.Pattern;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class JavaQueryToSqlQuery extends ButtonI {

	public JavaQueryToSqlQuery(String label, WindowEventMenu wem) {
		super(label,wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		// basic
		clip = clip.replaceAll(Pattern.quote("\" +"), "");
		clip = clip.replaceAll(Pattern.quote("\"+"), "");
		clip = clip.replaceAll(Pattern.quote("\""), "");
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
}
