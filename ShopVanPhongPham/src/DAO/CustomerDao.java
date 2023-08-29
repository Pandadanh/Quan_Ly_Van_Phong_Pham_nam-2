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

import DTO.Customer;

import GUI.ProductPanel;

import GUI.ProviderPanel;

public interface CustomerDao {
	public List<Customer> getList();
	
	public static void insert(String makhachhang, String hoTen, String sDT){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `customer` (`maKhachHang`, `hoTen`, `sDT`) VALUES ( ?, ? ,?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, makhachhang);
			ps.setString(2, hoTen);
			ps.setString(3, sDT);
			

			
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
	public static void delete(int id) {
		try {
			Connection cons = DBConnect.getConnection();
			
			String sql = "DELETE FROM `customer` WHERE `maKhachHang` = " + id;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
//			JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhà cung  này!!!");
		}catch (SQLException ex) {
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
	public static void update(String makhachhang, String hoTen, String sDT){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "UPDATE `customer` set `hoTen`= ? ,`sDT`= ?   Where `maKhachHang`= ?";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, hoTen);
			ps.setString(2, sDT);
			
			ps.setString(3, makhachhang);
			
			
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
	


	
	}
