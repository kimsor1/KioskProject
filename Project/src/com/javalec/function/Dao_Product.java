package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao_Product {
	
	// Field
		private final String url_mysql = ShareVar.dbName;
		private final String id_mysql = ShareVar.dbUser;
		private final String pass_mysql = ShareVar.dbPass;
		
		int seqno;
		String name;
		int price;
		
		public Dao_Product(){
			
		}
		
		public  Dao_Product(int seqno, String name, int price) {
			this.seqno = seqno;
			this.name = name;
			this.price = price;
		}
		
		public ArrayList<Dto_Product> selectList() {
			ArrayList<Dto_Product> dtoList = new ArrayList<Dto_Product>(); // Dto_Product라는 타입을 가진 ArrayList 생성

			String whereDefault = "select seq, name, price from product";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

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
				e.printStackTrace(); // 어디서 오류가 났는지 출력
			}

			return dtoList;
		}


}
