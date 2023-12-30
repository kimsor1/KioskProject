package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.function.Dto_Purchase;
import com.javalec.function.ShareVar;
import com.mysql.cj.protocol.Resultset;

public class Dao_Purchase {

	
	
	String pro_id;
	int sales_price;
	String color;
	int size;
	int quantity;
				
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pass_mysql = ShareVar.dbPass;
	
	
	
	public Dao_Purchase( String pro_id, int sales_price, String color, int size, int quantity) {
		super();		
		this.pro_id = pro_id;
		this.sales_price = sales_price;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		
	}
	
	
	
  public Dao_Purchase() {

}
	//장바구니 목록  삭제
	public void Dao_PurchaseDelete( String productId, int quantityToRemove) {				
		Connection conn = null;
		PreparedStatement pstmtDelete = null;
		//SQL 접속

		// 선택한 제품들을 장바구니에서 삭제하기 위한 SQl 문
        try {
       // 	Class.forName("com.mysql.cj.jdbc.Driver");
        	String deleteSql = "DELETE FROM cart WHERE id = ? AND quantity = ? ";
        	conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
        	pstmtDelete = conn.prepareStatement(deleteSql);
        	// 선택한 제품들을  장바구니에서 삭제
        	
        	 pstmtDelete.setString(1, productId);
             pstmtDelete.setInt(2, quantityToRemove);
                 pstmtDelete.executeUpdate();
//             }
            

   
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtDelete != null) pstmtDelete.close();
                if (conn != null) conn.close();
            } catch ( SQLException e) {
            	e.printStackTrace();
                 
            }
        
        }
      
	}
	// 장바구니를 삭제하고 수량을 업데이트 하는  매서드
	public void updateQuantity(String proId, int newQuantity) {
        Connection conn = null;
        PreparedStatement pstmtUpdate = null;

        try {
            conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

            // 수량을 업데이트하기 위한 SQL 문
            String updateSql = "UPDATE cart SET quantity = ? WHERE id = ?";
            pstmtUpdate = conn.prepareStatement(updateSql);

            // 수량을 업데이트
            pstmtUpdate.setInt(1, newQuantity);
            pstmtUpdate.setString(2, proId);
            pstmtUpdate.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtUpdate != null) pstmtUpdate.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	


	// 장바구니 목록을 구매해서 purchaseTable 로 보내기
	public void purchaseon(String cartId) {

		PreparedStatement pstmtPurchase = null;
//		PreparedStatement pstmtDelete = null;

		// 검색 후 검색한 결과를 insert 하기 위한 분리 쿼
		String searchQuery = "SELECT DISTINCT c.id, pro.Seq, ca.quantity, ca.size, ca.color, date_format(curdate(), '%y-%m-%d')"
				+ "FROM cart ca " + "JOIN customer c ON ca.c_id = c.id " + "JOIN product pro ON ca.pro_id = pro.Seq "
				+ "JOIN storage s ON pro.Seq = s.p_id " + "where ca.id = " + cartId;

		String insertQuery = "INSERT INTO purchase (c_id, pro_id, quantity, size, color, date) values (?, ?, ?, ?, ?, date_format(curdate(), '%y-%m-%d')";

//			    String purchaseQuery = "INSERT INTO purchase (c_id, pro_id, quantity, size, color, date)" +
//			            "SELECT DISTINCT c.id, pro.Seq, ca.quantity, ca.size, ca.color, date_format(curdate(), '%y-%m-%d')" +
//			            "FROM cart ca " +
//			            "JOIN customer c ON ca.c_id = c.id " +
//			            "JOIN product pro ON ca.pro_id = pro.Seq " +
//			            "JOIN storage s ON pro.Seq = s.p_id " +
//			            "where ca.id = " + cartId;

		String clearCartQuery = "DELETE FROM cart WHERE id = " + cartId;

		int c_id = 0;
		int pro_id = 0;
		int quantity = 0;
		int size = 0;
		String color = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(searchQuery);

			if (rs.next()) {
				c_id = rs.getInt(1);
				pro_id = rs.getInt(2);
				quantity = rs.getInt(3);
				size = rs.getInt(4);
				color = rs.getString(5);
			}

			pstmtPurchase = con.prepareStatement(insertQuery);
			pstmtPurchase = con.prepareStatement(clearCartQuery);

			pstmtPurchase.setInt(1, c_id);
			pstmtPurchase.setInt(2, pro_id);
			pstmtPurchase.setInt(3, quantity);
			pstmtPurchase.setInt(4, size);
			pstmtPurchase.setString(5, color);

			pstmtPurchase.executeUpdate();
//			pstmtDelete.executeUpdate();

			con.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

//			        // purchase 테이블에 데이터 추가
//			        pstmtPurchase = conn.prepareStatement(purchaseQuery);
//			        pstmtPurchase.executeUpdate();
//
//			        // cart 테이블 비우기
//			        pstmtClearCart = conn.prepareStatement(clearCartQuery);
//			        pstmtClearCart.setString(1, c_id);
//			        pstmtClearCart.executeUpdate();

	}
					//구매한 물건의 정보를 저장하는 매소드
		public ArrayList<Dto_Purchase> getPurchaseInformation(String userId) {
		    ArrayList<Dto_Purchase> purchaseList = new ArrayList<>();

		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

		        String query = "SELECT p.name, pu.quantity, pu.sales_price FROM purchase pu JOIN product p ON pu.pro_id = p.seq WHERE pu.c_id = ?";
		        pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, userId);

		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            String name = rs.getString("name");
		            int quantity = rs.getInt("quantity");
		            int salesPrice = rs.getInt("sales_price");
 
		      //*******************************
		            Dto_Purchase purchase = new Dto_Purchase(name, quantity, salesPrice);					// 구매한물건에 대한 정보를 출력해야하는대 안돼내요 .
		       //********************************
		   
	            purchaseList.add(purchase);		 }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return purchaseList;
		}

	// 장바구니 화면
	          

	public ArrayList<Dto_Purchase> cartList() {
		ArrayList<Dto_Purchase> dtoList = new ArrayList<>(); 
						String A = "select distinct(c.id), p.name, c.size, c.color,  p.price, c.quantity FROM cart c, product p, customer cu where c.pro_id = p.seq and c.c_id = '" +  ShareVar.id +"' order by c.id asc";
								
			       try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			            Statement stmt_mysql = conn_mysql.createStatement();
			            ResultSet rs = stmt_mysql.executeQuery(A);								
			            	
			            while (rs.next()) {
			             
			                String name = rs.getString(2);											
			                int size = rs.getInt(3);  
			                String color = rs.getString(4);														                
			                int price = rs.getInt(5);
			                int quantity = rs.getInt(6);
			                			            	
			                Dto_Purchase dto_Purchase = new Dto_Purchase(name, size, color, price, quantity);
			                dtoList.add(dto_Purchase);
			            }

			            conn_mysql.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        return dtoList;
	
	}



	  
	}