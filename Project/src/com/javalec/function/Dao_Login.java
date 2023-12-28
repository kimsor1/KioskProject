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
	
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	private String id;
	private String name;
	private String phone;
	private String address;
	private String pw;
	
	
	// Constructor
	public Dao_Login() {
		// TODO Auto-generated constructor stub
	}
	
	public Dao_Login(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public Dao_Login(String id) {
		this.id = id;
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
		boolean boolFlag = false;
		String query = "select id from customer where id = '" + id + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
					boolFlag = true;
			}
			conn.close();
			
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
		return boolFlag;
	}
	
	
}
