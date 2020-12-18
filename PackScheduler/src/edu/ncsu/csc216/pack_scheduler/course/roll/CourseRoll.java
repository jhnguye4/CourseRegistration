/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayQueue;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;


/**
 * This class is uses a linked list and is able to add or remove students to the class roll 
 * if there is enough space on the roll.
 * 
 * @author jhnguye4
 *
 */
public class CourseRoll {
	/**private field for an abstract list*/
	private LinkedAbstractList<Student> roll;
	/**private field for number of students that can be enrolled in a class*/
	private int enrollmentCap;
	/**constant for minimum students that can be enrolled in class*/
	private static final int MIN_ENROLLMENT = 10;
	/**constant for maximum students that can be enrolled in class*/
	private static final int MAX_ENROLLMENT = 250;
	/**constant for maximum waitlist capacity that can be enrolled in class*/
	private static final int WAITLIST_SIZE = 10;
	/**private field that creates waitlist that has capacity of 10*/
	private ArrayQueue<Student> waitlist;
	
	
	/**
	 * This is the constructor class that initializes the linkedlist and then sets the capacity for the class by calling 
	 * the setEnrollmentCap method. 
	 * 
	 * @param c Course object that is passed through to put student on course roll
	 * @param capacity is the parameter that is passed through the setEnrollmentCap to check if the capacity is valid
	 *
	 */
	public CourseRoll(Course c, int capacity) {
		if(c == null) {
			throw new IllegalArgumentException();
		}
		setEnrollmentCap(capacity);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitlist = new ArrayQueue<Student>(WAITLIST_SIZE);
	}
	
	/**
	 * This method gets the enrollment capacity 
	 * 
	 * @return the number of students that can be enrolled
	 *
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
		
	}
	
	
	/**
	 * This method sets the enrollment capacity and throws an exception if it is outside the range or if the capacity is reset to a lower
	 * one
	 * 
	 * @param capacity is the number of students that can be enrolled in the class
	 * @throws IllegalArgumentException if the capacity is less than or greater than maximum enrollment or if capacity is set to a lower capacity
	 * than initial
	 *
	 */
	public void setEnrollmentCap(int capacity) {
		if (capacity < MIN_ENROLLMENT || capacity > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if(roll != null && capacity < roll.size()) {
			throw new IllegalArgumentException();
		}

		enrollmentCap = capacity;
	}
	
	/**
	 * This method enrolls the student into the class and reduces the capacity by one 
	 * 
	 * @param student is the student object that is trying to be added to the course 
	 * @throws IllegalArgumentException if the student being passed through is null.
	 *
	 */
	public void enroll(Student student) {
		if (canEnroll(student)) {
			roll.add(student);
		}
		else if(!canEnroll(student)) {
			waitlist.enqueue(student);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method drops the student from the course and increases the enrollment capacity by 1 if dropped
	 * 
	 * @param student is the student object that is trying to be added to the course 
	 * @throws IllegalArgumentException if the student being passed through is null.
	 *
	 */
	public void drop(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < roll.size(); i++) {
			if(student.equals(roll.get(i))) {
				roll.remove(i);
				if(waitlist.size() > 0) {
					roll.add((Student) waitlist.dequeue());
				}
			}
		}
		
	}
	
	/**
	 * This method gets the number of open seats left in the class
	 * 
	 * @return int which is the number of open seats in the class
	 *
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * This method checks if the student can be enrolled in the class. It will return false if the number of open seats is less than
	 * 0 and if the student is already enrolled in the class. 
	 * 
	 * @param student is the student that is being checked to be enrolled
	 * @return true if student can be added to the course and false otherwise
	 *
	 */
	public boolean canEnroll(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if(roll.size() == enrollmentCap) {
			return false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if(student.equals(roll.get(i))) {
				return false;
			}
		}

		return true;
	}
	/**
	 * This method is used to get the number of students on the waitlist. This is 
	 * used during testing to see if the waitlist is filling up after capacity is met
	 * @return count which is number of students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		int count = 0;
		for(int i = 0; i < waitlist.size(); i++) {
			count++;
		}
		return count;
	}
}
