/**
 * Project: a00123456_assingnment1
 * File: Student.java
 * Date: Jan 31, 2011
 * Time: 2:53:45 PM
 */
package a00123456.assignment.data;

/**
 * @author Bullwinkle Moose, A00123456
 *
 */
public class Student {
	
	private String firstName;
	private String lastName;
	private String studentID;
	private StudentMarks theMarks;
	
	public Student() {
		//super();
		firstName = "no";
		firstName = "body";
		studentID = "A00000000";
		
	}

	public Student(String firstName, String lastName, String studentID) {
		//super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
	}
	
	public Student(String firstName, String lastName, String studentID, 
			StudentMarks studentMarks) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.theMarks = studentMarks;
	}
	
	public Student(String[] data){
		firstName = data[0];
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	public String getFullName() {
		
		return firstName + " " + lastName;
	}

	public StudentMarks getTheMarks() {
		return theMarks;
	}

	public void setTheMarks(StudentMarks theMarks) {
		this.theMarks = theMarks;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName
				+ ", studentID=" + studentID + "]";
	}
	
	
	
}