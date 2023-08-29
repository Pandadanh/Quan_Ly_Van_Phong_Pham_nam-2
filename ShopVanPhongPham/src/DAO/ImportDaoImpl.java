package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Imports;

public class ImportDaoImpl implements ImportDao{

	@Override
	public List<Imports> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Import";
			List<Imports> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				Imports imports = new Imports(0, null,null, 0, 0, 0);
				imports.setMaPhieuNhap(rs.getInt("maPhieuNhap"));
				imports.setMaNhaCungCap(rs.getInt("maNhaCungCap"));
				imports.setNgayNhap(rs.getDate("ngayNhap"));
				imports.setNgaynhap(rs.getTime("ngaynhap"));
				imports.setMaNhanVien(rs.getInt("maNhanVien"));
				imports.setTongTien(rs.getFloat("tongTien"));
				list.add(imports);
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
