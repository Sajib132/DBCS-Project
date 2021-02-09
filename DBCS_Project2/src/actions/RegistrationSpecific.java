package actions;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.UserInterface.TabNames;

public class RegistrationSpecific extends Application {
	/**********************************************************************************************

	Class Attributes
	
	**********************************************************************************************/
	
	//---------------------------------------------------------------------------------------------
	// These attributes enable us to hide the details of the tab height and the height and width of
	// of the window decorations for the code that implements this user interface
	private static int xOffset = 0;
	private static int yOffset = 0;

	private static Label theTitle;
	private static Button btnbrowse = new Button("Student Details");
	private static Button btnbrowse1 = new Button("DBCS Score");
	private static Button btnbrowse2 = new Button("Big Data Score");
	private static Button btnGeneratePDF = new Button("Big Data Percentage");
	private static Button btnGeneratePDF1 = new Button("DBCS Percentage");
	

	static java.sql.Statement stat;


	
	/**********
	 * This constructor establishes the base ListItem and then initializes the Life Cycle specific
	 * attributes for the application.
	 * 
	 * @param g		The Group link is used to establish the list of GUI elements for this tab
	 * @param x		The x offset for the GUI elements to fit into the decorative borders
	 * @param y		The y offset
	 * @param t		The enumeration that helps select the right strings for labels, etc.
	 */
	public RegistrationSpecific(Group studentMasterControls, int x, int y, TabNames studentMaster) {
		nextMethod(studentMasterControls, x, y, studentMaster);
	

	}
	
	
	public static void nextMethod(Group g, int x, int y, TabNames t) {
		xOffset = x;
		yOffset = y;
		theTitle = new Label("Student Feedback");
		setupLabelUI(theTitle, "Arial", 30, 100, Pos.BASELINE_LEFT, 100 + xOffset, 20 + yOffset);

		setupButtonUI(btnbrowse, "Arial", 20, 50, Pos.BASELINE_LEFT, 100 + xOffset, 100 +  yOffset);
		btnbrowse.setOnAction((event)->{
			
			generatePDF();			
		});
		
		setupButtonUI(btnbrowse1, "Arial", 20, 50, Pos.BASELINE_LEFT, 100 + xOffset, 180 +  yOffset);
		btnbrowse1.setOnAction((event)->{
			
			generatePDF3();			
		});
		setupButtonUI(btnbrowse2, "Arial", 20, 50, Pos.BASELINE_LEFT,100 + xOffset, 260 +  yOffset);
		btnbrowse2.setOnAction((event)->{
			
			generatePDF4();			
		});
		setupButtonUI(btnGeneratePDF, "Arial", 20, 50, Pos.BASELINE_LEFT, 100 + xOffset, 340 +  yOffset);
		btnGeneratePDF.setOnAction((event)->{
			
			generatePDF2();
			
				
		});
		setupButtonUI(btnGeneratePDF1, "Arial", 20, 50, Pos.BASELINE_LEFT, 100 + xOffset, 420 +  yOffset);
		btnGeneratePDF1.setOnAction((event)->{
			
			generatePDF1();
			
				
		});
		

		g.getChildren().addAll(theTitle,btnbrowse, btnbrowse1,btnbrowse2,btnGeneratePDF,btnGeneratePDF1);
	}
	
	
	public static void generatePDF() {
		
		Document studentReport = new Document();
		try {
			PdfWriter.getInstance(studentReport, new FileOutputStream(new File("StudentReportAll.pdf")));
		
		studentReport.open();

		
		PdfPTable table = new PdfPTable(4);
		table.addCell("Serial No"); table.addCell("Roll");table.addCell("Student Name");table.addCell("Father Name");
		 Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = null;
		
			// Setting-up the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs", "root","sajib1432");
		    	
			stat = con.createStatement(); 	// Creating statement 
		    
			String statment = "Select  * from student_master";
			ResultSet set = stat.executeQuery(statment);
			
			while(set.next()) {
				
				table.addCell(String.valueOf(set.getString(1)));
				table.addCell(String.valueOf(set.getString(2)));
				table.addCell(String.valueOf(set.getString(3)));
				table.addCell(String.valueOf(set.getString(4)));
//				

				
			}
			
			studentReport.add(table);
			studentReport.close();
			Desktop.getDesktop().open(new File("StudentReportAll.pdf"));
	}
	catch(DocumentException | IOException | SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	
	
	
	
	
	
	
	
	

	public static void generatePDF2() {
				
		Document studentReport = new Document();
		try {
			PdfWriter.getInstance(studentReport, new FileOutputStream(new File("StudentReportAll.pdf")));
		
		studentReport.open();
		
		PdfPTable table = new PdfPTable(2);
		table.addCell("Roll No"); table.addCell("Percentage (%)");
		 Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = null;
		
			// Setting-up the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs", "root","sajib1432");
		    	
			stat = con.createStatement(); 	// Creating statement 
		    
			String statment = "select v.roll,  round(((sh+enb)/23.28)*100,2) from (select roll, ((w1sh1*0.119) + (w1sh2*0.119) + "
					+ "(w2sh1*0.119) +(w2sh2*0.119)+(w3sh*0.119)+ (w4sh*0.119) +(w5sh1*0.119)+(w5sh2*0.119)+"
					+ "(w6sh1*0.119)+(w6sh2*0.119)) as SH,((w1enb*0.015) + (w2enb*0.015) + (w3enb*0.015) +"
					+ "(w4enb*0.015)+(w5enb*0.015)+ (w6enb*0.015)) as ENB from bd) v;";
			ResultSet set = stat.executeQuery(statment);
			
			while(set.next()) {
				
				table.addCell(String.valueOf(set.getString(1)));
				table.addCell(String.valueOf(set.getString(2)));



				
			}
			
			studentReport.add(table);
			studentReport.close();
			Desktop.getDesktop().open(new File("StudentReportAll.pdf"));
	}
	catch(DocumentException | IOException | SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	public static void generatePDF1() {
		
		Document studentReport = new Document();
		try {
			PdfWriter.getInstance(studentReport, new FileOutputStream(new File("StudentReportDBCS.pdf")));
		
		studentReport.open();
		
		PdfPTable table = new PdfPTable(2);
		table.addCell("Roll No"); table.addCell("Percentage (%)");
		 Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = null;
		
			// Setting-up the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs", "root","sajib1432");
		    	
			stat = con.createStatement(); 	// Creating statement 
		    
			String statment = "select v.roll,  round(((sh+enb)/21.67)*100,2) from (select roll, ((w1sh1*0.125) + (w1sh2*0.125) + " + 
					"(w2sh1*0.125) +(w2sh2*0.125)+(w3sh*0.125)+ (w4sh1*0.125)+(w4sh2*0.125) +(w5sh1*0.125)+(w5sh2*0.125)+" + 
					"(w6sh1*0.125)+(w6sh2*0.125)) as SH,((w1enb*0.0111) + (w2enb*0.0111) + (w3enb*0.0111) +" + 
					"(w4enb*0.0111)+(w5enb*0.0111)+ (w6enb*0.0111)) as ENB from dbcss) v;";
			ResultSet set = stat.executeQuery(statment);
			
			while(set.next()) {
				
				table.addCell(String.valueOf(set.getString(1)));
				table.addCell(String.valueOf(set.getString(2)));
			
			}
			
			studentReport.add(table);
			studentReport.close();
			Desktop.getDesktop().open(new File("StudentReportDBCS.pdf"));
	}
	catch(DocumentException | IOException | SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
public static void generatePDF3() {
		
		Document studentReport = new Document();
		try {
			PdfWriter.getInstance(studentReport, new FileOutputStream(new File("StudentReportAll.pdf")));
		
		studentReport.open();
//		PdfPTable table = new PdfPTable(5);
//		table.addCell("Serial No"); table.addCell("Roll No"); table.addCell("Name");
//		 table.addCell("Father Name"); table.addCell("R");
		
		PdfPTable table = new PdfPTable(19);
		table.addCell("Roll No"); table.addCell("W1SH1");table.addCell("W1SH2");
		table.addCell("W2SH1");table.addCell("W2SH2");table.addCell("W3SH");
		table.addCell("W4SH1");table.addCell("W4SH2");table.addCell("W5SH1");
		table.addCell("W5SH2");table.addCell("W6SH1");table.addCell("W6SH2");
		table.addCell("W1ENB");table.addCell("W2ENB");table.addCell("W3ENB");
		table.addCell("W4ENB");table.addCell("W5ENB");table.addCell("W6ENB");
		table.addCell("Total");
		 Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = null;
		
			// Setting-up the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs", "root","sajib1432");
		    	
			stat = con.createStatement(); 	// Creating statement 
		    
			String statment = "Select  * from dbcss";
			ResultSet set = stat.executeQuery(statment);
			
			while(set.next()) {
				
				table.addCell(String.valueOf(set.getString(1)));
				table.addCell(String.valueOf(set.getString(2)));
				table.addCell(String.valueOf(set.getString(3)));
				table.addCell(String.valueOf(set.getString(4)));
				table.addCell(String.valueOf(set.getString(5)));
				table.addCell(String.valueOf(set.getString(6)));
				table.addCell(String.valueOf(set.getString(7)));
				table.addCell(String.valueOf(set.getString(8)));
				table.addCell(String.valueOf(set.getString(9)));
				table.addCell(String.valueOf(set.getString(10)));
				table.addCell(String.valueOf(set.getString(11)));
				table.addCell(String.valueOf(set.getString(12)));
				table.addCell(String.valueOf(set.getString(13)));
				table.addCell(String.valueOf(set.getString(14)));
				table.addCell(String.valueOf(set.getString(15)));
				table.addCell(String.valueOf(set.getString(16)));
				table.addCell(String.valueOf(set.getString(17)));
				table.addCell(String.valueOf(set.getString(18)));
				table.addCell(String.valueOf(set.getString(19)));


				
			}
			
			studentReport.add(table);
			studentReport.close();
			Desktop.getDesktop().open(new File("StudentReportAll.pdf"));
	}
	catch(DocumentException | IOException | SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
public static void generatePDF4() {
	
	Document studentReport = new Document();
	try {
		PdfWriter.getInstance(studentReport, new FileOutputStream(new File("StudentReportAll.pdf")));
	
	studentReport.open();
	
	PdfPTable table = new PdfPTable(18);
	table.addCell("Roll No"); table.addCell("W1SH1");table.addCell("W1SH2");
	table.addCell("W2SH1");table.addCell("W2SH2");table.addCell("W3SH");
	table.addCell("W4SH");table.addCell("W5SH1");
	table.addCell("W5SH2");table.addCell("W6SH1");table.addCell("W6SH2");
	table.addCell("W1ENB");table.addCell("W2ENB");table.addCell("W3ENB");
	table.addCell("W4ENB");table.addCell("W5ENB");table.addCell("W6ENB");
	table.addCell("Total");
	 Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = null;
	
		// Setting-up the connection
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs", "root","sajib1432");
	    	
		stat = con.createStatement(); 	// Creating statement 
	    
		String statment = "Select  * from bd";
		ResultSet set = stat.executeQuery(statment);
		
		while(set.next()) {
			
			table.addCell(String.valueOf(set.getString(1)));
			table.addCell(String.valueOf(set.getString(2)));
			table.addCell(String.valueOf(set.getString(3)));
			table.addCell(String.valueOf(set.getString(4)));
			table.addCell(String.valueOf(set.getString(5)));
			table.addCell(String.valueOf(set.getString(6)));
			table.addCell(String.valueOf(set.getString(7)));
			table.addCell(String.valueOf(set.getString(8)));
			table.addCell(String.valueOf(set.getString(9)));
			table.addCell(String.valueOf(set.getString(10)));
			table.addCell(String.valueOf(set.getString(11)));
			table.addCell(String.valueOf(set.getString(12)));
			table.addCell(String.valueOf(set.getString(13)));
			table.addCell(String.valueOf(set.getString(14)));
			table.addCell(String.valueOf(set.getString(15)));
			table.addCell(String.valueOf(set.getString(16)));
			table.addCell(String.valueOf(set.getString(17)));
			table.addCell(String.valueOf(set.getString(18)));

			
		}
		
		studentReport.add(table);
		studentReport.close();
		Desktop.getDesktop().open(new File("StudentReportAll.pdf"));
}
catch(DocumentException | IOException | SQLException | ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**********
	 * Private local method to initialize the standard fields for a JavaFX Label object
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font face for the label's text
	 * @param f		The font size for the label's text
	 * @param w		The minimum width for the Label
	 * @param p		The alignment for the text within the specified width
	 * @param x		The x-axis location for the Label
	 * @param y		The y-axis location for the Label
	 */
	private static void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}
	/**********
	 * Private local method to initialize the standard fields for a text field
	 * 
	 * @param t		The TextField object to be initialized
	 * @param ff	The font face for the label's text
	 * @param f		The font size for the label's text
	 * @param w		The minimum width for the TextField
	 * @param p		The alignment for the text within the specified width
	 * @param x		The x-axis location for the TextField
	 * @param y		The y-axis location for the TextField
	 */
	private static void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
	}

	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button to be initialized
	 * @param ff	The font face for the label's text
	 * @param f		The font size for the label's text
	 * @param w		The minimum width for the TextArea
	 * @param p		The alignment for the text within the specified width
	 * @param x		The x-axis location for the TextField
	 * @param y		The y-axis location for the TextField
	 */
	private static void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
				
	}

	
	
	
}
