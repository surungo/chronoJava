package AWT;

// Using AWT containers and components
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
// Using AWT events classes and listener interfaces
import java.awt.event.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import clipboard.Clipboard;
import clipboard.services.*;
import util.DateTimeUtils;

// An AWT GUI program inherits the top-level container java.awt.Frame
public class WindowEventMenu extends Frame implements ActionListener, WindowListener {
	// This class acts as listener for ActionEvent and WindowEvent
	// A Java class can extend only one superclass, but it can implement multiple
	// interfaces.
	private static final String version = " - v0.1.3";
	private static final int SIZE_TEXT = 42;
	private static final int ROWS_TEXTAREA = 98;
	private static final int COL_TEXTAREA = 12;
	private static final int SAVES_ROWS_TEXTAREA = 48;
	private static final int SAVES_COL_TEXTAREA = 3;
	private static final int SIZE_HEIGHT = 640;
	private static final int SIZE_WIDTH = 800;
	
	private TextField tfClipboad; // Declare a TextField component
	private TextField tfDe;
	private TextField tfPara;
	private TextField tfErro;
	private Checkbox chboxDebug;
	
	private TextArea commentTextArea = new TextArea("",COL_TEXTAREA,ROWS_TEXTAREA);
	private TextArea save1TextArea = new TextArea("",SAVES_COL_TEXTAREA,SAVES_ROWS_TEXTAREA);
	private TextArea save2TextArea = new TextArea("",SAVES_COL_TEXTAREA,SAVES_ROWS_TEXTAREA);
	private Map<String, Button> btns = null;
	private Map<String, JButton> jbtns = null;
	private int count = 0; // Counter's value

	// Constructor to setup the GUI components and event handlers
	public WindowEventMenu() {

		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout

		JPanel panel01 = new JPanel();

		panel01.add(new Label("Clipboard")); // "super" Frame adds an anonymous Label

		tfClipboad = new TextField(Clipboard.getClipboard(), SIZE_TEXT); // Construct the TextField
		// Clipboad.setEditable(false); // read-only
		panel01.add(tfClipboad); // "super" Frame adds TextField
		add(panel01);

		JPanel panel02 = new JPanel();
		panel02.add(new Label("De"));
		tfDe = new TextField("", SIZE_TEXT);
		panel02.add(tfDe);
		add(panel02);

		JPanel panel03 = new JPanel();
		panel03.add(new Label("Para"));
		tfPara = new TextField("", SIZE_TEXT);
		panel03.add(tfPara);
		add(panel03);
		
		JPanel panel04 = new JPanel();
		panel04.add(new Label("Erro"));
		tfErro = new TextField("", SIZE_TEXT);
		panel04.add(tfErro);
		add(panel04);
		
		JPanel panel05 = new JPanel();
		panel05.add(new Label("SAVES"));
		panel05.add(save1TextArea);
		panel05.add(save2TextArea);
		add(panel05);

		JPanel panel06 = new JPanel();
		panel06.add(new Label("TXAREA"));
		panel06.add(commentTextArea);
		add(panel06);



		btns = new HashMap<>();
		jbtns = new HashMap<>();

		addButton(new ExitService("Exit",this));
		addButton(new ClearTextArea("ClearTxArea",this));
		addButton(new ClipSaveTxArea("SaveTxArea",this));
		addButton(new ClipLoadTxArea("LoadTxArea",this));
		addButton(new ClipGet("SaveDe",this));
		addButton(new ClipSet("LoadDe",this));
		
		addButton(new ClipSave1("ClipSave1",this));
		addButton(new ClipLoad1("ClipLoad1",this));
		addButton(new ClipSave2("ClipSave2",this));
		addButton(new ClipLoad2("ClipLoad2",this));		
		
		addButton(new JavaQueryToSqlQuery("JavaQueryToSqlQuery",this));
		addButton(new SqlColunmToJavaParam("SqlColunmToJavaParam",this));
		addButton(new FormatterSqlQueryClip("FormatterSqlQueryClip",this));

		addButton(new OpenActuator("OpenActuator",this));
		addButton(new OpenSwagger("OpenSwagger",this));
		addButton(new OpenSwaggerActuator("OpenSwaggerActuator",this));
		addButton(new OpenUrlsClip("OpenUrlsClip",this));
		addButton(new OpenUrlsParamsClip("OpenUrlsParamsClip",this));		
		addButton(new ClipToMorse("ClipToMorse",this));
		addButton(new BinToDec("BinToDec",this));
		addButton(new DecToBin("DecToBin",this));
		addButton(new Md5("Md5",this));
		addButton(new UrlEncodeClip("UrlEncodeClip",this));
		addButton(new UrlDecodeClip("UrlDecodeClip",this));
		
		addButton(new RemoveEspacosDuplicados("RemoveEspacosDuplicados",this));
		addButton(new RemoveEspacos("RemoveEspacos",this));
		addButton(new CutRight("CutRight",this));
		addButton(new CutLeft("CutLeft",this));
		addButton(new CutRightLines("CutRightLines",this));
		addButton(new CutLeftLines("CutLeftLines",this));
		//addButton(new AnaliseTexto("AnaliseTexto",this));
		//addButton(new AnaliseJson("AnaliseJson",this));

		addButton(new DvMatricula("DvMatricula",this));
		addButton(new Length("Length",this));
		addButton(new Lower("Lower",this));
		addButton(new Upper("Upper",this));
		addButton(new RemoveDuplicadosChar("RemoverCharRepetidos",this));
		addButton(new GeraCpfClip("GeraCpfClip",this));
		addButton(new GeraCpfComPontClip("GeraCpfComPontClip",this));
		addButton(new GeraCnpjClip("GeraCnpjClip",this));
		addButton(new GeraCnpjComPontClip("GeraCnpjComPontClip",this));
		addButton(new GeraPasswordClip("GeraPasswordClip",this));
		addButton(new EmbaralharClip("EmbaralharClip",this));

		addButton(new Base64EncodeClip("Base64EncodeClip",this));
		addButton(new Base64DecodeClip("Base64DecodeClip",this));
		addButton(new JasyptEncrypt("JasyptEncrypt",this));
		addButton(new JasyptDecrypt("JasyptDecrypt",this));

		addButton(new SpringYmlToBasicBase64Clip("SpringYmlToBasicBase64Clip",this));
		addButton(new SpringBasicBase64ToYMLClip("SpringBasicBase64ToYMLClip",this));


		addButton(new TomlBackService("TomlBack",this));
		addButton(new TomlFrontAngularService("TomlFrontAngular",this));
		addButton(new BarraDupla("BarraDupla",this));
		addButton(new GeraTokenTrocaSenha("GeraTokenTrocaSenha",this));
		addButton(new DataClip("Data",this));
		addButton(new DataInvertidaClip("DataInvertida",this));
		addButton(new DataBrClip("DataBrClip",this));
		addButton(new DataBrExtensoClip("DataBrExtensoClip",this));
		addButton(new DateLogsClip("DateLogsClip",this));
		addButton(new DataConvert1Clip("DateLogsConvertElk",this));




		chboxDebug=new Checkbox("Debug", false);
		add(chboxDebug);
		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.

		setTitle("Clipboard Tools"+version); // "super" Frame sets title
		setSize(SIZE_WIDTH, SIZE_HEIGHT); // "super" Frame sets initial size
		setVisible(true); // "super" Frame shows
	}
	private void addButton(JButton bt) {
		jbtns.put(bt.getLabel(), bt);
		add(bt);
	}
	private void addButton(Button bt) {
		btns.put(bt.getLabel(), bt);
		add(bt);
//		bt.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            System.out.println("getTextFieldContent()");
//
//	        }
//	    });
	}

//   // The entry main() method
//   public static void main(String[] args) {
//      new WindowEventMenu();  // Let the construct do the job //
//   }

