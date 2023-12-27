package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_Purchase {

	
	int seqno;
	String name;
	int price;
	int size;
				
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pass_mysql = ShareVar.dbPass;
	
	
	
	public Dao_Purchase() {
		
	}



	public Dao_Purchase(int seqno, String name, int price, int size) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.price = price;
		this.size = size;
	}
	
	
	
	
	public ArrayList<Dto_Product> selectList() {
		ArrayList<Dto_Product> dtoList = new ArrayList<Dto_Product>(); // Dto_Product라는 타입을 가진 ArrayList 생성

		// SQL 구문
		String whereDefault = "select seq, name, price from product";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			// 데이터 추가, 수정, 삭제가 아니기에 executeQuery문 사용
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				int wkSeq = rs.getInt(1); 
				String wkName = rs.getString(2);
				int wkprice = rs.getInt(3);

				Dto_Product dto = new Dto_Product(wkSeq, wkName, wkprice);
				dtoList.add(dto);
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); 						// 오류 출력
		}

		return dtoList;									 //입력 dtoList 리턴
	
	
	
	
	
	
}
