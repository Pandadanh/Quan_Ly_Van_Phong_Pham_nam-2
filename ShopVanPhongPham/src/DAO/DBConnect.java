package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() throws SQLException {

		Connection cons = null;
		try {
			String DB_URL = "jdbc:mysql://localhost:3306/vanphongpham";
			String USER_NAME = "root";
			String PASSWORD = "";
			 cons = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
//			if(cons != null)
//				 System.out.println("Database is connected!!!");
//			else 
//			{
//				System.out.println("Not condeddd.");
//			}
		} catch (Exception ex) {
			System.out.println("Not condd.");
		}
		return cons; 
		
	}
	
	public static void main(String [] args) throws SQLException {
		Connection conn = getConnection();
	
	}
}
