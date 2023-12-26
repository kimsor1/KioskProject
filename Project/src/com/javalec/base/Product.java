package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Product extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product dialog = new Product();
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
	public Product() {
		setBounds(100, 100, 450, 565);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"제품명", "사이즈", "색상"}));
		comboBox.setBounds(16, 57, 89, 27);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(101, 56, 222, 27);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setBounds(322, 56, 104, 29);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 96, 400, 400);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("장바구니");
		btnNewButton_1.setBounds(79, 502, 117, 29);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("상세보기");
		btnNewButton_1_1.setBounds(208, 502, 117, 29);
		getContentPane().add(btnNewButton_1_1);

	}
}
