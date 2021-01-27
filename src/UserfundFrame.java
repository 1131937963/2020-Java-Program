import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserfundFrame extends JFrame {

        private JLabel username_Label;	//用户名标签。
        private JLabel telphone_label;	//电话标签。
        private JLabel retelphone_label;	//确认密码标签。
        private JTextField username_Text;	//用户名文本域。
        private JPasswordField telphone_Text;	//密码文本域。
        private JPasswordField retelphone_Text;	//确认密码文本域。
        private JButton fund_Button;	//找回按钮。
        private JButton cancel_Button;	//取消按钮。
        private JFrame jf;	//当前窗口 。

        public UserfundFrame(){
            super("学生信息管理系统找回密码界面");
            this.jf = this;
            this.setLayout(null);
            this.setSize(400,300);
            Container c = this.getContentPane();
            c.setBackground(Color.WHITE);
            username_Label = new JLabel("用户名:");
            username_Label.setBounds(100, 60, 50, 20);
            c.add(username_Label);

            username_Text = new JTextField();
            username_Text.setBounds(160, 60, 120, 20);
            username_Text.grabFocus();
            c.add(username_Text);

            telphone_label = new JLabel("联系电话:");
            telphone_label.setBounds(90, 110, 70, 20);
            c.add(telphone_label);

            telphone_Text = new JPasswordField();
            telphone_Text.setBounds(160, 110, 120, 20);
            c.add(telphone_Text);

            retelphone_label = new JLabel("确认电话：");
            retelphone_label.setBounds(90, 162, 70, 20);
            c.add(retelphone_label);

            retelphone_Text = new JPasswordField();
            retelphone_Text.setBounds(160, 162, 120, 20);
            c.add(retelphone_Text);

            fund_Button = new JButton("找回密码");
            fund_Button.setBounds(90, 210, 100, 20);
            fund_Button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String username = username_Text.getText().trim();
                    String telphone = new String(telphone_Text.getPassword());
                    String retelphone = new String(retelphone_Text.getPassword());
                    if(username.equals("")){
                        JOptionPane.showMessageDialog(jf, "用户名不能为空！","", JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                    if(telphone.equals("")){
                        JOptionPane.showMessageDialog(jf, "联系电话不能为空！","", JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                    if(retelphone.equals("")){
                        JOptionPane.showMessageDialog(jf, "确认电话不能为空！","", JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                    if(!telphone.equals(retelphone)){
                        JOptionPane.showMessageDialog(jf, "两次电话输入不一致！","", JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                    User user = new User();//创建用户对象
                    user.setUsername(username);
                    user.setTelphone(telphone);
                    ManageHelper helper = new ManageHelper();
                    System.out.println("12");
                    if(helper.Found(user)){//找回密码处理
                        JOptionPane.showMessageDialog(jf, "找回成功！");
                        Sqlfunc helpe;	//数据库对象
                        String b = null;
                        helpe = new Sqlfunc();
                        User newUser = helpe.getUser(user); //获得用户数据
                        b = newUser.getPassword();
                        JOptionPane.showMessageDialog(jf,b,"您账户的密码是：",JOptionPane.WARNING_MESSAGE);

                        jf.dispose();
                        LoginFrame frame = new LoginFrame();
                        return ;
                    }else{
                        JOptionPane.showMessageDialog(jf, "找回失败！");
                        Reset();
                        return ;
                    }

                }
            });
            c.add(fund_Button);
            cancel_Button = new JButton("取消");
            cancel_Button.setBounds(250, 210, 60, 20);
            cancel_Button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    jf.dispose();
                    LoginFrame LoginFrame = new LoginFrame();
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
            telphone_Text.setText("");
            retelphone_Text.setText("");
        }
    }

