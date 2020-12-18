package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This is the abstract class called Activity. This is the parent class for the subclasses Course and Event.
 * The similar parameters that both Course and Event have are in this class, so the setter/getter methods are 
 * implemented in this class. This class also has three abstract methods called getShortDisplayArray, getLongDisplayArray,
 * and isDuplicate. This class is used so that certain methods are not duplicated in Event and Course, rather they are just
 * created once.
 * 
 * @author jhnguye4
 *
 */
public abstract class Activity implements Conflict {
	/** UPPER_TIME is the constant used in setActivityTime to throw exceptions if its outside this upper limit */
	private static final int UPPER_TIME = 2400;
	/** UPPER_HOUR is the constant used in setActivityTime to throw exceptions if its outside this upper limit*/
	private static final int UPPER_HOUR = 60;
	/** UPPER_PM is the constant used in getMeetingString to subtract current time by 1200 if the startTime or
	 * endTime is greater than this value
	 */
	private static final int UPPER_PM = 1300;
	/** VALUE1200 is the constant used in getMeetingString to substract the time by this value if the 
	 * startTime or endTime is greater than 1300
	 */
	private static final int VALUE1200 = 1200;
	/** VALUE100 is the constant used in getMeetingString to divide or find remainder of the our or 
	 * minutes of start or end time
	 */
	private static final int VALUE100 = 100;
	/** Title of Activity, can be Course or an Event Title */
	private String title;
	/** Days that activity meets, can be Course or an Event activity  */
	private String meetingDays;
	/** Starting time of activity */
	private int startTime;
	/** Ending time of activity */
	private int endTime;
	
	/** Abstract method that returns an array of 4 parameters, name, section, title, meeting string 
	 * 	This method if used in getScheduledActivities and getFullScheduledActivites in WolfScheduler to store 
	 *  information of Activity into an array.
	 *  @return String [] of name, section, title, and meeting String of activity
	 */
	public abstract String[] getShortDisplayArray();
	
	/** Abstract method that returns an array of 7 parameters,
	 *  name, section, title, credits, instructorId, meeting String, and event detail.
	 *  This method if used in getScheduledActivities and getFullScheduledActivites in WolfScheduler to store 
	 *  information of Activity into an array.
	 *  @return String [] of name, section, title, credits, instructorId, meeting String, and event detail.
	 */
	public abstract String[] getLongDisplayArray();
	
