package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.Dao_Login;
import com.javalec.function.Dto_Login;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbLoginBackImage;
	private JTextField tfId;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_3_1;
	private JButton btnTitle;
	private JPasswordField pw;
	private JTextField tfPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("Login");
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getTfId());
		contentPanel.add(getTfPw());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getLblNewLabel_3());
		contentPanel.add(getLblNewLabel_3_1());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getPw());
		contentPanel.add(getLbLoginBackImage());
		contentPanel.add(getBtnTitle());
	}
	private JLabel getLbLoginBackImage() {
		if (lbLoginBackImage == null) {
			lbLoginBackImage = new JLabel("");
			lbLoginBackImage.setBounds(0, 0, 512, 655);
			lbLoginBackImage.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/images/loginBack.png")));
		}
		return lbLoginBackImage;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setText("아이디 입력");
			tfId.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(tfId.getText().equals("아이디 입력")) {
						tfId.setText("");
						tfId.setForeground(Color.BLACK);
						tfId.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if(tfId.getText().equals("")) {
						tfId.setText("아이디 입력");
						tfId.setForeground(Color.LIGHT_GRAY);
						tfId.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
			});
			tfId.setBackground(new Color(254, 255, 255));
			tfId.setForeground(Color.LIGHT_GRAY);
			tfId.setBounds(46, 289, 416, 47);
			tfId.setColumns(10);
		}
		return tfId;
	}
	
	private JTextField getTfPw() {
		if (tfPw == null) {
			tfPw = new JTextField();
			tfPw.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(tfPw.getText().equals("비밀번호 입력")) {
						tfPw.setVisible(false);
						pw.setVisible(true);
						
						pw.requestFocus();
					}
				}
//				@Override
//				public void focusLost(FocusEvent e) {
//					if(tfPw.getText().equals("")) {
//						tfPw.setVisible(true);
//						tfPw.setText("비밀번호 입력");
//						tfPw.setForeground(Color.LIGHT_GRAY);
//						tfPw.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
//					}
//				}
			});
			tfPw.setText("비밀번호 입력");
			tfPw.setForeground(Color.LIGHT_GRAY);
			tfPw.setColumns(10);
			tfPw.setBackground(new Color(254, 255, 255));
			tfPw.setBounds(46, 348, 416, 47);
		}
		return tfPw;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("로그인");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login();
				}
			});
			btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			btnNewButton.setForeground(new Color(0, 0, 0));
			btnNewButton.setBounds(46, 443, 416, 40);
		}
		return btnNewButton;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("회원가입");
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clickRegister();
				}
			});
			lblNewLabel.setForeground(Color.LIGHT_GRAY);
			lblNewLabel.setBounds(139, 510, 61, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("아이디 찾기");
			lblNewLabel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clickFindId();
				}
			});
			lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_1.setBounds(220, 510, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("비밀번호 찾기");
			lblNewLabel_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clickFindPw();
				}
			});
			lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_2.setBounds(316, 510, 73, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel(" |");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_3.setBounds(167, 509, 61, 16);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel(" |");
			lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3_1.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_3_1.setBounds(265, 510, 61, 16);
		}
		return lblNewLabel_3_1;
	}
	private JButton getBtnTitle() {
		if (btnTitle == null) {
			btnTitle = new JButton("");
			btnTitle.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					moveMain();
				}
			});
			btnTitle.setBounds(45, 141, 429, 100);
			
		}
		return btnTitle;
		
	}
	private JPasswordField getPw() {
		if (pw == null) {
			pw = new JPasswordField();
			pw.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					// 비밀번호가 입력 됬다면 tf 가리기 입력되지 않았다면 pw필드 가리기
					char[] pass = pw.getPassword();
					String sPass = new String(pass);
					if (sPass.length() > 0) {
						tfPw.setVisible(false);
						pw.setVisible(true);
					}
					else {
						pw.setVisible(false);
						tfPw.setVisible(true);
						tfPw.setText("비밀번호 입력");
						tfPw.setForeground(Color.LIGHT_GRAY);
						tfPw.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
			});
			pw.setForeground(Color.BLACK);
			pw.setBounds(46, 348, 416, 47);
		}
		return pw;
	}
	
// ---- Fucntion ----
	// logo 클릭 시 메인 화면으로 back
	private void moveMain() {
		// 화면 메인으로 돌리기
		Main window = new Main();
		// 이거 사용 안하면 프로그램이 그냥 종료됨
		window.main(null);
		this.setVisible(false);
	}
	
	// 회원가입 클릭
	private void clickRegister() {
		Register register = new Register();
		register.setVisible(true);
		
		this.setVisible(false);
	}
	
	// 아이디 찾기 클릭
	private void clickFindId() {
		FindId fId = new FindId();
		
		fId.setVisible(true);
		this.setVisible(false);
		
	}
	
	private void clickFindPw() {
		FindPw fPw = new FindPw();
		
		fPw.setVisible(true);
		this.setVisible(false);
	}
	
	// id, pw가 입력이 되지 않았다면 입력하라고 나오는 알림
	private void login() {
		String hintId = "아이디 입력";
		String hintPw = "비밀번호 입력";
		// char의 pw를 string으로 변환
		char[] pass = pw.getPassword();
		String sPass = new String(pass);
		
		Dao_Login dao = new Dao_Login(tfId.getText().trim(), sPass.trim());
		
		
		if (tfId.getText().trim().equals(hintId) && tfPw.getText().trim().equals(hintPw)) {
			JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			tfId.requestFocus();
		}
		else if (tfId.getText().trim().equals(hintId)) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			tfId.requestFocus();
		}
		else if (sPass.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			pw.requestFocus();
		}
		else {
			if (dao.loginAction() == true) {
				JOptionPane.showMessageDialog(null, "로그인 성공 하였습니다", "알림", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 다시 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
				pw.requestFocus();
			}
		}
		
	}
}
