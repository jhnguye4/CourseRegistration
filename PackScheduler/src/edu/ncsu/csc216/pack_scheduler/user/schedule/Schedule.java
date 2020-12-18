/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * The Schedule class is used to add, remove, reset, and set the title of courses for the student.
 * This class will be implemented in the StudentRegistrationPanel and the Student class.
 * 
 * @author jhnguye4
 *
 */
public class Schedule {
	/** Field for ArrayList of Courses */
	private ArrayList<Course> schedule;
	/** Field for title */
	private String title;
	
	/** Columns for name, section, title, and meetingDays in getSchedule */
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
	
	/**
	 * The constructor for the Schedule class that will set the title to "My Schedule". 
	 * It will also instantiate the ArrayList of Courses.
	 *
	 */
	public Schedule(){
		setTitle("My Schedule");
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * This method adds a course to the schedule. If the course trying to be added 
	 * is a duplicate then it will throw an illegal argument exception
	 * 
	 * @param c is the Course parameter that is being passed through and will be added to the schedule if it is
	 * not a duplicate
	 * @return boolean true if the course can be added and false if it cant.
	 * @throws IllegalArgumentException if the the course is a duplicate
	 * @throws IllegalArgumentException if the course conflicts with another course
	 *
	 */
	public boolean addCourseToSchedule(Course c) {
		if (schedule.isEmpty()) {
			schedule.add(c);
			return true;
		}
		if (schedule.size() > 0) {
			for (int j = 0; j < schedule.size(); j++) {
				Course s = schedule.get(j);
				if (s.isDuplicate(c)) {
					throw new IllegalArgumentException("You are already enrolled in " + c.getName());
				}
				try {
					s.checkConflict(c);
				} catch(ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict");
				}
			}
			schedule.add(c);
			return true;
		}
		return false;
	}
	/**
	 * Removes the course from student's schedule.
	 * @param c  Course that is to be removed
	 * @return boolean true if course can be removed
	 */
	public boolean removeCourseFromSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
			if (s.equals(c)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * This method makes the ArrayList of Courses empty.
	 *
	 */
	public void resetSchedule() {
		ArrayList<Course> newArray = new ArrayList<Course>();
		schedule = newArray;
	}
	/**
	 * Creates 2D array of the courses in the schedule. It will return the course name, 
	 * section, title, and meetingDays.
	 * @return String[][] array of schedule activities name, section, title, and meeting
	 */
	public String[][] getScheduledCourses() {
		int row = schedule.size();
		String[][] array = new String[row][COLUMN5];
		
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				Course c = schedule.get(i);
				String [] s = ((Activity)c).getShortDisplayArray();
				for (int j = 0; j < COLUMN5; j++) {
					if (j == NAME_SHORT) {
						array[i][j] = s[NAME_SHORT]; 
					}
					if (j == SECTION_SHORT) {
						array[i][j] = s[SECTION_SHORT]; 
					}
					if (j == TITLE_SHORT) {
						array[i][j] = s[TITLE_SHORT]; 
					}
					if (j == MEETING_DAYS_SHORT) {
						array[i][j] = s[MEETING_DAYS_SHORT]; 
					}
					if (j == ENROLLMENT_CAP_SHORT) {
						array[i][j] = s[ENROLLMENT_CAP_SHORT];
					}
				}
			}
		}
		return array;
	}
	/**
	 * This method sets the title of the schedule. It is called upon in the schedule constructor 
	 * and is set to "My Schedule" initially. It will throw an IllegalArgumentException if the the parameter is
	 * null or empty.
	 * @param title is the String that the title will be set as 
	 * @throws IllegalArgumentException if the String being passed in is null or empty.
	 *
	 */
	public void setTitle(String title) {
		if(title == null || title.contentEquals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}
	/**
	 *This method gets the title of the schedule. It is used when testing to check if the
	 *assertions match.
	 *@return String of the title name
	 */
	public String getTitle() {
		return title;
	}
	/**
	 *This method returns the credits in the students schedule
	 *@return int of number of credits in the students schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (int i = 0; i < schedule.size(); i++) {
			sum += schedule.get(i).getCredits();
		}
		return sum;
	}
	
	/**
	 * This method is used to see if a course can be added to the schedule. Will return true
	 * if schedule can be added. This method is used in the RegistrationManager to check
	 * if a Course can be added.
	 * @param c is the Course being checked to be added
	 *@return True if a course can be added.
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
			if(s.isDuplicate(c)) {
				return false;
			}
			try {
				s.checkConflict(c);
			} catch(ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
