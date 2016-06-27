package a00123456.assignment.data;

import java.util.Comparator;
import java.lang.String;
import java.util.ArrayList;

public class StudentSorter{
	
	public static class SortByFirstName implements Comparator<Student>{
		
		public int compare(Student s1, Student s2) {
			String s1Name = s1.getFirstName();
			String s2Name = s2.getFirstName();
			return s1Name.compareTo(s2Name);
		}
	}
	
	public static class SortByLastName implements Comparator<Student>{
		@Override 
		public int compare(Student s1, Student s2) {
			String s1Name = s1.getLastName();
			String s2Name = s2.getLastName();
			return s1Name.compareTo(s2Name);
		}
	}
	
	public static class SortByStudentID implements Comparator<Student>{
		@Override 
		public int compare(Student s1, Student s2) {
			String s1Name = s1.getStudentID();
			String s2Name = s2.getStudentID();
			return s1Name.compareTo(s2Name);
		}
	}
}
