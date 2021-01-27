
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

//主界面
public class SystemMainFrame extends JFrame {
	private JMenuBar menuBar;	//应用菜单条。
	private JMenu student_Management;	//"学生管理"菜单。
	private JMenu personal_Management;	//"个人管理"菜单。
	private JMenu information_Management; //"信息关于"菜单。
	private JMenuItem add_Student;	//"添加学生"菜单项。
	private JMenuItem query_Student;	//"查询学生信息"菜单项。
	private JMenuItem modify_Student;	//"修改学生信息"菜单项。
	private JMenuItem delete_Student;	//"删除学生"菜单项。
	private JMenuItem change_Password;	//"修改密码"菜单项。
	private JMenuItem logout;	//"退出登录"菜单项。
	private JMenuItem System_info;//"系统介绍"菜单项。
	private JMenuItem Author_info;//"作者"菜单项。
	private JFrame jf;	//当前窗口。
	private User user;//当前用户。
	public SystemMainFrame(User user){
		super("学生管理系统,欢迎你    "+user.getUsername());
		this.user = user;
		this.jf = this;
		menuBar = new JMenuBar();	//创建菜单条。
		this.setJMenuBar(menuBar);
		student_Management = new JMenu("学生管理");
		menuBar.add(student_Management);
		add_Student = new JMenuItem("添加学生");
		add_Student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddStudentFrame addStudentFrame = new AddStudentFrame(jf,"添加学生",true);
			}
		});
		student_Management.add(add_Student);
		query_Student = new JMenuItem("查询学生信息");
		query_Student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChaXunStudentFrame queryStudentFrame = new ChaXunStudentFrame(jf, "查询学生信息", true);
				
			}
		});
		student_Management.add(query_Student);
		modify_Student = new JMenuItem("修改学生信息");
		modify_Student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XiuGaiStudentFrame modifyStudentFrame = new XiuGaiStudentFrame(jf, "修改学生信息", true);
			}
		});
		student_Management.add(modify_Student);
		delete_Student = new JMenuItem("删除学生");
		delete_Student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStudentFrame deleteStudentFrame = new DeleteStudentFrame(jf, "删除学生", true);
			}
		});
		student_Management.add(delete_Student);
		personal_Management = new JMenu("个人管理");
		menuBar.add(personal_Management);
		logout = new JMenuItem("退出登录");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				//修改登陆状态
				ManageHelper helper = new ManageHelper();
				user.setIsLogin(0);//设置登陆状态为未登录。
				helper.Update_IsLogin(user);
				LoginFrame frame = new LoginFrame();
			}
		});
		change_Password = new JMenuItem("修改密码");
		change_Password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePasswordFrame frame = new UpdatePasswordFrame(jf, "修改密码", true,user);
			}
		});
		personal_Management.add(change_Password);
		personal_Management.add(logout);

		information_Management = new JMenu("关于");
		menuBar.add(information_Management);
		System_info = new JMenuItem("系统介绍");
		System_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//jf.dispose();
//				String b = "系统介绍\n本系统为学生管理系统";
//				String b;
//				Read data = new Read();
//				b = data.read();
//				JOptionPane.showMessageDialog(jf,b,"系统介绍",JOptionPane.WARNING_MESSAGE);

				GuanyuFrame frame = new GuanyuFrame();
			}
		});
		information_Management.add(System_info);

		Author_info = new JMenuItem("关于作者");
		Author_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String b = "\t\t关于作者\n@Author：\t419软件工程1班\t敖佳强\nQQ:\t1131937963";
				JOptionPane.showMessageDialog(jf,b,"关于作者",JOptionPane.WARNING_MESSAGE);
			}
		});
		information_Management.add(Author_info);

		this.setSize(578, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		WindowUI.setFrameCenter(this);//设置窗体居中。
		ImagePancel imagePanel = new ImagePancel();

		setContentPane(imagePanel);//设置为内容面板
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("/2.png"));
			this.setIconImage(img);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setVisible(true);//设置窗体可见。
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				//修改登陆状态
				ManageHelper helper = new ManageHelper();
				user.setIsLogin(0);//设置登陆状态为未登录。
				helper.Update_IsLogin(user);
				
			}
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
}
