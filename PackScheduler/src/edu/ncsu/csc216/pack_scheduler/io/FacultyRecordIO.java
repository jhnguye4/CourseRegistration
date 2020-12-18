/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * Reads Faculty files from text files. Writes a set of facultyRecords to a
 * file. This class will then be used in FacultyDirectory to create a LinkedList and to write 
 * output files. 
 * 
 * @author jhnguye4
 */
public class FacultyRecordIO {
	/**
	 * Reads faculty records from a file and generates a list of valid Faculty objects.
	 * If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * This method is used in loadFacultyFromFile in FacultyDirectory to display all faculty who are 
	 * currently in the directory.
	 * 
	 * @param fileName file to read Faculty records from
	 * @return a list of valid Faculty objects
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		LinkedList<Faculty> wholeFaculty = new LinkedList<Faculty>();
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
			while (fileReader.hasNextLine()) {
				try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				wholeFaculty.add(faculty);
				} catch (IllegalArgumentException e){
					continue;
				}
			}
			fileReader.close();
		return wholeFaculty;
	}
	
	/**
	 * Scans a line and uses delimiter to remove ','. It then places each token into specific variables.
	 * After variables are created, it is placed into Student object and returned. 
	 * This method is used readStudentRecords to create Student from the line being passed through.
	 * 
	 * @param fileName file to read Faculty records from
	 * @return a list of valid Faculty object
	 * @throws IllegalArgumentException if the line being scanned does not have one of the elements
	 */
	private static Faculty processFaculty(String line) throws IllegalArgumentException{
		Faculty f = null;
		try {
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter(",");
			String firstName = lineScan.next();
			String lastName = lineScan.next();
			String id = lineScan.next();
			String email = lineScan.next();
			String password = lineScan.next();
			int maxCourse = lineScan.nextInt();
			f = new Faculty(firstName, lastName, id, email, password, maxCourse);
			lineScan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		return f;
	}
	/**
	 * Writes the given list of Faculty in the directory.
	 * This method is used in FacultyDirectory in method saveFacultyDirectory where it saves all the faculty 
	 * after adding/removing to a file.
	 * 
	 * @param fileName is name of file
	 * @param facultyDirectory  is the LinkedList which is parses through with the for loop
	 * @throws IOException if file is empty
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}
