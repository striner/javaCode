package demo3;

import java.awt.Color;  
import java.awt.Container;  
import java.awt.Cursor;  
import java.awt.Font;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JCheckBox;  
import javax.swing.JComboBox;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JPasswordField;  
import javax.swing.JRootPane;  
import javax.swing.JTextField;  
  
public class QQ extends JFrame{ 
      
    private static final long serialVersionUID = -6788045638380819221L;  
    //用户名  
    private JTextField ulName;  
    //密码  
    private JPasswordField ulPasswd;  
    //小容器  
    private JLabel j1;  
    private JLabel j2;  
    private JLabel j3;  
    private JLabel j4;  
    //小按钮  
    private JButton b1;  
    private JButton b2;  
    private JButton b3;  
    //复选框  
    private JCheckBox c1;  
    private JCheckBox c2;  
    //列表框  
    private JComboBox<String> cb1;  
    /** 
     * 初始化QQ登录页面 
     * */  
    public QQ(){  
        //设置登录窗口标题  
        this.setTitle("QQ登录");  
        //去掉窗口的装饰(边框)  
//      this.setUndecorated(true);   
        //采用指定的窗口装饰风格  
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);  
        //窗体组件初始化  
        init();  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //设置布局为绝对定位  
        this.setLayout(null);  
        this.setBounds(0, 0, 355, 265);  
        //设置窗体的图标  
        Image img0 = new ImageIcon("D:/logo.png").getImage();  
        this.setIconImage(img0);  
        //窗体大小不能改变  
        this.setResizable(false);  
        //居中显示  
        this.setLocationRelativeTo(null);  
        //窗体显示  
        this.setVisible(true);  
    }  
    /** 
     * 窗体组件初始化 
     * */  
    public void init(){  
        //创建一个容器,其中的图片大小和setBounds第三、四个参数要基本一致(需要自己计算裁剪)  
        Container container = this.getContentPane();  
        j1 = new JLabel();  
        //设置背景色  
        Image img1 = new ImageIcon("D:/bgimg.png").getImage();  
        j1.setIcon(new ImageIcon(img1));  
        j1.setBounds(0, 0, 355, 265);  
        //qq头像设定  
        j2 = new JLabel();  
        Image img2 = new ImageIcon("D:/hdimg.png").getImage();  
        j2.setIcon(new ImageIcon(img2));  
        j2.setBounds(40, 95, 50, 53);  
        //用户名输入框  
        ulName = new JTextField();  
        ulName.setBounds(100, 100, 150, 20);  
        //注册账号  
        j3 = new JLabel("注册账号");  
        j3.setBounds(260, 100, 70, 20);  
        //密码输入框  
        ulPasswd = new JPasswordField();  
        ulPasswd.setBounds(100, 130, 150, 20);  
        //找回密码  
        j4= new JLabel("找回密码");  
        j4.setBounds(260, 130, 70, 20);  
        //记住密码  
        c1 = new JCheckBox("记住密码");  
        c1.setBounds(105, 155, 80, 15);  
        //自动登陆  
        c2 = new JCheckBox("自动登陆");  
        c2.setBounds(185, 155, 80, 15);  
        //用户登陆状态选择  
        cb1 = new JComboBox<String>();  
        cb1.addItem("在线");  
        cb1.addItem("隐身");  
        cb1.addItem("离开");  
        cb1.setBounds(40, 150, 55, 20);  
        //登陆按钮  
        b1 = new JButton("登录");  
        //设置字体和颜色和手形指针  
        b1.setFont(new Font("宋体", Font.PLAIN, 12));  
        b1.setForeground(Color.RED);  
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
        b1.setBounds(280, 200, 65, 20);  
        //给按钮添加  
        b1.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                String cmd = e.getActionCommand();  
                if("登录".equals(cmd)){  
                    String username = ulName.getText();  
                    String userpassword = ulPasswd.getText();  
                    if(username.equals("tskk") && userpassword.equals("123456")){  
                        JOptionPane.showConfirmDialog(null, "登录成功");  
                    }else{  
                        JOptionPane.showConfirmDialog(null, "登录失败");  
                    }  
                }  
            }  
        });  
        //多账号  
        b2 = new JButton("多账号");  
        b2.setBounds(5, 200, 75, 20);  
        //设置  
        b3 = new JButton("设置");  
        b3.setBounds(100, 200, 65, 20);  
        //所有组件用容器装载  
        j1.add(j2);  
        j1.add(j3);  
        j1.add(j4);  
        j1.add(c1);  
        j1.add(c2);  
        j1.add(cb1);  
        j1.add(b1);  
        j1.add(b2);  
        j1.add(b3);  
        container.add(j1);  
        container.add(ulName);  
        container.add(ulPasswd);  
    }  
    public static void main(String[] args) {  
        new QQ();  
    }  
}  