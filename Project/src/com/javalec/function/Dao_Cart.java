package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	public void checkStock() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String A = "select c.c_id,c.pro_id,sum(c.quantity) as quantity from cart as c group by c.c_id,c.pro_id";

			ps = conn_mysql.prepareStatement(A);
		
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
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
