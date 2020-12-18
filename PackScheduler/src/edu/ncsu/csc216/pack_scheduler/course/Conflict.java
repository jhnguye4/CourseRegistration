
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This interface class is used to see if Activity being set conflicts with an Activity already set
 * in the schedule. If the times conflict then it will tell the user that the Activity is
 * invalid.
 * @author jhnguye4
 *
 */
public interface Conflict {
	/**
	 * Method checks if the Activity parameter conflicts with a Activity already set in
	 * schedule.
	 * @param possibleConflictingActivity Activity object being passed through and checked
	 * @throws ConflictException Custom checked exception
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
}
