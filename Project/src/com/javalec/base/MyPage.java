package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import com.javalec.base.Product;
import com.javalec.function.*;
import com.mysql.cj.xdevapi.Statement;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;


public class MyPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblID;
	private JTextField tfID;
	private JLabel lblName;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfAddress;
	
	//******************************
	public final String url_mysql = ShareVar.dbName;
	public final String id_mysql = ShareVar.dbUser;
	public final String pw_mysql = ShareVar.dbPass;
	public final String inputID = ShareVar.id;
	
	// Table
		private final DefaultTableModel outer_Table = new DefaultTableModel();
		private JTextField tfPassword;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage dialog = new MyPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public MyPage() {
		addWindowListener(new WindowAdapter() {
			@Override  // input***************
			public void windowActivated(WindowEvent e) {
				 tableInit();
				 searchAction();
			}
		});
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("My Page");
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(null);
		getContentPane().add(getLblID());
		getContentPane().add(getTfID());
		getContentPane().add(getLblName());
		getContentPane().add(getTfName());
		
		JLabel lblPhone = new JLabel("전화번호 :");
		lblPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPhone.setForeground(new Color(254, 255, 255));
		lblPhone.setBounds(70, 360, 69, 16);
		getContentPane().add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(150, 350, 158, 41);
		getContentPane().add(tfPhone);
		
		JButton btnPhone = new JButton("수정");
		btnPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   phoneUpdate(); // input*************
			}
		});
		btnPhone.setBounds(332, 350, 69, 41);
		getContentPane().add(btnPhone);
		
		JLabel lblAddress = new JLabel("주 소 :");
		lblAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAddress.setForeground(new Color(254, 255, 255));
		lblAddress.setBounds(70, 430, 61, 16);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(130, 420, 262, 41);
		getContentPane().add(tfAddress);
		
		JButton btnAddress = new JButton("수정");
		btnAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAddress.setForeground(new Color(0, 0, 0));
		btnAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addressUpdate(); // input******************
			}
		});
		btnAddress.setBounds(408, 420, 69, 41);
		getContentPane().add(btnAddress);
		
		JLabel lblPassword = new JLabel("비밀번호 :");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPassword.setForeground(new Color(254, 255, 255));
		lblPassword.setBounds(70, 500, 77, 16);
		getContentPane().add(lblPassword);
		
		JButton btnPassword = new JButton("수정");
		btnPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordUpdate(); // input**************
			}
		});
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(150, 490, 150, 41);
		getContentPane().add(tfPassword);
		btnPassword.setBounds(332, 490, 69, 41);
		getContentPane().add(btnPassword);
		
		JButton btnOk = new JButton("돌아가기");
		btnOk.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returntoProduct(); // input********************
			}
		});
		btnOk.setBounds(130, 564, 117, 35);
		getContentPane().add(btnOk);
		
		JButton btnExit = new JButton("회원탈퇴");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitAction(); //input***********
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnExit.setBounds(280, 564, 117, 35);
		getContentPane().add(btnExit);
		
		JLabel lbBack = new JLabel("");
		lbBack.setVerticalAlignment(SwingConstants.TOP);
		lbBack.setHorizontalAlignment(SwingConstants.CENTER);
		lbBack.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		lbBack.setIcon(new ImageIcon(MyPage.class.getResource("/com/javalec/images/Group 38 (2).png")));
		lbBack.setBounds(0, -28, 512, 683);
		getContentPane().add(lbBack);

	}

	private JLabel getLblID() {
		if (lblID == null) {
			lblID = new JLabel("아이디 :");
			lblID.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblID.setForeground(new Color(254, 255, 255));
			lblID.setBounds(70, 230, 61, 16);
		}
		return lblID;
	}
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setEditable(false);
			tfID.setBounds(150, 220, 150, 41);
			tfID.setColumns(10);
		}
		return tfID;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("이름 :");
			lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblName.setForeground(new Color(254, 255, 255));
			lblName.setBounds(70, 290, 61, 16);
		}
		return lblName;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(150, 280, 150, 41);
		}
		return tfName;
	}

	//---------Functions--------------
	
	// Table 컬럼을 정의하고 data 내용을 초기화 한다.
	

	private void tableInit() {
	   outer_Table.addColumn("ID");
	   outer_Table.addColumn("이름");
	   outer_Table.addColumn("전화번호");
	   outer_Table.addColumn("주소");
	   outer_Table.addColumn("비밀번호");
	   outer_Table.setColumnCount(5);
			
	   int i = outer_Table.getRowCount();
	   for (int j = 0; j < i; j++) {
		   outer_Table.removeRow(0);
	   }
	}	
	
		
 //------------------------------------------------------	
	//테스트 위한 사용자 데이터 불러오기.
 	/*		
	private void searchAction() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

	        String sql = "SELECT * FROM shoeskiosk.customer WHERE id=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "ggg"); // 원하는 아이디를 설정

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            // 데이터베이스에서 가져온 정보를 UI에 설정
	            tfID.setText(rs.getString("id"));
	            tfName.setText(rs.getString("name"));
	            tfPhone.setText(rs.getString("phone"));
	            tfAddress.setText(rs.getString("address"));
	            tfPassword.setText(rs.getString("password"));
	        } 

	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	*/
	
	
	//------입력된 정보 불러오기---------------
		
		
	private void searchAction() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String userID = inputID; 

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);

	        String sql = "SELECT * FROM shoeskiosk.customer WHERE id='?'";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ShareVar.id); // 사용자로부터 입력받은 ID를 설정

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            // 데이터베이스에서 가져온 정보를 UI에 설정
	            tfID.setText(rs.getString("id"));
	            tfName.setText(rs.getString("name"));
	            tfPhone.setText(rs.getString("phone"));
	            tfAddress.setText(rs.getString("address"));
	            tfPassword.setText(rs.getString("pw"));
	        } else {
	            // 해당 ID에 대한 데이터가 없을 경우 처리
	            // 예: 메시지 출력 또는 필요한 작업 수행
	            System.out.println("해당 ID에 대한 데이터가 없습니다.");
	        }
	       conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	        
