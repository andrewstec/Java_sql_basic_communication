package a00123456.assignment.ui;

import a00123456.assignment.data.Student;

public class UIDisplay {
	
	public static void display(Student aStudent){
		
		System.out.println(aStudent + "---" + aStudent.getTheMarks().toString());
	}

}
