package BLL;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;

import DTO.Account;
import DTO.Staff;

public class StaffController {
	private JButton btnAdd;
	private JTextField jtfMaNhanVien;
	private JTextField jtfHoTen;
	private JTextField jtfSDT;
	private JTextField jtfEmail;
	private JTextField jtfDiaChi;
	private JTextField jtfMaLevel;
	private JTextField jtfMaTaiKhoan;
	private JTextField jtfTrangThai;
	
	public StaffController(JButton btnAdd, JTextField jtfMaNhanVien, JTextField jtfHoTen, JTextField jtfSDT,
			JTextField jtfEmail, JTextField jtfDiaChi, JTextField jtfMaLevel, JTextField jtfMaTaiKhoan,JTextField jtfTrangThai) {

		this.btnAdd = btnAdd;
		this.jtfMaNhanVien = jtfMaNhanVien;
		this.jtfHoTen = jtfHoTen;
		this.jtfSDT = jtfSDT;
		this.jtfEmail = jtfEmail;
		this.jtfDiaChi = jtfDiaChi;
		this.jtfMaLevel = jtfMaLevel;
		this.jtfMaTaiKhoan = jtfMaTaiKhoan;
		this.jtfTrangThai = jtfTrangThai;
	}


	public void setView(Staff staff) {
		jtfMaNhanVien.setText("#"+staff.getMaNhanVien());
		jtfHoTen.setText(staff.getHoTen());
		jtfSDT.setText("#" + staff.getsDT());
		jtfEmail.setText(staff.getEmail());
		jtfDiaChi.setText(staff.getDiaChi());
		jtfMaLevel.setText("#" + staff.getMaLevel());
		jtfMaTaiKhoan.setText("#" + staff.getMaTaiKhoan());
		jtfTrangThai.setText("#" + staff.getTrangThai());
	} 
}
