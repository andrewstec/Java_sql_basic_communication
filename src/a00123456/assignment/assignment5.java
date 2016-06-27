package a00123456.assignment;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.IOException;

import a00123456.assignment.dao.Database;
import a00123456.assignment.dao.StudentDAO;
import a00123456.assignment.data.Student;
import a00123456.assignment.data.StudentSorter.SortByFirstName;
import a00123456.assignment.data.StudentSorter.SortByLastName;
import a00123456.assignment.data.StudentSorter.SortByStudentID;
import a00123456.assignment.exceptions.DataInputException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Collections;
import java.util.List;

public class assignment5 {

	public static void main(String[] args) throws Exception {

		Database database = new Database();
		database.Connect();
		
		List<Student> students = null;
		
		try {
			
			students = StudentDAO.readData("student_data.txt");
			

		} catch (DataInputException die) {
			die.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		System.out.println("Creating students object");
		
		
		//Creates table, and if it exists, it will drop it and recreate it.
		if (database.tableExists("A00584083_students")) {
			System.out.println("Table exists. Dropping table");
			
			database.dropTable("IF OBJECT_ID('A00584083_students', 'U') IS NOT NULL " +
					 "DROP TABLE A00584083_students;");
			
			System.out.println("-Dropped table complete. Creating a new table");
			
			database.createTable("CREATE TABLE A00584083_students " + 
					"( " + 
							"lastName varchar(45), firstName varchar(45), studentID char(9), grade1 varchar(4), grade2 varchar(4), grade3 varchar(4), " +
							" PRIMARY KEY (studentID) " + 
						")");
			
			System.out.println("-Created new table.");
			
			System.out.println("Adding student data to table...");
			/*
			Statement statement = database.connection.prepareStatement("INSERT INTO A00584083_students (lastName, firstName, studentID, grade1, grade2, grade3) " +
					"VALUES ('?', '?', '?', '?', '?', '?');");
			
			for ( int i = 0 ; i < students.size(); i++ ) {
				((PreparedStatement) statement).setString(1, students.get(i).getLastName());
				((PreparedStatement) statement).setString(2, students.get(i).getFirstName());
				((PreparedStatement) statement).setString(2, students.get(i).getStudentID());
				((PreparedStatement) statement).setLong(2, (long) students.get(i).getTheMarks().getAssignments());
				((PreparedStatement) statement).setLong(2, (long) students.get(i).getTheMarks().getAssignments());
				((PreparedStatement) statement).setLong(2, (long) students.get(i).getTheMarks().getQuizzes());
			}
			ResultSet resultSet = statement.executeQuery();
			*/
			
			for (int i = 0; i < students.size(); i++) {
				
				database.insertData("INSERT INTO A00584083_students (lastName, firstName, studentID, grade1, grade2, grade3) " +
						"VALUES ('" + students.get(i).getLastName() + "', '"+ students.get(i).getFirstName() +"', '"+ students.get(i).getStudentID() +
						"', '"+ students.get(i).getTheMarks().getAssignments() +"', '"+ students.get(i).getTheMarks().getExams() +"', '"+ students.get(i).getTheMarks().getQuizzes() +"');");
				
			}			
			System.out.println("-Added student data to table.");
			
			
			ResultSet resultSet = database.getData("SELECT * FROM A00584083_students;");
			
			System.out.println("Printing first iteration of the data that was added:");
			
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " 
				+ resultSet.getString(4) + ", " + resultSet.getString(5) + ", " + resultSet.getString(6) + ", ");
				i++;
			}
			
			System.out.println("Modifying data with update");
			
			database.insertData("UPDATE A00584083_students  "
					+ "SET firstName = 'Homer' " +
					"WHERE lastName = 'Simpson'");
			
			System.out.println("-Modified Data");
			System.out.println("Reprinting data. Should say Homer instead of Bart");
			resultSet = database.getData("SELECT * FROM A00584083_students;");
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " 
				+ resultSet.getString(4) + ", " + resultSet.getString(5) + ", " + resultSet.getString(6) + ", ");
				i++;
			}
			
			System.out.println("Removing a row of data where 'Duck' exists, as he flunked");
			database.insertData("UPDATE A00584083_students  "
					+ "SET firstName = 'Homer' " +
					"WHERE lastName = 'Simpson'");
			

			database.insertData("DELETE FROM A00584083_students " +
					"WHERE lastName = 'Duck'");
			System.out.println("-Removal complete. Reprinting results:");
			resultSet = database.getData("SELECT * FROM A00584083_students;");
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " 
				+ resultSet.getString(4) + ", " + resultSet.getString(5) + ", " + resultSet.getString(6) + ", ");
				i++;
			}
			
			
			database.shutDown();
			
			
		}
		else {
			System.out.println("table does not exist. creating table.");
			database.createTable("CREATE TABLE A00584083_students " + 
					"( " + 
							"lastName varchar(45), firstName varchar(45), studentID char(9), grade1 varchar(4), grade2 varchar(4), grade3 varchar(4), " +
							" PRIMARY KEY (studentID) " + 
						")");
			

			System.out.println("-Created new table.");
			
			System.out.println("Adding student data to table...");
			/*
			Statement statement = database.connection.prepareStatement("INSERT INTO A00584083_students (lastName, firstName, studentID, grade1, grade2, grade3) " +
					"VALUES ('?', '?', '?', '?', '?', '?');");
			
			for ( int i = 0 ; i < students.size(); i++ ) {
				((PreparedStatement) statement).setString(1, students.get(i).getLastName());
				((PreparedStatement) statement).setString(2, students.get(i).getFirstName());
				((PreparedStatement) statement).setString(2, students.get(i).getStudentID());
				((PreparedStatement) statement).setLong(2, (long) students.get(i).getTheMarks().getAssignments());
				((PreparedStatement) statement).setLong(2, (long) students.get(i).getTheMarks().getAssignments());
				((PreparedStatement) statement).setLong(2, (long) students.get(i).getTheMarks().getQuizzes());
			}
			ResultSet resultSet = statement.executeQuery();
			*/
			
			for (int i = 0; i < students.size(); i++) {
				
				database.insertData("INSERT INTO A00584083_students (lastName, firstName, studentID, grade1, grade2, grade3) " +
						"VALUES ('" + students.get(i).getLastName() + "', '"+ students.get(i).getFirstName() +"', '"+ students.get(i).getStudentID() +
						"', '"+ students.get(i).getTheMarks().getAssignments() +"', '"+ students.get(i).getTheMarks().getExams() +"', '"+ students.get(i).getTheMarks().getQuizzes() +"');");
				
			}			
			System.out.println("-Added student data to table.");
			
			
			ResultSet resultSet = database.getData("SELECT * FROM A00584083_students;");
			
			System.out.println("Printing first iteration of the data that was added:");
			
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " 
				+ resultSet.getString(4) + ", " + resultSet.getString(5) + ", " + resultSet.getString(6) + ", ");
				i++;
			}
			
			System.out.println("Modifying data with update");
			
			database.insertData("UPDATE A00584083_students  "
					+ "SET firstName = 'Homer' " +
					"WHERE lastName = 'Simpson'");
			
			System.out.println("-Modified Data");
			System.out.println("Reprinting data. Should say Homer instead of Bart");
			resultSet = database.getData("SELECT * FROM A00584083_students;");
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " 
				+ resultSet.getString(4) + ", " + resultSet.getString(5) + ", " + resultSet.getString(6) + ", ");
				i++;
			}
			
			System.out.println("Removing a row of data where 'Duck' exists, as he flunked");
			database.insertData("UPDATE A00584083_students  "
					+ "SET firstName = 'Homer' " +
					"WHERE lastName = 'Simpson'");
			

			database.insertData("DELETE FROM A00584083_students " +
					"WHERE lastName = 'Duck'");
			System.out.println("-Removal complete. Reprinting results:");
			resultSet = database.getData("SELECT * FROM A00584083_students;");
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " 
				+ resultSet.getString(4) + ", " + resultSet.getString(5) + ", " + resultSet.getString(6) + ", ");
				i++;
			}
			
			
			database.shutDown();
			
			
		}
		
		
		
		
		//continue here
		//database.insertData(insertQuery);
		
		

		
		
		
		
		
		/*1. if your table exists. If it does you will DROP it and recreate it from scratch by reading the seed data 
		/*2.
		
		
		
		/*
		
		
		Assignment3 assignment = new Assignment3();
		//assignment.test();
		assignment.PrintSorted();
	}	
	
	public void PrintSorted() throws Exception, Exception {
		
		List<Student> students = null;
		
		try {
		
			students = StudentDAO.readData("student_data.txt");
			
			//write before sort
			StudentDAO.writeDataSorted(students, "output.txt");

		} catch (DataInputException die) {
			die.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		SortByFirstName sortByFirstName = new SortByFirstName();
		SortByLastName sortByLastName = new SortByLastName();
		SortByStudentID sortByStudentID = new SortByStudentID(); 
		
		Collections.sort(students, sortByFirstName);
		
		try {
			Collections.sort(students, sortByFirstName);
			StudentDAO.writeDataSorted(students, "output.txt");
			Collections.sort(students, sortByLastName);
			StudentDAO.writeDataSorted(students, "output.txt");
			Collections.sort(students, sortByStudentID);
			StudentDAO.writeDataSorted(students, "output.txt");
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void test() {

		List<Student> students = null;
		try {

			students = StudentDAO.readData("student_data.txt");
			
			StudentDAO.writeData(students, "output.txt");

		} catch (DataInputException die) {
			die.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} */
	} 
}

