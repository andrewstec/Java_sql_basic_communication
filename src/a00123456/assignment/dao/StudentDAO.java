package a00123456.assignment.dao;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;

import a00123456.assignment.data.Student;
import a00123456.assignment.data.StudentMarks;
import a00123456.assignment.exceptions.DataInputException;
import a00123456.assignment.ui.UIDisplay;

public class StudentDAO {

	public static List<Student> readData(String filename)
			throws FileNotFoundException, DataInputException {

		List<Student> students = new ArrayList<Student>();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(filename));

		for ( int i = 0; scanner.hasNext(); i++ ) {
			//String lineData = scanner.nextLine();
			//System.out.println(lineData);
			String[] studentData = scanner.nextLine().split("\\|");
			if (studentData.length < 6){
				throw new DataInputException("6 args expected, only " + studentData.length + " received.");
			}
			
			StudentMarks studentMarks;
			
			try{
			studentMarks = new StudentMarks(Double
					.parseDouble(studentData[3]), Double
					.parseDouble(studentData[4]), Double
					.parseDouble(studentData[5]));
			} catch(NumberFormatException nfe){
				throw new DataInputException("Input is not a double.");
			}
						
			students.add(new Student(studentData[0], studentData[1],
					studentData[2], studentMarks));
		}

		return students;
	}

	public static void writeData(List<Student> students, String filename)
			throws FileNotFoundException {

		PrintWriter output = new PrintWriter(new File(filename));

		for (Student student : students) {
			UIDisplay.display(student);
			output.print(student.getFullName() + ", " + student.getStudentID());
			output.print(" - Quizzes: " + student.getTheMarks().getQuizzes());
			output.print(", Assignment: "
					+ student.getTheMarks().getAssignments());
			output.println(", Exams: " + student.getTheMarks().getExams());
		}
		output.close();
	}

	public static void writeDataSorted(List<Student> students, String filename)
			throws IOException, Exception {
		
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
		for (Student student : students) {
			UIDisplay.display(student);
			output.print(student.getFullName() + ", " + student.getStudentID());
			output.print(" - Quizzes: " + student.getTheMarks().getQuizzes());
			output.print(", Assignment: "
					+ student.getTheMarks().getAssignments());
			output.println(", Exams: " + student.getTheMarks().getExams());
		}
		output.println();
		output.close();
	}

	
	public static Student[] readData_ver2(String filename)
			throws FileNotFoundException {

		Student[] students = new Student[3];
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(filename);

		while (scanner.hasNext()) {
			// String lineData = scanner.nextLine();
			// System.out.println(lineData);
			// String[] studentData = lineData.split("\\|");
			for (int i = 0; i < students.length; i++) {
				students[i] = new Student(scanner.next(), scanner.next(),
						scanner.next());
				students[i].setTheMarks(new StudentMarks(scanner.nextDouble(),
											scanner.nextDouble(), 
											scanner.nextDouble()));
			}

		}

		return students;
	}

}
