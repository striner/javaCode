package demo2;

public class frame {
	public static void main(String[] args) {
		           Frame f = new Frame();
		         
		         //�رմ���
		           f.addWindowListener(new WindowAdapter() {
		               @Override
		               public void windowClosing(WindowEvent e) {
		                   System.exit(0);
		              }
		          });
		          
		          f.setTitle("QQ��¼");//��ӱ���
		          f.setSize(420, 230);//���ô���ĳߴ�
		          f.setLocation(455, 207);//���ô����������
		          f.setLayout(null);//�������Ĭ�ϲ���
		          f.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\qq.png"));//����ͼ��
		          f.setResizable(false);//��ֹ����ı�ߴ�
		          
		      
		          
		          //�˺ű�ǩ
		          Label user = new Label("�˺�:");
		          user.setLocation(75, 50);
		          user.setSize(50, 25);
		          user.setFont(new Font("΢���ź�",Font.BOLD,16));
		         f.add(user);
		          
		          //�����ǩ
		          Label password = new Label("����:");
		          password.setLocation(75, 100);
		          password.setSize(50, 25);
		          password.setFont(new Font("΢���ź�",Font.BOLD,16));
		         f.add(password);
		          
		          //�����˺ŵ��ı���
		          TextField t1 = new TextField();
		          //�����������
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
		          t1.setFont(new Font("΢���ź�",Font.PLAIN,16));
		          f.add(t1);
		         
		          //����������ı���
		          TextField t2 = new TextField();
		          t2.setEchoChar('*');
		         t2.setSize(220,25);
		          t2.setLocation(130, 100);
		          t2.setFont(new Font("΢���ź�",Font.PLAIN,16));
		          f.add(t2);
		          
		          //��¼��ť
		          Button login = new Button("��¼");
		         //��ť�����¼�
		          login.addActionListener(new ActionListener() {
		             
		              @Override
		              public void actionPerformed(ActionEvent arg0) {
		                  String zh = t1.getText();
		                  String ma = t2.getText();
		                  if(zh.equals("34598700") && ma.equals("meinv123")){
		                      System.out.println("��¼�ɹ�");
		                 }else{
		                      JOptionPane.showMessageDialog(f, "�˺Ż������������");
		                      t1.setText("");
		                      t2.setText("");
		                  }
		              }
		          });
		          login.setLocation(100, 160);//��ť�ڴ����е�����
		          login.setSize(75, 30);//��ư�ť�ĳߴ�
		          f.add(login);//�Ѱ�ťԪ����ӵ�������
		          
		          //ע�ᰴť
		          Button reg = new Button("ע��");
		         //�����¼�
		         reg.addActionListener(new ActionListener() {
		              
		              @Override
		              public void actionPerformed(ActionEvent e) {
		                  new Reg();
		              }
		          });
		          reg.setLocation(250, 160);//��ť�ڴ����е�����
		          reg.setSize(75, 30);//��ư�ť�ĳߴ�
		          f.add(reg);//�Ѱ�ťԪ����ӵ�������
		          
		          f.setVisible(true);//���ô���Ŀɼ���
		 
		     }
}
