/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Custom made Exception if user inputs an invalid Activity
 * @author jhnguye4
 *
 */
public class ConflictException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor method for custom exception if user adds an activity that conflicts time. 
	 * This constructor has a parameter of the String that will be displayed
	 * 
	 * @param message Is the message that will be displayed if this exception is thrown
	 */
	public ConflictException(String message) {
		super(message);
	}
	/**
	 * Constructor method for custom exception if user adds an activity that conflicts time. 
	 * This constructor does not have a parameter and will display the default message "Schedule Conflict".
	 * 
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
