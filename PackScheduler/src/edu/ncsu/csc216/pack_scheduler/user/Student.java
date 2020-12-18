package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class has setter and getter methods, along with hash() and equals().
 * The student class is used as a composition and is used mostly in StudentDirectory but also in 
 * StudentDirectoryPanel/Student class. This classes main purpose is to get certain parameters of Student in different classes. 
 * 
 * @author jhnguye4
 *
 */
public class Student extends User implements Comparable<Student> {
	/** Max number of credits a student can take */
	public static final int MAX_CREDITS = 18;
	/** Student schedule. */
	private Schedule schedule;
	/** Student credits. */
	private int maxCredits;

	/**
	 * Constructor method that creates Student Object and contains all fields 
	 * @param firstName name of user
	 * @param lastName name of user
	 * @param id is the id user will use to access account
	 * @param email of user
	 * @param password for user 
	 * @param maxCredits number of credits student is taking
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	/**
	 * Constructor method that creates Student Object and contains all fields except maxCredits which is set to 18 
	 * @param firstName name of user
	 * @param lastName name of user 
	 * @param id is the id user will use to access account
	 * @param email of user
	 * @param password for user 
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}
	
	/**
	 * Getter method for getting number of credits user is taking
	 * @return the maxCredits user is taking 
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	/**
	 * Setter method for maxCredits
	 * @param maxCredits is number of credits user is taking
	 * @throws IllegalArgumentException if maxCredits is less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}
	
	/**
	 * Generates a hashCode for Student that is used to map data
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}
	
	/**
	 * Compares a given object to this object for equality on all fields.
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
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}


	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() +
				"," + super.getEmail() + "," + super.getPassword() + "," + maxCredits;
		
	}
	
	/**
	 * Compares this Student to the Student being passed through the parameter. It will compare the lastName 
	 * first, then firstName, and lastly the ID. It will return depending if this student is less than, equal to,
	 * or greater than the Student object being passed through.
	 * 
	 * @param s Is a Student object being passed through to be compared to this object.
	 * @return int depending if this student is less than, equal to,
	 * or greater than the Student object being passed through.
	 */
	@Override
	public int compareTo(Student s) {
		int last = super.getLastName().compareTo(s.getLastName());
		if(last != 0) {
			return last;
		}
		
		int first = super.getFirstName().compareTo(s.getFirstName());
		if (first != 0) {
			return first;
		}
		
		
		return super.getId().compareTo(s.getId());
	}
	
	/**
	 * Returns the schedule of the student
	 * @return Schedule object that has the students schedule.
	 *
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	/**
	 * This method is used to see if a course can be added to the schedule. Will return true
	 * if schedule can be added. This method is used in the RegistrationManager to check
	 * if a Course can be added.
	 * @param c is the Course being checked to be added
	 *@return True if a course can be added.
	 */
	public boolean canAdd(Course c) {
		if((schedule.getScheduleCredits() + c.getCredits()) <= maxCredits) {
			return schedule.canAdd(c);
		}
		
		return false;
	}
}
