package DTO;

public class Customer {
	private int maKhachHang;
	private String hoTen;
	private int sDT;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Customer(int maKhachHang, String hoTen, int sDT) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.sDT = sDT;
	}


	public int getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getsDT() {
		return sDT;
	}
	public void setsDT(int sDT) {
		this.sDT = sDT;
	}
}
