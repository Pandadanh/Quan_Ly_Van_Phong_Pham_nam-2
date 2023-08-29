package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.Invoice;
import DTO.Product;

import GUI.ProductPanel;

public class InvoiceDao {
	public List<Invoice> getList() {
		return null;
	}
	public static void insertSell(String ngayBan,int maNhanVien, int maKhachHang,float tongtien){
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "INSERT INTO `invoice` (`ngayBan`, `maNhanVien`, `maKhachHang`, `tongtien`) VALUES ( ?, ? ,? , ?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setNString(1, ngayBan);
			ps.setLong(2, maNhanVien);
			ps.setLong(3, maKhachHang);
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
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	public static int checkmakh(int maKhachHang){
		int abc = 1;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `customer` where sDT = '"+maKhachHang+"'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 abc = r1.getInt("maKhachHang");
			
			}
			pr.close();
			r1.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return abc;
	}

	public static int checkmanv(int matk){
		int abc = -1;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `staff` where maTaiKhoan = '"+matk+"'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 abc = r1.getInt("maNhanVien");
			
			}
			
			pr.close();
			r1.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return abc;
	}
	
	
	public static void checklaisl(int maSanPham, int soLuong){

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delete(int maHoaDon){

		try {
			Connection cons = DBConnect.getConnection();
			int masp;
			int soluong;
			int sl;
			String sql2 = "SELECT * FROM `invoicedetail` where maHoaDon = '"+maHoaDon+"'";
			PreparedStatement pr = cons.prepareStatement(sql2);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				masp = r1.getInt("maSanPham");
				soluong = r1.getInt("soLuong");
				String sql3 = "SELECT * FROM `product` where maSanPham = '"+masp+"'";
				PreparedStatement pr2 = cons.prepareStatement(sql3);
				ResultSet r2 = pr2.executeQuery();
				while(r2.next()) {
					sl = r2.getInt("soLuong");
					int sluong = soluong + sl;
					String sql4 = "UPDATE `product` set `soLuong` = ? Where `maSanPham` = ?";
					PreparedStatement ps1 = cons.prepareStatement(sql4, PreparedStatement.RETURN_GENERATED_KEYS);
					ps1.setLong(1, sluong);
					ps1.setLong(2, masp);
					ps1.executeUpdate();
					
				}
				
			}
			
			String sql = "DELETE FROM `invoicedetail` WHERE `maHoaDon` = " + maHoaDon;
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
	
			
			String sql5 = "DELETE FROM `invoice` WHERE `maHoaDon` = " + maHoaDon;
			PreparedStatement ps5 = cons.prepareStatement(sql5, PreparedStatement.RETURN_GENERATED_KEYS);
			
		
			ps5.executeUpdate();
			ps5.close();
			ps.close();
			cons.close();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void checklaisladd(int maSanPham, int soLuong){

		try {
			Connection cons = DBConnect.getConnection();
		
			//
			int sol = 0;
			String sql = "SELECT * FROM `product` where maSanPham = '"+maSanPham+"'";
			PreparedStatement pr1 = cons.prepareStatement(sql);
			ResultSet r2 = pr1.executeQuery();
			while(r2.next()) {
				 sol = r2.getInt("soLuong");
			}
			int soluong1 = sol - soLuong;
			String sql2 = "UPDATE `product` set `soLuong` = ? Where `maSanPham` = ?";
			PreparedStatement ps1 = cons.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
			ps1.setLong(1, soluong1);
			ps1.setLong(2, maSanPham);
			
			ps1.executeUpdate();
			ps1.close();
			//
			pr1.close();
			r2.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int checkslsp(int maSanPham, int soLuong){
		int abc = -1;
		try {
			Connection cons = DBConnect.getConnection();
			int sl = 0;
			String sql1 = "SELECT * FROM `product` where maSanPham = '"+maSanPham+"'";
			PreparedStatement pr = cons.prepareStatement(sql1);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 sl = r1.getInt("soLuong");
			}
			int soluong = sl - soLuong;	
			if(soluong < 0)
			{
				abc =  maSanPham;
			}
			//

			pr.close();
			r1.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return abc;
	}
	public static int checkmahd(String ngayBan){
		int abc = -1;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `invoice` where ngayBan = '"+ngayBan+"'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				 abc = r1.getInt("maHoaDon");
			
			}
			
			
			pr.close();
			r1.close();
			cons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return abc;
	}
	public static void insertDetailSell(int  maHoaDon,int maSanPham, int soLuong,float thanhTien){
		try {
			Connection cons = DBConnect.getConnection();
			
		
			String sql = "INSERT INTO `invoicedetail` (`maHoaDon`, `maSanPham`, `soLuong`, `thanhTien`) VALUES ( ?, ? ,? , ?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1, maHoaDon);
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
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
