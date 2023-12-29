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
	public void Dao_PurchaseDelete(String userId, ArrayList<String> productIds, ArrayList<Integer> quantities) {				
		Connection conn = null;
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtInsert = null; 
			
				
		// 선택한 제품들을 장바구니에서 삭제하기 위한 SQl 문
		String deleteSql = "DELETE FROM cart WHERE user_id = ? AND pro_id = ?";
				
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);
            pstmtDelete = conn.prepareStatement(deleteSql);

            
            		
            // 선택한 제품들을  장바구니에서 삭제
            for (int i = 0; i < productIds.size(); i++) {
                pstmtDelete.setString(1, userId);
                pstmtDelete.setString(2, productIds.get(i));														
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
  public void purchasecartdelete() {
	  
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
				rs.getString("DISTINCT c.id"); 
		        rs.getInt("pro.Seq");
				rs.getInt("ca.quantity");
				rs.getInt("ca.size");
				rs.getString("ca.color");
				rs.getString("date_format(curdate(), '%y-%m-%d')");
				
				
			
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
			             
			            	int id = rs.getInt(1);
			                String name = rs.getString(2);											
			                int size = rs.getInt(3);  
			                String color = rs.getString(4);														                
			                int price = rs.getInt(5);
			                int quantity = rs.getInt(6);
			                			            	
			                Dto_Purchase dto_Purchase = new Dto_Purchase(id, name, size, color, price, quantity);
			                dtoList.add(dto_Purchase);
			            }

			            conn_mysql.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        return dtoList;
	
	}


    //장바구니 삭제
	//  public void deleteSelectedItems(String userId, ArrayList<String> productIds) {
	 public void deleteSelectedItems(String id) {
	        Connection conn = null;
	        PreparedStatement pstmtDelete = null;
	      
				
				
				
			
	        try {
	            conn = DriverManager.getConnection(url_mysql, id_mysql, pass_mysql);

	            // 선택한 제품들을 삭제하기 위한 SQL 문
	            String deleteSql = "DELETE FROM cart WHERE id = ?";			//cart table 에 접속
	            pstmtDelete = conn.prepareStatement(deleteSql);

	            // 선택한 제품들을 삭제
	            
	                pstmtDelete.setString(1, id);   
	                pstmtDelete.executeUpdate();
	            
	                		
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