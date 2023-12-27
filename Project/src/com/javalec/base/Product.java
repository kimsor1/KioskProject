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
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import com.javalec.function.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JComboBox cbSort;

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
		getContentPane().add(getCbSort());

	}

	private JComboBox getCbSelect() {
		if (cbSelect == null) {
			cbSelect = new JComboBox();
			cbSelect.setModel(new DefaultComboBoxModel(new String[] { "제품명", "사이즈", "색상" }));
			cbSelect.setBounds(28, 80, 100, 40);
		}
		return cbSelect;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(130, 79, 235, 40);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					specSearch();
				}
			});
			btnSearch.setBounds(365, 80, 117, 40);
		}
		return btnSearch;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 163, 454, 431);
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

	private JComboBox getCbSort() {
		if (cbSort == null) {
			cbSort = new JComboBox();
			cbSort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortAction();
				}
			});
			cbSort.setModel(new DefaultComboBoxModel(new String[] { "기본순", "낮은가격순", "높은가격순" }));
			cbSort.setBounds(375, 132, 107, 27);
		}
		return cbSort;
	}

	// ---------- Method

	private void tableInit() {

		// Coulmn명 초기화
		outerTable.addColumn("Seq");
		outerTable.addColumn("상품명");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(3);

		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 30;
		col.setPreferredWidth(width);

		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);

		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);

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
			innerTable.setRowHeight(i, 50);
		}
	}

	private void sortAction() {
		tableInit();
		
		Dao_Product dao = new Dao_Product();
		ArrayList<Dto_Product> dtolist = null;
		
		if (cbSort.getSelectedIndex() == 1) {
			dtolist = dao.sort("asc");
		}
		else if (cbSort.getSelectedIndex() == 2) {
			dtolist = dao.sort("desc");
		}else {
			dtolist = dao.selectList();
		}
		
		int listCount = dtolist.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtolist.get(i).getSeqno());
			String[] qTxt = { temp, dtolist.get(i).getName(), Integer.toString(dtolist.get(i).getPrice()) };
			outerTable.addRow(qTxt);
			innerTable.setRowHeight(i, 50);
		}
	}
	
	private void specSearch() {
		tableInit();
	}

} // ------- END
