/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the Faculty getter/setters and equals/hashCode
 * @author jhnguye4
 *
 */
public class FacultyTest {
	/** Faculty first name. */
	private static final String FIRST_NAME = "Sarah";
	/** Faculty last name. */
	private static final String LAST_NAME = "Heckman";
	/** Faculty id. */
	private static final String ID = "sheckman";
	/** Faculty email. */
	private static final String EMAIL = "sheckman@ncsu.edu";
	/** Faculty password. */
	private static final String PASSWORD = "CSC216";
	/** Faculty maxCourses. */
	private static final int COURSES = 1;

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Faculty f = null;
		Faculty f2 = null;
		Faculty f3 = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
			f2 = new Faculty("James", "Tetterton", "jtetterton", "jtetterton@ncsu.edu", "123", 2);
			assertFalse(f.equals(f2));
			f3 = new Faculty("James", "Tetterton", "jtetterton", "jtetterton@ncsu.edu", "123", 2);
			assertTrue(f2.equals(f3));
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Test method for hashCode
	 */
	@Test
	public void testHashObject() {
		Faculty f = null;
		Faculty f2 = null;
		Faculty f3 = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
			f2 = new Faculty("James", "Tetterton", "jtetterton", "jtetterton@ncsu.edu", "123", 2);
			f3 = new Faculty("James", "Tetterton", "jtetterton", "jtetterton@ncsu.edu", "123", 2);
			assertEquals(f2.hashCode(), f3.hashCode());
			assertNotEquals(f.hashCode(), f3.hashCode());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#Faculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testFaculty() {
		Faculty f = null;
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(COURSES, f.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#setMaxCourses(int)}.
	 */
	@Test
	public void testSetMaxCourses() {
		Faculty f = null;
		//Invalid Courses
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
			f.setMaxCourses(-1);
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Course");
		}
		
		//Invalid Courses
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
			f.setMaxCourses(4);
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Course");
		}
		
		//Valid Courses set
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(COURSES, f.getMaxCourses());
			f.setMaxCourses(3);
			assertEquals(3, f.getMaxCourses());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Course");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Faculty#toString()}.
	 */
	@Test
	public void testToString() {
		Faculty f = null;
		//Valid Courses set
		try {
			f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, COURSES);
			assertEquals(FIRST_NAME, f.getFirstName());
			assertEquals(LAST_NAME, f.getLastName());
			assertEquals(ID, f.getId());
			assertEquals(EMAIL, f.getEmail());
			assertEquals(PASSWORD, f.getPassword());
			assertEquals(COURSES, f.getMaxCourses());
			assertEquals("Sarah,Heckman,sheckman,sheckman@ncsu.edu,CSC216,1", f.toString());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Course");
		}
	}

}
