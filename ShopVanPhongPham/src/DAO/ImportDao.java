package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Imports;

public interface ImportDao {
	public List<Imports> getList();
	public static void insertSell(String ngayNhap,int maNhanVien, int idProvider,float tongtien){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `import` (`ngayNhap`, `maNhaCungCap`, `maNhanVien`, `tongtien`) VALUES ( ?, ? ,? , ?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setNString(1, ngayNhap);
			ps.setLong(2, idProvider);
			ps.setLong(3, maNhanVien);
			ps.setLong(4, (long) tongtien);
			

			
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
			
		}
	}
	
	public static int checkmahd(String ngayNhap){
		int abc = -1;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `import` where ngayNhap = '"+ngayNhap+"'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 abc = r1.getInt("maNhapHang");
			
			}
			
			pr.close();
			r1.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return abc;
	}
	
	public static void insertDetailSell(int  maNhapHang,int maSanPham, int soLuong,float thanhTien){
		try {
			Connection cons = DBConnect.getConnection();
			int sl = 0;
			String sql1 = "SELECT * FROM `product` where maSanPham = '"+maSanPham+"'";
			PreparedStatement pr = cons.prepareStatement(sql1);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 sl = r1.getInt("soLuong");
			}
			int soluong = sl + soLuong;
			String sql2 = "UPDATE `product` set `soLuong` = ? Where `maSanPham` = ?";
			PreparedStatement ps1 = cons.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
			ps1.setLong(1, soluong);
			ps1.setLong(2, maSanPham);
			
			ps1.executeUpdate();
			ps1.close();
			
			String sql = "INSERT INTO `importdetail` (`maNhapHang`, `maSanPham`, `soLuong`, `thanhTien`) VALUES ( ?, ? ,? , ?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1, maNhapHang);
			ps.setLong(2, maSanPham);
			ps.setLong(3, soLuong);
			ps.setLong(4, (long) thanhTien);
			
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
			
		}
	}
}
