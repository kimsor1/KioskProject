package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	
	// Method
	public boolean loginAction() {
		boolean returnFlag = false;
		
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt = conn.createStatement();
			
			String query = "select id, pw from customer where id = '" + id + "' && pw = '" + pw + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(id);
				System.out.println(pw);
				if (rs.getString(1).equals(id) && rs.getString(2).equals(pw)) {
					System.out.println("get in");
					returnFlag = true;
					System.out.println(returnFlag);
				}
			}
			conn.close();
		}
		catch (Exception e) {
			System.out.println("get in catch");
			return returnFlag = false;
		}
		
		return returnFlag;
	}
	
}
