package BLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class check_all {

	public check_all() {
		
	}
	
	public boolean checkDataExist(String nametb, String colum, String key) {
	    boolean exists = false;
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanphongpham", "root", "");
	        PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + nametb + " WHERE " + colum + " = ?");
	        pstmt.setString(1, key);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            exists = true;
	        }
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exists;
	}

}
