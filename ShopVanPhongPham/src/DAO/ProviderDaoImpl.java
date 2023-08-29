package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Provider;

public class ProviderDaoImpl implements ProviderDao{
	@Override
	public List<Provider> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Provider`";
			List<Provider> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Provider provider = new Provider();
				provider.setMaNhaCungCap(rs.getInt("maNhaCungCap"));
				provider.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
				provider.setsDT(rs.getInt("sDT"));
//				product.setHinhAnh(rs.getString("hinhAnh"));
				provider.setEmail(rs.getString("email"));
				provider.setDiaChi(rs.getString("diaChi"));
				provider.setGhiChu(rs.getString("ghiChu"));
				list.add(provider); 
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
