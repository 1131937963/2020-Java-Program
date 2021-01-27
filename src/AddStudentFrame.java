/*
***添加学生信息界面
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

public class AddStudentFrame extends JDialog {
	 	private JButton add_Button;	//"添加"按钮。
	    private JButton cancel_Button;	//"取消"按钮。
	    private JComboBox sex_Box;	//"性别"选项。
	    private JComboBox major_Box;	//"专业"选项。
	    private JComboBox department_Box;	//"所属院系"选项。
	    private JLabel student_ID;    //"学号"标签。
	    private JLabel student_Name;	//"姓名"标签。
	    private JLabel sex_Label;	//"性别"标签。
		private JLabel age_Label;  //"年龄"标签
		private JLabel telphone_Label;//"联系"标签
	    private JLabel classe_Label;	//"班级"标签。
	    private JLabel grade_Label;	//"年级标签"。
	    private JLabel major_Label;	//"专业"标签。
	    private JLabel department_Label;	//"所属院系"标签。
	    private JTextField student_IDText;	//"学号"文本域。
	    private JTextField student_NameText;	//"姓名"文本域。
		private JTextField age_Text;  //"年龄"文本域
		private JTextField telphone_Text;  //"联系"文本域
	    private JComboBox classe_Box;	//"班级"文本域。
	    private JComboBox grade_Box;	//"年级"文本域。
	    private JDialog jd;	//当前窗口。
	    private HashMap<String, String> departments;	//所有院系集合
	    private HashMap<String, String> all_Major;	//所有专业集合
	    private Vector<String> majors;	//专业名称集合
	    private ManageHelper helper;	//数据库业务处理对象
	    private Vector<String> classes;	//班级集合

	public AddStudentFrame(JFrame owner, String title, boolean modal){
		super(owner, title, modal);
		helper = new ManageHelper();
		departments = this.helper.getAllDepartment();	//所有院系
		all_Major = this.helper.getAllMajor();	//所有的专业
		this.jd = this;
		this.setSize(350,429);
		this.setLayout(null);
		//*****************************************
		student_ID = new JLabel("学号:");
		student_ID.setBounds(78, 28, 30, 20);
		this.add(student_ID);
		student_IDText = new JTextField();
		student_IDText.setBounds(116, 28, 150, 20);
		this.add(student_IDText);
		//**************************************
		student_Name = new JLabel("姓名:");
		student_Name.setBounds(78, 58, 30, 20);
		this.add(student_Name);
		student_NameText = new JTextField();
		student_NameText.setBounds(116, 58, 150, 20);
		this.add(student_NameText);
		//****************************************
		sex_Label = new JLabel("性别:");
		sex_Label.setBounds(78, 148, 30, 20);
		this.add(sex_Label);
		sex_Box = new JComboBox(new String[]{"","男","女"});
		sex_Box.setBounds(116, 148, 60, 20);
		this.add(sex_Box);
		//*******************************************************
		age_Label = new JLabel("年龄:");
		age_Label.setBounds(78,88,30,20);
		this.add(age_Label);
		age_Text = new JTextField();
		age_Text.setBounds(116,88,150,20);
		this.add(age_Text);
		//********************************************************
		telphone_Label = new JLabel("联系电话");
		telphone_Label.setBounds(50,118,50,20);
		this.add(telphone_Label);
		telphone_Text = new JTextField();
		telphone_Text.setBounds(116,118,150,20);
		this.add(telphone_Text);
		//*********************************************************************
		grade_Label = new JLabel("年级:");
		grade_Label.setBounds(78, 178, 30, 20);
		this.add(grade_Label);
		grade_Box = new JComboBox<String>(StudentID.CreateGrade());	//需要获得获得年级选项
		grade_Box.setBounds(116, 178, 150, 20);
		grade_Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				classe_Box.removeAllItems();//移除"班级选项"的内容
				String option = major_Box.getSelectedItem().toString();
				String major_id = all_Major.get(option);	//专业编号
				String grade = grade_Box.getSelectedItem().toString();
				if(!grade.equals("")){
					 classes = helper.getAllClasse(grade,major_id);	//获得班级
					for(String s : classes){
						classe_Box.addItem(s);
					}
				}
			}
		});
		this.add(grade_Box);

		//************************************************
		department_Label = new JLabel("院系:");
		department_Label.setBounds(78, 208, 30, 20);
		this.add(department_Label);
		department_Box = new JComboBox(departments.keySet().toArray());//获得键的集合
		department_Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				major_Box.removeAllItems();
				String option = department_Box.getSelectedItem().toString();
				String department_ID = departments.get(option);
				if(!department_ID.equals("")) {
					majors = helper.getMajor(department_ID);//获得专业
					for (String s : majors) {
						major_Box.addItem(s);
					}
				}
			}
		});
		department_Box.setBounds(116, 208, 150, 20);
		this.add(department_Box);

		//***********************************************************
		major_Label = new JLabel("专业:");
		major_Label.setBounds(78, 238, 30, 20);
		this.add(major_Label);
		major_Box = new JComboBox(new String[]{""});//防止空指针异常
		major_Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(major_Box.getSelectedItem()!=null){	//防空
					if(!major_Box.getSelectedItem().toString().equals("")){
						if(major_Box.getSelectedItem().toString().equals("") || grade_Box.getSelectedItem()==null || grade_Box.getSelectedItem().toString().equals("")){
							JOptionPane.showMessageDialog(jd, "年级不能为空", "", JOptionPane.WARNING_MESSAGE);
							major_Box.setSelectedIndex(0);	//设置为空选项
							return ;
						}
						classe_Box.removeAllItems();//移除"班级选项"
						String option = major_Box.getSelectedItem().toString();
						String major_id = all_Major.get(option);
						String grade = grade_Box.getSelectedItem().toString();
						if(!grade.equals("")){
							 classes = helper.getAllClasse(grade,major_id);
							for(String s : classes){
								classe_Box.addItem(s);
							}
						}
					}
				}
			}
		});
		major_Box.setBounds(116, 238, 150, 20);
		this.add(major_Box);

		//************************************************************
		classe_Label = new JLabel("班级:");	//需要获得班级选项
		classe_Label.setBounds(78, 268, 30, 20);
		this.add(classe_Label);
		classe_Box = new JComboBox(new String[]{""});//防止空指针异常
		classe_Box.setBounds(116, 268, 150, 20);
		this.add(classe_Box);

		//*************************************************************
		add_Button = new JButton("添加");
		add_Button.setBounds(70, 320, 60, 25);
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				String sid = student_IDText.getText().trim();
				String name = student_NameText.getText().trim();
				String sex = sex_Box.getSelectedItem().toString();
				String age = age_Text.getText().trim();
				String telphone = telphone_Text.getText().trim();
				String classe = classe_Box.getSelectedItem().toString();
				String grade = grade_Box.getSelectedItem().toString();
				String department_ID = null;
				String major_ID = null;
				String department_Name = null;
				String major_Name = null;
				if(sid.equals("")){
					JOptionPane.showMessageDialog(jd, "班号不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(sid.length()!=2){
					JOptionPane.showMessageDialog(jd, "班号必须是两位数！", "", JOptionPane.WARNING_MESSAGE);
					student_IDText.setText("");
					return ;
				}
				if(name.equals("")){
					JOptionPane.showMessageDialog(jd, "姓名不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(sex.equals("")){
					JOptionPane.showMessageDialog(jd, "性别不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(grade.equals("")){
					JOptionPane.showMessageDialog(jd, "年级不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(classe.equals("")){
					JOptionPane.showMessageDialog(jd, "班级不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(department_Box.getSelectedItem()==null){	//先检查再用
					JOptionPane.showMessageDialog(jd, "院系不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}else{
					department_Name = department_Box.getSelectedItem().toString();	//获得院系名称
					department_ID = departments.get(department_Name);	//获得院系编号	
				}
				if(department_ID.equals("")){
					JOptionPane.showMessageDialog(jd, "院系不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if(major_Box.getSelectedItem()==null){	//先检查再用
					JOptionPane.showMessageDialog(jd, "专业不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}else{
					major_Name = major_Box.getSelectedItem().toString();//获得专业名称
					 major_ID = all_Major.get(major_Name);	//获得专业编号
				}
				if(major_ID.equals("")){
					JOptionPane.showMessageDialog(jd, "专业不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				String id = StudentID.CreateID(grade, classe, major_ID, department_ID, sid);//生成学号
				student_ID.setText("学号:");
				student_IDText.setText(id);
				JOptionPane.showMessageDialog(jd, "该学生的id为:"+id);
				student.setStudent_ID(id);
				student.setStudent_Name(name);
				student.setSex(sex);
				student.setAge(age);
				student.setTelphone(telphone);
				student.setGrade(grade);
				student.setClasse(classe);
				student.setMajor_Name(major_Name);
				student.setDepartment_Name(department_Name);
				student.setMajor_ID(major_ID);
				student.setDepartment_ID(department_ID);
				if(helper.addStudent(student)){
					JOptionPane.showMessageDialog(jd, "添加成功！");
					jd.dispose();
					return ;
				}else{
					JOptionPane.showMessageDialog(jd, "添加失败！", "", JOptionPane.WARNING_MESSAGE);
					jd.dispose();
					return ;
				}
			}
		});
		this.add(add_Button);
		cancel_Button = new JButton("取消");
		cancel_Button.setBounds(230, 320, 60, 25);
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jd.dispose();
			}
		});
		this.add(cancel_Button);
		WindowUI.setFrameCenter(this);
		this.setResizable(false);
		this.setVisible(true);
	}
}
