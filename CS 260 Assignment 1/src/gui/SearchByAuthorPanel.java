package gui;
import java.sql.*;

public class SearchByAuthorPanel {
	static final String DB_URL = "jdbc:oracle:thin@dario.cs.uwec.edu:1521:csdev";
	
//	Database credentials 
	static final String USER="ERICSOSM5070";
	static final String PASS="KWPI9MDP";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
//			Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
//			Execute a query
			stmt = conn.createStatement();
			String sql = "";
			rs = stmt.executeQuery(sql);
			
//			Use the result set
			while(rs.next()) {
//				Access field values in the current row using the column name
				int _____ = rs.getInt("");
				String ____ = rs.getString("");
				System.out.println("____" + ____ + ", " + ________);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
}
