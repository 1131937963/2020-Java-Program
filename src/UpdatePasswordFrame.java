/*
**修改密码
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePasswordFrame extends JDialog {

	private JLabel password_Label;	//原密码标签。
	private JLabel newpassword_Label;	//新密码标签。
	private JLabel repassword_Label;	//确认密码标签。
	private JPasswordField password_Text;	//原密码文本域。
	private JPasswordField newpassword_Text;	//新密码文本域。
	private JPasswordField repassword_Text;	//确认密码文本域。
	private JButton confirm_Button;	//确认按钮。
	private JButton cancel_Button;	//取消按钮。
	private JDialog jd;	//当前窗口 。
	private User user;

	public UpdatePasswordFrame(JFrame owner, String title, boolean modal, User user){
		super(owner, title, modal);
		this.user = user;
		this.jd = this;
		this.setLayout(null);
		this.setSize(400,300);
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);
		password_Label = new JLabel("原密码:");
		password_Label.setBounds(100, 60, 50, 20);
		c.add(password_Label);
		
		password_Text = new JPasswordField();
		password_Text.setBounds(160, 60, 120, 20);
		password_Text.grabFocus();
		c.add(password_Text);
		
		newpassword_Label = new JLabel("新密码:");
		newpassword_Label.setBounds(100, 110, 50, 20);
		c.add(newpassword_Label);
		
		newpassword_Text = new JPasswordField();
		newpassword_Text.setBounds(160, 110, 120, 20);
		c.add(newpassword_Text);
		
		repassword_Label = new JLabel("确认密码");
		repassword_Label.setBounds(100, 162, 70, 20);
		c.add(repassword_Label);
		
		repassword_Text = new JPasswordField();
		repassword_Text.setBounds(160, 162, 120, 20);
		c.add(repassword_Text);
		
		confirm_Button = new JButton("确认");
		confirm_Button.setBounds(90, 210, 60, 20);
		confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(password_Text.getPassword());
				String repassword = new String(repassword_Text.getPassword());
				String newpassword = new String(newpassword_Text.getPassword());
				if(password.equals("")){
					JOptionPane.showMessageDialog(jd, "原密码不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(newpassword.equals("")){
					JOptionPane.showMessageDialog(jd, "新密码不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(repassword.equals("")){
					JOptionPane.showMessageDialog(jd, "确认密码不能为空！","", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(repassword.equals(newpassword)){
					//检查原密码是否正确
					ManageHelper helper = new ManageHelper();
					user.setPassword(password);
					if(helper.Login(user)){//检查原密码是否正确
						helper.update_Password(user, newpassword);
						JOptionPane.showMessageDialog(jd, "修改密码成功！");
						jd.dispose();
						return ;
					}else{
						JOptionPane.showMessageDialog(jd,"原密码不正确！","", JOptionPane.WARNING_MESSAGE);
						Reset();
						return ;
					}
				}else{
					JOptionPane.showMessageDialog(jd,"两次密码不一致","", JOptionPane.WARNING_MESSAGE);
					Reset();
					return ;
				}
				
			}
		});
		c.add(confirm_Button);	//添加"确认"按钮。
		
		
		cancel_Button = new JButton("取消");	//创建"取消"按钮。
		cancel_Button.setBounds(250, 210, 60, 20);	//设置"取消"按钮位置。
		//注册"取消"按钮事件监听。
		cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.dispose();//关闭当前页面。
			
			}
		});
		c.add(cancel_Button);	//添加"取消"按钮。
		

		this.setResizable(false);	//设置大小不可改变。
		WindowUI.setFrameCenter(this);//设置窗口居中。
		this.setVisible(true);	//设置窗体可见。
	}
	
	//重置
	public void Reset(){
		password_Text.setText("");
		repassword_Text.setText("");
		newpassword_Text.setText("");
	}
}
