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

import DAO.DBConnect;
import DTO.Account;
import DTO.Customer;

import GUI.ProductPanel;


public interface AccountDao {
	public List<Account> getList();
	
	public static void insert(int maTaiKhoan, int sDT, String passWord,int level){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `account` (`maTaiKhoan`, `sDT`, `passWord`,`level`) VALUES ( ?, ? ,?,?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, maTaiKhoan);
			ps.setInt(2, sDT);
			ps.setString(3, passWord);
			ps.setInt(4, level);
			

			
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
			
			String sql = "DELETE FROM `account` WHERE `maTaiKhoan` = " + id;
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
	public static void update(int maTaiKhoan, int sDT, String passWord,int level){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "UPDATE `account` set `sDT`= ? ,`passWord`= ? ,`level`= ?  Where `maTaiKhoan`= ?";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, sDT);
			ps.setString(2, passWord);
			ps.setInt(3, level);
			ps.setInt(4, maTaiKhoan);
			
			
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
