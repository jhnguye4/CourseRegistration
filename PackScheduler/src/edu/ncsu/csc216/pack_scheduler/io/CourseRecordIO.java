package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.File;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author jhnguye4
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());

				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				continue;
			}
		}
		fileReader.close();
		return courses;
	}
	/**
	 * Scans a line and uses delimeter to remove ','. It then places each token into specific variables.
	 *  After variables are created, it is placed into Course object and returned. If meeting days is 'A',
	 *  then it will use the constructor that does not have start and end times.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	private static Course readCourse(String nextLine) throws IllegalArgumentException {
		Course c = null;
		String instructorID = null;
		try {
			Scanner lineScan = new Scanner(nextLine);
			lineScan.useDelimiter(",");
			String name = lineScan.next();
			String title = lineScan.next();
			String section = lineScan.next();
			int credits = lineScan.nextInt();
			lineScan.next();
			
//			FacultyDirectory facultyDirectory = RegistrationManager.getInstance().getFacultyDirectory();
//			for(int i = 0; i < facultyDirectory.getFacultyDirectory().length; i++) {
//				if(facultyDirectory.getFacultyById(instructorID) != null) {
//					break;
//				} else {
//					instructorID = null;
//				}
//			}
			int enrollmentCap = lineScan.nextInt();
			String meetingDays = lineScan.next();
			
			
			
			if (meetingDays.contentEquals("A")) {
				if (lineScan.hasNext()) {
					lineScan.close();
					throw new IllegalArgumentException();
				}
				c = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays);
			} else {
				int startTime = lineScan.nextInt();
				int endTime = lineScan.nextInt();
				c = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays, startTime, endTime);
			}
			lineScan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		

		
		return c;
	}

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName is name of file
	 * @param courses  is the ArrayList which is parses through with the for loop
	 * @throws IOException if file is empty
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();

	}

}