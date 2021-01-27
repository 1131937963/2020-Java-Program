/*
*****用户登录界面
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame {
	private JLabel author_text;//
	private JLabel username_Label;	//用户名标签。
	private JLabel password_Label;	//密码标签。
	private JTextField username_Text;	//用户名文本域。
	private JPasswordField password_Text;	//密码文本域。
	private JButton login_Button;	//登陆按钮。
	private JButton Zhuche_Button;	//注册按钮。
	private JButton fund_Button;//找回密码按钮
	private JFrame jf;	//当前窗口 。
	public LoginFrame(){
		super("学生管理系统登录界面");
		this.jf = this;
		this.setLayout(null);
		this.setSize(700,500);
		Container c = this.getContentPane();//获得JFrame的内容面板
		JLabel jl3=new JLabel(new ImageIcon("src/login.png"));
		jf.add(jl3);
		jl3.setBounds(0, 0-30, 700, 300);
		JLabel U=new JLabel(new ImageIcon("src/User.png"));
		U.setBounds(225,300,20,20);
		jf.add(U);
		JLabel M=new JLabel(new ImageIcon("src/密码.png"));
		M.setBounds(225,350,20,20);
		jf.add(M);
		author_text = new JLabel("@author:  敖佳强");
		author_text.setBounds(300, 440, 500, 20);
		c.add(author_text);
		username_Label = new JLabel("用户名:");
		username_Label.setBounds(250, 300, 50, 20);
		c.add(username_Label);
		
		username_Text = new JTextField();
		username_Text.setBounds(300, 300, 120, 20);
		username_Text.grabFocus();//获得光标。
		c.add(username_Text);
		
		password_Label = new JLabel("密码:");
		password_Label.setBounds(260, 350, 50, 20);
		c.add(password_Label);
		
		password_Text = new JPasswordField();
		password_Text.setBounds(300, 350, 120, 20);
		c.add(password_Text);

//		JLabel D=new JLabel(new ImageIcon("src/确认.png"));
//		D.setBounds(230,400,20,20);
//		jf.add(D);

		fund_Button = new JButton("找回密码");
		fund_Button.setBounds(310, 400, 90, 20);
		fund_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();	//当前窗口关闭。
				UserfundFrame studentSystemFrame = new UserfundFrame();	//打开找回界面

			}
		});
		c.add(fund_Button);

		login_Button = new JButton("登录");
		login_Button.setBounds(250, 400, 60, 20);
		login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = username_Text.getText().trim();
				String password = new String(password_Text.getPassword());
				if(username.equals("")){//判断输入框是否为空；
					JOptionPane.showMessageDialog(jf, "用户名不能为空","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(password.equals("")){
					JOptionPane.showMessageDialog(jf, "密码不能为空","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				//登录
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				ManageHelper helper = new ManageHelper();
				if(helper.Login(user)){	//
						JOptionPane.showMessageDialog(jf, "登陆成功！");
						jf.dispose();
						user.setIsLogin(1);//修改成为已经登陆。
						helper.Update_IsLogin(user);
						user.setPassword("");//重置密码
						SystemMainFrame frame = new SystemMainFrame(user);
						return ;
				}else{
					JOptionPane.showMessageDialog(jf, "用户名或密码错误！");
					 Reset();
					return ;
				}
				
			}
		});
		c.add(login_Button);

		Zhuche_Button = new JButton("注册");
		Zhuche_Button.setBounds(400, 400, 60, 20);
		Zhuche_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();	//当前窗口关闭。
				UserZhucheFrame studentSystemFrame = new UserZhucheFrame();	//打开注册界面
				
			}
		});
		c.add(Zhuche_Button);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	//设置大小不可改变。
		WindowUI.setFrameCenter(this);//设置窗口居中。
		try {
			Image img = ImageIO.read(this.getClass().getResource("/2.png"));
			this.setIconImage(img);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setVisible(true);	//设置窗体可见。
	}
		public void Reset(){
			username_Text.setText("");
			password_Text.setText("");
		}
}
