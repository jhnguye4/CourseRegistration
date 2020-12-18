/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule Class
 * @author jhnguye4
 *
 */
public class ScheduleTest {

	/**
	 * Tests the Schedule constructor where it should create an empty ArrayList
	 *
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		assertEquals(0, s.getScheduledCourses().length);
		
	}
	/**
	 * Test adds valid courses to the ArrayList 
	 *
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Course c1 = new Course("CSC116", "Introduction into Java", "001", 3, "mglatz", 15, "TH", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math", "001", 3, "sheckman", 15, "MW", 1000, 1100);
		Course c3 = new Course("CSC316", "Data Structures", "002", 4, "grouskas", 15, "MW", 800, 900);
		s.addCourseToSchedule(c);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);
		assertEquals(4, s.getScheduledCourses().length);
		assertEquals("CSC226", s.getScheduledCourses()[2][0]);
		assertEquals("CSC316", s.getScheduledCourses()[3][0]);
		assertEquals("002", s.getScheduledCourses()[3][1]);
		assertEquals("Data Structures", s.getScheduledCourses()[3][2]);
		assertEquals("MW 8:00AM-9:00AM", s.getScheduledCourses()[3][3]);
		assertEquals("15", s.getScheduledCourses()[3][4]);
		
		
	}
	/**
	 * Test adds a duplicate course and should catch an exception
	 *
	 */
	@Test
	public void testAddDuplicateToSchedule() {
		Schedule s = new Schedule();
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Course c1 = new Course("CSC116", "Introduction into Java", "001", 3, "mglatz", 15, "TH", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math", "001", 3, "sheckman", 15, "MW", 1000, 1100);
		Course c3 = new Course("CSC216", "Data Structures", "002", 4, "grouskas", 15, "MW", 800, 900);
		s.addCourseToSchedule(c);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		try {
			s.addCourseToSchedule(c3);
			fail();
		} catch(IllegalArgumentException e) { 
			assertEquals(3, s.getScheduledCourses().length);
			System.out.println("Duplicate");
		}

	}
	
	/**
	 * Test adds a conflicting course and should catch an exception
	 *
	 */
	@Test
	public void testAddConflictToSchedule() {
		Schedule s = new Schedule();
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Course c1 = new Course("CSC116", "Introduction into Java", "001", 3, "mglatz", 15, "TH", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math", "001", 3, "sheckman", 15, "MW", 1000, 1100);
		Course c3 = new Course("CSC316", "Data Structures", "002", 4, "grouskas", 15, "MW", 1330, 1445);
		s.addCourseToSchedule(c);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		try {
			s.addCourseToSchedule(c3);
			fail();
		} catch(IllegalArgumentException e) { 
			assertEquals(3, s.getScheduledCourses().length);
			System.out.println("Conflict");
		}

	}
	/**
	 * Test removes a course 
	 *
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Course c1 = new Course("CSC116", "Introduction into Java", "001", 3, "mglatz", 15, "TH", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math", "001", 3, "sheckman", 15, "MW", 1000, 1100);
		Course c3 = new Course("CSC316", "Data Structures", "002", 4, "grouskas", 15, "MW", 800, 900);
		s.addCourseToSchedule(c);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);
		assertEquals(4, s.getScheduledCourses().length);
		assertEquals("CSC116", s.getScheduledCourses()[1][0]);
		s.removeCourseFromSchedule(c1);
		assertEquals(3, s.getScheduledCourses().length);
		assertEquals("CSC226", s.getScheduledCourses()[1][0]);
		assertEquals("CSC316", s.getScheduledCourses()[2][0]);

	}
	
	/**
	 * Test resets the ArrayList of Courses so that it has a size of 0
	 *
	 */
	@Test
	public void testResetSchedule() {
		Schedule s = new Schedule();
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Course c1 = new Course("CSC116", "Introduction into Java", "001", 3, "mglatz", 15, "TH", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math", "001", 3, "sheckman", 15, "MW", 1000, 1100);
		Course c3 = new Course("CSC316", "Data Structures", "002", 4, "grouskas", 15, "MW", 800, 900); 
		s.addCourseToSchedule(c);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);
		assertEquals(4, s.getScheduledCourses().length);
		assertEquals("CSC116", s.getScheduledCourses()[1][0]);
		s.resetSchedule();
		assertEquals(0, s.getScheduledCourses().length);
	}
	/**
	 * Test valid and invalid titles where invalid should catch exceptions and 
	 * valid titles should be able to be set.
	 *
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();
		try {
			s.setTitle("");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(0, s.getScheduledCourses().length);
		}
		try {
			s.setTitle(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(0, s.getScheduledCourses().length);
		}
		s.setTitle("New Title");
		assertEquals("New Title", s.getTitle());
	}
	/**
	 * Test valid and invalid titles where invalid should catch exceptions and 
	 * valid titles should be able to be set.
	 *
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		Course c1 = new Course("CSC116", "Introduction into Java", "001", 3, "mglatz", 15, "TH", 1330, 1445);
		Course c2 = new Course("CSC226", "Discrete Math", "001", 3, "sheckman", 15, "MW", 1000, 1100);
		Course c3 = new Course("CSC316", "Data Structures", "002", 4, "grouskas", 15, "MW", 800, 900); 
		s.addCourseToSchedule(c);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);
		assertEquals(14, s.getScheduleCredits());
		
	}
}
