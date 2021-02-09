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
 * Description: This specific class load the data from G:\\DBCS.xls to database table 'dbcss';
 * @author: Sajib Biswas
 * @version: 1.1
 * Date:  16-11-2020
 */
 
 
public class EXCELTOBD1 {
 
    
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcs","root","sajib1432");
            con.setAutoCommit(false);
            PreparedStatement pstm = null ;
            FileInputStream input = new FileInputStream("G:\\DBCS.xls");
            POIFSFileSystem fs = new POIFSFileSystem( input );
            @SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                
                int rollno = (int) row.getCell(0).getNumericCellValue();
                int s1 = (int) row.getCell(1).getNumericCellValue();
                int s2 = (int) row.getCell(2).getNumericCellValue();
                int s3 = (int) row.getCell(3).getNumericCellValue();
                int s4 = (int) row.getCell(4).getNumericCellValue();
                int s5 = (int) row.getCell(5).getNumericCellValue();
                int s6 = (int) row.getCell(6).getNumericCellValue();
                int s7 = (int) row.getCell(7).getNumericCellValue();
                int s8 = (int) row.getCell(8).getNumericCellValue();
                int s9 = (int) row.getCell(9).getNumericCellValue();
                int s10 = (int) row.getCell(10).getNumericCellValue();
                int s11 = (int) row.getCell(11).getNumericCellValue();
                int s12 = (int) row.getCell(12).getNumericCellValue();
                int s13 = (int) row.getCell(13).getNumericCellValue();
                int s14 = (int) row.getCell(14).getNumericCellValue();
                int s15 = (int) row.getCell(15).getNumericCellValue();
                int s16 = (int) row.getCell(16).getNumericCellValue();
                int s17 = (int) row.getCell(17).getNumericCellValue();
                int s18 = (int) row.getCell(18).getNumericCellValue();
                
              
                String sql = "INSERT INTO dbcss VALUES('"+rollno+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"'"
                		+ ",'"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"'"
                		+ ",'"+s12+"','"+s13+"','"+s14+"','"+s15+"','"+s16+"','"+s17+"','"+s18+"')";
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