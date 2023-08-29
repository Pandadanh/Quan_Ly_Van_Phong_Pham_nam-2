package DTO;

public class ImportDetail {
	private int maChiTietPhieuNhap;
	private int maNhapHang;
	private int maSanPham;
	private int soLuong;
	private float thanhTien;
	
	public ImportDetail()
	{
		
	}
	public ImportDetail(int maChiTietPhieuNhap, int maNhapHang, int maSanPham, int soLuong, float thanhTien) 
	{
		this.maChiTietPhieuNhap = maChiTietPhieuNhap;
		this.maNhapHang = maNhapHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		// TODO Auto-generated constructor stub
	}
	
	public int getMaChiTietPhieuNhap() {
		return maChiTietPhieuNhap;
	}
	public void setMaChiTietPhieuNhap(int maChiTietPhieuNhap) {
		this.maChiTietPhieuNhap = maChiTietPhieuNhap;
	}
	public int getMaPhieuNhap() {
		return maNhapHang;
	}
	public void setMaPhieuNhap(int maPhieuNhap) {
		this.maNhapHang = maPhieuNhap;
	}
	public int getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
}
