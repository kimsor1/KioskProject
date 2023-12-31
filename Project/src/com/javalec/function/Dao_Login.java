package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class Dao_Login {
	
	/*
	 * Description : Shoeskiosk DAO of Login
	 * Author : Woody Jo
	 * Version : 1.0.0
	 * Date : 2023.12.31
	 */
	
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	private String id;
	private String name;
	private String phone;
	private String address;
	private String pw;
	private int activeDate;
	private int deactiveDate;
	
	
	// Constructor
	public Dao_Login() {
		// TODO Auto-generated constructor stub
	}
	
	public Dao_Login(String id) {
		this.id = id;
	}
	
	public Dao_Login(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public Dao_Login(String name, String phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public Dao_Login(String id, String name, String phone, String address) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Dao_Login(String id, String name, String phone, String address, String pw) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.pw = pw;
	}

	// Method
	// Login 버튼을 위함
	public boolean loginAction() {
		boolean boolFlag = false;
		String query = "select pw from customer where id = '" + id + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					boolFlag = true;
				}
			}
		}
		catch (Exception e) {
			boolFlag = false;
		}
		return boolFlag;
		
	}
	
	
	// ID 중복확인을 위함
	public boolean checkIdAction() {
		boolean boolFlag = true;
		String query = "select id from customer where id = '" + id + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				boolFlag = false;
			}
			conn.close();
			
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
		return boolFlag;
	}
	
	// ID 찾기
	public boolean checkFindIdAction() {
		boolean boolFlag = false;
		String query = "select id from customer where name = '" + name + "' and phone = '" + phone + "' and address = '" + address + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "아이디는 " + rs.getString(1) + "입니다");
				boolFlag = true;
			}
			else {
				JOptionPane.showMessageDialog(null, "등록된 정보의 아이디가 없습니다");
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return boolFlag;
	}
	
	// 비밀번호 찾기
	public boolean checkFindPwAction() {
		boolean boolFlag = false;
		String query = "select pw from customer where id = '" + id + "' and name = '" + name + "' and phone = '" + phone + "' and address = '" + address + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "패스워드는 " + rs.getString(1) + "입니다");
				boolFlag = true;
			}
			else {
				JOptionPane.showMessageDialog(null, "입력하신 정보가 틀렸습니다");
			}
			
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return boolFlag;
	}
	
	// 회원가입 완료
	public void completeRegister() {
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			
			String query = "insert into customer (id, name, phone, address, pw, activedate) values (?, ?, ?, ?, ?, date_format(curdate(), '%y-%m-%d'))";
			
			ps = conn.prepareStatement(query);
			
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, pw);
			
			ps.executeUpdate();
			
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