	/** Abstract method that returns a boolean. Returns true if the activity is a duplicate and false if it 
	 * is not. This method is used in WolfScheduler to check if the activity in the schedule is a duplicate or not.
	 * @param activity is the Activity object being passed through this method
	 * @return boolean true if it is duplicate and false if not
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Constructor method of Activity that has four parameters.
	 * Calls on setter methods to error check the title, meetingDays, start time, and end time of the activity.
	 * 
	 * @param title is the title of the activity
	 * @param meetingDays is the days during the week that the activity meets
	 * @param startTime is the time that the activity starts 
	 * @param endTime is the time that the activity ends
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns title of course
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title of course, is used to throw exception if the title is null or ""
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or ""
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("Invalid course title");
		}
		this.title = title;
	}

	/**
	 * Returns days classes meet
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the days the classes meet
	 * 
	 * @param meetingDays is the days that activity meets
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Getter method that returns time the activity starts
	 * 
	 * @return startTime time that the activity starts
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Getter method that returns the time the class ends
	 * 
	 * @return endTime time that the activity ends
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the time the activity start and ends, and is used to throw exceptions and set time to 0 if meeting time is 'A'.
	 * Throws an exception if start and end time are below 0 or above 2400.
	 * Throws an exception if start time is greater than end time.
	 * Throws an exception if remainder of startTime or endTime is greater than 60 after dividing by 100
	 * 
	 * @param startTime the startTime of the class
	 * @param endTime   the endTime of the class 
	 * @throws IllegalArgumentException if certain cases of startTime and endTime are not met
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < 0000 || startTime >= UPPER_TIME) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (endTime < 0000 || endTime >= UPPER_TIME) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if ((startTime % VALUE100) >= UPPER_HOUR || (endTime % VALUE100) >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (meetingDays.equals("A")) {
			startTime = 0;
			endTime = 0;
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Converts standard time format rather than military format.
	 * Returns the String of days activity meets, and the correct time in standard time.
	 * @return String of time into standard time. 
	 */
	public String getMeetingString() {
		String startMeridiem = "AM";
		String endMeridiem = "AM";
		int meetStart = startTime;
		int meetEnd = endTime;
		int startHour = 0;
		int startMin = 0;
		int endHour = 0;
		int endMin = 0;
		if (startTime >= UPPER_PM) {
			meetStart -= VALUE1200;
		}
		if (endTime >= UPPER_PM) {
			meetEnd -= VALUE1200;
		}
		if (startTime >= VALUE1200) {
			startMeridiem = "PM";
		}
		if (endTime >= VALUE1200) {
			endMeridiem = "PM";
		}
		startHour = meetStart / VALUE100;
		startMin = meetStart % VALUE100;
		endHour = meetEnd / VALUE100;
		endMin = meetEnd % VALUE100;
	
		if (meetingDays.equals("A")) {
			return "Arranged";
		}
		if (startMin == 0 && endMin == 0) {
			return meetingDays + " " + startHour + ":" + startMin + "0" + startMeridiem + "-" + endHour + ":" + endMin
					+ "0" + endMeridiem;
		}
		if (startMin == 0) {
			return meetingDays + " " + startHour + ":" + startMin + "0" + startMeridiem + "-" + endHour + ":" + endMin
					+ endMeridiem;
		}
		if (endMin == 0) {
			return meetingDays + " " + startHour + ":" + startMin + startMeridiem + "-" + endHour + ":" + endMin + "0"
					+ endMeridiem;
		}
	
		return meetingDays + " " + startHour + ":" + startMin + startMeridiem + "-" + endHour + ":" + endMin
				+ endMeridiem;
	
	}
	
	/**
	 * Hashcode method is used to map data
	 * 
	 * return int unique number allocated to object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	/**
	 * Equals method to compare details of Activity to see if object passing through has the same value
	 * @param obj is the object being passed through the method to see if value is equal to
	 * @return boolean true if it is equal and false if it is not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	/**
	 * This method is called upon in addCourse/addEvent of WolfScheduler with its purpose to check if the Activity about to be set
	 * conflicts with an Activity time that is already in the schedule. If the Activity conflicts with an Activity already in schedule
	 * then it will return a message "The course/event cannot be added due to a conflict" depending what kind of Activity is being passed through.
	 * If the Activity does not conflict then this method does not do anything.
	 * @param possibleConflictingActivity is an Activity being passed through to check if it conflicts with Activities in the schedule
	 * @throws ConflictException is the custom exception created that is thrown if the Activity does conflict with an Activity in the schedule.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String[] s = possibleConflictingActivity.getShortDisplayArray();
		for (int i = 0; i < this.meetingDays.length(); i++) {
			for(int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++){
				if (meetingDays.charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)){
					if (possibleConflictingActivity.getStartTime() >= this.startTime && this.endTime >= possibleConflictingActivity.getStartTime()) {
						if(meetingDays.contains("A") && possibleConflictingActivity.getMeetingDays().contains("A")) {
							continue;
						}
						if(s[0].equals("")) {
							throw new ConflictException("The event cannot be added due to a conflict");
						} else {
							throw new ConflictException("The course cannot be added due to a conflict");
						}
					}
					if (possibleConflictingActivity.getEndTime() >= this.startTime && this.endTime >= possibleConflictingActivity.getEndTime()) {
						if(s[0].equals("")) {
							throw new ConflictException("The event cannot be added due to a conflict");
						} else {
							throw new ConflictException("The course cannot be added due to a conflict");
						}
					}
					if (possibleConflictingActivity.getStartTime() <= this.startTime && this.endTime <= possibleConflictingActivity.getEndTime()) {
						if(s[0].equals("")) {
							throw new ConflictException("The event cannot be added due to a conflict");
						} else {
							throw new ConflictException("The course cannot be added due to a conflict");
						}
					}
				}
			}	
		}
		
	}
	


}