package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.base.Main;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindPw extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbBack;
	private JButton btnTitle;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private JTextField tfBirth;
	private JLabel lbOk;
	private JLabel lblNewLabel_2;
	private JLabel lbCancel;
	private JTextField tfId;

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
		contentPanel.add(getTfName());
		contentPanel.add(getTfId());
		contentPanel.add(getTfPhone());
		contentPanel.add(getTfAddress());
		contentPanel.add(getTfBirth());
		contentPanel.add(getLbOk());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getLbCancel());
		contentPanel.add(getLbBack());
		contentPanel.add(getBtnTitle());
	}
	private JLabel getLbBack() {
		if (lbBack == null) {
			lbBack = new JLabel("");
			lbBack.setBounds(0, 0, 512, 683);
			lbBack.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/images/findPw.png")));
		}
		return lbBack;
	}
	private JButton getBtnTitle() {
		if (btnTitle == null) {
			btnTitle = new JButton("");
			btnTitle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moveMain();
				}
			});
			btnTitle.setBounds(46, 105, 431, 96);
		}
		return btnTitle;
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
			tfId.setColumns(10);
			tfId.setBounds(57, 218, 416, 47);
		}
		return tfId;
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
			tfName.setBounds(57, 278, 416, 47);
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
			tfPhone.setBounds(57, 338, 416, 47);
			tfPhone.setColumns(10);
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
			tfAddress.setBounds(57, 398, 416, 47);
		}
		return tfAddress;
	}
	private JTextField getTfBirth() {
		if (tfBirth == null) {
			tfBirth = new JTextField();
			tfBirth.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (tfBirth.getText().equals("생년월일 입력")) {
						tfBirth.setText("");
						tfBirth.setForeground(Color.BLACK);
						tfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (tfBirth.getText().equals("")) {
						tfBirth.setText("생년월일 입력");
						tfBirth.setForeground(Color.LIGHT_GRAY);
						tfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					}
					focusLostBirth();
				}
			});
			tfBirth.setForeground(Color.LIGHT_GRAY);
			tfBirth.setText("생년월일 입력");
			tfBirth.setColumns(10);
			tfBirth.setBounds(56, 458, 416, 47);
		}
		return tfBirth;
	}
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("확인");
			lbOk.setForeground(Color.LIGHT_GRAY);
			lbOk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbOk.setBounds(183, 588, 61, 16);
		}
		return lbOk;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("|");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(217, 588, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLbCancel() {
		if (lbCancel == null) {
			lbCancel = new JLabel("취소");
			lbCancel.setForeground(Color.LIGHT_GRAY);
			lbCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lbCancel.setBounds(290, 588, 61, 16);
		}
		return lbCancel;
	}

// ---- Function ----
	private void moveMain() {
		Main window = new Main();
		window.main(null);
		
		this.setVisible(false);
	}
	
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
			JOptionPane.showMessageDialog(null, "010-0000-0000 타입 형식의 번호를 입력하세요.");
			tfPhone.requestFocus();

			}

		}

	}
	
	// 생일 정규식 형식
	private void focusLostBirth() {
		Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
		Matcher matcher = pattern.matcher(tfBirth.getText());
		
		if (!tfBirth.getText().equals("생년월일 입력")) {
			if (!matcher.matches()) {
				JOptionPane.showMessageDialog(null, "1995-01-01 형식의 생일을 입력하세요.");
				tfBirth.requestFocus();
			}
		}
	}
	
}