	/* ActionEvent handler */
	@Override
	public void actionPerformed(ActionEvent evt) {
		// ((Button) evt.getSource()).getActionCommand();
	}

	/* WindowEvent handlers */
	// Called back upon clicking close-window button
	@Override
	public void windowClosing(WindowEvent evt) {
		System.exit(0); // Terminate the program
	}

	// Not Used, BUT need to provide an empty body to compile.
	@Override
	public void windowOpened(WindowEvent evt) {
	}

	@Override
	public void windowClosed(WindowEvent evt) {
	}

	// For Debugging
	@Override
	public void windowIconified(WindowEvent evt) {
		System.out.println("Window Iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent evt) {
		System.out.println("Window Deiconified");
	}

	@Override
	public void windowActivated(WindowEvent evt) {
		System.out.println("Window Activated");
	}

	@Override
	public void windowDeactivated(WindowEvent evt) {
		System.out.println("Window Deactivated");
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	public String getTextClipboad() {
		return this.tfClipboad.getText();
	}
	
	public void setTextClipboad(String text) {
		this.tfClipboad.setText(text);
	}
	
	public String getTextPara() {
		return this.tfPara.getText();
	}
	
	public void setTextPara(String text) {
		this.tfPara.setText(text);
	}
	public int getIntPara() {
		String tamanho = getTextPara();
		if("".equals(tamanho) || !Useful.testBin(tamanho)) {
			setTextErro("Para não é numero");
		} else {
			return Integer.parseInt(tamanho);
		}
		return -1;
	}
	
	public String getTextDe() {
		return this.tfDe.getText();
	}

	public void setTextDe(String text) {
		this.tfDe.setText(text);
	}

	public String getTextErro() {
		return this.tfErro.getText();
	}

	public void setTextErro(String text) {
		this.tfErro.setText(text);
	}

	public String getSave1TextArea() {
		return this.save1TextArea.getText();
	}

	public void setSave1TextArea(String text) {
		this.save1TextArea.setText(text);
	}
	public String getSave2TextArea() {
		return this.save2TextArea.getText();
	}

	public void setSave2TextArea(String text) {
		this.save2TextArea.setText(text);
	}
	
	public String getCommentTextArea() {
		return this.commentTextArea.getText();
	}

	public void setCommentTextArea(String text) {
		this.commentTextArea.setText(text);
	}
	
	public void addCommentTextArea(String text) {
		this.commentTextArea.setText(text+"\n"+this.commentTextArea.getText());
	}

	public void fdebug(String string) {
		String msg = DateTimeUtils.dateLogs(new Date())+" DEBUG: "+string;
		if(chboxDebug.getState()) {
			this.addCommentTextArea(msg);
		}
		System.out.println(msg);
	}
}