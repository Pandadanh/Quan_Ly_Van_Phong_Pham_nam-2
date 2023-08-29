package BLL;

import javax.swing.JButton;
import javax.swing.JTextField;

import DTO.Category;

public class CategoryController {
	private JButton btnAdd;
	private JTextField jtfMaLoai;
	private JTextField jtfTenLoai;
	private JTextField jtfTrangThai;
	 
	public CategoryController(JButton btnAdd, JTextField jtfMaLoai, JTextField jtfTenLoai, JTextField jtfTrangThai) {
		this.btnAdd = btnAdd;
		this.jtfMaLoai = jtfMaLoai;
		this.jtfTenLoai = jtfTenLoai;
		this.jtfTrangThai = jtfTrangThai;
	}



	public void setView(Category category) {
		jtfMaLoai.setText("#" + category.getMaLoai());
		jtfTenLoai.setText(category.getTenLoai());
		jtfTrangThai.setText("#" + category.getTrangThai());
	} 
}
