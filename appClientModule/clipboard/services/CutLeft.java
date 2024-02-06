package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

import java.awt.*;

public class CutLeft extends JButtonI {
	public CutLeft(String label, WindowEventMenu wem ) {
		super(label,wem);
		this.setBackground(Color.green);
		this.setToolTipText("Adicione novo size no campo Para");
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