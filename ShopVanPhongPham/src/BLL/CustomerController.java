package BLL;

import javax.swing.JButton;
import javax.swing.JTextField;

import DTO.Customer;


public class CustomerController {
	private JButton btnAdd;
	private JTextField jtfMaKhachHang;
	private JTextField jtfhoTen;
	private JTextField jtfSDT;
	
	public CustomerController(JButton btnAdd, JTextField jtfMaKhachHang, JTextField jtfhoTen, JTextField jtfSDT) {
		this.btnAdd = btnAdd;
		this.jtfMaKhachHang = jtfMaKhachHang;
		this.jtfhoTen = jtfhoTen;
		this.jtfSDT = jtfSDT;
	}
	 
	public void setView(Customer customer) {
		jtfMaKhachHang.setText("#" + customer.getMaKhachHang());
		jtfhoTen.setText(customer.getHoTen());
		jtfSDT.setText("#" + customer.getsDT()); 
	}
}
