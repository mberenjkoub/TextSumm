package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.poi.hssf.record.formula.functions.Request;

//import pdf.DOCRead;

//import Stemming.Stemmer;

import java.sql.*;



public class mydb {

	/**
	 * @param args
	 * @throws IOException 
	 */
	

	private static String[][] data;
	private static Connection conn;
	private static Statement st;
	public String lastError;

    private Connection con;
        
         public mydb() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            lastError = e.getMessage();
        }
    }
    public void Connect() {
        try {
            String url = "jdbc:mysql://localhost/dictionary?user=root&password=root&useUnicode=true&characterEncoding=UTF-8";
            con = DriverManager.getConnection(url);
            st = con.createStatement();
        } catch (Exception e) {
            lastError = e.getMessage();
        }
    }
    
    public void Disconnect() {
        try {
            con.close();
            st.close();
        } catch (Exception e) {
            lastError = e.getMessage();
        }
    }
    //Insert Update Delete Command Do By This Method
    public void Docommand(String sql) {
        try {
            st.execute(sql);
        } catch (Exception e) {
            lastError = e.getMessage();
        }
    }
    public String[][] Select(String Selectsql) {
        String[][] data = null;
        try {
            st.executeQuery(Selectsql);
            ResultSet rs = st.getResultSet();
            
            rs.last();
            int row_count = rs.getRow();
            rs.beforeFirst();
            
            //Get Column Count From RS
            int col_count = rs.getMetaData().getColumnCount();
            
            data = new String[row_count][col_count];
            
            int i=0;
            while(rs.next()==true) {
                for(int j=0;j<col_count;j++) {
                    data[i][j]  = rs.getString(j+1);
                }
                i++;
            }
            rs.close();
        } catch (Exception e) {
            lastError = e.getMessage();
        }
        return data;
    }
    
}

        
	 