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

import DTO.Category;
import GUI.CategoryPanel;
import GUI.ProductPanel;

public interface CategoryDao {
	public List<Category> getList();
	
	public static void insert(String maLoai,String tenLoai, int trangThai){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `category` (`maLoai`, `tenLoai`, `trangThai`) VALUES (?, ?, ?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
			ps.setString(1, maLoai);
			ps.setString(2, tenLoai);
			ps.setLong(3, trangThai);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			int generatedKeys = 0;
			if(rs.next()) {
				generatedKeys = rs.getInt(1);
			}
			ps.close();
			rs.close();
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
	
	public static void update(String maLoai,String tenLoai, int trangThai) {
		try {
			
			Connection cons = DBConnect.getConnection();
			

			String sql = "UPDATE `category` set `maLoai` = ?, `tenLoai` = ?, `trangThai` = ? Where `maLoai` =  " + maLoai ;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, maLoai);
			ps.setString(2, tenLoai);
			ps.setInt(3, trangThai);

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
	
	public static void delete(int id) {
		try {
			Connection cons = DBConnect.getConnection();
			
			String sql = "DELETE FROM `category` WHERE `maLoai` = " + id;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
//			JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhà cung  này!!!");
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
}
