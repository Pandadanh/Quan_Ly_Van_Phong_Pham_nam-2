package DTO;

public class Category {
	private int maLoai;
	private String tenLoai;
	private int trangThai;
	
	public Category()
	{
		
	}
	public Category(int maLoai,String tenLoai,int trangThai ) {
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.trangThai = trangThai;
		// TODO Auto-generated constructor stub
	}
	public int getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(int maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
}
