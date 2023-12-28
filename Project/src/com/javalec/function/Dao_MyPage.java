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

	public Dao_MyPage(String id, String name, String phone, String address, String pw) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.pw = pw;
	}
	
	//Method 
	
	// 검색 결과 보내기.
	public ArrayList<Dto_MyPage> selectList() {
		
		ArrayList<Dto_MyPage> dtomyList = new ArrayList<Dto_MyPage>();
		String whereDefault = "select id, name, phone, address, pw from customer";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			
			while(rs.next()) {
				String wkId = rs.getString(1); // whereDefault 에 지정된 문자열의 첫 번째 순서.
				String wkName = rs.getString(2);
				String wkPhone = rs.getString(3);
				String wkAddress = rs.getString(4);
				String wkPw = rs.getString(5);
			
				Dto_MyPage dto_mypage = new Dto_MyPage(wkId, wkName, wkPhone, wkAddress, wkPw);
				dtomyList.add(dto_mypage);
			
			}
			conn_mysql.close();
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return dtomyList;
				
	} 
	
	// 수정
	public boolean phoneUpdate() {
		
		PreparedStatement ps = null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = "update customer set phone = ?, address =?, pw =?";
			String B = " where seqno = ?";
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, pw);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
			
			
		}
		
		
		
	
		
	
	

}
