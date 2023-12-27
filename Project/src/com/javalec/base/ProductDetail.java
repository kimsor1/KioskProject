package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;

public class ProductDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblNewLabel_1;
	private JTextField tfPname;
	private JButton btnBack;
	private JTextField tfPsize;
	private JLabel lblNewLabel_1_1;
	private JTextField tfPcolor;
	private JLabel lblNewLabel_1_2;
	private JTextField tfPstock;
	private JLabel lblNewLabel_1_3;

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
		getContentPane().setBackground(SystemColor.infoText);
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("상세보기");
		setBackground(SystemColor.window);
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(null);
		getContentPane().add(getLblImage());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getTfPname());
		getContentPane().add(getBtnBack());
		getContentPane().add(getTfPsize());
		getContentPane().add(getLblNewLabel_1_1_1());
		getContentPane().add(getTfPcolor());
		getContentPane().add(getLblNewLabel_1_2_1());
		getContentPane().add(getTfPstock());
		getContentPane().add(getLblNewLabel_1_3_1());

	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(101, 29, 300, 300);
		}
		return lblImage;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품명 : ");
			lblNewLabel_1.setForeground(SystemColor.menu);
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(89, 345, 83, 28);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfPname() {
		if (tfPname == null) {
			tfPname = new JTextField();
			tfPname.setEditable(false);
			tfPname.setBounds(184, 341, 266, 35);
			tfPname.setColumns(10);
		}
		return tfPname;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("뒤로가기");
			btnBack.setBounds(186, 589, 133, 40);
		}
		return btnBack;
	}
	private JTextField getTfPsize() {
		if (tfPsize == null) {
			tfPsize = new JTextField();
			tfPsize.setEditable(false);
			tfPsize.setColumns(10);
			tfPsize.setBounds(184, 396, 135, 35);
		}
		return tfPsize;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("사이즈 :");
			lblNewLabel_1_1.setForeground(SystemColor.menu);
			lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(89, 400, 83, 28);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfPcolor() {
		if (tfPcolor == null) {
			tfPcolor = new JTextField();
			tfPcolor.setEditable(false);
			tfPcolor.setColumns(10);
			tfPcolor.setBounds(184, 453, 215, 35);
		}
		return tfPcolor;
	}
	private JLabel getLblNewLabel_1_2_1() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("색상 : ");
			lblNewLabel_1_2.setForeground(SystemColor.menu);
			lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_1_2.setBounds(89, 457, 83, 28);
		}
		return lblNewLabel_1_2;
	}
	private JTextField getTfPstock() {
		if (tfPstock == null) {
			tfPstock = new JTextField();
			tfPstock.setEditable(false);
			tfPstock.setColumns(10);
			tfPstock.setBounds(184, 505, 135, 35);
		}
		return tfPstock;
	}
	private JLabel getLblNewLabel_1_3_1() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("재고 :");
			lblNewLabel_1_3.setForeground(SystemColor.menu);
			lblNewLabel_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_1_3.setBounds(89, 509, 83, 28);
		}
		return lblNewLabel_1_3;
	}
}
