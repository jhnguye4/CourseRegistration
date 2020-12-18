/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * This class is used to Test the CourseRoll class
 * @author jhnguye4
 *
 */
public class CourseRollTest {
	/**private field for course roll*/
	private CourseRoll roll;
	/**
	 * this tests the constructor of CourseRoll for valid capacity
	 */
	@Test
	public void courseRollTest() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		roll = c.getCourseRoll();
		assertEquals(10, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
	}
	/**
	 * this tests the constructor of CourseRoll for invalid capacity
	 */
	@Test
	public void invalidCourseRollTest() {
		try {
			Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 1, "A");
			roll = c.getCourseRoll();
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("invalid enrollement cap");
		}
		try {
			Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 251, "A");
			roll = c.getCourseRoll();
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("invalid enrollement cap");
		}
	}
	
	/**
	 * this tests the enroll method for the class
	 */
	@Test
	public void enrollTest() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
		assertEquals(10, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
		
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		roll.enroll(s1);
		roll.enroll(s2);
		assertEquals(8, roll.getOpenSeats());
	}
	
	/**
	 * this tests the enroll method for the class for invalid student
	 */
	@Test
	public void invalidEnrollTest() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
		assertEquals(10, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
		
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("will", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s6 = new Student("tyler", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s7 = new Student("michael", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s8 = new Student("brandon", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s9 = new Student("david", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s10 = new Student("adrian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s11 = new Student("kevin", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		try {
			roll.enroll(null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("invalid enroll");
		}
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		try {
			roll.enroll(s11);
			assertEquals(1, roll.getNumberOnWaitlist());
		} catch(IllegalArgumentException e) {
			System.out.println("Exceeds capacity");
		}
		assertEquals(0, roll.getOpenSeats());
	}
	
	/**
	 * this tests the enroll method for the class for invalid student
	 */
	@Test
	public void invalidEnrollTest2() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
		assertEquals(10, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
		
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("will", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s6 = new Student("tyler", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s7 = new Student("michael", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s8 = new Student("brandon", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s9 = new Student("david", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s10 = new Student("adrian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s11 = new Student("kevin", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		try {
			roll.enroll(null);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("invalid enroll");
		}
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.setEnrollmentCap(10);
		roll.enroll(s10);
		try {
			roll.enroll(s11);
			//fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Exceeds capacity");
		}
		//assertEquals(0, roll.getOpenSeats());
	}
	
	/**
	 * This tests the drop method for the class
	 */
	@Test
	public void dropTest() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
		assertEquals(10, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
		
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		assertEquals(6, roll.getOpenSeats());

		
		roll.drop(s2);
		assertEquals(7, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
	}
	
	/**
	 * This tests the canEnroll method of CourseRoll
	 */
	@Test
	public void canEnrollTest() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		
		roll.enroll(s1);
		assertFalse(roll.canEnroll(s2));
	}
	
	/**
	 * This tests the number on waitlist
	 */
	@Test
	public void testNumberOnWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		roll = c.getCourseRoll();
		assertEquals(10, roll.getOpenSeats());
		assertEquals(10, roll.getEnrollmentCap());
		
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("will", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s6 = new Student("tyler", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s7 = new Student("michael", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s8 = new Student("brandon", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s9 = new Student("david", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s10 = new Student("adrian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s11 = new Student("kevin", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s12 = new Student("lebron", "james", "ljames", "ljames@ncsu.edu", "pw2", 13);
		Student s13 = new Student("james", "harden", "jharden", "jharden@ncsu.edu", "pw2", 13);
		
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		assertEquals(1, roll.getNumberOnWaitlist());
		roll.enroll(s12);
		assertEquals(2, roll.getNumberOnWaitlist());
		roll.enroll(s13);
		assertEquals(0, roll.getOpenSeats());
		assertEquals(3, roll.getNumberOnWaitlist());
	}


}
