package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.javalec.function.ShareVar;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblNewLabel_1;
	private JTextField tfPname;
	private JButton btnBack;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JTextField tfPstock;
	private JLabel lblNewLabel_1_3;
	private JComboBox cbPsize;
	private JComboBox cbPcolor;
	private JLabel lblNewLabel_1_3_;
	private JTextField tfPprice;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				inputData();
			}
		});
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
		getContentPane().add(getLblNewLabel_1_1_1());
		getContentPane().add(getLblNewLabel_1_2_1());
		getContentPane().add(getTfPstock());
		getContentPane().add(getLblNewLabel_1_3_1());
		getContentPane().add(getCbPsize());
		getContentPane().add(getCbPcolor());
		getContentPane().add(getLblNewLabel_1_3_1_1());
		getContentPane().add(getTfPprice());

	}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(101, 29, 250, 250);
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

	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("사이즈 :");
			lblNewLabel_1_1.setForeground(SystemColor.menu);
			lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(89, 400, 83, 28);
		}
		return lblNewLabel_1_1;
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

	private JComboBox getCbPsize() {
		if (cbPsize == null) {
			cbPsize = new JComboBox();
			cbPsize.setModel(new DefaultComboBoxModel(new String[] {"230", "240", "250", "260", "270"}));
			cbPsize.setBounds(184, 403, 100, 30);
		}
		return cbPsize;
	}

	private JComboBox getCbPcolor() {
		if (cbPcolor == null) {
			cbPcolor = new JComboBox();
			cbPcolor.setModel(new DefaultComboBoxModel(new String[] {"white", "black"}));
			cbPcolor.setBounds(184, 460, 100, 27);
		}
		return cbPcolor;
	}

	private JLabel getLblNewLabel_1_3_1_1() {
		if (lblNewLabel_1_3_ == null) {
			lblNewLabel_1_3_ = new JLabel("가격 :");
			lblNewLabel_1_3_.setForeground(SystemColor.menu);
			lblNewLabel_1_3_.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblNewLabel_1_3_.setBounds(89, 556, 83, 28);
		}
		return lblNewLabel_1_3_;
	}

	private JTextField getTfPprice() {
		if (tfPprice == null) {
			tfPprice = new JTextField();
			tfPprice.setEditable(false);
			tfPprice.setColumns(10);
			tfPprice.setBounds(184, 552, 135, 35);
		}
		return tfPprice;
	}

	// -------- Function

	private void inputData() {
		tfPname.setText(ShareVar.productName);
		tfPstock.setText(Integer.toString(ShareVar.productStock));
		tfPprice.setText(Integer.toString(ShareVar.productPrice));
	}
}// End
