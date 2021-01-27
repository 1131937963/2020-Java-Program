/*
**连接数据库及其数据库功能
 */
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

public class Sqlfunc {
	private PreparedStatement ps = null;//数据库语句
	private ResultSet rs = null;
	private Connection ct = null;
	String DRIVER = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/StudentSystemDao?characterEncoding=utf8";
	String USERNAME = "root";
	String PASSWORD = "123456";
	private  void init(){
		try {
			Class.forName(DRIVER);
			ct = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//构造函数
	public Sqlfunc(){
		this.init();
	}
	public User getUser(User user){
		User newUser = new User();
		try {
			ps = ct.prepareStatement("select * from tb_User where User_name=?");
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if(rs.next()){
				newUser.setUsername(rs.getString(1));	//设置用户名
				newUser.setPassword(rs.getString(2));	//设置密码
				newUser.setTelphone(rs.getString(3));
				newUser.setIsLogin(rs.getInt(4));	//设置是否登陆
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return newUser;
	}

	public User found(User user) {
		User newUser = new User();
		try {
			ps = ct.prepareStatement("select * from tb_User where User_name=?");
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				newUser.setUsername(rs.getString(1));
				newUser.setTelphone(rs.getString(3));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return newUser;
	}
		public boolean register(User user){
		boolean b = true;
		try {
			ps = ct.prepareStatement("insert into tb_User(User_name,Password_,Telphone_) values(?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3,user.getTelphone());
			if(ps.executeUpdate()!=1){	//执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean update_IsLogin(User user){
		boolean b = true;
		try {
			ps = ct.prepareStatement("update tb_User set IsLogin=? where User_name=?");
			ps.setInt(1, user.getIsLogin());
			ps.setString(2, user.getUsername());
			if(ps.executeUpdate()!=1){
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

		public boolean update_Password(User user, String new_Password){
			boolean b = true;
			try {
				ps = ct.prepareStatement("update tb_User set Password_=? where User_name=?");
				ps.setString(1, new_Password);
				ps.setString(2, user.getUsername());
				if(ps.executeUpdate()!=1){	//执行sql语句
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}

		public HashMap<String, String> getAllDepartment(){
			HashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("", "");//添加一个空的元素
			try {
				ps = ct.prepareStatement("select * from tb_Department order by Department_ID");
				rs = ps.executeQuery();
				while(rs.next()){
					map.put(rs.getString(2),rs.getString(1)); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return map;
		}

		public Vector<String> getMajor(String department_ID){
			Vector<String> vector = new Vector<String>();
			vector.add("");//添加一个空的元素
			try {
				ps = ct.prepareStatement("select * from tb_Major where Department_ID=? order by Major_ID");
				ps.setString(1, department_ID);
				rs = ps.executeQuery();
				while(rs.next()){
					vector.add(rs.getString(2));	//获得专业名称
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vector;
		}


	public Vector<Integer> getClass(String Major_ID){
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(Integer.valueOf(""));//添加一个空的元素
		try {
			ps = ct.prepareStatement("select * from tb_class where Major=? order by class_ID");
			ps.setString(1, Major_ID);
			rs = ps.executeQuery();
			while(rs.next()){
				vector.add(rs.getInt(2));	//获得班级名称
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}
	//获得所有年级
	public HashMap<String, String> getAllGrade(){
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");//添加一个空的元素
		try {
			ps = ct.prepareStatement("select * from tb_Student order by Grade");
			rs = ps.executeQuery();
			while(rs.next()){
				map.put(rs.getString(2),rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}


		public HashMap<String, String> getAllMajor(){
			HashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("", "");//添加一个空的元素
			try {
				ps = ct.prepareStatement("select * from tb_Major order by Major_ID");
				rs = ps.executeQuery();
				while(rs.next()){
					map.put(rs.getString(2),rs.getString(1)); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return map;
		}

	public HashMap<String, String> getClass1(){
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");//添加一个空的元素
		try {
			ps = ct.prepareStatement("select * from tb_class order by class_ID");
			rs = ps.executeQuery();
			while(rs.next()){
				map.put(rs.getString(2),rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

		public boolean addStudent(Student student){
			boolean b = true;
			try {
				ps = ct.prepareStatement("insert into tb_Student(Student_Id,Student_Name,Student_Sex,Student_age,Student_Telphone,Classe,Grade,Major_ID,Department_ID,Major_Name,Department_Name) values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, student.getStudent_ID());
				ps.setString(2, student.getStudent_Name());
				ps.setString(3, student.getSex());
				ps.setString(4, student.getAge());
				ps.setString(5, student.getTelphone());
				ps.setString(6, student.getClasse());
				ps.setString(7, student.getGrade());
				ps.setString(8, student.getMajor_ID());
				ps.setString(9, student.getDepartment_ID());
				ps.setString(10, student.getMajor_Name());
				ps.setString(11, student.getDepartment_Name());
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}

		public boolean updateStudent(Student newStudent, String oldStudentID){
			boolean b = true;
			try {
				//update
				ps = ct.prepareStatement("update tb_Student set Student_Id=?, Student_Name=?, Student_Sex=? ,Student_age=?,Student_Telphone=?,Classe=? ,Grade=?  ,Major_ID=? ,Department_ID=? ,Major_Name=? ,Department_Name=? where Student_Id=?");
				ps.setString(1, newStudent.getStudent_ID());
				ps.setString(2, newStudent.getStudent_Name());
				ps.setString(3, newStudent.getSex());
				ps.setString(4, newStudent.getAge());
				ps.setString(5, newStudent.getTelphone());
				ps.setString(6, newStudent.getClasse());
				ps.setString(7, newStudent.getGrade());
				ps.setString(8, newStudent.getMajor_ID());
				ps.setString(9, newStudent.getDepartment_ID());
				ps.setString(10,newStudent.getMajor_Name());
				ps.setString(11,newStudent.getDepartment_Name());
				ps.setString(12,oldStudentID);
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			return b;
		}
		

		public boolean deleteStudent(String studentID){
			boolean b = true;
			try {
				ps = ct.prepareStatement("delete from tb_Student where Student_Id=?");
				ps.setString(1, studentID);
				if(ps.executeUpdate()!=1){
					b = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
				e.printStackTrace();
			}
			return b;
		}

		public Vector<Student> getStudent(String sql){
			Vector<Student> students = new Vector<Student>();
			try {
				ps = ct.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					Student student = new Student();
					student.setStudent_ID(rs.getString(1));
					student.setStudent_Name(rs.getString(2));
					student.setSex(rs.getString(3));
					student.setAge(rs.getString(4));
					student.setTelphone(rs.getString(5));
					student.setGrade(rs.getString(6));
					student.setClasse(rs.getString(7));
					student.setMajor_ID(rs.getString(8));
					student.setMajor_Name(rs.getString(9));
					student.setDepartment_ID(rs.getString(10));
					student.setDepartment_Name(rs.getString(11));
					students.add(student);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return students;
		}
		

		public Vector<String> getAllClasse(String grade, String major_ID){
			Vector<String> vector = new Vector<String>();
			vector.add("");	//添加一个空选项
			try {
				ps = ct.prepareStatement("select Classe from tb_Classe where Grade=? and Major_ID=?");
				ps.setString(1, grade);
				ps.setString(2, major_ID);
				rs = ps.executeQuery();
				while(rs.next()){
					vector.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vector;
		}


		public Vector<String> getCourse(String major_Id, String grade){
			Vector<String> vector = new Vector<String>();
			try {
				ps = ct.prepareStatement("select Course_Name from tb_Course where Major_ID=? and Grade=?");
				ps.setString(1, major_Id);
				ps.setString(2, grade);
				rs = ps.executeQuery();
				while(rs.next()){
					vector.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vector;
		}


	public void close()	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
