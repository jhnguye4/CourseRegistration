package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;


import org.junit.Test;




/**
 * Tests all the methods of the Student Class
 * @author jhnguye4
 *
 */
public class StudentTest {
	/** Student first name. */
	private static final String FIRST_NAME = "JONATHAN";
	/** Student last name. */
	private static final String LAST_NAME = "NGUYEN";
	/** Student id. */
	private static final String ID = "jhnguye4";
	/** Student email. */
	private static final String EMAIL = "jhnguye4@ncsu.edu";
	/** Student password. */
	private static final String PASSWORD = "CSC216";
	/** Student credits. */
	private static final int CREDITS = 16;
	
	/**
	 * Tests the Course constructor with all field parameters.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		//Setting name can only be tested through the constructor
		
		//Testing for null first name
		Student s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Testing for empty string first name
		s = null;
		try {
			s = new Student("", LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		
		//Test a valid construction
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the Course constructor with 5 parameters.
	 */
	@Test
	public void testCourseStringStringStringStringString() {
		Student s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(18, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
	}
	
	/**
	 * Tests setFirstName().
	 */
	@Test
	public void testSetFirstName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting the First Name to null doesn't change the first name (or anything else).
		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		//Test that setting the First Name to "" doesn't change the first name (or anything else).
		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Valid set
		s.setFirstName("MIKE");
		assertEquals("MIKE", s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests setLastName().
	 */
	@Test
	public void testSetLastName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting the Last Name to null doesn't change the last name (or anything else).
		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		//Test that setting the Last Name to "" doesn't change the last name (or anything else).
		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Valid set
		s.setLastName("WU");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals("WU", s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests setId().
	 */
	@Test
	public void testSetId() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting the ID to null doesn't change the id (or anything else).
		try {
			s.setId(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		//Test that setting the ID to "" doesn't change the id (or anything else).
		try {
			s.setId("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Valid set
		s.setId("mwu15");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals("mwu15", s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests setEmail().
	 */
	@Test
	public void testSetEmail() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting the Email to null doesn't change the email (or anything else).
		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		//Test that setting the Email to "" doesn't change the email (or anything else).
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Test that setting the Email to not have "@" doesn't change the email (or anything else).
		try {
			s.setEmail("mwu15.edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Test that setting the Email to not have "." doesn't change the email (or anything else).
		try {
			s.setEmail("mwu15@edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Test that setting the Email to have "." before "@" and no "." at end. Doesn't change the email (or anything else).
		try {
			s.setEmail("mwu.15@edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Valid set
		s.setEmail("mwu15@ncsu.edu");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals("mwu15@ncsu.edu", s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests setPassword().
	 */
	@Test
	public void testSetPassword() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting the Password to null doesn't change the password (or anything else).
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		//Test that setting the Password to "" doesn't change the password (or anything else).
		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Valid set
		s.setPassword("12345");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals("12345", s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests setMaxCredits().
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting the MaxCredits to < 3 doesn't change the max credits (or anything else).
		try {
			s.setMaxCredits(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		//Test that setting the MaxCredits to > 18 doesn't change the max credits (or anything else).
		try {
			s.setMaxCredits(20);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Valid set
		s.setMaxCredits(15);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(15, s.getMaxCredits());
	}
	
	/**
	 * Tests that the equals method works for all Student fields.
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		User s3 = new Student("MIKE", LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		User s4 = new Student(FIRST_NAME, "WU", ID, EMAIL, PASSWORD, CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "mwu15", EMAIL, PASSWORD, CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "mwu15@ncsu.edu", PASSWORD, CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "12345", CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 15);

		
		//Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		//Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}
	
	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		User s3 = new Student("MIKE", LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		User s4 = new Student(FIRST_NAME, "WU", ID, EMAIL, PASSWORD, CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "mwu15", EMAIL, PASSWORD, CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "mwu15@ncsu.edu", PASSWORD, CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "12345", CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 15);
		
		//Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}
	
	/**
	 * Tests that toString returns the correct comma-separated value.
	 */
	@Test
	public void testToString() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		String s1 = "JONATHAN,NGUYEN,jhnguye4,jhnguye4@ncsu.edu,CSC216,16";
		assertEquals(s1, s.toString());
	}
	
	/**
	 * Tests that compareTo returns integer depending if this student is less than, equal to,
	 * or greater than the Student object being passed through. This tests different firstName, lastName, and ids.
	 * One cases will have same firstNames but different lastNames, another case will have everything the same 
	 * and should output 0, etc. 
	 */
	@Test
	public void testCompareTo() {
		
		Student s1 = new Student("jonathan", "nguyen", "jhnguye4", EMAIL, PASSWORD, CREDITS);
		Student s2 = new Student("jonathan", "nguyen", "jhnguye4", EMAIL, PASSWORD, CREDITS);
		Student s3 = new Student("mike", "nguyen", "jhnguye4", EMAIL, PASSWORD, CREDITS);
		Student s4 = new Student("jonathan", "wu", "jhnguye4", EMAIL, PASSWORD, CREDITS);
		Student s5 = new Student("jonathan", "nguyen", "mwu15", EMAIL, PASSWORD, CREDITS);
		
		//Testing object that has same lastname firstname and id
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		//Testing object that has same lastname but different firstname 
		assertEquals(-3, s1.compareTo(s3));
		assertEquals(3, s3.compareTo(s1));
		//Testing object that has different lastname 
		assertEquals(-9, s1.compareTo(s4));
		assertEquals(9, s4.compareTo(s1));
		//Testing object that has same lastname, firstname but different id 
		assertEquals(-3, s1.compareTo(s5));
		assertEquals(3, s5.compareTo(s1));
	}
	
//	/**
//	 * Tests the Course constructor with all field parameters.
//	 */
//	@Test
//	public void testCanAdd() {
//		//Test a valid construction
//		Student s = null;
//		Schedule schedule = s.getSchedule();
//		
//		try {
//			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 3);
//			assertEquals(FIRST_NAME, s.getFirstName());
//			assertEquals(LAST_NAME, s.getLastName());
//			assertEquals(ID, s.getId());
//			assertEquals(EMAIL, s.getEmail());
//			assertEquals(PASSWORD, s.getPassword());
//			assertEquals(CREDITS, s.getMaxCredits());
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//		
//	}
	

}
