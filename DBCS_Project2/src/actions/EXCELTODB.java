package actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
/*
 * Description: This specific class load the data from G:\\student.xls to database table 'dbcss';
 * @author: Sajib Biswas
 * @version: 1.1
 * Date:  16-11-2020
 */

 
 
public class EXCELTODB {
 
    
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs","root","sajib1432");
            con.setAutoCommit(false);
            PreparedStatement pstm = null ;
            FileInputStream input = new FileInputStream("G:\\student.xls");
            POIFSFileSystem fs = new POIFSFileSystem( input );
            @SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                int srno = (int) row.getCell(0).getNumericCellValue();
                int rollno = (int) row.getCell(1).getNumericCellValue();
                String name = row.getCell(2).getStringCellValue();
                String deptno = row.getCell(3).getStringCellValue();
              
                String sql = "INSERT INTO student_master VALUES('"+srno+"','"+rollno+"','"+name+"','"+deptno+"')";
                pstm = (PreparedStatement) con.prepareStatement(sql);
                pstm.execute();
                System.out.println("Import rows "+i);
            }
            con.commit();
            pstm.close();
            con.close();
            input.close();
            System.out.println("Success import excel to mysql table");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
 
    }
}