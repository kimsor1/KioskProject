package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;

public class FindPw extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbBack;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FindPw dialog = new FindPw();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FindPw() {
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("비밀번호 찾기");
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLbBack());
		contentPanel.add(getBtnNewButton());
	}
	private JLabel getLbBack() {
		if (lbBack == null) {
			lbBack = new JLabel("");
			lbBack.setBounds(0, 0, 512, 683);
			lbBack.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/images/findPw.png")));
		}
		return lbBack;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
			btnNewButton.setBounds(46, 105, 117, 29);
		}
		return btnNewButton;
	}
}
