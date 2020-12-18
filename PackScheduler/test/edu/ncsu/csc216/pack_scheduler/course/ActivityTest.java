/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;

/**
 * This class is used to test the Activity class and go through different scenarios if the Activity is conflicting
 * with another Activity in the schedule. Some scenarios that it will test are with testing two courses, two event, or a
 * course and event. It will also test boundaries to see if it is correctly failing in certain scenarios.
 * 
 * @author jhnguye4
 *
 */
public class ActivityTest {


	
	/**
	 * Test checks if two course activites conflict. Which it should not and should set the Activities in the schedule.
	 * Both Activities have the same times but are on different days.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	   //Checking if method is commutative by switching Activity objects to check if there are conflicts
	    try {
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	}
	/**
	 * Testing where endTime for this is the same as startTime for second Activity. Also tests on multiple days
	 */
	@Test
	public void testCheckConflictSameDay() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
		  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("MW");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail();
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("MW 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("MW 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}
	
	/**
	 * Testing on same day but startTime of Activity 2 and endTime of Activity 2 conflict 
	 */
	@Test
	public void testCheckConflictSameDayOther() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
		  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a2.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail();
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("TH 2:45PM-3:30PM", a2.getMeetingString());
	    }
	}
	
	/**
	 * Testing startTime of this conflicts with end time of other
	 */
	@Test
	public void testConflictThisStart() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
		  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a2.setMeetingDays("MW");
	    a2.setActivityTime(1200, 1330);
	    try {
	        a1.checkConflict(a2);
	        fail(); 
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("MW 12:00PM-1:30PM", a2.getMeetingString());
	    }
	}
	/**
	 * Testing startTime of this Course is in between startTime and endTime of other Course
	 */
	@Test
	public void testConflictBetweenStart() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
		  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a2.setMeetingDays("MW");
	    a2.setActivityTime(1300, 1430);
	    try {
	        a1.checkConflict(a2);
	        fail(); 
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("MW 1:00PM-2:30PM", a2.getMeetingString());
	    }
	}
	/**
	 * Testing endTime of this Course is in between startTime and endTime of other Course
	 */
	@Test
	public void testConflictBetweenEnd() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "TH", 1330, 1445);
		  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a2.setMeetingDays("MW");
	    a2.setActivityTime(1400, 1500);
	    try {
	        a1.checkConflict(a2);
	        fail();
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("MW 2:00PM-3:00PM", a2.getMeetingString());
	    }
	}



	

	
	

	
	
}
