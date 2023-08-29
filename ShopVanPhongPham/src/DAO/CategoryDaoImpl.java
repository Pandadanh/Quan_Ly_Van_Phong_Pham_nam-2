package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Category;

public class CategoryDaoImpl implements CategoryDao {
	@Override
	public List<Category> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Category`";
			List<Category> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setMaLoai(rs.getInt("maLoai"));
				category.setTenLoai(rs.getString("tenLoai"));
//				category.setTrangThai(rs.getBoolean("trangThai"));
				list.add(category); 
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
		CategoryDao categoryDao = new CategoryDaoImpl();
		System.out.println(categoryDao.getList());
	}
}
