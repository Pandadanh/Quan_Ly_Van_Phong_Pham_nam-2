package DTO;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Staff implements List<Staff> {
	private int maNhanVien;
	private int maTaiKhoan;
	private String hoTen;
	private int sDT;
	private String email;
	private String diaChi;
	private int maLevel;
	private int trangThai;
	
	public Staff() {}
	public Staff(int maNhanVien, String hoTen, int sDT, String email, String diaChi, int maLevel,
			int trangthai, int maTaiKhoan) {
		super();
		this.maNhanVien = maNhanVien;
		this.maTaiKhoan = maTaiKhoan;
		this.hoTen = hoTen;
		this.sDT = sDT;
		this.email = email;
		this.diaChi = diaChi;
		this.maLevel = maLevel;
		this.trangThai = trangthai;
	}



	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public int getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(int maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
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
	public int getMaLevel() {
		return maLevel;
	}
	public void setMaLevel(int maLevel) {
		this.maLevel = maLevel;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<Staff> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(Staff e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends Staff> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends Staff> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Staff get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Staff set(int index, Staff element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void add(int index, Staff element) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Staff remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ListIterator<Staff> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<Staff> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Staff> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
