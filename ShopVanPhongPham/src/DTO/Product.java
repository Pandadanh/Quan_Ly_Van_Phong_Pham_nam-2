package DTO;

public class Product {
	private int maSanPham;
	private int maLoai;
	private String tenSanPham;
//	private String hinhAnh;
	private String dVT;
	private int soLuong;
	private float donGia;
	private String moTa;
	private String trangThai;
	private int maNhaCungCap;
	
	public Product()
	{
		
	}
//	public Product(int maSanPham, String tenSanPham, int soLuong, float donGia, Object object, Object object2, Object object3, Object object4, Object object5) {
//		this.maSanPham = maSanPham;
//		this.tenSanPham = tenSanPham;
//		this.soLuong = soLuong;
//		this.donGia = donGia;
//	}
	
	public Product(int maSanPham, String tenSanPham, int soLuong, Float donGia, String dVT, String moTa, int maLoai,
			String trangThai, int maNhaCungCap) {
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.dVT = dVT;
		this.moTa = moTa;
		this.maLoai = maLoai;
		this.trangThai = trangThai;
		this.maNhaCungCap = maNhaCungCap;
		
		// TODO Auto-generated constructor stub
	}
	public int getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(int maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public int getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
//	public String getHinhAnh() {
//		return hinhAnh;
//	}
//	public void setHinhAnh(String hinhAnh) {
//		this.hinhAnh = hinhAnh;
//	}
	public String getdVT() {
		return dVT;
	}
	public void setdVT(String dVT) {
		this.dVT = dVT;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public int getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(int maLoai) {
		this.maLoai = maLoai;
	}
	public float ThanhTien()
	{
		
		return donGia*soLuong;
		
	}
	
}