//	    } finally {
//	        try {
//	            if (rs != null) rs.close();
//	            if (pstmt != null) pstmt.close();
//	            if (conn != null) conn.close();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
	    }
	}

	
	
	
	
	
	// -------전화번호 수정하기 -----------------------------
		
	private void phoneUpdate() {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		   
		    // 정확하지 않은 전화번호 입력시 에러 표현
			Pattern pattern = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
			Matcher matcher = pattern.matcher(tfPhone.getText());

			if (!tfPhone.getText().equals("전화번호 입력")) {
				if (!matcher.matches()) {
					JOptionPane.showMessageDialog(null, "010-0000-0000 타입 형식의 번호를 입력하세요.");
					tfPhone.requestFocus();
					return; // 유효성 검사 실패시 메소드 종료.
			}
		}		    
		   String query = "update customer set phone=? where id=?";
						
			ps = conn.prepareStatement(query);
			ps.setString(1, tfPhone.getText().trim());
			ps.setString(2, tfID.getText().trim());
			
			int updatedRows = ps.executeUpdate();

	        if (updatedRows > 0) {
	            JOptionPane.showMessageDialog(null, "전화번호가 성공적으로 수정되었습니다.");
	        } else {
	            JOptionPane.showMessageDialog(null, "전화번호 수정에 실패했습니다.");
	        }
						
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	}// end of updateAction()
	
	//----- 주소 수정하기------------------- 
	private void addressUpdate() {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		    
		    // 주소 유효성 검사
		    
			String addressPattern ="^[가-힣a-zA-Z0-9\\s]+$";
		if (!tfAddress.getText().equals("주소 입력")) {
			// 정확하지 않은 주소 입력시 에러 표현// 
			
			if (!tfAddress.getText().matches(addressPattern)) {
					JOptionPane.showMessageDialog(null, "주소는 한글, 영문, 숫자, 공백만 입력하세요.");
					tfAddress.requestFocus();
					return; // 유효성 검사 실패시 메소드 종료.
			   } 
		}  	
		String query = "update customer set address=? where id=?";
		
		ps = conn.prepareStatement(query);
		ps.setString(1, tfAddress.getText().trim());
		ps.setString(2, tfID.getText().trim());
		
		int updatedRows = ps.executeUpdate();

        if (updatedRows > 0) {
            JOptionPane.showMessageDialog(null, "주소가 성공적으로 수정되었습니다.");
        } else {
            JOptionPane.showMessageDialog(null, "주소 수정에 실패했습니다.");
        }
  					
		
	}catch(Exception e) {
		e.printStackTrace();
	} finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        	}
		}

	}// end of addressUpdate()
  //----------비밀 번호 수정하기--------------
	
	private void passwordUpdate() {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
	     conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		    
		    // 비밀번호 유효성 검사.
		    
	     String passwordPattern ="^[가-힣a-zA-Z0-9\\s]+$";
	  		
		if (!tfPassword.getText().matches(passwordPattern)) {
		    JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력하세요.");
		    tfPassword.requestFocus();
		     return;
		    }
		
		String query = "update customer set pw = ? where id = ?";
						
		ps = conn.prepareStatement(query);
		ps.setString(1, tfPassword.getText().trim());
		ps.setString(2, tfID.getText().trim());
		
		int updatedRows = ps.executeUpdate();

        if (updatedRows > 0) {
            JOptionPane.showMessageDialog(null, "비밀번호가 성공적으로 수정되었습니다.");
        } else {
            JOptionPane.showMessageDialog(null, "비밀번호 수정에 실패했습니다.");
        }
  					
	  } catch (ClassNotFoundException e) {
         e.printStackTrace();
        // Handle ClassNotFoundException (e.g., log the error or show a user-friendly message)
     } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException (e.g., log the error or show a user-friendly message)
     } finally {
        try {
          if (ps != null) ps.close();
           if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
					
   }// end of passwordUpdate()
		
 //------------회원탈퇴-----------------------------
	
	private void exitAction() {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
		    
		   String query1 = "delete from customer where id = '?'";
		  		   
		   ps = conn.prepareStatement(query1);
		   ps.setString(1, ShareVar.id);
		   
		   		   
		   int updatedRows = ps.executeUpdate();

	        if (updatedRows > 0) {
	            JOptionPane.showMessageDialog(null, "탈퇴 하였습니다.");
	        } else {
	            JOptionPane.showMessageDialog(null, "탈퇴에 실패했습니다.");
	        }
		   		   
		   ps.executeUpdate();
		   conn.close();
		   
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Main window = new Main();
		window.main(null);
		
		this.setVisible(false);
			
			
	}	
  
	// end of exitActiion()
		
		
//-------------------------------------	
	
	
	//  확인 click 시 메일 환면으로 이동.
	/*
	private void returntoMain() {
		Main window = new Main();
		window.main(null);
		
		this.setVisible(false);
		
		}
	*/
	
	// 확인 click 시 물건 목록으로 이동
		private void returntoProduct() {
			Product p = new Product();
			p.setVisible(true);
			this.setVisible(false);
		}
 }	//-----end------------
