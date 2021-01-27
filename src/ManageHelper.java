
import java.util.HashMap;
import java.util.Vector;


//数据库
public class ManageHelper {
	static private Sqlfunc helper;	//数据库对象
//判断登录是否成功
	public boolean Login(User user){
		boolean b = true;
		helper = new Sqlfunc();
		User newUser = helper.getUser(user); //获得用户数据
		if(!user.getPassword().equals(newUser.getPassword())){
			b = false;
		}
		helper.close();
		return b;
	}

	//判断注册情况
	public boolean Register(User user){
		helper = new Sqlfunc();//
		boolean b = helper.register(user);
		helper.close();
		return b;
	}

	//判断找回情况
	public boolean Found(User user){
		boolean b = true;
		helper = new Sqlfunc();
		User newUser = helper.found(user);
		if(!user.getTelphone().equals(newUser.getTelphone())){
			b = false;
		}
		helper.close();
		return b;
	}

//登录情况
	public boolean  Update_IsLogin(User user){
		helper = new Sqlfunc();
		boolean b = helper.update_IsLogin(user);
		helper.close();
		return b;
	}

	//密码修改
	public boolean update_Password(User user, String new_Password){
		boolean b;
		helper = new Sqlfunc();
		b = helper.update_Password(user, new_Password);
		helper.close();
		return b;
	}

	//返回院系HashMap集合
	public HashMap<String, String> getAllDepartment(){
		helper = new Sqlfunc();
		HashMap<String, String> map;//获得结果集合;
		map = helper.getAllDepartment();
		helper.close();
		return map;
	}

	//返回所有专业集合
	public HashMap<String, String> getAllMajor(){
		helper = new Sqlfunc();
		HashMap<String, String> map;
		map = helper.getAllMajor();
		helper.close();
		System.out.println(map);
		return map;
	}


	//返回所有年级集合
	public  HashMap<String, String> getAllGrade(){
		helper = new Sqlfunc();
		HashMap<String, String> map;
		map = helper.getAllGrade();
		helper.close();
		return map;
	}

	//返回对应的专业集合
	public Vector<String> getMajor(String department_ID){
		helper = new Sqlfunc();
		Vector<String> vector;//能够存放任意类型的动态数组
		vector = helper.getMajor(department_ID);
		helper.close();
		return vector;
	}

	public boolean addStudent(Student student){
		boolean b = true;
		helper = new Sqlfunc();
		b = helper.addStudent(student);
		helper.close();
		return b;
	}

	public boolean updateStudent(Student newStudent, String oldStudentID){
		boolean b = true;
		helper = new Sqlfunc();
		b = helper.updateStudent(newStudent, oldStudentID);
		helper.close();
		return b;
	}

	//删除学生
	public boolean deleteStudent(String studentID){
		boolean b;
		helper = new Sqlfunc();
		b = helper.deleteStudent(studentID);
		helper.close();
		return b;
	}

	//sql语句返回学生集合
	public Vector<Student> getStudent(String sql){
		Vector<Student> students;
		helper = new Sqlfunc();
		students = helper.getStudent(sql);
		helper.close();
		return students;
	}

	//根据年级和专业编号编号返回对应的班级
	public Vector<String> getAllClasse(String grade, String major_ID){
		Vector<String> vector;
		helper = new Sqlfunc();
		vector = helper.getAllClasse(grade,major_ID);
		helper.close();
		return vector;
		
	}
	
	//根据专业编号返回课程集合
	public Vector<String> getCourse(String major_Id, String grade){
		Vector<String> vector;
		helper = new Sqlfunc();
		vector = helper.getCourse(major_Id,grade);
		helper.close();
		return vector;
	}

}
