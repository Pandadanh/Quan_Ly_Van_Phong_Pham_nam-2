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

import DTO.Provider;
import GUI.CategoryPanel;
import GUI.ProductPanel;

public interface ProviderDao {
	public List<Provider> getList();

	public static void insert(String manhacungcap, String tennhacungcap, String sdt, String email, String diachi,
			String ghichu, int trangthai) {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `provider` (`maNhaCungCap`, `tenNhaCungCap`, `sDT`, `email`, `diaChi`, `ghiChu` ,`trangThai`) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, manhacungcap);
			ps.setString(2, tennhacungcap);
			ps.setString(3, sdt);
			ps.setString(4, email);
			ps.setString(5, diachi);
			ps.setString(6, ghichu);
			ps.setInt(7, trangthai);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			int generatedKeys = 0;
			if (rs.next()) {
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
	
	public static void update(String manhacungcap, String tennhacungcap, String sdt, String email, String diachi,
			String ghichu, int trangthai) {
		try {
			
			Connection cons = DBConnect.getConnection();
			

			String sql = "UPDATE `provider` set `maNhaCungCap` = ? , `tenNhaCungCap` = ? , `sDT` = ? , `email` = ? , `diaChi` = ? , `ghiChu` = ?  ,`trangThai` = ? Where `maNhaCungCap` = " + manhacungcap ;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, manhacungcap);
			ps.setString(2, tennhacungcap);
			ps.setString(3, sdt);
			ps.setString(4, email);
			ps.setString(5, diachi);
			ps.setString(6, ghichu);
			ps.setInt(7, trangthai);

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
			
			String sql = "DELETE FROM `provider` WHERE `maNhaCungCap` = " + id;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			
			ps.close();
			cons.close();
//			JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhà cung  này!!!");
		} catch (SQLException e) {
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
