package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Product;
import DTO.Staff;

import GUI.ProductPanel;

import GUI.ProviderPanel;

public interface StaffDao {
public List<Staff> getList();
	
	public static void insert(String maNhanVien, String hoTen, String sDT, String email, String diaChi, int maLevel,
			int trangThai, String maTaiKhoan){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `staff` (`maNhanVien`, `hoTen`, `sDT`, `email`, `diaChi`, `maLevel`, `trangThai`, `maTaiKhoan`) VALUES ( ?, ? ,? , ?, ?, ?, ?,?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, maNhanVien);
			ps.setString(2, hoTen);
			ps.setString(3, sDT);
			ps.setString(4, email);
			ps.setString(5, diaChi);
			ps.setLong(6, maLevel);
			ps.setLong(7, trangThai);
			ps.setString(8, maTaiKhoan);

			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			int generatedKeys = 0;
			if(rs.next()) {
				generatedKeys = rs.getInt(1);
			}
			ps.close();
			rs.close();
			cons.close();
		
		} catch (SQLException e) {
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	public static void delete(int id) {
		try {
			Connection cons = DBConnect.getConnection();
			
			String sql = "UPDATE `staff` set `trangThai`= 0 WHERE `maNhanVien` = " + id;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
//			JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhà cung  này!!!");
		} catch (SQLException e) {
			Logger.getLogger(ProviderPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	public static void update(String maNhanVien, String hoTen, String sDT, String email, String diaChi, int maLevel,
			int trangThai, String maTaiKhoan) {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "UPDATE `staff` set `hoTen`= ? ,`sDT`= ? ,`email`= ? ,`diaChi`= ? ,`maLevel`= ? ,`trangThai`= ? ,`maTaiKhoan`= ?  Where `maNhanVien`= ?";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, hoTen);
			ps.setString(2, sDT);
			ps.setString(3, email);
			ps.setString(4, diaChi);
			ps.setInt(5, maLevel);
			ps.setInt(6, trangThai);
			ps.setString(7, maTaiKhoan);
			ps.setString(8, maNhanVien);
			
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
			
		} catch (SQLException e) {
			Logger.getLogger(ProviderPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	


	
	}




