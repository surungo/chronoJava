package clipboard.services;

import AWT.WindowEventMenu;
import clipboard.Clipboard;
import util.MyStringUtils;

public class FormatterSqlQueryClip extends ButtonI {

	public FormatterSqlQueryClip(String label, WindowEventMenu wem) {
		super(label, wem);
	}

	
	public String getActionCommand() {
		String clip = Clipboard.getClipboard();
		clip=MyStringUtils.removeEspacosDuplicados(clip);
		clip=MyStringUtils.removeSqlJavaString(clip);
		
		String unformattedSQL = clip;
		clip=formatSql(unformattedSQL);
		Clipboard.setClipboard(clip);
		wem.setTextClipboad(clip);
		wem.addCommentTextArea(clip);
		return "";
	}
	
	public static String formatSql(String sql) {
		String tab="\t";
		String rn="\r\n";
		sql = sql.replaceAll("\r",rn); // ajuste break lines
		sql = sql.replaceAll("\r\n\n",rn); // ajuste break lines
		sql = sql.replaceAll("\r\r\n",rn); // ajuste break lines
		sql = sql.replaceAll(rn," "); // replace break lines to spaces
		sql = sql.replaceAll("\t"," "); // replace tabs to spaces
		
		// remove multiples spaces e especiais
		sql=UrlEncodeClip.encode(sql);
		
		String sb1 = UrlEncodeClip.encode("[");
		String sb2 = UrlEncodeClip.encode("]");
		sql = sql.replaceAll(sb1,"").replaceAll(sb2,"");
		
		sql = sql.replaceAll("[+]","><");
		sql = sql.replaceAll("<>","");
		sql = sql.replaceAll("><","+");
		sql = " "+sql+" ";
		sql=UrlDecodeClip.decode(sql);
		
		//
		sql = sql.replaceAll("(?i) select ",rn+"select"+rn+tab);
		sql = sql.replaceAll("(?i) insert ",rn+"insert"+rn+tab);
		sql = sql.replaceAll("(?i) update ",rn+"update"+rn+tab);
		sql = sql.replaceAll("(?i) set ",rn+"set"+rn+tab);
		sql = sql.replaceAll("(?i) delete  from",rn+"delete from"+rn+tab);
		sql = sql.replaceAll("(?i) delete ",rn+"delete"+rn+tab);
		sql = sql.replaceAll("(?i) from ",rn+"from"+rn+tab);
		sql = sql.replaceAll("(?i) full outer join ",rn+"full outer join"+rn+tab);
		sql = sql.replaceAll("(?i) left outer join ",rn+"left outer join"+rn+tab);
		sql = sql.replaceAll("(?i) right outer join ",rn+"right outer join"+rn+tab);
		sql = sql.replaceAll("(?i) left join ",rn+"left join"+rn+tab);
		sql = sql.replaceAll("(?i) right join ",rn+"right join"+rn+tab);
		sql = sql.replaceAll("(?i) inner join ",rn+"inner join"+rn+tab);
		sql = sql.replaceAll("(?i) cross join ",rn+"cross join"+rn+tab);
		sql = sql.replaceAll("(?i) join ",rn+"join"+rn+tab);
		sql = sql.replaceAll("(?i) on ",rn+"on"+rn+tab);
		sql = sql.replaceAll("(?i) where ",rn+"where"+rn+tab);
		sql = sql.replaceAll("(?i) group by ",rn+"group by"+rn+tab);
		sql = sql.replaceAll("(?i) order by ",rn+"order by"+rn+tab);
		sql = sql.replaceAll("(?i) having ",rn+"having"+rn+tab);
		sql = sql.replaceAll(", ",","+rn+tab);
		sql = sql.replaceAll(" and ",rn+"and"+rn+tab);
		sql = sql.replaceAll(" or ",rn+"or"+rn+tab);
		sql = sql.replaceAll(" begin ",rn+"begin"+rn+tab);
		sql = sql.replaceAll(" end; ",rn+"end;"+rn+tab);
		
		//sql = sql.replaceAll(rn+rn,rn); // remove duplicates break lines
		
		
		sql = sql.replaceAll(tab,"  "); // change tabs to two spaces
		return sql;
	}
}
