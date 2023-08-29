package DTO;

public class Level {
	public static final java.util.logging.Level SEVERE = null;
	private int maLevel;
	private String tenLevel;
	
	public Level() {
		
	}
	
	public Level(int maLevel, String tenLevel) {
		super();
		this.maLevel = maLevel;
		this.tenLevel = tenLevel;
	}
	public int getMaLevel() {
		return maLevel;
	}
	public void setMaLevel(int maLevel) {
		this.maLevel = maLevel;
	}
	public String getTenLevel() {
		return tenLevel;
	}
	public void setTenLevel(String tenLevel) {
		this.tenLevel = tenLevel;
	}
}
