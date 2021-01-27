

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class StudentModel extends AbstractTableModel {
	private ManageHelper helper;
	private Vector<Student> students;
	private Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据
	
		
	 public StudentModel(String sql, JDialog jd) {
		helper = new ManageHelper();
		students = helper.getStudent(sql);
		
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("联系电话");
		columnNames.add("年级");
		columnNames.add("班级");
		columnNames.add("院系");
		columnNames.add("专业");
		for(Student student : students){
			Vector<String> hang = new Vector<String>();
			hang.add(student.getStudent_ID());
			hang.add(student.getStudent_Name());
			hang.add(student.getSex());
			hang.add(student.getAge());
			hang.add(student.getTelphone());
			hang.add(student.getGrade());
			hang.add(student.getClasse());
			hang.add(student.getDepartment_Name());
			hang.add(student.getMajor_Name());
			rowData.add(hang);
		}
		if(getRowCount()!=0){
			JOptionPane.showMessageDialog(jd, "一共有"+getRowCount()+"条记录！");
			return ;
		}else{
			JOptionPane.showMessageDialog(jd, "没有任何记录！");
			return ;
		}
	}
	
	//得到共有多少行
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.rowData.size();
		}
		//得到共有多少列
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.columnNames.size();
		}
		//得到某行某列的数据
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
		}
		
		//重写方法 getColumnName
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return (String)this.columnNames.get(column);
		}

}
