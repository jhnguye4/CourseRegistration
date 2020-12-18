
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class has setter and getter methods, along with hash() and equals(). This class is a subclass of the 
 * parent class called activities. If the activity that is placed into schedule is a course object then it will access
 * specific methods in this class to check if the Course is valid to be added to schedule.
 * @author jhnguye4
 *
 */
public class Course extends Activity implements Comparable<Course> { 
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**private field for course validator*/
	private CourseNameValidator validator;
	/**private field for course roll*/
	private CourseRoll roll;
	/**
	 * Constructs a Course Object with values for all fields. Calls upon the setter methods in the parent and current class to 
	 * error check and will throw exceptions if appropriate parameters are not used
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      hours for Course
	 * @param instructorId instructor's unity ID
	 * @param enrollmentCap the cap of students for class
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime); 
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID,
	 * and meetingDays for course that are arranged. Calls on previous constructor to set values.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      hours for Course
	 * @param instructorId instructor's unity ID
	 * @param enrollmentCap the cap of students for class
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId,  int enrollmentCap, String meetingDays){
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns course name
	 * 
	 * @return name course name that is greater than 4 but less than 6 characters long
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets course name and error checks if an appropriate name is used
	 * 
	 * @param name the name to set
	 * @throws InvalidTransitionException 
	 * @throws IllegalArgumentException if name is null or has a length less than 4 or greater than 6
	 */
	private void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Invalid course name");
		}
		if (name.length() < 4 || name.length() > 6) {
			throw new IllegalArgumentException();
		}
		try {
			validator = new CourseNameValidator();
			if(validator.isValid(name)) {
				this.name = name;
			}
		} catch(InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid Course Name");
		}
	}

	/**
	 * Returns section of course
	 * 
	 * @return section which is the String that has a length of 3
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets section of course and error checks if an appropriate section is displayed
	 * 
	 * @param section is the String passed through of section number
	 * @throws IllegalArgumentException if section is null, "", or if length of section is not 3
	 */
	public void setSection(String section) {
		if (section == null || section.equals("")) {
			throw new IllegalArgumentException("Invalid section number");
		}
		if (section.length() != 3) {
			throw new IllegalArgumentException("Invalid section number");
		}
		this.section = section;
	}

	/**
	 * Returns credits of course
	 * 
	 * @return credits which is an integer between 1-4
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets credits of course and error checks if an appropriate credits are displayed
	 * 
	 * @param credits the number credits to set
	 * @throws IllegalArgumentException if credits is greater than 5 or less than 1
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns ID of Instructor
	 * 
	 * @return instructorId is the String of ID of the instructor
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets ID of instructor and error checks if an appropriate instructorId is used
	 * 
	 * @param instructorId is the name of the instructor
	 * @throws IllegalArgumentException if instructorId is null or ""
	 */
	public void setInstructorId(String instructorId) {
//		if (instructorId == null || instructorId.equals("")) {
//			throw new IllegalArgumentException("Invalid instructor unity id");
//		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets meeting days that the course meets. It is called in constructor method and is used 
	 * to error check and throw an exception if meeting days are null, "", or has letter that shouldn't be used.
	 * 
	 * @param meetingDays is the String of days that the course meets
	 * @throws IllegalArgumentException if meeting days is null, empty, have a string that is not allowed
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (meetingDays.contains("A") && meetingDays.length() > 1) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			Character c = meetingDays.charAt(i);
			if (!c.equals('M') && !c.equals('T') && !c.equals('W') && !c.equals('H') && !c.equals('F')
					&& !c.equals('A')) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Hashcode method is used to map data
	 * 
	 * return int unique number allocated to object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	/**
	 * Equals method to compare details of course to see if object passing through has the same value
	 * @param obj is the object being passed through the method to see if value is equal to
	 * @return boolean true if it is equal and false if it is not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (super.getMeetingDays().equals("A")) {
			return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() +  "," + super.getMeetingDays();
		}
		return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + super.getMeetingDays() + ","
				+ super.getStartTime() + "," + super.getEndTime();
	}
	/**
	 * Returns String array with name section, title and meetingDays. Used in WolfScheduler for getCourseCatalog,
	 * and getScheduledActivities.
	 * 
	 * @return String array with name section, title and meetingDays
	 */
	@Override
	public String[] getShortDisplayArray() {
		String [] s = {name, section, super.getTitle(), super.getMeetingString(), Integer.toString(roll.getOpenSeats())}; 
		return s;
	}
	/**
	 * Returns String array with name section, title, credits, instructorId, meetingDays, and an empty String.
	 * Used in WolfScheduler for getFullScheduledActivities.
	 * 
	 * @return String array of name section, title, credits, instructorId, meetingDays, and an empty String
	 */
	@Override
	public String[] getLongDisplayArray() {
		String [] l = {name, section, super.getTitle(), Integer.toString(credits), instructorId, super.getMeetingString(), ""};
		return l;
	}
	/**
	 * Method is used to check if Activity is a duplicate to another activity in schedule array.
	 * It will first check if the activity is an instanceof Course first before it goes further
	 * 
	 * @param activity is an Activity object passed through and can either be a Course or Event
	 * @return boolean, true if it is a duplicate and false if it is not
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			return this.getName().equals(((Course)activity).getName());
			
		}
		return false;
	}
	
	/**
	 * Compares this Course to the Course being passed through the parameter. It will compare the name 
	 * and then section. It will return depending if this Course is less than, equal to,
	 * or greater than the Course object being passed through.
	 * 
	 * @param c Is a Course object being passed through to be compared to this object.
	 * @return int depending if this Course is less than, equal to,
	 * or greater than the Course object being passed through.
	 */
	@Override
	public int compareTo(Course c) {
		int courseName = name.compareTo(c.getName());
		if(courseName != 0) {
			return courseName;
		}	
		
		return section.compareTo(c.getSection());
	}
	
	/**
	 * This method gets the Course roll.
	 * @return CourseRoll of the course 
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

}
