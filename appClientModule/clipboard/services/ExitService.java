package clipboard.services;

import java.awt.Button;

public class ExitService  extends Button{

	public ExitService(String label) {
		super(label);
	}
	public String getActionCommand() {
		System.exit(0);
		return "";
	}
}
