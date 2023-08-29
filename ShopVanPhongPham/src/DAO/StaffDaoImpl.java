package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Staff;

public class StaffDaoImpl implements StaffDao{
	@Override
	public List<Staff> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Staff`";
			List<Staff> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Staff staff = new Staff();
				staff.setMaNhanVien(rs.getInt("maNhanVien"));
				staff.setHoTen(rs.getString("hoTen"));
				staff.setsDT(rs.getInt("sDT"));
				staff.setEmail(rs.getString("email"));
				staff.setDiaChi(rs.getString("diaChi"));
				staff.setMaLevel(rs.getInt("maLevel"));
				staff.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
				staff.setTrangThai(rs.getInt("trangThai"));
				list.add(staff); 
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
		StaffDao staffDao = new StaffDaoImpl();
		System.out.println(staffDao.getList());
	}
}
