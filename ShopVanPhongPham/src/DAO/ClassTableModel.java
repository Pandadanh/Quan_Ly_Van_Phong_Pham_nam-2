 package DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.table.DefaultTableModel;

import DTO.Account;
import DTO.Customer;
import DTO.Staff;

public class ClassTableModel {
	
	public DefaultTableModel setTableCustomer(List<Customer> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn);
		int columns = listColumn.length;
		Object[] obj = null;
		int rows = listItem.size();
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				Customer customer = listItem.get(i);
				obj = new Object[columns];
				obj[0] = customer.getMaKhachHang();
				obj[1] = customer.getHoTen();
				obj[2] = customer.getsDT();
				dtm.addRow(obj);
			}
		}
		return dtm;
	}
	
	public DefaultTableModel setTableCategory(List<DTO.Category> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn);
		int columns = listColumn.length;
		Object[] obj = null;
		int rows = listItem.size();
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				DTO.Category category = listItem.get(i);
				obj = new Object[columns];
				obj[0] = category.getMaLoai();
				obj[1] = category.getTenLoai();
				obj[2] = category.getTrangThai();
				dtm.addRow(obj);
			}
		}
		return dtm;
	}
	
	public DefaultTableModel setTableAccount(List<DTO.Account> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn); //Account
		int columns = listColumn.length; //Account
		Object[] obj = null; //Account
		int rows = listItem.size(); 
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				DTO.Account account = listItem.get(i);
				obj = new Object[columns];
				obj[0] = account.getMaTaiKhoan();
				obj[1] = account.getsDT();
				obj[2] = account.getPassWord();
				dtm.addRow(obj);	
			}
		}
		return dtm;
	}
	
	public DefaultTableModel setTableStaff(List<DTO.Staff> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn); //Account
		int columns = listColumn.length; //Account
		Object[] obj = null; //Account
		int rows = listItem.size(); 
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				DTO.Staff staff = listItem.get(i);
				obj = new Object[columns];
				obj[0] = staff.getMaNhanVien();
				obj[1] = staff.getHoTen();
				obj[2] = staff.getsDT();
				obj[3] = staff.getEmail();
				obj[4] = staff.getDiaChi();
				obj[5] = staff.getMaLevel();
				obj[6] = staff.getMaTaiKhoan();
				obj[7] = staff.getTrangThai();
				dtm.addRow(obj);
				
			}
		}
		return dtm;
	}
	
	public DefaultTableModel setTableProvicer(List<DTO.Provider> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn); //Account
		int columns = listColumn.length; //Account
		Object[] obj = null; //Account
		int rows = listItem.size(); 
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				DTO.Provider provider = listItem.get(i);
				obj = new Object[columns];
				obj[0] = provider.getMaNhaCungCap();
				obj[1] = provider.getTenNhaCungCap();
				obj[2] = provider.getsDT();
				obj[3] = provider.getEmail();
				obj[4] = provider.getDiaChi();
				obj[5] = provider.getGhiChu();
				dtm.addRow(obj);	
			}
		}
		return dtm;
	}
	
	public DefaultTableModel setTableProduct(List<DTO.Product> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn);
		int columns = listColumn.length;
		Object[] obj = null;
		int rows = listItem.size();
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				DTO.Product product = listItem.get(i);
				obj = new Object[columns];
				obj[0] = product.getMaSanPham();
				obj[1] = product.getMaLoai();
				obj[2] = product.getTenSanPham();
//				obj[3] = product.getHinhAnh();
				obj[3] = product.getdVT();
				obj[4] = product.getSoLuong();
				obj[5] = product.getDonGia();
				obj[6] = product.getMoTa();
				obj[7] = product.getTrangThai();
				dtm.addRow(obj);
			}
		}
		return dtm;
	}
	
	public DefaultTableModel setTableProvider(List<DTO.Provider> listItem, String[] listColumn) {
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(listColumn);
		int columns = listColumn.length;
		Object[] obj = null;
		int rows = listItem.size();
		if(rows > 0) {
			for(int i = 0; i < rows; i++) {
				DTO.Provider provider = listItem.get(i);
				obj = new Object[columns];
				obj[0] = provider.getMaNhaCungCap();
				obj[1] = provider.getTenNhaCungCap();
				obj[2] = provider.getsDT();
				obj[3] = provider.getEmail();
				obj[4] = provider.getDiaChi();
				obj[5] = provider.getGhiChu();
				dtm.addRow(obj);
			}
		}
		return dtm;
	}
	
}
