package util;

import org.apache.commons.lang3.StringUtils;

import clipboard.services.UrlDecodeClip;
import clipboard.services.UrlEncodeClip;

public class MyStringUtils extends StringUtils{

	
	public static String removeEspacosDuplicados(String string) {
		String out = "";
		out = string;
		out = UrlEncodeClip.encode(out);
		out = out.replace("+", "><").replace("<>", "").replace("><", "+");
		out = UrlDecodeClip.decode(out);

		return out;
	}

	public static String removeSqlJavaString(String string) {
		String out = string;
		out = out.replaceAll( "\n"," ");
		out = out.replaceAll( "\r"," ");
		out = out.replaceAll( "\t"," ");
		out = removeEspacosDuplicados(out);
		out = out.replaceAll("\" [+]","");  
		out = out.replaceAll( "\" ","");
		out = out.replaceAll( "\"","");
		
		return out;
	}

	public static String cutLeft(String clip,int newSize) {
		int size = clip.length();
		if(size>newSize)
			clip=clip.substring(size-newSize,size);
		return clip;
	}
	public static String cutRight(String clip,int newSize) {
		int size = clip.length();
		if(size>newSize)
			clip=clip.substring(0,newSize);
		return clip;
	}
	
	public static String removeCharDuplicados(String input) {
		String output="";
		for (int i = 0; i < input.length(); i++) {
            
            if (!output.contains(String.valueOf(input.charAt(i)))) {
                output = output + input.charAt(i);
            }
            
        }
		return output;
	}
	
	public static String Lower(String string) {
		String out = "";
		out = string.toLowerCase();
		return out;
	}
	
	public static String Upper(String string) {
		String out = "";
		out = string.toUpperCase();
		return out;
	}
	
    public static int calcularDV(int pmatricula) {
    	String matricula = String.valueOf(pmatricula);
        int iDv = 0;
        int i = 0;

        while (i <= 6) {
            iDv += Character.getNumericValue(matricula.charAt(i));
            i += 2;
        }

        if (iDv > 9) {
            iDv %= 10;
        }

        i = 1;

        while (i <= 7) {
            int iAux = 2 * Character.getNumericValue(matricula.charAt(i));

            if (iAux > 9) {
                iDv += iAux / 10 + iAux % 10;
            } else {
                iDv += iAux;
            }

            i += 2;
        }

        if (iDv > 9) {
            iDv %= 10;
        }

        if (iDv > 0) {
            iDv = 10 - iDv;
        }

        return iDv;
    }

    public static String replaceSpecialToSpace(String clip) {
		return clip.replaceAll("[^A-Za-z0-9]"," ");
    }

	public static String removeEspeciais1(String str) {
		return  str.replaceAll("[^a-zA-Z0-9,=._-]", " ");
	}
}
