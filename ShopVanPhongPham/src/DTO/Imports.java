package DTO;

import java.sql.Date;
import java.sql.Time;

public class Imports {
	private int maNhapHang;
	private int maNhaCungCap;

	private int maNhanVien;
	private Date ngayNhap;
	private Time ngaynhap;
	private float tongTien;
	
	public Imports()
	{
		
	}
	public Imports(int maNhapHang, Date ngayNhap, Time ngaynhap,int maNhaCungCap, int maNhanVien, float tongTien) {
		// TODO Auto-generated constructor stub
		this.maNhaCungCap = maNhaCungCap;
		this.ngayNhap = ngayNhap;
		this.ngaynhap = ngaynhap;
		this.maNhanVien = maNhanVien;
		this.maNhapHang = maNhapHang;
		this.tongTien = tongTien;
	}
	public int getMaPhieuNhap() {
		return maNhapHang;
	}
	public void setMaPhieuNhap(int maNhapHang) {
		this.maNhapHang = maNhapHang;
	}
	public int getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(int maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	
	public Time getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(Time ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
	
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	
}
