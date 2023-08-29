package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import DTO.Invoice;

public class InvoiceDaoImpl {
	
	public List<Invoice> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Invoice";
			List<Invoice> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
//				Invoice invoice = new Invoice();
//				invoice.(rs.getInt("maPhieuNhap"));
//				invoice.setMaNhaCungCap(rs.getInt("maNhaCungCap"));
//				invoice.setNgayNhap(rs.getDate("ngayNhap"));
//				invoice.setMaNhanVien(rs.getInt("maNhanVien"));
//				invoice.setTongTien(rs.getFloat("tongTien"));
//				list.add(invoice);
			}
			rs.close();
			ps.close();
//			cons.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		ImportDao importDao = new ImportDaoImpl();
		System.out.println(importDao.getList());
	}
}
