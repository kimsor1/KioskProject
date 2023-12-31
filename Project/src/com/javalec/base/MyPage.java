package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import com.javalec.base.Product;
import com.javalec.function.*;
import com.mysql.cj.xdevapi.Statement;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

public class MyPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblID;
	private JTextField tfID;
	private JLabel lblName;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfAddress;

	// ******************************
	public final String url_mysql = ShareVar.dbName;
	public final String id_mysql = ShareVar.dbUser;
	public final String pw_mysql = ShareVar.dbPass;
	public final String inputID = ShareVar.id;

	// Table
	private final DefaultTableModel outer_Table = new DefaultTableModel();
	private JTextField tfPassword;
	private JButton btnPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage dialog = new MyPage();
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
	public MyPage() {
		addWindowListener(new WindowAdapter() {
			@Override // input***************
			public void windowActivated(WindowEvent e) {
				inputInfo();
			}
		});
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("My Page");
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(null);
		getContentPane().add(getLblID());
		getContentPane().add(getTfID());
		getContentPane().add(getLblName());
		getContentPane().add(getTfName());

		JLabel lblPhone = new JLabel("전화번호 :");
		lblPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPhone.setForeground(new Color(254, 255, 255));
		lblPhone.setBounds(70, 360, 69, 16);
		getContentPane().add(lblPhone);

		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(150, 350, 158, 41);
		getContentPane().add(tfPhone);

		JButton btnPhone = new JButton("수정");
		btnPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePhone();
			}
		});
		btnPhone.setBounds(332, 350, 69, 41);
		getContentPane().add(btnPhone);

		JLabel lblAddress = new JLabel("주 소 :");
		lblAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAddress.setForeground(new Color(254, 255, 255));
		lblAddress.setBounds(70, 430, 61, 16);
		getContentPane().add(lblAddress);

		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(130, 420, 262, 41);
		getContentPane().add(tfAddress);

		JButton btnAddress = new JButton("수정");
		btnAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAddress.setForeground(new Color(0, 0, 0));
		btnAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAddress();
			}
		});
		btnAddress.setBounds(408, 420, 69, 41);
		getContentPane().add(btnAddress);

		JLabel lblPassword = new JLabel("비밀번호 :");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPassword.setForeground(new Color(254, 255, 255));
		lblPassword.setBounds(70, 500, 77, 16);
		getContentPane().add(lblPassword);

		JButton btnShow = new JButton("보기");
		btnShow.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPw();
			}
		});

		tfPassword = new JTextField();
		tfPassword.setEditable(false);
		tfPassword.setColumns(10);
		tfPassword.setBounds(150, 490, 150, 41);
		getContentPane().add(tfPassword);
		btnShow.setBounds(332, 490, 69, 41);
		getContentPane().add(btnShow);

		JButton btnOk = new JButton("돌아가기");
		btnOk.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returntoProduct(); // input********************
			}
		});
		btnOk.setBounds(130, 564, 117, 35);
		getContentPane().add(btnOk);

		JButton btnExit = new JButton("회원탈퇴");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteInfo();
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnExit.setBounds(280, 564, 117, 35);
		getContentPane().add(btnExit);
		getContentPane().add(getBtnPw());
		
		JLabel lbBack = new JLabel("");
		lbBack.setIcon(new ImageIcon(MyPage.class.getResource("/com/javalec/images/Group 38 (2).png")));
		lbBack.setVerticalAlignment(SwingConstants.TOP);
		lbBack.setHorizontalAlignment(SwingConstants.CENTER);
		lbBack.setFont(new Font("Lucida Grande", Font.PLAIN, 5));
		lbBack.setBackground(SystemColor.window);
		lbBack.setBounds(0, -28, 512, 683);
		getContentPane().add(lbBack);

	}

	private JLabel getLblID() {
		if (lblID == null) {
			lblID = new JLabel("아이디 :");
			lblID.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblID.setForeground(new Color(254, 255, 255));
			lblID.setBounds(70, 230, 61, 16);
		}
		return lblID;
	}

	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setEditable(false);
			tfID.setBounds(150, 220, 150, 41);
			tfID.setColumns(10);
		}
		return tfID;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("이름 :");
			lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblName.setForeground(new Color(254, 255, 255));
			lblName.setBounds(70, 290, 61, 16);
		}
		return lblName;
	}

	private JButton getBtnPw() {
		if (btnPw == null) {
			btnPw = new JButton("수정");
			btnPw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updatePassword();
				}
			});
			btnPw.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			btnPw.setBounds(408, 490, 69, 41);
		}
		return btnPw;
	}

	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(150, 280, 150, 41);
		}
		return tfName;
	}

	// ---------Functions--------------

	private void inputInfo() {
		Dao_MyPage dao = new Dao_MyPage();
		Dto_MyPage dto = dao.MyPage();

		tfID.setText(dto.getId());
		tfName.setText(dto.getName());
		tfPhone.setText(dto.getPhone());
		tfAddress.setText(dto.getAddress());
		
		String pw = dto.getPw(); // dto에서 비밀번호 가져오기
		StringBuilder maskedPassword = new StringBuilder();

		for (int i = 0; i < pw.length(); i++) {
		    maskedPassword.append("*");
		}

		tfPassword.setText(maskedPassword.toString());
	}

	private void deleteInfo() {
		Dao_MyPage dao = new Dao_MyPage();
		dao.delete();

		JOptionPane.showMessageDialog(null, "탈퇴 하였습니다.");

		dispose();

		Main main = new Main();
		main.setVisible(true);
	}

	private void updatePhone() {
		Dao_MyPage dao = new Dao_MyPage();
		Dto_MyPage dto = dao.MyPage();
		dao.updatePhone(tfPhone.getText());

		JOptionPane.showMessageDialog(null, dto.getName() + "의 전화번호가 수정되었습니다.");

		tfPhone.setText(tfPhone.getText());
	}

	private void updateAddress() {
		Dao_MyPage dao = new Dao_MyPage();
		Dto_MyPage dto = dao.MyPage();
		dao.updateAddress(tfAddress.getText());

		JOptionPane.showMessageDialog(null, dto.getName() + "님의 주소가 수정되었습니다.");

		tfAddress.setText(tfAddress.getText());
	}
	
	private void showPw() {
		Dao_MyPage dao = new Dao_MyPage();
		Dto_MyPage dto = dao.MyPage();
		
		tfPassword.setEditable(true);
		tfPassword.setText(dto.getPw());
	}

	private void updatePassword() {
		Dao_MyPage dao = new Dao_MyPage();
		Dto_MyPage dto = dao.MyPage();
		dao.updatePw(tfPassword.getText());

		JOptionPane.showMessageDialog(null, "비밀번호가 수정되었습니다.");
		
		String pw = dto.getPw(); // dto에서 비밀번호 가져오기
		StringBuilder maskedPassword = new StringBuilder();

		for (int i = 0; i < pw.length(); i++) {
		    maskedPassword.append("*");
		}

		tfPassword.setText(maskedPassword.toString());
	}

	// 확인 click 시 물건 목록으로 이동
	private void returntoProduct() {
		Product p = new Product();
		p.setVisible(true);
		this.setVisible(false);
	}
} // -----end------------
