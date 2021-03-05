package demo2;

public class frame {
	public static void main(String[] args) {
		           Frame f = new Frame();
		         
		         //关闭窗体
		           f.addWindowListener(new WindowAdapter() {
		               @Override
		               public void windowClosing(WindowEvent e) {
		                   System.exit(0);
		              }
		          });
		          
		          f.setTitle("QQ登录");//添加标题
		          f.setSize(420, 230);//设置窗体的尺寸
		          f.setLocation(455, 207);//设置窗体出现坐标
		          f.setLayout(null);//清除窗体默认布局
		          f.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\qq.png"));//设置图标
		          f.setResizable(false);//禁止窗体改变尺寸
		          
		      
		          
		          //账号标签
		          Label user = new Label("账号:");
		          user.setLocation(75, 50);
		          user.setSize(50, 25);
		          user.setFont(new Font("微软雅黑",Font.BOLD,16));
		         f.add(user);
		          
		          //密码标签
		          Label password = new Label("密码:");
		          password.setLocation(75, 100);
		          password.setSize(50, 25);
		          password.setFont(new Font("微软雅黑",Font.BOLD,16));
		         f.add(password);
		          
		          //输入账号的文本框
		          TextField t1 = new TextField();
		          //键盘输入监听
		          t1.addKeyListener(new KeyAdapter() {
		              @Override
		              public void keyTyped(KeyEvent e) {
		                  int key = e.getKeyChar();
		                 if(key>=KeyEvent.VK_0 && key<=KeyEvent.VK_9){
		                      
		                  }else{
		                      e.consume();
		                 }
		              }
		          });
		          t1.setSize(220,25);
		          t1.setLocation(130, 50);
		          t1.setFont(new Font("微软雅黑",Font.PLAIN,16));
		          f.add(t1);
		         
		          //输入密码的文本框
		          TextField t2 = new TextField();
		          t2.setEchoChar('*');
		         t2.setSize(220,25);
		          t2.setLocation(130, 100);
		          t2.setFont(new Font("微软雅黑",Font.PLAIN,16));
		          f.add(t2);
		          
		          //登录按钮
		          Button login = new Button("登录");
		         //按钮触发事件
		          login.addActionListener(new ActionListener() {
		             
		              @Override
		              public void actionPerformed(ActionEvent arg0) {
		                  String zh = t1.getText();
		                  String ma = t2.getText();
		                  if(zh.equals("34598700") && ma.equals("meinv123")){
		                      System.out.println("登录成功");
		                 }else{
		                      JOptionPane.showMessageDialog(f, "账号或密码输入错误");
		                      t1.setText("");
		                      t2.setText("");
		                  }
		              }
		          });
		          login.setLocation(100, 160);//按钮在窗体中的坐标
		          login.setSize(75, 30);//设计按钮的尺寸
		          f.add(login);//把按钮元素添加到窗体中
		          
		          //注册按钮
		          Button reg = new Button("注册");
		         //触发事件
		         reg.addActionListener(new ActionListener() {
		              
		              @Override
		              public void actionPerformed(ActionEvent e) {
		                  new Reg();
		              }
		          });
		          reg.setLocation(250, 160);//按钮在窗体中的坐标
		          reg.setSize(75, 30);//设计按钮的尺寸
		          f.add(reg);//把按钮元素添加到窗体中
		          
		          f.setVisible(true);//设置窗体的可见性
		 
		     }
}
