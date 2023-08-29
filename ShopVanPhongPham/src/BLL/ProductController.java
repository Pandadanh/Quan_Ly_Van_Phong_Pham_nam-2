package BLL;

import javax.swing.JButton;
import javax.swing.JTextField;

import DTO.Product;

public class ProductController {
	private JButton btnAdd;
	private JTextField jtfMaSanPham;
	private JTextField jtfMaLoai;
	private JTextField jtfTenSanPham;
	private JTextField jtfDVT;
	private JTextField jtfSoLuong;
	private JTextField jtfDonGia;
	private JTextField jtfMoTa;
	private JTextField jtfTrangThai;
	private JTextField jtfMaNhaCungCap;
	 
	public ProductController(JButton btnAdd, JTextField jtfMaSanPham, JTextField jtfMaLoai, JTextField jtfTenSanPham,
			 JTextField jtfDVT, JTextField jtfSoLuong, JTextField jtfDonGia, JTextField jtfMoTa,
			JTextField jtfTrangThai, JTextField jtfMaNhaCungCap) {
		this.btnAdd = btnAdd;
		this.jtfMaSanPham = jtfMaSanPham;
		this.jtfMaLoai = jtfMaLoai;
		this.jtfTenSanPham = jtfTenSanPham;
		this.jtfDVT = jtfDVT;
		this.jtfSoLuong = jtfSoLuong;
		this.jtfDonGia = jtfDonGia;
		this.jtfMoTa = jtfMoTa;
		this.jtfTrangThai = jtfTrangThai;
		this.jtfMaNhaCungCap = jtfMaNhaCungCap;
	}

	public void setView(Product product) {
		jtfMaSanPham.setText("#" + product.getMaSanPham());
		jtfMaLoai.setText("#" + product.getMaLoai());
		jtfTenSanPham.setText(product.getTenSanPham());
		jtfDVT.setText("#" + product.getdVT());
		jtfSoLuong.setText("#" + product.getSoLuong());
		jtfDonGia.setText("#" + product.getDonGia());
		jtfMoTa.setText(product.getMoTa());
		jtfTrangThai.setText("#" + product.getTrangThai());
		jtfMaNhaCungCap.setText("#" + product.getMaNhaCungCap());
	} 
}
