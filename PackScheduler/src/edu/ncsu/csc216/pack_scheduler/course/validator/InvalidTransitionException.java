/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Creates custom Exception class when a states transition is invalid
 * @author jhnguye4
 *
 */
public class InvalidTransitionException extends Exception { 
	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor method for custom exception if transition is invalid
	 * This constructor has a parameter of the String that will be displayed
	 * 
	 * @param message Is the message that will be displayed if this exception is thrown
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	/**
	 * Constructor method for custom exception if transition is invalid. 
	 * This constructor does not have a parameter and will display the default message "Invalid FSM Transition".
	 * 
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
