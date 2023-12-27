package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

public class Product extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox cbSelect;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JButton btnbasket;
	private JButton btndetail;
	private JTable innerTable;

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
		setBounds(100, 100, 450, 501);
		getContentPane().setLayout(null);
		getContentPane().add(getCbSelect());
		getContentPane().add(getTfSearch());
		getContentPane().add(getBtnSearch());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnbasket());
		getContentPane().add(getBtndetail());

	}
	private JComboBox getCbSelect() {
		if (cbSelect == null) {
			cbSelect = new JComboBox();
			cbSelect.setModel(new DefaultComboBoxModel(new String[] {"제품명", "사이즈", "색상"}));
			cbSelect.setBounds(28, 71, 100, 27);
		}
		return cbSelect;
	}
	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(130, 70, 180, 26);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.setBounds(309, 70, 117, 29);
		}
		return btnSearch;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 110, 400, 300);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JButton getBtnbasket() {
		if (btnbasket == null) {
			btnbasket = new JButton("장바구니");
			btnbasket.setBounds(97, 438, 117, 29);
		}
		return btnbasket;
	}
	private JButton getBtndetail() {
		if (btndetail == null) {
			btndetail = new JButton("상세보기");
			btndetail.setBounds(233, 438, 117, 29);
		}
		return btndetail;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
		}
		return innerTable;
	}
}
