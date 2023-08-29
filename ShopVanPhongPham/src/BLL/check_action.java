package BLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class check_action {
	
public check_action() {
		
	}
	
public static boolean check_action_user_by_level(int level, int id_function, String action_code) {
    boolean result = false;
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanphongpham", "root", "");
        PreparedStatement pstmt = conn.prepareStatement("SELECT check_action FROM `per_detail` WHERE maLevel = ? AND id_function = ? AND action_code = ?");
    ) {
        pstmt.setInt(1, level);
        pstmt.setInt(2, id_function);
        pstmt.setString(3, action_code);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt("check_action") == 1;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}



}
