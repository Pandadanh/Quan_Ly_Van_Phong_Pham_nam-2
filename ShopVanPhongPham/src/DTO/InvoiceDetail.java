package DTO;

public class InvoiceDetail {
	private int maChiTietHoaDon;
	private int maHoaDon; 
	private int maSanPham;
	private int soLuong;
	private float thanhTien;
	
	public InvoiceDetail()
	{
		
	}
	
	public InvoiceDetail(int maChiTietHoaDon, int maHoaDon, int maSanPham, int soLuong, float thanhTien) 
	{
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		// TODO Auto-generated constructor stub
	}
	public int getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}
	public void setMaChiTietHoaDon(int maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
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
