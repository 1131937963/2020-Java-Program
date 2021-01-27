/*
**注册模块
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserZhucheFrame extends JFrame {

	private JLabel username_Label;	//用户名标签。
	private JLabel password_Label;	//密码标签。
	private JLabel repassword_Label;	//确认密码标签。
	private JLabel telphone_Label;     //招回电话标签。
	private JTextField username_Text;	//用户名文本域。
	private JPasswordField password_Text;	//密码文本域。
	private JPasswordField repassword_Text;	//确认密码文本域。
	private JPasswordField telphone_Text;//找回电话文本域
	private JButton register_Button;	//注册按钮。
	private JButton cancel_Button;	//取消按钮。
	private JFrame jf;	//当前窗口 。
	
	public UserZhucheFrame(){
		super("学生信息管理系统注册界面");
		this.jf = this;
		this.setLayout(null);
		this.setSize(400,350);
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);
		username_Label = new JLabel("用户名:");
		username_Label.setBounds(100, 60, 50, 20);
		c.add(username_Label);
		
		username_Text = new JTextField();
		username_Text.setBounds(160, 60, 120, 20);
		username_Text.grabFocus();
		c.add(username_Text);
		
		password_Label = new JLabel("密码:");
		password_Label.setBounds(110, 110, 50, 20);
		c.add(password_Label);
		
		password_Text = new JPasswordField();
		password_Text.setBounds(160, 110, 120, 20);
		c.add(password_Text);
		
		repassword_Label = new JLabel("确认密码:");
		repassword_Label.setBounds(90, 162, 70, 20);
		c.add(repassword_Label);
		
		repassword_Text = new JPasswordField();
		repassword_Text.setBounds(160, 162, 120, 20);
		c.add(repassword_Text);

		telphone_Label = new JLabel("找回电话:");
		telphone_Label.setBounds(90,210,70,20);
		c.add(telphone_Label);

		telphone_Text = new JPasswordField();
		telphone_Text.setBounds(160,210,120,20);
		c.add(telphone_Text);
		
		register_Button = new JButton("注册");
		register_Button.setBounds(90, 260, 60, 20);
		register_Button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String username = username_Text.getText().trim();
				String password = new String(password_Text.getPassword());
				String repassword = new String(repassword_Text.getPassword());
				String telphone = new String(telphone_Text.getPassword());
				if(username.equals("")){
					JOptionPane.showMessageDialog(jf, "用户名不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(password.equals("")){
					JOptionPane.showMessageDialog(jf, "密码不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(repassword.equals("")){
					JOptionPane.showMessageDialog(jf, "确认密码不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(telphone.equals("")){
					JOptionPane.showMessageDialog(jf, "找回电话不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(!password.equals(repassword)){
					JOptionPane.showMessageDialog(jf, "两次密码不一致！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				User user = new User();//创建用户对象
				user.setUsername(username);
				user.setPassword(password);
				user.setTelphone(telphone);
				ManageHelper helper = new ManageHelper();
				if(helper.Register(user)){//注册处理
					JOptionPane.showMessageDialog(jf, "注册成功！");
					jf.dispose();
					LoginFrame frame = new LoginFrame();//返回登陆页面。
					return ;
				}else{
					JOptionPane.showMessageDialog(jf, "注册失败！");
					Reset();
					return ; 
				}
				
			}
		});
		c.add(register_Button);	//添加"注册"按钮。
		
		
		cancel_Button = new JButton("取消");	//创建"取消"按钮。
		cancel_Button.setBounds(250, 260, 60, 20);	//设置"取消"按钮位置。
		//注册"取消"按钮事件监听。
		cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.dispose();//关闭当前页面。
				LoginFrame studentSystemLoginFrame = new LoginFrame();//打开登陆页面。
			}
		});
		c.add(cancel_Button);	//添加"取消"按钮。
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
	
	//重置
	public void Reset(){
		username_Text.setText("");
		password_Text.setText("");
		repassword_Text.setText("");
	}
}
