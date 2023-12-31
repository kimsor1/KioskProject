package com.javalec.function;

public class Dto_MyPage {
	
	String id;
	String name;
	String phone;
	String address;
	String pw;
	
	public Dto_MyPage() {
		
	}

	public Dto_MyPage(String id, String name, String phone, String address, String pw) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.pw = pw;
	}
	
	// 생성자를 Dao Class 로 보내주기 위하여 Getter, Setter 사용한다.
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	
}
