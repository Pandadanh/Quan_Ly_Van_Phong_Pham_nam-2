package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JLabel;

import DTO.Product;
import DTO.Staff;
import DTO.role;
import GUI.ProductPanel;

import GUI.ProviderPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface roleDao {
public List<role> getList();
List<Integer> listIdFunction = new ArrayList<>();	
List<Integer> listMaLevel = new ArrayList<>();
	
public static void delete(String action_name_code) {
    try {
        Connection cons = DBConnect.getConnection();

        String sql = "DELETE FROM `per_detail` WHERE `action_code` = ?";
        PreparedStatement ps = cons.prepareStatement(sql);
        ps.setString(1, action_name_code);

        ps.executeUpdate();

        ps.close();
        cons.close();
    } catch (SQLException ex) {
        ex.printStackTrace();

        // Hiển thị JDialog với thông báo lỗi
        JDialog dialog = new JDialog();
        dialog.setTitle("Lỗi");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Lỗi: " + ex.getMessage());
        dialog.getContentPane().add(label);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


    }
}

	
	public static void update(int id, int level, int id_func, String action_name, int check_action){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "UPDATE `per_detail` set  `maLevel`= ? ,`id_function`= ? ,`action_code`= ? ,`check_action`= ?  Where `id_per_detail`= ?";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
		
			ps.setInt(1, level);
			ps.setInt(2, id_func);
			ps.setString(3, action_name);
			ps.setInt(4, check_action);
			ps.setInt(5, id);
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
			
		} catch (SQLException ex) {
	        ex.printStackTrace();

	        // Hiển thị JDialog với thông báo lỗi
	        JDialog dialog = new JDialog();
	        dialog.setTitle("Lỗi");
	        dialog.setModal(true);
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        JLabel label = new JLabel("Lỗi: " + ex.getMessage());
	        dialog.getContentPane().add(label);

	        dialog.setSize(300, 150);
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);


	    }

	}
	
	
	public static void insert(String action_name) {
		
		try {
		    Connection cons = DBConnect.getConnection();
		    String sql = "SELECT id_function FROM function";
		    PreparedStatement ps = cons.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();

		    while (rs.next()) {
		        int idFunction = rs.getInt("id_function");
		        listIdFunction.add(idFunction);
		    }

		    rs.close();
		    ps.close();
		    cons.close();
		} catch (SQLException ex) {
	        ex.printStackTrace();

	        // Hiển thị JDialog với thông báo lỗi
	        JDialog dialog = new JDialog();
	        dialog.setTitle("Lỗi");
	        dialog.setModal(true);
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        JLabel label = new JLabel("Lỗi: " + ex.getMessage());
	        dialog.getContentPane().add(label);

	        dialog.setSize(300, 150);
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);


	    }

		
	

		try {
		    Connection cons = DBConnect.getConnection();
		    String sql = "SELECT maLevel FROM level";
		    PreparedStatement ps = cons.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();

		    while (rs.next()) {
		        int maLevel = rs.getInt("maLevel");
		        listMaLevel.add(maLevel);
		    }

		    rs.close();
		    ps.close();
		    cons.close();
		} catch (SQLException ex) {
	        ex.printStackTrace();

	        // Hiển thị JDialog với thông báo lỗi
	        JDialog dialog = new JDialog();
	        dialog.setTitle("Lỗi");
	        dialog.setModal(true);
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        JLabel label = new JLabel("Lỗi: " + ex.getMessage());
	        dialog.getContentPane().add(label);

	        dialog.setSize(300, 150);
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);


	    }


		
		
		
		 try {
		        Connection cons = DBConnect.getConnection();
		        String sql = "INSERT INTO per_detail(maLevel, id_function, action_code, check_action) VALUES (?, ?, ?, 0)";
		        PreparedStatement ps = cons.prepareStatement(sql);

		        for (int maLevel : listMaLevel) {
		            for (int idFunction : listIdFunction) {
		                ps.setInt(1, maLevel);
		                ps.setInt(2, idFunction);
		                ps.setString(3, action_name);
		                ps.executeUpdate();
		            }
		        }

		        ps.close();
		        cons.close();
		    } catch (SQLException ex) {
		        ex.printStackTrace();

		        // Hiển thị JDialog với thông báo lỗi
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Lỗi");
		        dialog.setModal(true);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		        JLabel label = new JLabel("Lỗi: " + ex.getMessage());
		        dialog.getContentPane().add(label);

		        dialog.setSize(300, 150);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);

 
		    }

	}

	
	
	
	
	
	public static void insert_mess_role(String name_level, List<String> list_fuc_str, List<String> list_fuc_actionname) {
	    try {
	        Connection conn = DBConnect.getConnection();
	        PreparedStatement pstmt;

	        // Chèn dữ liệu vào bảng level
	        String insertLevelQuery = "INSERT INTO level (tenLevel) VALUES (?)";
	        pstmt = conn.prepareStatement(insertLevelQuery, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, name_level);
	        pstmt.executeUpdate();

	      
	        int maLevel = -1;
	        ResultSet rs = pstmt.getGeneratedKeys();
	        if (rs.next()) {
	            maLevel = rs.getInt(1);
	        }
	        rs.close();
	        pstmt.close();

	        // Chèn dữ liệu vào bảng per_detail
	        String insertPerDetailQuery = "INSERT INTO per_detail (maLevel, id_function, action_code, check_action) VALUES (?, ?, ?, 0)";
	        pstmt = conn.prepareStatement(insertPerDetailQuery);

	        for (int i = 1 ; i < list_fuc_str.size(); i ++) {
	            for (String actionName : list_fuc_actionname) {
	                pstmt.setInt(1, maLevel);
	                pstmt.setInt(2, i); // Giả sử fucStr là một chuỗi số
	                pstmt.setString(3, actionName);
	                pstmt.executeUpdate();
	            }
	        }

	        pstmt.close();
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();

	        // Hiển thị JDialog với thông báo lỗi
	        JDialog dialog = new JDialog();
	        dialog.setTitle("Lỗi");
	        dialog.setModal(true);
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        JLabel label = new JLabel("Lỗi: " + ex.getMessage());
	        dialog.getContentPane().add(label);

	        dialog.setSize(300, 150);
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);


	    }

	}
	
	public static void deleteLevel(int maLevel) {
	    String deletePerDetailQuery = "DELETE FROM per_detail WHERE maLevel = ?";
	    String deleteLevelQuery = "DELETE FROM level WHERE maLevel = ?";
	    
	    try {
	        Connection conn = DBConnect.getConnection();
	        conn.setAutoCommit(false);  

	     
	        PreparedStatement pstmtPerDetail = conn.prepareStatement(deletePerDetailQuery);
	        pstmtPerDetail.setInt(1, maLevel);
	        pstmtPerDetail.executeUpdate();
	        pstmtPerDetail.close();

	       
	        PreparedStatement pstmtLevel = conn.prepareStatement(deleteLevelQuery);
	        pstmtLevel.setInt(1, maLevel);
	        pstmtLevel.executeUpdate();
	        pstmtLevel.close();

	        conn.commit();  
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	
	}




