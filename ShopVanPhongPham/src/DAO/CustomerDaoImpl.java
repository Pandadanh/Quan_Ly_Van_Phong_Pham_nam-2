package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Customer;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public List<Customer> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `customer`";
			List<Customer> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setMaKhachHang(rs.getInt("maKhachHang"));
				customer.setHoTen(rs.getString("hoTen"));
				customer.setsDT(rs.getInt("sDT"));
				list.add(customer); 
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
		CustomerDao customerDao = new CustomerDaoImpl();
		System.out.println(customerDao.getList());
	}
}
