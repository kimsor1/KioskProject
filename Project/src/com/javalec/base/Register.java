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
			tfId.setText("아이디 입력");
			tfId.setForeground(Color.LIGHT_GRAY);
			tfId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String hintId = "아이디 입력";
					
					Font lostFont = new Font("Lucida Grande", Font.ITALIC, 13);
					Font gainFont = new Font("Lucida Grande", Font.PLAIN, 13);
					
					
					if (tfId.getText().length() == 0) {
						tfId.setText(hintId);
						tfId.setFont(lostFont);
						tfId.setForeground(Color.LIGHT_GRAY);
					}
					else {
						tfId.setText("");
						tfId.setFont(gainFont);
						tfId.setForeground(Color.BLACK);
					}
				}
			});
			tfId.setBounds(57, 201, 416, 47);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfPw() {
		if (tfPw == null) {
			tfPw = new JTextField();
			tfPw.setText("비밀번호 입력");
			tfPw.setForeground(Color.LIGHT_GRAY);
			tfPw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setFont();
				}
			});
			tfPw.setColumns(10);
			tfPw.setBounds(57, 260, 416, 47);
		}
		return tfPw;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setText("전화번호 입력");
			tfPhone.setForeground(Color.LIGHT_GRAY);
			tfPhone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setFont();
				}
			});
			tfPhone.setColumns(10);
			tfPhone.setBounds(57, 319, 416, 47);
		}
		return tfPhone;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setText("이메일 입력");
			tfAddress.setForeground(Color.LIGHT_GRAY);
			tfAddress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setFont();
				}
			});
			tfAddress.setColumns(10);
			tfAddress.setBounds(57, 378, 416, 47);
		}
		return tfAddress;
	}
	private JTextField getTfBirth() {
		if (tfBirth == null) {
			tfBirth = new JTextField();
			tfBirth.setText("생일 입력");
			tfBirth.setForeground(Color.LIGHT_GRAY);
			tfBirth.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setFont();
				}
			});
			tfBirth.setColumns(10);
			tfBirth.setBounds(57, 439, 416, 47);
		}
		return tfBirth;
	}
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("확인");
			lbOk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbOk.setForeground(SystemColor.controlHighlight);
			lbOk.setBounds(198, 535, 61, 16);
		}
		return lbOk;
	}
	private JLabel getLbCancle() {
		if (lbCancle == null) {
			lbCancle = new JLabel("취소");
			lbCancle.setForeground(SystemColor.controlHighlight);
			lbCancle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbCancle.setBounds(305, 535, 61, 16);
		}
		return lbCancle;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("|");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setForeground(SystemColor.controlHighlight);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(232, 535, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JButton getBtnCheckDup() {
		if (btnCheckDup == null) {
			btnCheckDup = new JButton("중복확인");
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
		Login login = new Login();
		login.setVisible(true);
		
		this.setVisible(false);
	}
	
	private void setFont() {
		String idHint = "아이디 입력";
		String pwHint = "비밀번호 입력";
		String phoneHint = "전화번호 입력";
		String emailHint = "이메일 입력";
		String addressHint = "주소 입력";
		
		Font gainFont = new Font("Lucida Grande", Font.PLAIN, 13);
		Font lostFont = new Font("Lucida Grande", Font.ITALIC, 13);

		tfId.setText(idHint);	// 텍스트 필드 힌트의 기본 문자
		tfId.setFont(lostFont);	// 텍스트 필드 힌트의 기본 폰트
		tfId.setForeground(Color.LIGHT_GRAY);	// 텍스트 필드 힌트의 기본 색상
		tfId.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (tfId.getText().equals("")) {
					tfId.setText(idHint);
					tfId.setFont(lostFont);
					tfId.setForeground(Color.LIGHT_GRAY);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (tfId.getText().equals(idHint)) {
					tfId.setText("");
					tfId.setFont(gainFont);
					tfId.setForeground(Color.BLACK);
				}
			}
		});
	}
	
}

	
