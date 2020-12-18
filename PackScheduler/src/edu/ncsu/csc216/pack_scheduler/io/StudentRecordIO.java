package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
/**
 * Reads Student files from text files. Writes a set of studentRecords to a
 * file. This class will then be used in StudentDirectory to create a SortedList and to write 
 * output files. 
 * 
 * @author jhnguye4
 */
public class StudentRecordIO {
	/**
	 * Reads student records from a file and generates a list of valid Student objects.
	 * If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * This method is used in loadStudentsFromFile in StudentDirectory to display all students who are 
	 * currently in the directory.
	 * 
	 * @param fileName file to read Student records from
	 * @return a list of valid Student objects
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		SortedList<Student> students = new SortedList<Student>();
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
			while (fileReader.hasNextLine()) {
				try {
				Student student = processStudent(fileReader.nextLine());
				students.add(student);
				} catch (IllegalArgumentException e){
					continue;
				}
			}
			fileReader.close();
		return students;
	}
	
	/**
	 * Scans a line and uses delimiter to remove ','. It then places each token into specific variables.
	 * After variables are created, it is placed into Student object and returned. 
	 * This method is used readStudentRecords to create Student from the line being passed through.
	 * 
	 * @param fileName file to read Student records from
	 * @return a list of valid Student object
	 * @throws IllegalArgumentException if the line being scanned does not have one of the elements
	 */
	private static Student processStudent(String line) throws IllegalArgumentException{
		Student s = null;
		try {
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter(",");
			String firstName = lineScan.next();
			String lastName = lineScan.next();
			String id = lineScan.next();
			String email = lineScan.next();
			String password = lineScan.next();
			int maxCredits = lineScan.nextInt();
			s = new Student(firstName, lastName, id, email, password, maxCredits);
			lineScan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		return s;
	}
	/**
	 * Writes the given list of Students in the directory.
	 * This method is used in StudentDirectory in method saveStudentDirectory where it saves all the students 
	 * after adding/removing to a file.
	 * 
	 * @param fileName is name of file
	 * @param studentDirectory  is the ArrayList which is parses through with the for loop
	 * @throws IOException if file is empty
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}
