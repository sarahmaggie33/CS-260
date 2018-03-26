import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class HenryDAO {
	static final String DB_URL = "jdbc:oracle:thin:@dario.cs.uwec.edu:1521:csdev";
	
	//Database credentials
	static final String USER="FOREHANN8651";
	static final String PASS="HS16B1JQ";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;



	public ArrayList getBookData() {
		ArrayList<String> arr = new ArrayList<>();
		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select title from henry_book";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("title");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return arr;
	}

// 1st dropdown
	public ArrayList getAuthorData() {
		ArrayList<String> arr = new ArrayList<>();

		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select unique author_last from henry_author where author_num in (select unique author_num from henry_wrote)";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("author_last");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}

		return arr;
	}
	
// 2nd dropdown
	public ArrayList getAuthorsBookData(String a) {
		ArrayList<String> arr = new ArrayList<>();
		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select title from henry_book where book_code in (select book_code from henry_wrote where author_num = (select author_num from henry_author where author_last = '" + a + "'))";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("title");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return arr;
	}

//	1st dropdown
	public ArrayList getCategoryData() {
		ArrayList<String> arr = new ArrayList<>();

		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select unique type from henry_book";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("type");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}

		return arr;
	}
	
//	2nd dropdown
	public ArrayList getCategoryBookData(String a) {
		ArrayList<String> arr = new ArrayList<>();
		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select title from henry_book where book_code in (select book_code from henry_wrote where type = '" + a + "')";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("title");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return arr;
	}
	
//	1st dropdown
	public ArrayList getPublisherData() {
		ArrayList<String> arr = new ArrayList<>();

		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select unique publisher_name from henry_publisher";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("publisher_name");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}

		return arr;
	}
	
//	2nd dropdown
	public ArrayList getPublisherBookData(String a) {
		ArrayList<String> arr = new ArrayList<>();
		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select title from henry_book where publisher_code = (select publisher_code from henry_publisher where publisher_name = '" + a + "')";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("title");
				arr.add(s);
//				System.out.println(s);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return arr;
	}
	
	public double getPrice(String t)
	{
		double p = 0;

		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select price from henry_book where title = '" + t +"'";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				p = rs.getDouble("price");
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return p;


	}
	
	public Vector<String> authorBookstore(String a) {
		Vector<String> arr = new Vector<>();
		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select branch_name from henry_branch where branch_num in (select branch_num from henry_inventory where book_code in (select book_code from henry_book where title = '" + a + "'))";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("branch_name");
				arr.add(s);
//				System.out.println(s);
			}
			
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return arr;
	}

	public Vector<String> authorNBooks(String a) {
		Vector<String> nBooks = new Vector<>();
		try {
			//Open a connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Execute a query
			stmt = conn.createStatement();
			String sql = "select on_hand from henry_inventory where book_code in (select book_code from henry_book where title = '" + a + "')";
			rs = stmt.executeQuery(sql);

			//Loops through the result set
			while(rs.next()) {
				String s = rs.getString("on_hand");
				nBooks.add(s);
//				System.out.println(s);
			}
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return nBooks;
	}
	


}
