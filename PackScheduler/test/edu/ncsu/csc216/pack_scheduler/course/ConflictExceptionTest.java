/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * Test class for ConflictException class. This class will test both constructors of
 * ConflictException and check if they output the correct message.
 * @author jhnguye4
 *
 */
public class ConflictExceptionTest {



	/**
	 * Test method for constructor method with parameter. Testing a custom message and the 
	 * exception should return that custom message.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for constructor method without parameter. The default message should be returned.
	 */
	@Test
	public void testConflictException() {
		ConflictException ce2 = new ConflictException();
		assertEquals("Schedule conflict.", ce2.getMessage());
	}

}
