package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

import java.awt.*;

public class CutLeft extends Button {

	private WindowEventMenu wem;

	public CutLeft(String label) {
		super(label);
	}

	public CutLeft(String label, WindowEventMenu wem ) {
		super(label);
		this.wem= wem;
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		clip = MyStringUtils.cutLeft(clip, wem.getIntPara());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return clip;
	}
}