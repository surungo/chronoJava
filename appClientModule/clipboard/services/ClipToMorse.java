package clipboard.services;

import java.util.ArrayList;
import java.util.HashMap;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class ClipToMorse extends ButtonI {

	public ClipToMorse(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public HashMap<String, int[]> morseMap = new HashMap<>();
	public String pass = "";
	
	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		pass = "";
		init();

		// Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		ArrayList<MorseLetter> out = new ArrayList<MorseLetter>();
		out = this.encodeMorse(clip);

		out.forEach((ml) -> {
			if (ml.valor != null) {
				for (int i : ml.valor) {
					pass+=i;
				}

			}
			pass+=" ";
		});
		clip=pass;
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

	public String toLetter(int number) {
		HashMap<Integer, String[]> letterMap = new HashMap<>();
		letterMap.put(0, null);
		letterMap.put(1, null);
		String[] letters = letterMap.get(number);
		int size = letters.length;

		return "x";
	}

	public ArrayList<MorseLetter> encodeMorse(String frase) {
		ArrayList<MorseLetter> out = new ArrayList<MorseLetter>();
		String[] inArr = frase.split("");
		for (String string : inArr) {
			out.add(encodeMorseChar(string));
		}
		return out;
	}

	public MorseLetter encodeMorseChar(String in) {
		MorseLetter ml = new MorseLetter();
		ml.chave = in;
		ml.valor = morseMap.get(in.toUpperCase());

		return ml;
	}

	public void init() {

		morseMap.put("A", new int[] { 0, 1 });
		morseMap.put("B", new int[] { 1, 0, 0, 0 });
		morseMap.put("C", new int[] { 1, 0, 1, 0 });
		morseMap.put("D", new int[] { 1, 0, 0 });
		morseMap.put("E", new int[] { 0 });
		morseMap.put("F", new int[] { 0, 0, 1, 0 });
		morseMap.put("G", new int[] { 1, 1, 0 });
		morseMap.put("H", new int[] { 0, 0, 0, 0 });
		morseMap.put("I", new int[] { 0, 0 });
		morseMap.put("J", new int[] { 0, 1, 1, 1 });
		morseMap.put("K", new int[] { 1, 0, 1 });
		morseMap.put("L", new int[] { 0, 1, 0, 0 });
		morseMap.put("M", new int[] { 1, 1 });
		morseMap.put("N", new int[] { 1, 0 });
		morseMap.put("O", new int[] { 1, 1, 1 });
		morseMap.put("P", new int[] { 0, 1, 1, 0 });
		morseMap.put("Q", new int[] { 1, 1, 0, 1 });
		morseMap.put("R", new int[] { 0, 1, 0 });
		morseMap.put("S", new int[] { 0, 0, 0 });
		morseMap.put("T", new int[] { 1 });
		morseMap.put("U", new int[] { 0, 0, 1 });
		morseMap.put("V", new int[] { 0, 0, 0, 1 });
		morseMap.put("W", new int[] { 0, 1, 1 });
		morseMap.put("X", new int[] { 1, 0, 0, 1 });
		morseMap.put("Y", new int[] { 1, 0, 1, 1 });
		morseMap.put("Z", new int[] { 1, 1, 0, 0 });
		morseMap.put("0", new int[] { 1, 1, 1, 1, 1 });
		morseMap.put("1", new int[] { 0, 1, 1, 1, 1 });
		morseMap.put("2", new int[] { 0, 0, 1, 1, 1 });
		morseMap.put("3", new int[] { 0, 0, 0, 1, 1 });
		morseMap.put("4", new int[] { 0, 0, 0, 0, 1 });
		morseMap.put("5", new int[] { 0, 0, 0, 0, 0 });
		morseMap.put("6", new int[] { 1, 0, 0, 0, 0 });
		morseMap.put("7", new int[] { 1, 1, 0, 0, 0 });
		morseMap.put("8", new int[] { 1, 1, 1, 0, 0 });
		morseMap.put("9", new int[] { 1, 1, 1, 1, 0 });
		morseMap.put(".", new int[] { 0, 1, 0, 1, 0, 1 });
		morseMap.put(",", new int[] { 1, 1, 0, 0, 1, 1 });
		morseMap.put("?", new int[] { 0, 0, 1, 1, 0, 0 });
		morseMap.put("'", new int[] { 0, 1, 1, 1, 1, 0 });
		morseMap.put("!", new int[] { 1, 0, 1, 0, 1, 1 });
		morseMap.put("/", new int[] { 1, 0, 0, 1, 0 });
		morseMap.put("(", new int[] { 1, 0, 1, 1, 0 });
		morseMap.put(")", new int[] { 1, 0, 1, 1, 0, 1 });
		morseMap.put("&", new int[] { 0, 1, 0, 0, 0 });
		morseMap.put(":", new int[] { 1, 1, 1, 0, 0, 0 });
		morseMap.put(";", new int[] { 1, 0, 1, 0, 1, 0 });
		morseMap.put("=", new int[] { 1, 0, 0, 0, 1 });
		morseMap.put("+", new int[] { 0, 1, 0, 1, 0 });
		morseMap.put("-", new int[] { 1, 0, 0, 0, 0, 1 });
		morseMap.put("_", new int[] { 0, 0, 1, 1, 0, 1 });
		morseMap.put("\"", new int[] { 0, 1, 0, 0, 1, 0 });
		morseMap.put("$", new int[] { 0, 0, 0, 1, 0, 0, 1 });
		morseMap.put("@", new int[] { 0, 1, 1, 0, 1, 0 });
		morseMap.put("�", new int[] { 0, 0, 1, 0, 1 });
		morseMap.put("�", new int[] { 1, 1, 0, 0, 0 });
	}

	class MorseLetter {
		public String chave;
		public int[] valor;
	}
}
