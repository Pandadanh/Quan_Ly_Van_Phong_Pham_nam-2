package DTO;

public class Provider {
	private int maNhaCungCap;
	private String tenNhaCungCap;
	private int sDT;
	private String email;
	private String diaChi;
	private String ghiChu;
	private int trangThai;
	
	public Provider()
	{
		
	}
	public Provider(int maNhaCungCap, String tenNhaCungCap, int sDT, String email, String diaChi,String ghiChu, int trangThai) {
		// TODO Auto-generated constructor stub
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.sDT = sDT;
		this.email = email;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
		
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	public int getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(int maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public int getsDT() {
		return sDT;
	}
	public void setsDT(int sDT) {
		this.sDT = sDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
}
