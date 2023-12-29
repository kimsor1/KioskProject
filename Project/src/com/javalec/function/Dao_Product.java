package com.javalec.function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Dao_Product {

	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pass_mysql = ShareVar.dbPass;

	int seqno;
	String name;
	int price;

	// Construct
	public Dao_Product() {

	}

	public Dao_Product(int seqno) {
		this.seqno = seqno;
	}

	public Dao_Product(int seqno, String name, int price) {
		this.seqno = seqno;
		this.name = name;
		this.price = price;
	}

	// Method

	// ArrayList를 사용하여 데이터 값 찾아 불러오기
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
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}

		return dtoList; // 불러온 데이터가 입력 된 dtoList 리턴
	}

	// 정렬한 데이터를 ArrayList를 사용하여 값 찾아 불러오기
	public ArrayList<Dto_Product> sort(int index) {
		ArrayList<Dto_Product> dtoList = new ArrayList<Dto_Product>(); // Dto_Product라는 타입을 가진 ArrayList 생성

		// 낮은가격순과 높은가격순은 다른 문장을 사용하기 때문에 분리
		String A = "select seq, name, price from product order by price asc";
		String B = "select seq, name, price from product order by price desc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			// 오름차순일때
			if (index == 1) {
				ResultSet rs = stmt_mysql.executeQuery(A); // asc구문 삽입

				while (rs.next()) {
					int wkSeq = rs.getInt(1);
					String wkName = rs.getString(2);
					int wkprice = rs.getInt(3);

					Dto_Product dto = new Dto_Product(wkSeq, wkName, wkprice);
					dtoList.add(dto);
				}
			}

			// 내림차순일때
			if (index == 2) {
				ResultSet rs = stmt_mysql.executeQuery(B); // desc 구문 삽입

				while (rs.next()) {
					int wkSeq = rs.getInt(1);
					String wkName = rs.getString(2);
					int wkprice = rs.getInt(3);

					Dto_Product dto = new Dto_Product(wkSeq, wkName, wkprice);
					dtoList.add(dto);
				}
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}

		return dtoList;
	}

	public ArrayList<Dto_Product> search(String name, int index) {
		ArrayList<Dto_Product> dtoList = new ArrayList<Dto_Product>(); // Dto_Product라는 타입을 가진 ArrayList 생성
		PreparedStatement ps = null;

		// SQL 구문
		String A = "select seq,name,price from product where name like '%" + name + "%'";
		String B = "select seq,name,price from product where name like '%" + name + "%' order by price asc";
		String C = "select seq,name,price from product where name like '%" + name + "%' order by price desc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			if (index == 0) {
				ResultSet rs = stmt_mysql.executeQuery(A); // asc구문 삽입

				while (rs.next()) {
					int wkSeq = rs.getInt(1);
					String wkName = rs.getString(2);
					int wkprice = rs.getInt(3);

					Dto_Product dto = new Dto_Product(wkSeq, wkName, wkprice);
					dtoList.add(dto);
				}
			}

			if (index == 1) {
				ResultSet rs = stmt_mysql.executeQuery(B); // asc구문 삽입

				while (rs.next()) {
					int wkSeq = rs.getInt(1);
					String wkName = rs.getString(2);
					int wkprice = rs.getInt(3);

					Dto_Product dto = new Dto_Product(wkSeq, wkName, wkprice);
					dtoList.add(dto);
				}
			}

			if (index == 2) {
				ResultSet rs = stmt_mysql.executeQuery(C); // asc구문 삽입

				while (rs.next()) {
					int wkSeq = rs.getInt(1);
					String wkName = rs.getString(2);
					int wkprice = rs.getInt(3);

					Dto_Product dto = new Dto_Product(wkSeq, wkName, wkprice);
					dtoList.add(dto);
				}
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}

		return dtoList; // 불러온 데이터가 입력 된 dtoList 리턴
	}

	// 클릭한 상품을 상세정보로 넘기기 위한 메소드
	public Dto_Product tableClick(int seqno) {

		Dto_Product dto = null;

		String A = "select p.seq, p.name, p.price, s.stock_quantity, s.stockimage from product as p, storage as s where p.seq = "
				+ seqno;
		String B = " and s.stocksize = 230 and s.stockcolor = 'white' and p.seq = s.p_id;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(A + B);

			while (rs.next()) {
				ShareVar.productSeq = rs.getInt(1);
				ShareVar.productName = rs.getString(2);
				ShareVar.productPrice = rs.getInt(3);
				ShareVar.productStock = rs.getInt(4);
				
				// file
				ShareVar.filename = ShareVar.filename + 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(5);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}

		return dto;
	}

	// 선택한 스펙에 따라 데이터를 불러오는 메소드
	public void searchStock(int size, String color) {

		Dto_Product dto = null;

		String A = "select p.name, p.price, s.stock_quantity, s.stockimage from product as p, storage as s where p.seq = "
				+ ShareVar.productSeq ;
		String B = " and s.stocksize = " + size;
		String C = " and s.stockcolor = '" + color + "'";
		String D = " and p.seq = s.p_id;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(A + B + C + D);

			while (rs.next()) {
				ShareVar.productName = rs.getString(1);
				ShareVar.productPrice = rs.getInt(2);
				ShareVar.productStock = rs.getInt(3);
				
				// file
				ShareVar.filename = ShareVar.filename + 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(4);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}


	}

}
