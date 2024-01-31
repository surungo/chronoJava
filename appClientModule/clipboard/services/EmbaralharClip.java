package clipboard.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class EmbaralharClip extends ButtonI {

	public EmbaralharClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = shuffleString(Clipboard.getClipboard());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);

		return "";
	}

	public static String shuffleString(String s) {
		List<String> letters = new ArrayList<String>();
		String temp = "";

		for (int i = 0; i < s.length(); i++) {
			letters.add(String.valueOf(s.charAt(i)));
		}
		System.out.println("");

		Collections.shuffle(letters);

		for (int i = 0; i < s.length(); i++) {
			temp += letters.get(i);
		}
		return temp;
	}
}
