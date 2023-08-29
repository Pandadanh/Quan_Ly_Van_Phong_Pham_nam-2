package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Account;

public class AccountDaoImpl implements AccountDao{
	@Override
	public List<Account> getList() {
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Account`";
			List<Account> list = new ArrayList<>();
			PreparedStatement ps = cons.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
				account.setsDT(rs.getInt("sDT"));
				account.setPassWord(rs.getString("passWord"));
//				account.setTrangThai(rs.getBoolean("trangThai"));
				list.add(account); 
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
		AccountDao accountDao = new AccountDaoImpl();
		System.out.println(accountDao.getList());
	}
}
