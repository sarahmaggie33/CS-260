package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HenryDAO {
	static final String DB_URL = "jdbc:oracle:thin:@dario.cs.uwec.edu:1521:csdev";
	
//	Database credentials 
	static final String USER="ERICSOSM5070";
	static final String PASS="KWPI9MDP";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	public static void main(String[] args) {
		
		try {
//			Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
//			execute queries
			getAuthorData();
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		
		} finally {
			try {
				if(rs != null) {
					rs.close();
				} 
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
	}
	
	public static void getAuthorData() {
		String selection = SearchByAuthorPanel.selection;
		
//		Execute a query
		int[] authorIds = new int[1000];
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT AUTHOR_NUM, AUTHOR_FIRST, AUTHOR_LAST FROM HENRY_AUTHOR";
			rs = stmt.executeQuery(sql);
//			Use the result set
			int i = 0;
			while(rs.next()) {
//				Access field values in the current row using the column name
				authorIds[i] = rs.getInt("AUTHOR_NUM");
				String author_name = rs.getString("AUTHOR_FIRST") + " " + rs.getString("AUTHOR_LAST");
				System.out.println(author_name);
				i++;
			} 
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public static void getCategoryData() {
		
	}
	
	public static void getPublisherData() {
		
	}
}
