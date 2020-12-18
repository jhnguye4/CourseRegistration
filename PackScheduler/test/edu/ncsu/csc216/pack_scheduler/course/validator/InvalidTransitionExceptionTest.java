package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests the InvalidTransitionException class
 * @author jhnguye4
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Test method for constructor method with parameter. Testing a custom message and the 
	 * exception should return that custom message.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for constructor method without parameter. The default message should be returned.
	 */
	@Test
	public void testInvalidTransitionExceptionException() {
		InvalidTransitionException ce2 = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ce2.getMessage());
	}
}
