package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ProductDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblNewLabel_1_1;
	private JTextField textField_1;
	private JLabel lblNewLabel_1_2;
	private JTextField textField_2;
	private JLabel lblNewLabel_1_3;
	private JTextField textField_3;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetail dialog = new ProductDetail();
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
	public ProductDetail() {
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getTextField());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getTextField_1());
		getContentPane().add(getLblNewLabel_1_2());
		getContentPane().add(getTextField_2());
		getContentPane().add(getLblNewLabel_1_3());
		getContentPane().add(getTextField_3());
		getContentPane().add(getBtnNewButton());

	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(106, 72, 211, 175);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품명 : ");
			lblNewLabel_1.setBounds(74, 279, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(158, 274, 160, 26);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("사이즈 : ");
			lblNewLabel_1_1.setBounds(73, 319, 61, 16);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(157, 314, 160, 26);
		}
		return textField_1;
	}
	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("색상 : ");
			lblNewLabel_1_2.setBounds(74, 362, 61, 16);
		}
		return lblNewLabel_1_2;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(158, 357, 160, 26);
		}
		return textField_2;
	}
	private JLabel getLblNewLabel_1_3() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("현재 재고 :");
			lblNewLabel_1_3.setBounds(74, 408, 61, 16);
		}
		return lblNewLabel_1_3;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(158, 403, 160, 26);
		}
		return textField_3;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("뒤로가기");
			btnNewButton.setBounds(150, 441, 117, 29);
		}
		return btnNewButton;
	}
}
