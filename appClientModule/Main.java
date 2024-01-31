import AWT.WindowEventMenu;

public class Main {

	public static void main(String[] args) {
		System.out.print("start");
		WindowEventMenu.centreWindow(new WindowEventMenu());
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/*
	 * (non-Java-doc)
	 *
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}