/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Creates a course catalog that is empty. The user may load a file into catalog, add a course, remove course,
 * or save the catalog. The courses in catalog are ordered by name and section. There may be multiple sections 
 * of the same course, but a course/section pair must be unique.
 * 
 * @author jhnguye4
 *
 */
public class CourseCatalog {
	/** Columns for name, section, title, and meetingDays in getCatalog and getSchedule */
	private static final int COLUMN5 = 5;
	/** Specific column in array where name is stored in the getShortDisplayArray*/
	private static final int NAME_SHORT = 0;
	/** Specific column in array where section is stored in the getShortDisplayArray*/
	private static final int SECTION_SHORT = 1;
	/** Specific column in array where title is stored in the getShortDisplayArray*/
	private static final int TITLE_SHORT = 2;
	/** Specific column in array where title is stored in the getShortDisplayArray*/
	private static final int MEETING_DAYS_SHORT = 3;
	/** Specific column in array where title is stored in the getShortDisplayArray*/
	private static final int ENROLLMENT_CAP_SHORT = 4;
	
	/** Private field called catalog that is a Sorted array list of Course objects*/
	private SortedList<Course> catalog ;
	
	/**
	 * This is the constructor method which takes in no parameters and will create an empty Sorted list array.
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * This method is used to create a new course catalog that is a Sorted list array.
	 */
	public void newCourseCatalog() {
		SortedList<Course> newCatalog = new SortedList<Course>();
		catalog = newCatalog;
	}
	
	/**
	 * This method is used to load courses into the catalog field. It does this by calling upon
	 * the readCourseRecords method in CourseRecordsIO. The parameter inputed is the file name the user wants to
	 * load, if the file does not exist then it will throw an exception.
	 * 
	 * @param fileName file containing list of courses
	 * @throws IllegalArgumentException if file selected could not be read.
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a course to the catalog.  Returns true if the course is added and false if
	 * the course is unable to be added because their name/section matches another course's name and section.
	 * This method has all the parameters of a Course
	 * 
	 * @param name name of the course
	 * @param title title of course
	 * @param section course section number
	 * @param credits number of credits for the course
	 * @param instructorId Id of the instructor
	 * @param enrollmentCap is the capacity of class
	 * @param meetingDays days that courses meet
	 * @param startTime courses startTime.
	 * @param endTime courses endTime.
	 * @return true if student is able to be added 
	 * @throws IllegalArgumentException if course being added already exists in the catalog.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		if (catalog.isEmpty()) {
			catalog.add(c);
			return true;
		}
		if (catalog.size() > 0) {
			for (int i = 0 ; i < catalog.size(); i++) {
				Course c1 = catalog.get(i);
				if (c1.getName().equals(name) && c1.getSection().equals(section)){
					return false;
				}
			}
			catalog.add(c);
			return true;
		} 
		return false;
	}
	
	/**
	 * Adds a course to the catalog.  Returns true if the course is added and false if
	 * the course is unable to be added because their name/section matches another course's name and section.
	 * This method has all the parameters of a Course except the startTime and endTime since the users meetingDays was "A"
	 * 
	 * @param name name of the course
	 * @param title title of course
	 * @param section course section number
	 * @param credits number of credits for the course
	 * @param instructorId Id of the instructor
	 * @param enrollmentCap is the capacity of the course
	 * @param meetingDays days that courses meet
	 * @return true if student is able to be added 
	 * @throws IllegalArgumentException if course being added already exists in the catalog.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
		if (catalog.isEmpty()) {
			catalog.add(c);
			return true;
		}
		if (catalog.size() > 0) {
			for (int i = 0 ; i < catalog.size(); i++) {
				Course c1 = catalog.get(i);
				if (c1.getName().equals(name) && c1.getSection().equals(section)){
					throw new IllegalArgumentException(name + " is already added to Catalog");
				}
			}
			catalog.add(c);
			return true;
		} 
		return false;
	}
	
	/**
	 * Removes the course with the given name and section from the list of courses in the catalog.
	 * Returns true if the course is removed and false if the student is not in the list.
	 * 
	 * @param name Removing the course which has this name
	 * @param section Removing the course which has this section
	 * @return true if Course can be removed from directory
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method gets course from catalog if the name and section match, if not it will return null
	 * @param name parameter that is compared to name of Course object in catalog
	 * @param section parameter that is compared to section of Course object in catalog
	 * @return Course if the name and section matches name and section of course in catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (name.equals(c.getName()) && section.equals(c.getSection())) {
				return c;
			}
		}
		return null;

	}
	
	/**
	 * Creates a 2D array of the class name, section, and title of all classes in catalog
	 * @return String[][] array with the course name, section, title, and meetingDays
	 */
	public String[][] getCourseCatalog() {
		int row = catalog.size();
		String[][] array = new String[row][COLUMN5];
		
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				Course c = catalog.get(i);
				for (int j = 0; j < COLUMN5; j++) {
					if (j == NAME_SHORT) {
						array[i][j] = c.getName(); 
					}
					if (j == SECTION_SHORT) {
						array[i][j] = c.getSection(); 
					}
					if (j == TITLE_SHORT) {
						array[i][j] = c.getTitle(); 
					}
					if (j == MEETING_DAYS_SHORT) {
						array[i][j] = c.getMeetingString(); 
					}
					if (j == ENROLLMENT_CAP_SHORT) {
						array[i][j] = Integer.toString(c.getCourseRoll().getEnrollmentCap()); 
					}
				}
			}
		}
		return array;
	}
	
	/**
	 * Creates output file of students schedule
	 * @param fileName String of name that is on output file
	 * @throws IllegalArgumentException when file cant be created
	 */
	public void saveCourseCatalog(String fileName){
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch(IOException e){
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
}

