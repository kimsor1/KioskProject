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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Product extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JButton btnbasket;
	private JButton btndetail;
	private JTable innerTable;

	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JComboBox cbSort;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;

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
		getContentPane().add(getTfSearch());
		getContentPane().add(getBtnSearch());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnbasket());
		getContentPane().add(getBtndetail());
		getContentPane().add(getCbSort());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getLblNewLabel_1());

	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(105, 133, 258, 40);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.setBackground(SystemColor.window);
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			btnSearch.setBounds(365, 134, 117, 40);
		}
		return btnSearch;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 226, 454, 368);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JButton getBtnbasket() {
		if (btnbasket == null) {
			btnbasket = new JButton("장바구니");
			btnbasket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Purchase pc = new Purchase();
					pc.setVisible(true);
				}
			});
			btnbasket.setBounds(110, 606, 117, 35);
		}
		return btnbasket;
	}

	private JButton getBtndetail() {
		if (btndetail == null) {
			btndetail = new JButton("상세보기");
			btndetail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductDetail productDetail = new ProductDetail();
					productDetail.setVisible(true);

					tableClick();
				}
			});
			btndetail.setBounds(275, 606, 117, 35);
		}
		return btndetail;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
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
					if (tfSearch.getText().equals("")) {
						sortAction();
					} else {
						search();
					}
				}
			});
			cbSort.setModel(new DefaultComboBoxModel(new String[] { "기본순", "낮은가격순", "높은가격순" }));
			cbSort.setBounds(375, 187, 107, 27);
		}
		return cbSort;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제품명 : ");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBounds(28, 130, 76, 40);
		}
		return lblNewLabel;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("My");
			btnNewButton.setBounds(442, 18, 40, 40);
		}
		return btnNewButton;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(Product.class.getResource("/com/javalec/images/Logo.png")));
			lblNewLabel_1.setBounds(124, 30, 268, 73);
		}
		return lblNewLabel_1;
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

		if (cbSort.getSelectedIndex() == 1 || cbSort.getSelectedIndex() == 2) {
			dtolist = dao.sort(cbSort.getSelectedIndex());
		} else {
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

	private void search() {
		tableInit();

		Dao_Product dao = new Dao_Product();
		ArrayList<Dto_Product> dtolist = null;

		dtolist = dao.search(tfSearch.getText(), cbSort.getSelectedIndex());

		int listCount = dtolist.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtolist.get(i).getSeqno());
			String[] qTxt = { temp, dtolist.get(i).getName(), Integer.toString(dtolist.get(i).getPrice()) };
			outerTable.addRow(qTxt);
			innerTable.setRowHeight(i, 50);
		}
	}

	private void tableClick() {
		int i = innerTable.getSelectedRow();
		String seqno = (String) innerTable.getValueAt(i, 0);
		Dao_Product dao = new Dao_Product();
		dao.tableClick(Integer.parseInt(seqno));
	}

} // ------- END
