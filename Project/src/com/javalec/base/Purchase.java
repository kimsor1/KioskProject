package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TabStop;

import com.javalec.function.Dao_Product;
import com.javalec.function.Dao_Purchase;
import com.javalec.function.Dto_Product;
import com.javalec.function.Dto_Purchase;
import com.javalec.function.ShareVar;
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
			 searchAction();
				
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
		getContentPane().add(getLbBackImage());

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
			innerTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			innerTable.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					innerTable.setDefaultEditor(Object.class, null);
				}
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
		
		int selectedRow = innerTable.getSelectedRow();
		int currentQuantity = Integer.parseInt((String) outer_Table.getValueAt(selectedRow, 5));
		int size = Integer.parseInt((String) outer_Table.getValueAt(selectedRow, 2));
		String color = (String) outer_Table.getValueAt(selectedRow, 3);
		
		// updateQuantity 메서드와 deleteAction 사용을 위한
		// cartId를 가져와 update, update하기 위해 파라미터를 입력 받는다.
		Dao_Purchase daoPurchase = new Dao_Purchase(currentQuantity, size, color);
		
		if (selectedRow != -1) {
//	        String productId = (String) outer_Table.getValueAt(selectedRow, 0);

			String inputQuantity = JOptionPane.showInputDialog(null, "제거할 수량을 입력하세요 : ", "수량 입력",
					JOptionPane.QUESTION_MESSAGE);
//	        if (inputQuantity != null && !inputQuantity.isEmpty()) {
			int reduceQuantity = Integer.parseInt(inputQuantity);
			if (reduceQuantity <= currentQuantity && reduceQuantity > 0) {
//        	if (inputQuantity.length() > 0) {
//	            if (reduceQuantity > 0 && reduceQuantity <= currentQuantity) {
				int newQuantity = currentQuantity - reduceQuantity;
				
				// 참일 경우 삭
				if (newQuantity == 0) {
					// 새로운 수량이 0이면 제품을 카트에서 완전히 제거
					daoPurchase.deleteAction();
					JOptionPane.showMessageDialog(null, "물품을 장바구니에서 삭제 하였습니다", "완료", JOptionPane.ERROR_MESSAGE);
				}
				// 그렇지 않다면 업데이트
				else {
					daoPurchase.updateQuantity(newQuantity);
					JOptionPane.showMessageDialog(null, "수량 변경이 완료 되었습니다", "완료", JOptionPane.ERROR_MESSAGE);
					// 수량 변경 후 테이블에 포커싱을 하고 싶은데 안됨 @@@@@@@@@@@@@@@@@@@
					innerTable.requestFocus(); // 수량 변경 후 테이블에 포커싱을 하고 싶은데 안됨 @@@@@@@@@@@@@@@@@@@
					// 수량 변경 후 테이블에 포커싱을 하고 싶은데 안됨 @@@@@@@@@@@@@@@@@@@
					
					SwingUtilities.invokeLater(() -> {
				        innerTable.requestFocus();
				        innerTable.changeSelection(selectedRow, 0, true, false); // Optionally select the updated row
				    });

				}
			} else {
				JOptionPane.showMessageDialog(null, "정확한 수량을 입력하세요", "입력 오류", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "제거할 품목을 선택하세요.", "선택 오류", JOptionPane.ERROR_MESSAGE);
		}
		
		
		tableInit();
		searchAction();
	}
	
	
	//구매창 팝업
	public void buyAction() {
		int i = 0;
		
		if (innerTable.getSelectedRow() >= 0) {
			i = innerTable.getSelectedRow();
			String proName = (String) innerTable.getValueAt(i, 1);
			String size = (String) innerTable.getValueAt(i, 2);
			String color = (String) innerTable.getValueAt(i, 3);
			String quantity = (String) innerTable.getValueAt(i, 5);
		
		
			Dao_Purchase dao = new Dao_Purchase(Integer.parseInt(quantity), Integer.parseInt(size), color.trim());
			dao.purchaseon(proName.trim());
			
		 // 추가로 구매한 물건의 정보 가져오기
		    ArrayList<Dto_Purchase> purchasedProducts = dao.getPurchaseInformation(ShareVar.id);
	
		    // 메시지로 출력
		    showMessageAfterPurchase(purchasedProducts);
		    
		    dao.deleteAction();
		  //Table 초기화
		    tableInit();	
		    searchAction();
		}
		else {
			JOptionPane.showMessageDialog(null, "구매를 원하는 제품을 선택하세요");
		}
	}

	// 메시지로 출력하는 메서드
	private void showMessageAfterPurchase(ArrayList<Dto_Purchase> purchasedProducts) {
	    StringBuilder message = new StringBuilder("구매가 완료되었습니다.\n\n구매한 제품 정보\n");
	    String totalPurchasePrice = "";

	    
	    for (Dto_Purchase product : purchasedProducts) {
	        message.append("제품명 : ").append(product.getName()).append("\n")
	                .append("수량 : ").append(product.getQuantity()).append("\n")
	                .append("가격 : ").append(String.format("%,d", product.getSales_price())).append("원\n\n");
	        
	        totalPurchasePrice = String.format("%,d", (product.getQuantity() * product.getSales_price()));
	    }

	    message.append("총 구매 가격 : ").append(totalPurchasePrice).append("원");

	    JOptionPane.showMessageDialog(null, message.toString(), "구매 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	    
	

	//뒤로가기 버튼 활성화
	public void backAction() {
		dispose();												//장바구니 창 닫기
		Product pd = new Product();
		pd.setVisible(true);						//productDetail창 띄우기						

		

	}
	
	//innerTable 기능
	

//	private String userId = "your_user_id";					//사용자 아이디 초기값
	private JLabel lbBackImage;
	
	public void tableInit() {				//장바구니 Table
		
	
		
		outer_Table.addColumn("No");
		outer_Table.addColumn("제품명");
		outer_Table.addColumn("사이즈");
		outer_Table.addColumn("색상");
		outer_Table.addColumn("가격");
		outer_Table.addColumn("수량");
		outer_Table.setColumnCount(6); 
				//No
				int colNo =0;
				TableColumn col = innerTable.getColumnModel().getColumn(colNo);
				int width= 30;		
				col.setPreferredWidth(width);
				//제품명
				 colNo =1;
				   col = innerTable.getColumnModel().getColumn(colNo);
		         width= 100;
				col.setPreferredWidth(width);
				//사이즈
				 colNo =2;
				  col = innerTable.getColumnModel().getColumn(colNo);
			     width= 50;
				col.setPreferredWidth(width);
				//색상
				 colNo =3;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 30;
				col.setPreferredWidth(width);
				//가격
				 colNo =4;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 70;
				col.setPreferredWidth(width);
				//수량
				 colNo =5;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 30;
				col.setPreferredWidth(width);
				
				
				
				int i = outer_Table.getRowCount();
				for (int j = 0; j < i; j++) {
					outer_Table.removeRow(0);
			
			
				
				}
	}
	private void searchAction() {
				Dao_Purchase dao = new Dao_Purchase();
				ArrayList<Dto_Purchase> dtoList = dao.cartList(); //유저 아이디를 받아서 그 유저장바구니만 출력
						   		
					    if (dtoList.isEmpty()  && showMessageCount){
					    	
					        JOptionPane.showMessageDialog(null, "장바구니가 비어 있습니다.");					//비어있을 때 메시지 출력
					        showMessageCount = false;
					        return; 															// 비어있다면 더 이상 진행하지 않고 종료
					    
					    
					    }
					    


					    for (int i = 0; i < dtoList.size(); i++ ) {
//							String id = Integer.toString(dtoList.get(i).getPro_id());
					    	int count = i+1;
							String size = Integer.toString(dtoList.get(i).getSize());
							String price = Integer.toString(dtoList.get(i).getSales_price());
							String quantity = Integer.toString(dtoList.get(i).getQuantity());
							String[] qTxt = {Integer.toString(count), dtoList.get(i).getName(), size, dtoList.get(i).getColor(), price, quantity};
							
							outer_Table.addRow(qTxt);
						}


	}
				
		
	private JLabel getLbBackImage() {
		if (lbBackImage == null) {
			lbBackImage = new JLabel("");
			lbBackImage.setBounds(0, 0, 512, 683);
			lbBackImage.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/images/purchaseBackImage.png")));
		}
		return lbBackImage;
	}
} //End