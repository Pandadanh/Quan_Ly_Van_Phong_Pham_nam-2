package BLL;

import javax.swing.JButton;
import javax.swing.JTextField;

import DTO.Product;
import DTO.Provider;

public class ProviderController {
	private JButton btnAdd;
	private JTextField jtfMaNhaCungCap;
	private JTextField jtfTenNhaCungCap;
	private JTextField jtfSDT;
	private JTextField jtfEmail;
	private JTextField jtfDiaChi;
	private JTextField jtfGhiChu;
	 
	public ProviderController(JButton btnAdd, JTextField jtfMaNhaCungCap, JTextField jtfTenNhaCungCap,
			JTextField jtfSDT, JTextField jtfEmail, JTextField jtfDiaChi, JTextField jtfGhiChu) {
		this.btnAdd = btnAdd;
		this.jtfMaNhaCungCap = jtfMaNhaCungCap;
		this.jtfTenNhaCungCap = jtfTenNhaCungCap;
		this.jtfSDT = jtfSDT;
		this.jtfEmail = jtfEmail;
		this.jtfDiaChi = jtfDiaChi;
		this.jtfGhiChu = jtfGhiChu;
	}
	
	public void setView(Provider provider) {
		jtfMaNhaCungCap.setText("#" + provider.getMaNhaCungCap());
		jtfTenNhaCungCap.setText(provider.getTenNhaCungCap());
		jtfSDT.setText("#" + provider.getsDT());
		jtfEmail.setText(provider.getEmail());
		jtfDiaChi.setText(provider.getDiaChi());
		jtfGhiChu.setText(provider.getGhiChu());
	} 
}
