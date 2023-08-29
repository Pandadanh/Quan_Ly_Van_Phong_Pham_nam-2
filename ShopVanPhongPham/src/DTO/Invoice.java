package DTO;

import java.sql.Date;
import java.sql.Time;

public class Invoice {
	private int maHoaDon;
	private int maNhanVien;
	private int maKhachHang;
	private Time ngayBan;
	private Date ngayban;
	private float tongTien;
	
	public Invoice() {}
	
	
	public Invoice(int maHoaDon,Date ngayban, Time ngayBan, int maNhanVien, int maKhachHang, float tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayban = ngayban;
		this.ngayBan = ngayBan;
		this.tongTien = tongTien;
	}


	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public int getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public Time getNgayBan() {
		return ngayBan;
	}
	public void setNgayBan(Time ngayBan) {
		this.ngayBan = ngayBan;
	}
	public Date getNgayban() {
		return ngayban;
	}
	public void setNgayban(Date ngayban) {
		this.ngayban = ngayban;
	}
	
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
}