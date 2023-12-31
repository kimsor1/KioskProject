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

	
	
	int pro_id;
	int sales_price;
	String color;
	int size;
	int quantity;
				
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pass_mysql = ShareVar.dbPass;
	
	
	
	
	public Dao_Purchase(int quantity, int size, String color) {
		this.color = color;
		this.size = size;
		this.quantity = quantity;
	}


	

	public Dao_Purchase( int pro_id, int sales_price, String color, int size, int quantity) {
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
//	public void Dao_PurchaseDelete( String productId, int quantityToRemove) {
//		
//		
//		//SQL 접속
//
//		// 선택한 제품들을 장바구니에서 삭제하기 위한 SQl 문
//        try {
//        	Class.forName("com.mysql.cj.jdbc.Driver");
//        	Connection con = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
//        	Statement st = con.createStatement();
//        	
//        	String query = "delete "
//        	
////             }
//            
//
//   
//        } catch (SQLException e) {
//            e.printStackTrace();
//        
//        }
//      
//	}
	// 장바구니를 삭제하고 수량을 업데이트 하는  매서드
	public void updateQuantity(int newQuantity) {
        Connection conn = null;
        PreparedStatement pstmtUpdate = null;

        try {
            conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

            // 수량을 업데이트하기 위한 SQL 문
            String updateSql = "UPDATE cart SET quantity = ? WHERE id = " + lookingforCartId();
            pstmtUpdate = conn.prepareStatement(updateSql);

            // 수량을 업데이트
            pstmtUpdate.setInt(1, newQuantity);
            pstmtUpdate.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// 카트에 있는 product id 찾기
	public int lookingforProId() {
		int i = 0;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            
            String query = "select pro_id from cart where c_id = '" + ShareVar.id + "' and quantity = " + quantity + " and size = " + size + " and color = '" + color + "'";
            ResultSet rs = stmt_mysql.executeQuery(query);								
            if (rs.next()) {
                i = rs.getInt(1);
                
            }
            conn_mysql.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return i;
	}
	
	// 카트 id 찾기
	public int lookingforCartId() {
		int i = 0;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            
            String query = "select id from cart where c_id = '" + ShareVar.id + "' and pro_id = " + lookingforProId() + " and quantity = " + quantity + " and size = " + size + " and color = '" + color + "'";
            ResultSet rs = stmt_mysql.executeQuery(query);								
            if (rs.next()) {
                i = rs.getInt(1);
                
            }
            conn_mysql.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return i;
	}


	// 장바구니 목록을 구매해서 purchaseTable 로 보내기
	public void purchaseon(String proName) {
		
		PreparedStatement pstmtPurchase = null;
//		PreparedStatement pstmtDelete = null;
		
		// 검색 후 검색한 결과를 insert 하기 위한 분리 쿼
//		String searchQuery = "SELECT DISTINCT c.id, pro.Seq, ca.quantity, ca.size, ca.color, date_format(curdate(), '%y-%m-%d')"
//				+ "FROM cart ca " + "JOIN customer c ON ca.c_id = c.id " + "JOIN product pro ON ca.pro_id = pro.Seq "
//				+ "JOIN storage s ON pro.Seq = s.p_id " + "where ca.id = " + cartId;
//
//		String insertQuery = "INSERT INTO purchase (c_id, pro_id, quantity, size, color, date) values (?, ?, ?, ?, ?, date_format(curdate(), '%y-%m-%d')";

	    String purchaseQuery = "INSERT INTO purchase (c_id, pro_id, quantity, size, color, date)" +
	            "SELECT DISTINCT ?, ?, ?, ?, ?, date_format(curdate(), '%y-%m-%d')" +
	            "FROM cart ca " +
	            "JOIN customer c ON ca.c_id = c.id " +
	            "JOIN product pro ON ca.pro_id = pro.Seq " +
	            "JOIN storage s ON pro.Seq = s.p_id " +
	            "where ca.c_id = '" + ShareVar.id + "' and pro.name = '" + proName + "' and ca.size = " + size + " and ca.color = '" + color + "' and ca.quantity = '" + quantity + "'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

			pstmtPurchase = con.prepareStatement(purchaseQuery);
			pstmtPurchase.setString(1, ShareVar.id); 
			pstmtPurchase.setInt(2, lookingforProId());
			pstmtPurchase.setInt(3, quantity);
			pstmtPurchase.setInt(4, size);
			pstmtPurchase.setString(5, color);
			pstmtPurchase.executeUpdate();

			
			con.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// 구매 확정 후 테이블의 열 삭제
	public void deleteAction() {
		PreparedStatement ps = null;
		String query = "DELETE FROM cart where id = " + lookingforCartId();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			
			ps = con.prepareStatement(query);
			
			ps.executeUpdate();
			
			con.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
					//구매한 물건의 정보를 저장하는 매소드
		public ArrayList<Dto_Purchase> getPurchaseInformation(String userId) {
		    ArrayList<Dto_Purchase> purchaseList = new ArrayList<>();

		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

		        String query = "SELECT pro.name, c.quantity, pro.price FROM cart c, product pro where c.pro_id = pro.seq and c.c_id = '" + ShareVar.id + "' and c.quantity = " + quantity + " and c.size = " + size +  " and c.color = '" + color + "'";
//		        pstmt = conn.prepareStatement(query);
//		        pstmt.setString(1, userId);

		        Statement st = conn.createStatement();
		        
		        rs = st.executeQuery(query); 

		        if (rs.next()) {
		            String name = rs.getString(1);
		            int quantity = rs.getInt(2);
		            int salesPrice = rs.getInt(3);
 
		      //*******************************
		            Dto_Purchase purchase = new Dto_Purchase(name, quantity, salesPrice);					// 구매한물건에 대한 정보를 출력해야하는대 안돼내요 .
		       //********************************
		   
	            purchaseList.add(purchase);		 
	            }
		        
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
//		    } finally {
//		        try {
//		            if (rs != null) rs.close();
//		            if (pstmt != null) pstmt.close();
//		            if (conn != null) conn.close();
//		        } catch (SQLException e) {
//		            e.printStackTrace();
//		        }
//		    }

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