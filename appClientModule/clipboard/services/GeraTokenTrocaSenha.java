package clipboard.services;

import java.util.Base64;
import java.util.Random;
import java.util.regex.Pattern;

import AWT.WindowEventMenu;
import clipboard.Clipboard;

public class GeraTokenTrocaSenha extends ButtonI{

	public GeraTokenTrocaSenha(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		String clip = geraTokenSms();
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
	
	public String geraTokenSms() {
		String token = "";
		Random r = new Random();
		//tamanho do bloco numerico base
		int casas = 10;
		//primeiro numero gerado, utilizar todos os bytes
		double inicio = Math.pow(10, casas);
		//ultimo numero gerado
		double fim = (Math.pow(10, casas + 1)) - 1;
		String aux = "";
		// aumento exponencial do tamanho do bloco base
		for (int i = 0; i <= Math.pow(casas, 2); i++) {
			//gera um objeto com numeros randomicos diferente a cada interacao
			r = new Random();
			// pega um numero com a quantidade de casas especificado, com numero de casa maiores o Base64 se repete
			aux = String.format("%.0f", r.doubles(inicio, fim).findFirst().getAsDouble());
			// faz Base64 e acumula no token
			token += Base64.getEncoder().encodeToString(Base64.getEncoder().encodeToString(aux.getBytes()).getBytes());
		}
		// remover caracter = que tem no Base64
		token = token.replaceAll("=", "");
		// remover caracteres duplicados
		token = removeDupsInPlace(token);
		// cria um novo objeto com numeros randomico
		r = new Random();
		// pega um numero de 0 a 3 para remover aleatoriamente os primeiros caracteres do token 
		int pos = r.nextInt(4);
		// remove ou não primeiros caracteres do token
		token = token.substring(pos);
		// busca uma sequencia valida, que contenha Numeros e letra maiuscular e minusculas
		// correndo o token caracter a caracter levando em considereção o tamanho passado
		token = getSequence(token,8);
		return token;
	}
	
	public static String removeDupsInPlace(String word) {
		final StringBuilder output = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			String character = word.substring(i, i + 1);
			if (output.indexOf(character) < 0)
				output.append(character);
		}
		return output.toString();
	}
	
	public static String getSequence(String word,int size) {
		final StringBuilder output = new StringBuilder();
		String expressions[][] = { { "Caracteres maiúsculos (A-Z)", "[A-Z]" },
				{ "Caracteres minúsculos (a-z)", "[a-z]" }, { "Dígitos básicos (0-9)", "[0-9]" } };
		int start = 0;
		int end = size;
		for (int i = 0; end < word.length(); i++) {
			start = start+i;
			end	  = end+i;
			String characters = word.substring(start, end);
			if (validToken(characters,size)) {
				return characters;
			}
		}
		return word.substring(start, end);
	}
	
	public static boolean validToken(String token,int minLength) {
		boolean retorno = true;
		// validar complexidade
		// deve passar em 3 dos 4 requisitos abaixo
		int passar = 3;
		String expressions[][] = { { "Caracteres maiúsculos (A-Z)", "[A-Z]" },
				{ "Caracteres minúsculos (a-z)", "[a-z]" }, { "Dígitos básicos (0-9)", "[0-9]" } };
		int pass = 0;

		for (String[] expression : expressions) {
			Pattern p = Pattern.compile(expression[1]);
			if (p.matcher(token).find()) {
				pass++;
			} else {
				return false;
			}
		}

		// validar tamanho
		if (token.length() < minLength) {
			retorno = false;
		}

		return retorno;
	}
	
}