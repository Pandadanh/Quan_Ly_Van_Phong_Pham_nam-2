package DTO;

public class role {
private int id_per_detail;
private int ma_level;
private int id_fuction;
private String action_code;
private int check_action;

public role() {
	
}

public role(int id_per_detail, int ma_level, int id_fuction, String action_code, int check_action) {
	super();
	this.id_per_detail = id_per_detail;
	this.ma_level = ma_level;
	this.id_fuction = id_fuction;
	this.action_code = action_code;
	this.check_action = check_action;
}

public int getId_per_detail() {
	return id_per_detail;
}

public void setId_per_detail(int id_per_detail) {
	this.id_per_detail = id_per_detail;
}

public int getMa_level() {
	return ma_level;
}

public void setMa_level(int ma_level) {
	this.ma_level = ma_level;
}

public int getId_fuction() {
	return id_fuction;
}

public void setId_fuction(int id_fuction) {
	this.id_fuction = id_fuction;
}

public String getAction_code() {
	return action_code;
}

public void setAction_code(String action_code) {
	this.action_code = action_code;
}

public int getCheck_action() {
	return check_action;
}

public void setCheck_action(int check_action) {
	this.check_action = check_action;
}


}
