package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import com.javalec.function.*;

public class Product extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox cbSelect;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JButton btnbasket;
	private JButton btndetail;
	private JTable innerTable;

	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		setForeground(SystemColor.window);
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("상품 목록");
		getContentPane().setBackground(SystemColor.infoText);
		setBounds(100, 100, 512, 683);
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
			cbSelect.setModel(new DefaultComboBoxModel(new String[] { "제품명", "사이즈", "색상" }));
			cbSelect.setBounds(28, 71, 100, 27);
		}
		return cbSelect;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(130, 70, 235, 26);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.setBounds(365, 70, 117, 29);
		}
		return btnSearch;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 110, 454, 484);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JButton getBtnbasket() {
		if (btnbasket == null) {
			btnbasket = new JButton("장바구니");
			btnbasket.setBounds(110, 606, 117, 35);
		}
		return btnbasket;
	}

	private JButton getBtndetail() {
		if (btndetail == null) {
			btndetail = new JButton("상세보기");
			btndetail.setBounds(275, 606, 117, 35);
		}
		return btndetail;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}

	// ---------- Method

	private void tableInit() {

		// Coulmn명 초기화
		outerTable.addColumn("Seq");
		outerTable.addColumn("상품명");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(3);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}

	}

	private void searchAction() {
		Dao_Product dao = new Dao_Product();
		ArrayList<Dto_Product> dtolist = dao.selectList();

		int listCount = dtolist.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtolist.get(i).getSeqno());
			String[] qTxt = { temp, dtolist.get(i).getName(), Integer.toString(dtolist.get(i).getPrice()) };
			outerTable.addRow(qTxt);
		}
	}

} // ------- END
