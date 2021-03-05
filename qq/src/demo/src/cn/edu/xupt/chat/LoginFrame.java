package cn.edu.xupt.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	
	public LoginFrame() {
		init();
	}
	
	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(430, 330);
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);
		
		LoginTopPanel topPanel = new LoginTopPanel();
		this.add(topPanel, BorderLayout.NORTH);
		
		LoginContentPanel contentPanel = new LoginContentPanel();
		this.add(contentPanel, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}
