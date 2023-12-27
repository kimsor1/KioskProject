package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Purchase extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JButton btnback;
	private JButton btnbuy;
	private JButton btndelete;


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
		getContentPane().add(getTextField());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnback());
		getContentPane().add(getBtnbuy());
		getContentPane().add(getBtndelete());

	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setText("장바구니");
			textField.setBounds(113, 76, 266, 49);
			textField.setColumns(10);
		}
		return textField;
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
			innerTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			innerTable.addMouseListener(new MouseAdapter() {
				
			});
		}
		return innerTable;
	}
	private JButton getBtnback() {
		if (btnback == null) {
			btnback = new JButton("뒤로가기");
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
		if (selectedRow != -1) {
		    outer_Table.removeRow(selectedRow);						//해당 열 삭제
		}

	}
	
	
	
	//구매창 팝업
	public void buyAction() {
		JOptionPane.showMessageDialog(null, "제품을 구매하였습니다.");
		
	}

	//뒤로가기 버튼 활성화
	public void backAction() {
				if(btnback.isSelected() ) {
					ProductDetail pd = new ProductDetail();
					pd.setVisible(true);						//productDetail창 띄우기
					
															
					setVisible(false);							//장바구니 창 닫기
					dispose();
						
						
						
					
				}
	}
	
	
	//innerTable 기능
	

	public void tableInit() {
		

		
		outer_Table.addColumn("no.");
		outer_Table.addColumn("제품명");
		outer_Table.addColumn("색깔");
		outer_Table.addColumn("사이즈");
		outer_Table.setColumnCount(4);
		//순서							
				int colNo =0;
				TableColumn col = innerTable.getColumnModel().getColumn(colNo);
				int width= 20;
				col.setPreferredWidth(width);
				//제품명
				 colNo =1;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 150;
				col.setPreferredWidth(width);
				//색깔
				 colNo =2;
				  col = innerTable.getColumnModel().getColumn(colNo);
			 width= 50;
				col.setPreferredWidth(width);
				//사이즈
				 colNo =3;
				  col = innerTable.getColumnModel().getColumn(colNo);
				 width= 70;
				col.setPreferredWidth(width);
				
				int i = outer_Table.getRowCount();
				for (int j = 0; j < i; j++) {
					outer_Table.removeRow(0);
					
					
					innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
					
					
//					try {
//						
//						
//						
//						
//						
//						
//						
//						
//					}catch(Exception e) {
//						e.printStackTrace();
//					}
					
					
				}
				
		
				
	}
	
	
	
	
	
	
} //End