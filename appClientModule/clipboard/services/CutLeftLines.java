package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

import java.awt.*;

public class CutLeftLines extends Button {

	private WindowEventMenu wem;

	public CutLeftLines(String label) {
		super(label);
	}

	public CutLeftLines(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		String split[] = clip.split("\\n");
		clip="";
		for (String line : split) {
			clip += MyStringUtils.cutLeft(line, wem.getIntPara()) + "\n";
		}
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return clip;
	}
}