package clipboard.services;

import java.io.IOException;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class OpenUrlsClip extends ButtonI {

	public OpenUrlsClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		// basic
		openUrlsList(clip,false);
		return "";
	}

	public static void openUrlsList(String clip,boolean isFirstUrlBase) {
		Runtime run = Runtime.getRuntime();
		// String app = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		String app = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
		String split[] = clip.split("\\n");
		int count = 0;
		String urlBase="";
		for (String url : split) {
			if(count==0&&isFirstUrlBase) {
				urlBase=url;
				count++;
				continue;
			}
			int miliseconds = 500;
			Useful.sleep(miliseconds);
			try {
				run.exec(app + " " + urlBase+url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
	}

}
