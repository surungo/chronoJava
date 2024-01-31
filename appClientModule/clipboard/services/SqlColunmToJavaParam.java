package clipboard.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class SqlColunmToJavaParam extends ButtonI {
	
	
	public SqlColunmToJavaParam(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	public String getActionCommand() {
		wem.fdebug("Start SqlColunmToJavaParam");
		try {
			String clip = Clipboard.getClipboard();
			// basic
		
			wem.fdebug("split lines");
			String[] linhas = clip.split("\n");
			for (int i = 0; i < linhas.length; i++) {
				wem.fdebug("process lines");
				linhas[i]=process(linhas[i],true);
			}
			clip=String.join("\n", linhas);
			Clipboard.setClipboard(clip);
			wem.setTextClipboad(clip);
			wem.addCommentTextArea(clip);
		}catch(Exception e) {
			wem.addCommentTextArea(e.getMessage());
		}
		wem.fdebug("end SqlColunmToJavaParam");
		return "";
	}

	private String process(String clip,boolean column) {
		String out = "";
		
		try {
			clip=clip.replace(",", "").trim();
			Map<String, String> tipos = new HashMap<>();
			tipos.put("cd", "long");
			tipos.put("id", "long");
			tipos.put("nu", "long");
			tipos.put("nr", "long");
			tipos.put("tx", "String");
			tipos.put("st", "String");
			tipos.put("no", "String");
			tipos.put("nm", "String");
			tipos.put("ha", "String");
			tipos.put("dt", "Date");
			
			wem.fdebug("split .");
			String[] comOwnereTabela = clip.split("[.]");
			wem.fdebug("if size more one was table.colunm then take last element");
			if(comOwnereTabela.length>1) {
				clip=comOwnereTabela[comOwnereTabela.length-1];
			}
			
			wem.fdebug("split _");
			String[] strings = clip.split("_");
			wem.fdebug("change string list for list");
			List<String> stringList = new ArrayList<String>(Arrays.asList(strings));
			
			wem.fdebug("if size more two stringList.size()"+stringList.size()+" clip: "+ clip );
			if(stringList.size()<2) {
				if( clip.length() > 2) {
				
					wem.fdebug("one element ");	
					stringList=new ArrayList<>();
					stringList.add(clip.substring(0,2));
					stringList.add(clip.substring(2));
				}
			}else {
				int index=0;
				wem.fdebug("join partes");
				for (String str : stringList) {
					//str = StringUtils.capitalize(str.toLowerCase());
					str = capitalize(str.toLowerCase());
					if (index == 0)
						str = str.toLowerCase().trim();
					out+=str;
					index++;
				}
			}
			wem.fdebug("mout text out");
			out="private "+tipos.get(stringList.get(0).toLowerCase())+" "+out+";";
			if(column)out="@Column(name=\""+clip+"\")\n"+out;
		}catch(Exception e) {
			wem.addCommentTextArea(e.getMessage());
		}
		
		return out;
		
	}
	
	public static String capitalize(final String str) {
        final int strLen = str.length();
        if (strLen == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int[] newCodePoints = new int[strLen]; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
         }
        return new String(newCodePoints, 0, outOffset);
    }
}


/*
CORPORAT.SSOPER.NUOPERADOR
CORPORAT.SSOPER.NOOPERADOR
CORPORAT.SSOPER.TXSENHA
CORPORAT.SSOPER.DTEXPIRACAO
CORPORAT.SSOPER.STOPERADOR
CORPORAT.SSOPER.NRTENTATIVASENHA
CORPORAT.SSOPER.DTLIBBLOQUEIO
CORPORAT.SSOPER.CDCATEGORIA
CORPORAT.SSOPER.NUMATRICULA
CORPORAT.SSOPER.DTFIMACESSO
CORPORAT.SSOPER.TX_LOGIN_REDE
CORPORAT.SSOPER.DT_CADASTRO_ATUALIZADO
CORPORAT.SSOPER.HASH
 
  
  */
