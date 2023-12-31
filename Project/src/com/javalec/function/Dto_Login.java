package com.javalec.function;

public class Dto_Login {
	
	/*
	 * Description : Shoeskiosk DTO of Login
	 * Author : Woody Jo
	 * Version : 1.0.0
	 * Date : 2023.12.31
	 */
	
	// Field
	
	private String id; 
	private String name;
	private String phone;
	private String address;
	private String pw;
	
	
	// Constructor
	public Dto_Login() {
		// TODO Auto-generated constructor stub
	}

	// Method
	public Dto_Login(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

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
