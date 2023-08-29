package DTO;

public class Account {
	private int maTaiKhoan;
	private int sDT;
	private String passWord;
	private int level;
	
	public Account()
	{

		
	}
	

	public Account(int maTaiKhoan,int sDT, String passWord,int level)
	{
this.maTaiKhoan=maTaiKhoan;
		this.sDT = sDT;
		this.passWord = passWord;
		this.level = level;
		
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaTaiKhoan() {
		return maTaiKhoan;
	}
	public void setMaTaiKhoan(int maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}
	public int getsDT() {
		return sDT;
	}
	public void setsDT(int sDT) {
		this.sDT = sDT;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
