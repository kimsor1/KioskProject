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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.FocusAdapter;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register extends JDialog {
	
	/*
	 * Description : Shoeskiosk Register user class
	 * Author : Woody Jo
	 * Version : 1.0.0
	 * Date : 2023.12.31
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbBack;
	private JTextField tfId;
	private JTextField tfPw;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private JLabel lbOk;
	private JLabel lbCancel;
	private JLabel lblNewLabel_2;
	private JButton btnCheckDup;
	private JButton btnTitle;
	private JPasswordField pw;
	private boolean flag = false;
	private JTextField tfName;

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
		contentPanel.add(getTfName());
		contentPanel.add(getTfPhone());
		contentPanel.add(getTfAddress());
		contentPanel.add(getLbOk());
		contentPanel.add(getLbCancel());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getPw());
		contentPanel.add(getLbBack());
		contentPanel.add(getBtnTitle());
	}
	private JLabel getLbBack() {
		if (lbBack == null) {
			lbBack = new JLabel("");
			lbBack.setBounds(0, -27, 512, 683);
			lbBack.setIcon(new ImageIcon(Register.class.getResource("/com/javalec/images/Register.png")));
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
					focusLostId();
				}
			});
			tfId.setText("아이디 입력");
			tfId.setForeground(Color.LIGHT_GRAY);
			tfId.setBounds(57, 210, 416, 47);
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
					
					char[] pass = pw.getPassword();
					String sPass = new String(pass);
					
					if (sPass.trim().length() > 0) {
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
			pw.setBounds(57, 270, 416, 47);
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
			tfPw.setBounds(57, 270, 416, 47);
		}
		return tfPw;
	}
	
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (tfName.getText().equals("이름 입력")) {
						tfName.setText("");
						tfName.setForeground(Color.BLACK);
						tfName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (tfName.getText().equals("")) {
						tfName.setText("이름 입력");
						tfName.setForeground(Color.LIGHT_GRAY);
						tfName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
					focusLostName();
				}
			});
			tfName.setText("이름 입력");
			tfName.setForeground(Color.LIGHT_GRAY);
			tfName.setColumns(10);
			tfName.setBounds(57, 330, 416, 47);
		}
		return tfName;
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
					focusLostPhone();
				}
				
			});
			tfPhone.setText("전화번호 입력");
			tfPhone.setForeground(Color.LIGHT_GRAY);
			tfPhone.setColumns(10);
			tfPhone.setBounds(57, 390, 416, 47);
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
			tfAddress.setBounds(57, 450, 416, 47);
		}
		return tfAddress;
	}
	
	
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("확인");
			lbOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (register()) {
						completeRegister();
						JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다", "알림", JOptionPane.ERROR_MESSAGE);
						Login login = new Login();
						login.setVisible(true);
						dispose();
						
					}
				}
			});
			lbOk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbOk.setForeground(Color.LIGHT_GRAY);
			lbOk.setBounds(183, 550, 61, 16);
		}
		return lbOk;
	}
	private JLabel getLbCancel() {
		if (lbCancel == null) {
			lbCancel = new JLabel("취소");
			lbCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cancel();
				}
			});
			lbCancel.setForeground(Color.LIGHT_GRAY);
			lbCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbCancel.setBounds(300, 550, 61, 16);
		}
		return lbCancel;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("|");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(227, 550, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JButton getBtnCheckDup() {
		if (btnCheckDup == null) {
			btnCheckDup = new JButton("중복확인");
			btnCheckDup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkId();
					flag = true;
				}
			});
			btnCheckDup.setBackground(Color.GRAY);
			btnCheckDup.setBounds(393, 220, 71, 29);
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
	
	// 로고 클릭시 메인 화면으로 이동
	private void moveBack() {
		Main window = new Main();
		window.main(null);
		
		this.setVisible(false);
	}
	
	// 아이디 정규 표현
	private void focusLostId() {
		// 문자열 형태의 정규표현식 문법을 정규식 패턴으로 변환
		Pattern pattern = Pattern.compile("^[a-z|A-Z|0-9]*$"); // Pattern 객체로 컴파일된 정규식은 뒤의 Matcher 클래스에서 사용된다
		Matcher matcher = pattern.matcher(tfId.getText()); // 패턴 객체로 문자열을 필터링한뒤 그 결과값들을 담은 매처 객체 생성

		if (!tfId.getText().equals("아이디 입력")) {
			if (!matcher.matches()) {
				JOptionPane.showMessageDialog(null, "영어와 숫자만 입력하세요");
				tfId.requestFocus();
			}	
		}
	}
	
	private void focusLostName() {
		Pattern pattern = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|가-힣]*$"); // Pattern 객체로 컴파일된 정규식은 뒤의 Matcher 클래스에서 사용된다
		Matcher matcher = pattern.matcher(tfName.getText()); // 패턴 객체로 문자열을 필터링한뒤 그 결과값들을 담은 매처 객체 생성

		if (!tfName.getText().equals("이름 입력")) {
			if (!matcher.matches()) {
				JOptionPane.showMessageDialog(null, "정확한 이름을 입력하세요");
				tfName.requestFocus();
			}	
		}
	}
	
	// 전화번호 정규식
	private void focusLostPhone() {
		// 정확하지 않은 전화번호 입력시 에러 표현
		Pattern pattern = Pattern.compile("^\\d{3}-\\d{3,4}-\\d{4}$");
		Matcher matcher = pattern.matcher(tfPhone.getText());

		if (!tfPhone.getText().equals("전화번호 입력")) {
			if (!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "010-0000-0000 타입 형식의 번호를 입력하세요");
			tfPhone.requestFocus();

			}

		}

	}
	
	// 아이디 등록 입력 체크, 중복 체크
	private boolean register() {
		boolean check = true;
		String checkId = tfId.getText().trim();
		char[] pass = pw.getPassword();
		String sPass = new String(pass);
		Dao_Login dao = new Dao_Login(checkId, sPass.trim());
		
		if (tfId.getText().equals("아이디 입력")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			tfId.requestFocus();
			check = false;
		}
		else if (! flag) {
			JOptionPane.showMessageDialog(null, "중복 체크를 해주세요", "알림", JOptionPane.ERROR_MESSAGE);
			btnCheckDup.requestFocus();
			check = false;
		}
		else if (tfPw.getText().equals("비밀번호 입력") && sPass.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			pw.requestFocus();
			check = false;
		}
		else if (tfName.getText().equals("이름 입력")) {
			JOptionPane.showMessageDialog(null, "이름 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			tfName.requestFocus();
			check = false;
		}
		else if (tfPhone.getText().equals("전화번호 입력")) {
			JOptionPane.showMessageDialog(null, "전화번호를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			tfPhone.requestFocus();
			check = false;
		}
		else if (tfAddress.getText().equals("주소 입력")) {
			JOptionPane.showMessageDialog(null, "주소를 입력하세요", "알림", JOptionPane.ERROR_MESSAGE);
			tfAddress.requestFocus();
			check = false;
		}
		else if (dao.checkIdAction() == false) {
			JOptionPane.showMessageDialog(null, "중복 체크가 완료되지 않았습니다", "알림", JOptionPane.ERROR_MESSAGE);
			btnCheckDup.requestFocus();
			check = false;
		}
		
		return check;
		
	}
	
	// 아이디 체크
	private void checkId() {
		flag = false;
		// char의 pw를 string으로 변환
		char[] pass = pw.getPassword();
		String sPass = new String(pass);
		
		String checkId = tfId.getText().trim();
		Dao_Login dao = new Dao_Login(checkId, sPass.trim());
		
		if (checkId.equals("아이디 입력")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
		}
		else {
			if (dao.checkIdAction() == false) {
				JOptionPane.showMessageDialog(null, "중복된 아이디입니다", "알림", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	// 취소 버튼
	private void cancel() {
		int checkCancle = JOptionPane.showConfirmDialog(null, "회원가입을 취소 하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		
		if (checkCancle == JOptionPane.YES_OPTION) {
			Login login = new Login();
			login.setVisible(true);
			
			this.setVisible(false);
		}
	}
	
	private void completeRegister() {
		char[] pass = pw.getPassword();
		String sPass = new String(pass);
		
		String id = tfId.getText().trim();
		String name = tfName.getText().trim();
		String phone = tfPhone.getText().trim();
		String address = tfAddress.getText().trim();
		String pw = sPass.trim();
		
		Dao_Login dao = new Dao_Login(id, name, phone, address, pw);
		
		dao.completeRegister();
		
	}
	
}

	
