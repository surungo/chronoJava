package clipboard.services;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import org.apache.commons.text.RandomStringGenerator;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class GeraPasswordClip extends ButtonI {

	public GeraPasswordClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		wem.setTextPara(clip);
		clip = this.generateCommonTextPassword(clip.length());
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}

	public String generateRandomSpecialCharacters(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
		return pwdGenerator.generate(length);
	}

	public String generateRandomNumber(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('0', '9')
				.filteredBy(DIGITS).build();
		return pwdGenerator.generate(length);
	}

	public String generateRandomText(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('A', 'z')
				.filteredBy(LETTERS).build();
		return pwdGenerator.generate(length);
	}

	public String generateRandomTextLower(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z')
				.filteredBy(LETTERS).build();
		return pwdGenerator.generate(length);
	}

	public String generateRandomTextUpper(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('A', 'Z')
				.filteredBy(LETTERS).build();
		return pwdGenerator.generate(length);
	}

	public String generateCommonTextPassword(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(LETTERS, DIGITS).build();
		return generator.generate(length);
	}

}