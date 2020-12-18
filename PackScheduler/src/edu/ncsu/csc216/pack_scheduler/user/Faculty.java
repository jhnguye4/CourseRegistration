/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * This class contains mostly setter and getter methods for faculty and is very similar
 * to the student class.
 * @author jhnguye4
 *
 */
public class Faculty extends User {
	/**private field for max courses*/
	private int maxCourses;
	/**constant for minimum number of courses*/
	private static final int MIN_COURSES = 1;
	/**constant for maximum number of courses*/
	private static final int MAX_COURSES = 3;
	/**private field for Faculty Schedule*/
	private FacultySchedule schedule;
	/**
	 * Constructor method for faculty that sets the firstName, lastName, id, email, and password
	 * by calling on super class. Then sets the maxCourses. 
	 * @param firstName is the first name of the faculty
	 * @param lastName is the last name of the faculty
	 * @param id is the id of the faculty
	 * @param email is email of faculty
	 * @param password is password for faculty
	 * @param courses is the max courses for faculty
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int courses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(courses);
		schedule = new FacultySchedule(super.getId());
	}
	/**
	 * Setter method that sets the max courses and throws IllegalArgumentException if the 
	 * course inputted is greater than 3 or less than 1
	 * @param course is the max course being inputted
	 * @throws IllegalArgumentException if the course inputted is greater than 3 or less than 1
	 */
	public void setMaxCourses(int course) {
		if(course < MIN_COURSES || course > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		maxCourses = course;
	}
	/**
	 * Getter method that gets the max courses
	 * @return maxCourses which is the max number of courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Getter method that gets the schedule of the faculty
	 * @return FacultySchedule which is the schedule of the faculty
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * This method will return boolean if the faculties schedule is at its max
	 * @return true if the faculties schedule is grater than max courses
	 */
	public boolean isOverloaded() {
		if(schedule.getScheduledCourses().length > this.getMaxCourses()) {
			return true;
		}
		return false;
	}
	/**
	 * Generates a hashCode for Faculty that is used to map data
	 * 
	 * @return hashCode for Faculty
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}
	
	/**
	 * Compares a given object to this object for equality of max courses.
	 * 
	 * @param obj the object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Faculty
	 */
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() +
				"," + super.getEmail() + "," + super.getPassword() + "," + maxCourses;
		
	}


}
