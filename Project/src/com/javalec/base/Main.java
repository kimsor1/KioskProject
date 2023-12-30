package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	private JFrame frame;
	private JLabel lbBackgroundImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.BOLD, 27));
		frame.setTitle("메인 화면");
		frame.setBounds(100, 100, 512, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLbBackgroundImage());
	}
	private JLabel getLbBackgroundImage() {
		if (lbBackgroundImage == null) {
			lbBackgroundImage = new JLabel("");
			lbBackgroundImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clickMain();
				}
			});
			lbBackgroundImage.setBounds(0, 0, 512, 683);
			lbBackgroundImage.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/images/MainBack.png")));
		}
		return lbBackgroundImage;
	}
	
	// ---- Fucntion ----
	
	private void clickMain() {
		// Main 프레임 오프
		frame.setVisible(false);
		
		// Login 창 띄우기
		Login login = new Login();
		login.setVisible(true);
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		if(b == true) {
			frame.setVisible(true);
		}
	}
	
	
}
