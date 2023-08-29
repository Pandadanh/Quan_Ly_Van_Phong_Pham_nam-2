package BLL;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DTO.Account;

public class AccountController {
	private JTextField jtfMaTaiKhoan;
	private JTextField jtfSDT;
	private JTextField jtfPassWord;
	

	public AccountController(JTextField jtfMaTaiKhoan, JTextField jtfSDT, JTextField jtfPassWord) {
		
		this.jtfMaTaiKhoan = jtfMaTaiKhoan;
		this.jtfSDT = jtfSDT;
		this.jtfPassWord = jtfPassWord;
		
	}

	public void setView(Account account) {
		jtfMaTaiKhoan.setText("#" + account.getMaTaiKhoan());
		jtfSDT.setText("#" + account.getsDT());
		jtfPassWord.setText(account.getPassWord());
		
	} 
}
