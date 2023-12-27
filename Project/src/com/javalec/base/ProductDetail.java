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
	private JTextField tfPname;
	private JLabel lblNewLabel_1_1;
	private JTextField tfPsize;
	private JLabel lblNewLabel_1_2;
	private JTextField tfPcolor;
	private JLabel lblNewLabel_1_3;
	private JTextField tfPstock;
	private JButton btnBack;

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
		getContentPane().add(getTfPname());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getTfPsize());
		getContentPane().add(getLblNewLabel_1_2());
		getContentPane().add(getTfPcolor());
		getContentPane().add(getLblNewLabel_1_3());
		getContentPane().add(getTfPstock());
		getContentPane().add(getBtnBack());

	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(112, 59, 211, 175);
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
	private JTextField getTfPname() {
		if (tfPname == null) {
			tfPname = new JTextField();
			tfPname.setEditable(false);
			tfPname.setBounds(158, 274, 160, 26);
			tfPname.setColumns(10);
		}
		return tfPname;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("사이즈 : ");
			lblNewLabel_1_1.setBounds(73, 319, 61, 16);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfPsize() {
		if (tfPsize == null) {
			tfPsize = new JTextField();
			tfPsize.setEditable(false);
			tfPsize.setColumns(10);
			tfPsize.setBounds(157, 314, 160, 26);
		}
		return tfPsize;
	}
	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("색상 : ");
			lblNewLabel_1_2.setBounds(74, 362, 61, 16);
		}
		return lblNewLabel_1_2;
	}
	private JTextField getTfPcolor() {
		if (tfPcolor == null) {
			tfPcolor = new JTextField();
			tfPcolor.setEditable(false);
			tfPcolor.setColumns(10);
			tfPcolor.setBounds(158, 357, 160, 26);
		}
		return tfPcolor;
	}
	private JLabel getLblNewLabel_1_3() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("현재 재고 :");
			lblNewLabel_1_3.setBounds(74, 408, 61, 16);
		}
		return lblNewLabel_1_3;
	}
	private JTextField getTfPstock() {
		if (tfPstock == null) {
			tfPstock = new JTextField();
			tfPstock.setEditable(false);
			tfPstock.setColumns(10);
			tfPstock.setBounds(158, 403, 160, 26);
		}
		return tfPstock;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("뒤로가기");
			btnBack.setBounds(150, 441, 117, 29);
		}
		return btnBack;
	}
}
