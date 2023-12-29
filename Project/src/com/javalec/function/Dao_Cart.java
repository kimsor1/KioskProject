package com.javalec.function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Dao_Cart {

	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pass_mysql = ShareVar.dbPass;

	String c_id;
	int p_id;
	int quantity;
	int size;
	String color;

	public Dao_Cart() {

	}

	public Dao_Cart(String cId, int pId, int pSize, String pColor, int opp) {
		this.c_id = cId;
		this.p_id = pId;
		this.size = pSize;
		this.color = pColor;
		this.quantity = opp;
	}

	public void searchStock() {

		Dto_Product dto = null;

		String A = "select sum(c.quantity) as quantity ";
		String B = "from cart as c ";
		String C = "where c.color = '" + color + "' and c.size = " + size + " and c.pro_id = '" + ShareVar.id + "' ";
		String D = "group by c.c_id, c.pro_id, c.size, c.color";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			System.out.println("1");
			
			ResultSet rs = stmt_mysql.executeQuery(A+B+C+D);
			
			System.out.println("2");
			while (rs.next()) {
				ShareVar.CartQuantity = rs.getInt(1);
				System.out.println("3");
			}
			
			JOptionPane.showMessageDialog(null, ShareVar.CartQuantity+"입니다");
			
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 어디서 오류가 났는지 출력
		}
	}

	public void addCart() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "insert into cart(c_id, pro_id, quantity, size, color) ";
			String B = "values(?, ?, ?, ?, ?)";

			ps = conn_mysql.prepareStatement(A + B);
			ps.setString(1, c_id);
			ps.setInt(2, p_id);
			ps.setInt(3, quantity);
			ps.setInt(4, size);
			ps.setString(5, color);
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null,
					"장바구니에 " + ShareVar.productName + " ( " + color + " ," + size + " )가 " + quantity + "개 담겼습니다.");

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
