/*
**学号生成
 */

import java.util.Calendar;
import java.util.Vector;

public class StudentID {

	//获取年级
		public static Vector<String> CreateGrade(){
			Vector<String> vector = new Vector<String>();
			vector.add("");	//添加一个空选项 
			Calendar c = Calendar.getInstance();
			int Year = c.get(Calendar.YEAR);
			for(int i=0;i<4;i++){
				vector.add(String.valueOf(Year--));
			}
			return vector;
			
		}

		//生成学生学号的方法(学号：department+major+grade+classe+num)
		public static String CreateID(String grade, String classe, String major, String department, String num){
			String id = grade.charAt(2)+department+major+""+grade.charAt(3)+classe+num;
			return id;
		}
}
