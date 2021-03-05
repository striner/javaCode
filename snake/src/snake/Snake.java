package snake;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		//frame.setTitle("Snake");
		frame.setBounds(200, 10, 900, 720);
		frame.setResizable(false);    //是否可以重定向
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设定点击关闭frame所触发的事件
		
		SnakePanel panel = new SnakePanel();
		frame.add(panel);
		
		frame.setVisible(true);
	}
}
