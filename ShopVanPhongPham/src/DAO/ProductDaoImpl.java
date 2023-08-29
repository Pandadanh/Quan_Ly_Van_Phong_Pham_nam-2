package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Category;
import DTO.Product;

public class ProductDaoImpl  implements ProductDao{
	@Override
	public List<Product> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `product`";
			List<Product> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setMaSanPham(rs.getInt("maSanPham"));
				product.setMaLoai(rs.getInt("maLoai"));
				product.setTenSanPham(rs.getString("tenSanPham"));
//				product.setHinhAnh(rs.getString("hinhAnh"));
				product.setdVT(rs.getString("dVT"));
				product.setSoLuong(rs.getInt("soLuong"));
				product.setDonGia(rs.getFloat("donGia"));
				product.setMoTa(rs.getString("moTa"));
				product.setTrangThai(rs.getString("trangThai"));
				product.setMaNhaCungCap(rs.getInt("maNhaCungCap"));
				list.add(product); 
			}
			ps.close();
			rs.close();
//			cons.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String [] args) {
		ProductDao productDao = new ProductDaoImpl();
		System.out.println(productDao.getList());
	}

}
