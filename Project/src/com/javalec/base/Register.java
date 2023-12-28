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
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import javax.swing.JPasswordField;

public class Register extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbBack;
	private JTextField tfId;
	private JTextField tfPw;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private JTextField tfBirth;
	private JLabel lbOk;
	private JLabel lbCancle;
	private JLabel lblNewLabel_2;
	private JButton btnCheckDup;
	private JButton btnTitle;
	private JPasswordField pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Register() {
		setFont(new Font("Lucida Grande", Font.BOLD, 27));
		setTitle("회원가입");
		setBounds(100, 100, 512, 683);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnCheckDup());
		contentPanel.add(getTfId());
		contentPanel.add(getTfPw());
		contentPanel.add(getTfPhone());
		contentPanel.add(getTfAddress());
		contentPanel.add(getTfBirth());
		contentPanel.add(getLbOk());
		contentPanel.add(getLbCancle());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getPw());
		contentPanel.add(getLbBack());
		contentPanel.add(getBtnTitle());
	}
	private JLabel getLbBack() {
		if (lbBack == null) {
			lbBack = new JLabel("");
			lbBack.setBounds(0, 0, 512, 655);
			lbBack.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/images/register.png")));
		}
		return lbBack;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (tfId.getText().equals("아이디 입력")) {
						tfId.setText("");
						tfId.setForeground(Color.BLACK);
						tfId.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (tfId.getText().equals("")) {
						tfId.setText("아이디 입력");
						tfId.setForeground(Color.LIGHT_GRAY);
						tfId.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
			});
			tfId.setText("아이디 입력");
			tfId.setForeground(Color.LIGHT_GRAY);
			tfId.setBounds(57, 201, 416, 47);
			tfId.setColumns(10);
		}
		return tfId;
	}
	
	private JPasswordField getPw() {
		if (pw == null) {
			pw = new JPasswordField();
			pw.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					// 비밀번호가 입력 됬다면 tf 가리기 입력되지 않았다면 pw필드 가리기
					if (changePw().length() > 0) {
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
			pw.setBounds(57, 260, 416, 47);
		}
		return pw;
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
			});
			tfPw.setText("비밀번호 입력");
			tfPw.setForeground(Color.LIGHT_GRAY);
			tfPw.setColumns(10);
			tfPw.setBounds(57, 260, 416, 47);
		}
		return tfPw;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (tfPhone.getText().equals("전화번호 입력")) {
						tfPhone.setText("");
						tfPhone.setForeground(Color.BLACK);
						tfPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (tfPhone.getText().equals("")) {
						tfPhone.setText("전화번호 입력");
						tfPhone.setForeground(Color.LIGHT_GRAY);
						tfPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				
			});
			tfPhone.setText("전화번호 입력");
			tfPhone.setForeground(Color.LIGHT_GRAY);
			tfPhone.setColumns(10);
			tfPhone.setBounds(57, 319, 416, 47);
		}
		return tfPhone;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (tfAddress.getText().equals("주소 입력")) {
						tfAddress.setText("");
						tfAddress.setForeground(Color.BLACK);
						tfAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (tfAddress.getText().equals("")) {
						tfAddress.setText("주소 입력");
						tfAddress.setForeground(Color.LIGHT_GRAY);
						tfAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
			});
			tfAddress.setText("주소 입력");
			tfAddress.setForeground(Color.LIGHT_GRAY);
			tfAddress.setColumns(10);
			tfAddress.setBounds(57, 378, 416, 47);
		}
		return tfAddress;
	}
	private JTextField getTfBirth() {
		if (tfBirth == null) {
			tfBirth = new JTextField();
			tfBirth.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (tfBirth.getText().equals("생일 입력")) {
						tfBirth.setText("");
						tfBirth.setForeground(Color.BLACK);
						tfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (tfBirth.getText().equals("")) {
						tfBirth.setText("생일 입력");
						tfBirth.setForeground(Color.LIGHT_GRAY);
						tfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
			});
			tfBirth.setText("생일 입력");
			tfBirth.setForeground(Color.LIGHT_GRAY);
			tfBirth.setColumns(10);
			tfBirth.setBounds(57, 439, 416, 47);
		}
		return tfBirth;
	}
	
	
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("확인");
			lbOk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbOk.setForeground(Color.LIGHT_GRAY);
			lbOk.setBounds(198, 535, 61, 16);
		}
		return lbOk;
	}
	private JLabel getLbCancle() {
		if (lbCancle == null) {
			lbCancle = new JLabel("취소");
			lbCancle.setForeground(Color.LIGHT_GRAY);
			lbCancle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbCancle.setBounds(305, 535, 61, 16);
		}
		return lbCancle;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("|");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(232, 535, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JButton getBtnCheckDup() {
		if (btnCheckDup == null) {
			btnCheckDup = new JButton("중복확인");
			btnCheckDup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					checkId();
				}
			});
			btnCheckDup.setBackground(Color.GRAY);
			btnCheckDup.setBounds(393, 211, 71, 29);
		}
		return btnCheckDup;
	}
	private JButton getBtnTitle() {
		if (btnTitle == null) {
			btnTitle = new JButton("");
			btnTitle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moveBack();
					
				}
			});
			btnTitle.setBounds(44, 93, 429, 100);
		}
		return btnTitle;
	}
	
	
	
	
	/// ---- Function ----
	
	private void moveBack() {
		Main window = new Main();
		window.main(null);
		
		this.setVisible(false);
	}
	
	// char의 pw를 string으로 변환
	private String changePw() {
		char[] pass = pw.getPassword();
		String strPass = new String(pass);
		
		return strPass.trim();
	}
	
	private void checkId() {
		String checkId = tfId.getText().trim();
		
		Dao_Login dao = new Dao_Login(checkId, changePw());
		
		if (checkId.length() == 0) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
		}
		else dao.checkIdAction();
	}
	
	
	
	
	
}

	
