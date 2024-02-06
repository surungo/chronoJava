package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

import java.awt.*;

public class CutLeftLines extends JButtonI {
	public CutLeftLines(String label, WindowEventMenu wem ) {
		super(label,wem);
		this.setBackground(Color.green);
		this.setToolTipText("Adicione novo size no campo Para");
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