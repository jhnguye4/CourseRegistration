/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * This tests the CourseNameValidator class
 * @author jhnguye4
 *
 */
public class CourseNameValidatorTest {
	/**
	 * Tests "C1" should throw exception
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFirstLetterSecondDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();

		assertFalse(cv.isValid("C1"));
		
	}
	/**
	 * Tests "CS1C116" should throw exception
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test 
	public void testSecondLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		try {
			cv.isValid("CS1C116");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
		
		
	}
	
	/**
	 * Tests "CSC1C16" should throw exception
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testThirdLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		try {
			cv.isValid("CSC1C16");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
	}
		
	/**
	 * Tests "CSCC16" should return false
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFourLetterTwoDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CSCC16"));
		
	}
	/**
	 * Tests "CSCC116" should return true
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFourLetterThreeDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertTrue(cv.isValid("CSCC116"));
		
	}
	/**
	 * Tests "CSCC116a" should return true
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFourLetterThreeDigitOneLetterSuffix() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertTrue(cv.isValid("CSCC116a"));
		
	}
	/**
	 * Tests "CSCC1167" should throw exception 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test 
	public void testFourLetterThreeDigitOneDigitSuffix() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		
		try {
			cv.isValid("CSCC1167");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
	}
	/**
	 * Tests "CSC1" should return false 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test 
	public void testThreeLetterOneDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CSC1"));
		
	}
	/**
	 * Tests "CSC11A" should throw exception 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testThreeLetterTwoDigit1Letter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		
		try {
			cv.isValid("CSC11A");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
		
	}
	/**
	 * Tests "CSC116" should return true 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testThreeLetterThreeDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertTrue(cv.isValid("CSC116"));
		
	}
	/**
	 * Tests "CSC116a" should return true
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test 
	public void testThreeLetterThreeDigit1LetterSuffix() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertTrue(cv.isValid("CSC116a"));
		
	}
	/**
	 * Tests "!" should throw exception 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testNoLetterOrDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		
		try {
			cv.isValid("!");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}

		
	}
	/**
	 * Tests "C" should return false  
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFirstCharLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("C"));

		
	}
	/**
	 * Tests "CS" should return false 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testTwoCharLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CS"));

		
	}
	/**
	 * Tests "CSC" should return false 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testThreeCharLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CSC"));

		
	}
	/**
	 * Tests "CSCC" should return false  
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFourCharLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CSCC"));

		
	}
	/**
	 * Tests "CSC11" should return false 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testThreeCharLetterTwoDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CSC11"));

		
	}
	/**
	 * Tests "CSCC1" should return false 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testFourCharLetterOneDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		assertFalse(cv.isValid("CSCC1"));

		
	}
	/**
	 * Tests "!" should throw exception 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testInitialDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		
		try {
			cv.isValid("2");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
	}
	/**
	 * Tests "CSC116AA" should throw exception 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testSufficOnLetter() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		
		try {
			cv.isValid("CSC116AA");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
	}
	/**
	 * Tests "CSC116A1" should throw exception 
	 * @throws InvalidTransitionException if transition is invalid
	 */
	@Test
	public void testSufficOnDigit() throws InvalidTransitionException {
		CourseNameValidator cv = new CourseNameValidator();
		
		try {
			cv.isValid("CSC116A1");
			fail();
		} catch(InvalidTransitionException e) {
			System.out.println("Invalid input");
		}
	}
}
