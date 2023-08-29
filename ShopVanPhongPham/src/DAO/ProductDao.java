package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import DTO.Product;

import GUI.ProductPanel;

import GUI.ProviderPanel;

public interface ProductDao {
	public List<Product> getList();
	
	public static void insert(String tensp,String dvt, String  dongia,String mota , String trangthai ,int sl,int idCategory,int idProvider){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `product` (`maLoai`, `tenSanPham`, `dVT`, `soLuong`, `donGia`, `moTa`, `trangThai`,`maNhaCungCap`) VALUES ( ?, ? ,? , ?, ?, ?, ?,?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1, idCategory);
			ps.setString(2, tensp);
			ps.setString(3, dvt);
			ps.setLong(4, sl);
			ps.setString(5, dongia);
			ps.setString(6, mota);
			ps.setString(7, trangthai);
			ps.setLong(8, idProvider);

			
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
			String trangthai = "false";
//			String sql = "DELETE FROM `product` WHERE `maSanPham` = " + id;
//			Connection cons = DBConnect.getConnection();
			String sql = "UPDATE `product` set  `trangThai` = ?  Where `maSanPham` = ?";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, trangthai);
			ps.setLong(2,id);
			
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
//			JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhà cung  này!!!");
		} catch (SQLException e) {
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	public static int checkncc(int masp){
		int abc = -1;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `product` where maSanPham = '"+masp+"'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 abc = r1.getInt("maSanPham");
			
			}
			
			
			pr.close();
			r1.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return abc;
	}
	public static void update(int masp , String tensp,String dvt, String  dongia,String mota , String trangthai ,int sl,int maloai ,int mancc) {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "UPDATE `product` set `maLoai` = ?, `tenSanPham` = ?, `dVT` = ?, `soLuong` = ?, `donGia` = ? , `moTa` = ? , `trangThai` = ? , `maNhaCungCap` = ? Where `maSanPham` = ?";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1, maloai);
			ps.setString(2, tensp);
			ps.setString(3, dvt);
			ps.setLong(4, sl);
			ps.setString(5, dongia);
			ps.setString(6, mota);
			ps.setString(7, trangthai);
			ps.setLong(8, mancc);
			ps.setLong(9, masp);
			
			ps.executeUpdate();
			
			ps.close();
			cons.close();
			
		} catch (SQLException e) {
			Logger.getLogger(ProviderPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	}


