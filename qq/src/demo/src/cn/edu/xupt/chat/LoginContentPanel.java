package cn.edu.xupt.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class LoginContentPanel extends JPanel {
	
	private JPanel moreAccountPanel;
	private JPanel headerPanel;
	private JPanel infoPanel;
	private JPanel helpPanel;
	private JPanel codePanel;

	public LoginContentPanel() {
		init();
	}
	
	private void init() {
		this.setBackground(new Color(235, 242, 249));
		((FlowLayout) this.getLayout()).setHgap(0);
		((FlowLayout) this.getLayout()).setVgap(0);
		
		moreAccountPanel = new JPanel();
		moreAccountPanel.setPreferredSize(new Dimension(40, 148));
		moreAccountPanel.setBackground(Color.black);
		this.add(moreAccountPanel);
		
		headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(80, 148));
		headerPanel.setBackground(Color.BLUE);
		this.add(headerPanel);
		
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(220, 148));
		infoPanel.setBackground(Color.CYAN);
		this.add(infoPanel);
		
		helpPanel = new JPanel();
		helpPanel.setPreferredSize(new Dimension(50, 148));
		helpPanel.setBackground(Color.GRAY);
		this.add(helpPanel);
		
		codePanel = new JPanel();
		codePanel.setPreferredSize(new Dimension(40, 148));
		codePanel.setBackground(Color.ORANGE);
		this.add(codePanel);
	}
}
