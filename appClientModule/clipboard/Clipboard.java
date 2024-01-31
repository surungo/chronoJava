package clipboard;

import clipboard.services.Useful;

public class Clipboard {
	public static String controlC() {
		RobotKey.controlC();
		TextTransfer textTransfer = new TextTransfer();
		String clip = textTransfer.getClipboardContents();
		// display what is currently on the clipboard
		textTransfer.log("Clipboard contains:" + clip);
		return clip;
	}

	public static void setClipboard(String clipboard) {
		Useful.sleep(700);
		TextTransfer textTransfer = new TextTransfer();
		// change the contents and then re-display
		textTransfer.setClipboardContents(clipboard);
		textTransfer.log("setClipboard contains:" + clipboard);
		

	}

	public static String getClipboard() {
		TextTransfer textTransfer = new TextTransfer();
		String clipboard = textTransfer.getClipboardContents();
		textTransfer.log("getClipboard contains:" + clipboard);
		return clipboard;
	}

	public static void controlV() {
		RobotKey.controlV();
	}
}



