package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
  public Dao_Purchase() {

}
	//장바구니 목록  삭제
	public void Dao_PurchaseDelete(String userId, ArrayList<String> productIds, ArrayList<Integer> quantities) {				//구매했을 경우의 코드
		Connection conn = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null; 
		
		//구매한 물건을 purchaseTable 에 추가하는 SQL 문 
//		String query = "INSERT INTO purchase (\n"
//				+ "  c_id,\n"
//				+ "  pro_id,\n"
//				+ "  quantity,\n"
//				+ "  size,\n"
//				+ "  color,\n"
//				+ "  date\n"
//				+ ")\n"
//				+ "SELECT\n"
//				+ "  DISTINCT c.id,\n"
//				+ "  pro.Seq,\n"
//				+ "  ca.quantity,\n"
//				+ "  ca.size,\n"
//				+ "  ca.color,\n"
//				+ "  date_format(curdate(), '%y-%m-%d')\n"
//				+ "FROM cart ca\n"
//				+ "JOIN customer c ON ca.c_id = c.id\n"
//				+ "JOIN product pro ON ca.pro_id = pro.Seq\n"
//				+ "JOIN storage s ON pro.Seq = s.p_id;";
				
				
				
		// 선택한 제품들을 장바구니에서 삭제하기 위한 SQl 문
		String deleteSql = "DELETE FROM cart WHERE user_id = ? AND pro_id = ?";
				
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

            pstmtDelete = conn.prepareStatement(deleteSql);

            
            		
            // 선택한 제품들을  장바구니에서 삭제
            for (int i = 0; i < productIds.size(); i++) {
                pstmtDelete.setString(1, userId);
                pstmtDelete.setString(2, productIds.get(i));															//이게 맞는지 모르갰내요.
                pstmtDelete.executeUpdate();
            }

            
            
            
            
//            																						// 구매 내역을 추가하는 SQL 문
//            String insertSql = "INSERT INTO purchase (user_id, pro_id, quantity) VALUES (?, ?, ?)";
//            pstmtInsert = conn.prepareStatement(insertSql);
//
//            																// 선택한 제품들을 내역에 추가하는 SQL 문
//            for (int i = 0; i < productIds.size(); i++) {
//                pstmtInsert.setString(1, userId);
//                pstmtInsert.setString(2, productIds.get(i));
//                pstmtInsert.setInt(3, quantities.get(i));
//                pstmtInsert.executeUpdate();
//            }
//
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtDelete != null) pstmtDelete.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        }
      
	}


	// 장바구니 목록을 구매해서 purchaseTable 로 보내기
	public void purchaseon(String c_id, String pro_id, int quantity, int size, String color, int date) {
		
		String query = "INSERT INTO purchase (\n"
		+ "  c_id,\n"
		+ "  pro_id,\n"
		+ "  quantity,\n"
		+ "  size,\n"
		+ "  color,\n"
		+ "  date\n"
		+ ")\n"
		+ "SELECT\n"
		+ "  DISTINCT c.id,\n"
		+ "  pro.Seq,\n"
		+ "  ca.quantity,\n"
		+ "  ca.size,\n"
		+ "  ca.color,\n"
		+ "  date_format(curdate(), '%y-%m-%d')\n"
		+ "FROM cart ca\n"
		+ "JOIN customer c ON ca.c_id = c.id\n"
		+ "JOIN product pro ON ca.pro_id = pro.Seq\n"
		+ "JOIN storage s ON pro.Seq = s.p_id;";
		
		PreparedStatement ps = null;			//검색할때 사용하는 문
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			Statement st = con_mysql.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			while ( rs.next()) {
			String id = rs.getString("c.id"); 
//			int Seq = rs.getInt();
			
			
			}
            ps = con_mysql.prepareStatement(query);
            
            ps.setString(1, c_id);
            ps.setString(2, pro_id);
            ps.setInt(3, quantity);
            ps.setInt(4, size);
            ps.setString(5, color);
            ps.setInt(6, date);
            
            ps.executeUpdate();
            
		
			
		   con_mysql.close();	
		}catch (Exception e) {
                e.printStackTrace();
		}
		
		
		
		
	}

	public Dao_Purchase( String pro_id, int sales_price, String color, int size, int quantity) {
		super();		
		this.pro_id = pro_id;
		this.sales_price = sales_price;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		
	}


	//	처음의 장바구니 화면
	          

	public ArrayList<Dto_Purchase> cartList(String userId) {
		ArrayList<Dto_Purchase> dtoList = new ArrayList<>(); 
						String A = "select c_id, id, pro_id, quantity from cart WHERE user_id = ?";
//				String A = "select pro_id, quantity, size, sales_price, color from purchase WHERE user_id = ?";
					
			       try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
			            PreparedStatement pstmt_mysql = conn_mysql.prepareStatement(A); //
			            pstmt_mysql.setString(1, userId);										//
			            ResultSet rs = pstmt_mysql.executeQuery();								//
			            
//			            Statement stmt_mysql = conn_mysql.createStatement();
//			            ResultSet rs = stmt_mysql.executeQuery(A);
			            while (rs.next()) {
			                String pro_id = rs.getString("pro_id");
			                int sales_price = rs.getInt("sales_price");											//아이디를 입력한 유저의 장바구니만 출력하고 싶은데 이렇게 하는게 맞나요?
			                String color = rs.getString("color");												//아니라면 어떻게 해야하는건가요.			                
			                int size = rs.getInt("size");  
			                int quantity = rs.getInt("quantity");
//			                int seq = rs.getInt("seq");
//			                String pname = rs.getString("pname");
//							int size = rs.getInt("size");
//			                int color = rs.getString
			                
			                
//			            	String c_id = rs.getString("c_id");
//			            	String id = rs.getString(id);
//			            	String pro_id = rs.getString(pro_id);
//			            	int quantity = rs.getInt(quantity);
			            	
			            	
			            	
			            	
			            	

			                Dto_Purchase dto_Purchase = new Dto_Purchase(pro_id, sales_price, color, size, quantity);
			                dtoList.add(dto_Purchase);
			            }

			            conn_mysql.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        return dtoList;
	
	}


    //장바구니 삭제
	  public void deleteSelectedItems(String userId, ArrayList<String> productIds) {
	        Connection conn = null;
	        PreparedStatement pstmtDelete = null;

	        try {
	            conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

	            // 선택한 제품들을 삭제하기 위한 SQL 문
	            String deleteSql = "DELETE FROM cart WHERE c_id = ? AND id = ? AND pro_id = ?";			//cart table 에 접속
	            pstmtDelete = conn.prepareStatement(deleteSql);

	            // 선택한 제품들을 삭제
	            for (String productId : productIds) {
	                pstmtDelete.setString(1, userId);
	                pstmtDelete.setString(2, productId);
//	                pstmtDelete.setString(3, pro_id);	                
	                pstmtDelete.executeUpdate();
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (pstmtDelete != null) pstmtDelete.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	
	  }
	  
	  //구매한 제품을 넣는 매써드
	  public void purchaseProducts(String userId, ArrayList<String> productIds, ArrayList<Integer> quantities) {
		    Connection conn = null;
			    PreparedStatement pstmtInsert = null;

		    try {
		        conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

		        													// 구매 내역을 추가하는 SQL 문
		        String insertSql = "INSERT INTO purchase (user_id, pro_id, quantity) VALUES (?, ?, ?)";
		        pstmtInsert = conn.prepareStatement(insertSql);

		        													// 선택한 제품들을 내역에 추가
		        for (int i = 0; i < productIds.size(); i++) {
		            pstmtInsert.setString(1, userId);
		            pstmtInsert.setString(2, productIds.get(i));
		            pstmtInsert.setInt(3, quantities.get(i));
		            pstmtInsert.executeUpdate();
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (pstmtInsert != null) pstmtInsert.close();
		            if (conn != null) conn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	  
	  
	  }
	  
	}
