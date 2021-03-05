package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SnakePanel extends JPanel implements ActionListener, KeyListener {

	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon body = new ImageIcon("body.png");
	
	Random rand = new Random();
	int len = 3;    //蛇的初始长度为3
	
	int score = 0;
	
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	boolean isStart = false;
	boolean isDied = false;
	String diriction = "R";     //蛇的方向

	Timer timer = new Timer(200, this);
	
	public SnakePanel(){
		this.setFocusable(true);  //设定该组件可获得焦点. 该组件上的键盘事件监听器能捕获键盘事件
		this.addKeyListener(this);
		initSetup();
		timer.start();
	}
	
	int foodx = rand.nextInt(34)*25+25;
	int foody = rand.nextInt(24)*25+75;
	
	
	public void paint(Graphics g) {
		this.setBackground(Color.white);    //将背景设置为白色
		title.paintIcon(this, g, 25, 11);    //用画笔g在this画布上画
		g.fillRect(25, 75, 850, 600);     //画出一个方框
		
		/*食物产生位置设定*/
		for (int i = 0; i < len; i++) {
			if (snakex[i] == foodx && snakey[i] == foody) {
				foodx = rand.nextInt(34)*25+25;
				foody = rand.nextInt(24)*25+75;
			}
		}
		
		/*画蛇头*/
		if (diriction.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if (diriction.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if (diriction.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if (diriction.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		
		/*画蛇的身体*/
		for (int i = 1; i < len; i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		if(!isStart) {
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Press space to stop/restart", 300, 300);
		}
		
		if (isDied) {
			timer.stop();
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("YOU DIE! Press space to restart", 250, 300);
		}
		
		food.paintIcon(this, g, foodx, foody);
		
		if (score >= 5) {
			timer = new Timer(150, this);
		}
		if (score >= 10) {
			timer = new Timer(100, this);
		}
		if (score >= 20) {
			timer = new Timer(80, this);
		}
		
		/*得分显示*/
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 10));
		g.drawString("score: " + score, 800, 30);
		g.drawString("length: " + len, 800, 50);
		
	}
	
	
	public void initSetup() {
		isStart = false;
		isDied = false;
		len = 3;
		diriction = "R";
		snakex[0] = rand.nextInt(31)*25+75;
		snakey[0] = rand.nextInt(18)*25+200;
		snakex[1] = snakex[0] - 25;
		snakey[1] = snakey[0];
		snakex[2] = snakex[1] - 25;
		snakey[2] = snakey[0];
		
//		snakex[0] = 100;
//		snakey[0] = 100;
//		snakex[1] = 75;
//		snakey[1] = 100;
//		snakex[2] = 50;
//		snakey[2] = 100;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_SPACE) {
			if (isDied) {
				timer.start();
				initSetup(); 
			} else {
				isStart = !isStart;
			}
		}else if ((keyCode == KeyEvent.VK_W | keyCode == KeyEvent.VK_UP) && diriction != "D") {
			diriction = "U";
		}else if ((keyCode == KeyEvent.VK_S | keyCode == KeyEvent.VK_DOWN) && diriction != "U") {
			diriction = "D";
		}else if ((keyCode == KeyEvent.VK_A | keyCode == KeyEvent.VK_LEFT) && diriction != "R") {
			diriction = "L";
		}else if ((keyCode == KeyEvent.VK_D | keyCode == KeyEvent.VK_RIGHT) && diriction != "L") {
			diriction = "R";
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

		timer.start();
		
		if (isStart) {
			
			/*蛇的移动*/
			for (int i = len; i > 0; i--) {
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}
			
			/*蛇的转向*/
			if (diriction.equals("R")) {
				snakex[0] = snakex[0] + 25;
			} else if (diriction.equals("L")) {
				snakex[0] = snakex[0] - 25;
			} else if (diriction.equals("U")) {
				snakey[0] = snakey[0] - 25;
			} else if (diriction.equals("D")) {
				snakey[0] = snakey[0] + 25;
			}
			
			/*蛇吃食*/
			if (snakex[0] == foodx && snakey[0] == foody) {
				len++;
				score++;
				foodx = rand.nextInt(34)*25+25;
				foody = rand.nextInt(24)*25+75;
			}
			
			/*蛇死亡的判断*/
			if (snakex[0] > 850 || snakex[0] < 25 || snakey[0] < 75 || snakey[0] > 650) {
				isDied = true;
				timer.stop();
			}
			for (int i = 1; i < len; i++) {
				if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
					isDied = true;
				}
			}
			repaint();
		}
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
