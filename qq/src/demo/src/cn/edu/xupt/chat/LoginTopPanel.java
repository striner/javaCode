package cn.edu.xupt.chat;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginTopPanel extends JPanel {
	
	private JButton btnMin;
	private JButton btnClose;
	private JButton btnSetting;
	
	public LoginTopPanel() {
		init();
	}
	
	private void init() {
		this.setPreferredSize(new Dimension(0, 182));
		((FlowLayout) this.getLayout()).setAlignment(FlowLayout.RIGHT);
		((FlowLayout) this.getLayout()).setHgap(0);
		((FlowLayout) this.getLayout()).setVgap(0);
		
		btnClose = new JButton() {
			protected void paintComponent(Graphics g) {
				ImageIcon iconImage = new ImageIcon(SysConstants.SYS_IMG_PATH + "/white_opaque.png");
				g.drawImage(iconImage.getImage(), 0, 0, LoginTopPanel.this);
			};
		};
		btnClose.setPreferredSize(new Dimension(30, 30));
		btnClose.setOpaque(false);
		btnClose.setBorderPainted(false);
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		this.add(btnClose);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		ImageIcon iconImage = new ImageIcon(SysConstants.SYS_IMG_PATH + "/bg_logo.gif");
		g.drawImage(iconImage.getImage(), 0, 0, this);
	}
}
