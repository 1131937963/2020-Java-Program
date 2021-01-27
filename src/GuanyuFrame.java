import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuanyuFrame extends JFrame {

//        private JLabel author_text;//
//        private JLabel username_Label;	//用户名标签。
//        private JLabel password_Label;	//密码标签。
//        private JTextField username_Text;	//用户名文本域。
//        private JPasswordField password_Text;	//密码文本域。
//        private JButton login_Button;	//登陆按钮。
//        private JButton Zhuche_Button;	//注册按钮。
//        private JButton fund_Button;//找回密码按钮
        private JFrame jf;	//当前窗口 。
        public GuanyuFrame(){
//            super("学生管理系统登录界面");
//            this.jf = this;
//            this.setLayout(null);
//            this.setSize(700,500);
//            Container c = this.getContentPane();//获得JFrame的内容面板


            JFrame frame=new JFrame("学生管理系统登录界面");    //创建Frame窗口
            JPanel jp=new JPanel();    //创建一个JPanel对象
            String b;
            Read data = new Read();
            b = data.read();
            JTextArea jta=new JTextArea(b,18,58);
            jta.setLineWrap(true);    //设置文本域中的文本为自动换行
            jta.setForeground(Color.BLACK);    //设置组件的背景色
            jta.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
            jta.setBackground(Color.WHITE);    //设置按钮背景色
            JScrollPane jsp=new JScrollPane(jta);    //将文本域放入滚动窗口
            Dimension size=jta.getPreferredSize();    //获得文本域的首选大小
            jsp.setBounds(500,350,size.width,size.height);
            jp.add(jsp);    //将JScrollPane添加到JPanel容器中
            this.add(jp);    //将JPanel容器添加到JFrame容器中
            this.setBackground(Color.LIGHT_GRAY);
            this.setSize(578,400);    //设置JFrame容器的大小


//            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}


