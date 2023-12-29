package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TabStop;

import com.javalec.function.Dao_Product;
import com.javalec.function.Dao_Purchase;
import com.javalec.function.Dto_Product;
import com.javalec.function.Dto_Purchase;
import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
import com.mysql.cj.xdevapi.Statement;

import javax.swing.JScrollPane;
//import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Purchase extends JDialog {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JButton btnback;
	private JButton btnbuy;
	private JButton btndelete;

	  	static boolean showMessageCount = true;
	private final DefaultTableModel outer_Table = new DefaultTableModel();				//테이블 설정

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase dialog = new Purchase();
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
	public Purchase() {
		getContentPane().setBackground(new Color(255, 255, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			 tableInit();
				
			}
		});
		setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 27));
		setTitle("장바구니 화면");
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnback());
		getContentPane().add(getBtnbuy());
		getContentPane().add(getBtndelete());
		getContentPane().add(getLblNewLabel());

	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(66, 198, 389, 211);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable(outer_Table);
			innerTable.setModel(outer_Table);		
		//int tablecount = innerTable.getSelectedRow();
			innerTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			innerTable.addMouseListener(new MouseAdapter() {
				
			});
		}
		return innerTable;
	}
	private JButton getBtnback() {
		if (btnback == null) {
			btnback = new JButton("뒤로가기");
			btnback.setBackground(new Color(255, 255, 255));
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
									backAction();
				}
			});
			btnback.setBounds(66, 505, 117, 29);
		}
		return btnback;
	}
	private JButton getBtnbuy() {
		if (btnbuy == null) {
			btnbuy = new JButton("구매확정");
			btnbuy.setBackground(Color.WHITE);
			btnbuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
								buyAction();
				}
			});
			btnbuy.setBounds(338, 505, 117, 29);
		}
		return btnbuy;
	}
	private JButton getBtndelete() {
		if (btndelete == null) {
			btndelete = new JButton("장바구니 제거");
			btndelete.setForeground(new Color(0, 0, 0));
			btndelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
								cartdelete();
				}
			});
			btndelete.setBounds(66, 421, 95, 29);
		}
		return btndelete;
	}
	
	
	//--------------Function------------
	
	

	
	//장바구니 삭제 기능
	public void cartdelete() {
//		int selectedRow = innerTable.getSelectedRow();					//장바구니 삭제
//		if (selectedRow != -1) {
//			outer_Table.removeRow(selectedRow);						//해당 열 삭제
//}			
//		int[] selectedRows = innerTable.getSelectedRows();
//	    int rowCount = selectedRows.length;
//	    if (rowCount > 0) {
//	        ArrayList<String> productIds = new ArrayList<>();
//	        for (int selectedRow : selectedRows) {
//	            String productId = (String) outer_Table.getValueAt(selectedRow, 0);
//	            productIds.add(productId);
//	            
//	        }
			
	    	int tablecount = innerTable.getSelectedRowCount();
	    					
	    	
	    					
	        Dao_Purchase daoPurchase = new Dao_Purchase();
//  	        daoPurchase.deleteSelectedItems(); 
	        														// 삭제 후 테이블 새로고침
	        tableInit();
	    }
		

	
	    //     tablecount(i). 
		
	
		 

	
	
	
	
	//구매창 팝업
	public void buyAction() {

		
		
		 int selectedRowCount = innerTable.getSelectedRowCount();
		    if (selectedRowCount == 0) {
		        JOptionPane.showMessageDialog(null, "구매할 제품을 선택하세요.");								//선택 메시지
		        return;
		    }

		    int[] selectedRows = innerTable.getSelectedRows();

		    int totalPrice = 0;
		    ArrayList<String> productIds = new ArrayList<>();
		    ArrayList<Integer> quantities = new ArrayList<>();

		    StringBuilder message = new StringBuilder("구매한 제품 정보:\n");

		    for (int selectedRow : selectedRows) {
		        String proId = (String) outer_Table.getValueAt(selectedRow, 0);
		        int quantity = Integer.parseInt((String) outer_Table.getValueAt(selectedRow, 1));
		        int price = Integer.parseInt((String) outer_Table.getValueAt(selectedRow, 3));
		        
		        totalPrice += (quantity * price);
		        	
		        message.append("제품번호: ").append(proId).append(", 수량: ").append(quantity).append(", 가격: ").append(price).append("\n");

		        						 																		// 선택한 제품들의 정보를 리스트에 저장
		        productIds.add(proId);
		        quantities.add(quantity);
		    }

		    message.append("\n총 가격: ").append(totalPrice);

		    																								// 구매 내역을 데이터베이스에 반영
		    Dao_Purchase daoPurchase = new Dao_Purchase();
		    daoPurchase.purchaseProducts(userId, productIds, quantities);


		    JOptionPane.showMessageDialog(null, message.toString());


		    tableInit();		//목록 다시 불러오기
		
		
		    }

		
	

	//뒤로가기 버튼 활성화
	public void backAction() {
		dispose();												//장바구니 창 닫기
		Product pd = new Product();
		pd.setVisible(true);						//productDetail창 띄우기						

		

	}
	
	//innerTable 기능
	

	private String userId = "your_user_id";					//사용자 아이디 초기값
	private JLabel lblNewLabel;
	public void tableInit() {				//장바구니 Table
		
	
		
		outer_Table.addColumn("No");
		outer_Table.addColumn("제품명");
		outer_Table.addColumn("사이즈");
		outer_Table.addColumn("색상");
		outer_Table.addColumn("가격");
		outer_Table.addColumn("수량");
		outer_Table.setColumnCount(6); 
				//제품명
				int colNo =0;
				TableColumn col = innerTable.getColumnModel().getColumn(colNo);
				int width= 40;		
				col.setPreferredWidth(width);
				//제품명
				 colNo =0;
				   col = innerTable.getColumnModel().getColumn(colNo);
		         width= 150;
				col.setPreferredWidth(width);
				//사이즈
				 colNo =1;
				  col = innerTable.getColumnModel().getColumn(colNo);
			     width= 50;
				col.setPreferredWidth(width);
				//색상
				 colNo =2;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 30;
				col.setPreferredWidth(width);
				//가격
				 colNo =3;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 60;
				col.setPreferredWidth(width);
				//수량
				 colNo =4;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 30;
				col.setPreferredWidth(width);
				
				
				
				int i = outer_Table.getRowCount();
				for (int j = 0; j < i; j++) {
					outer_Table.removeRow(0);
			
			
				
					    
				Dao_Purchase dao = new Dao_Purchase();
				ArrayList<Dto_Purchase> dtoList = dao.cartList(); //유저 아이디를 받아서 그 유저장바구니만 출력
						   		
					    if (dtoList.isEmpty()  && showMessageCount){
					    	
					        JOptionPane.showMessageDialog(null, "장바구니가 비어 있습니다.");					//비어있을 때 메시지 출력
					        showMessageCount = false;
					        return; 															// 비어있다면 더 이상 진행하지 않고 종료
					    
					    
					    }
					    


					    for (int i = 0; i < dtoList.size(); i++ ) {
							String id = Integer.toString(dtoList.get(i).getPro_id());
							String size = Integer.toString(dtoList.get(i).getSize());
							String price = Integer.toString(dtoList.get(i).getSales_price());
							String quantity = Integer.toString(dtoList.get(i).getQuantity());
							String[] qTxt = {id, dtoList.get(i).getName(), size, dtoList.get(i).getColor(), price, quantity};
							
							outer_Table.addRow(qTxt);
						}
//			if (listCount > 0 ) {
//				for (int l = 0; l < listCount; l++) {								//     장바구니 목록을 불러오는 문장인대 제대로 나올지 모르갰내요
////					Dto_Purchase dto = dtoList.get(l);
//				String[] list = {dtoList.get(l).getPro_id(),Integer.toString(dtoList.get(l).getSales_price()), dtoList.get(l).getColor(), Integer.toString(dtoList.get(l).getSize()), Integer.toString(dtoList.get(l).getQuantity())};
//					outer_Table.addRow(list);
//					innerTable.setRowHeight(l , 50);
//					
//				}
						
				     
					
//				}
				
		
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("/Users/tj/Downloads/Group 36.png"));
			lblNewLabel.setBounds(0, 0, 512, 683);
		}
		return lblNewLabel;
	}
} //End