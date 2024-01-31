package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

import java.util.ArrayList;
import java.util.List;

public class TesteSwagger extends ButtonI {

	private String out = "";

	public TesteSwagger(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		
		// basic
		Runtime run = Runtime.getRuntime();
		String app = Useful.getAppBrowser();
		List<String> envs = Useful.getEnvs();
		
		List<String> endpoints = new ArrayList();
		endpoints.add("/swagger-ui.html");

		List<String> urls = new ArrayList();
		out=Useful.openUrls(envs, endpoints, clip, urls, app, run);
		Clipboard.setClipboard(out);
		wem.setTextClipboad(out);
		wem.addCommentTextArea(out);
		return out;
	}

}
