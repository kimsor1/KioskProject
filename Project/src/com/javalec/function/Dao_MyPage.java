package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_MyPage {

	// Field

	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;

	String id;
	String name;
	String phone;
	String address;
	String pw;

	// FileInputStream file; ????? 무엇일까?

	// Constructor

	public Dao_MyPage() {

	}

	// Method

	public Dto_MyPage MyPage() {

		Dto_MyPage dto = null;

		String where1 = "select id, name, phone, address, pw from customer where id = '" + ShareVar.id + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where1);

			while (rs.next()) {
				String wkId = rs.getString(1);
				String wkName = rs.getString(2);
				String wkPhone = rs.getString(3);
				String wkAddress = rs.getString(4);
				String wkPw = rs.getString(5);

				dto = new Dto_MyPage(wkId, wkName, wkPhone, wkAddress, wkPw);
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}

		return dto;
	}

	public void delete() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "delete from customer where id = ?";

			ps = conn_mysql.prepareStatement(A);
			
			ps.setString(1, ShareVar.id);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void deletePurchase() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "delete from purchase where c_id = ?";

			ps = conn_mysql.prepareStatement(A);
			
			ps.setString(1, ShareVar.id);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void updatePhone(String phone) {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "update customer set phone = ? ";
			String B = "where id = '"+ShareVar.id+"'";

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, phone);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateAddress(String add) {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "update customer set address = ? ";
			String B = "where id = '"+ShareVar.id+"'";

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, add);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePw(String pw) {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "update customer set pw = ? ";
			String B = "where id = '"+ShareVar.id+"'";

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, pw);
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
