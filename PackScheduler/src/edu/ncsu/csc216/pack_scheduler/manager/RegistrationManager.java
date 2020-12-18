package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The RegistrationManager is a class that uses the singleton pattern that gets 
 * the instance of an object. When the instance of this class is called, it will 
 * create a Registrar and instatiate a course catalog and student directory.
 * 
 * @author Sarah Heckman, jhnguye4
 */
public class RegistrationManager {
	
	/** RegistationManager field */
	private static RegistrationManager instance;
	/** CourseCatalog object */
	  private CourseCatalog courseCatalog;
	  /** StudentDirectory object */
	private StudentDirectory studentDirectory;
	/** FacultyDirectory object */
	  private FacultyDirectory facultyDirectory;
	/** Registrar field */
	  private User registrar;
	  /** Current user field */
	   private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** File that is accesed for create registrar */
	private static final String PROP_FILE = "registrar.properties";

	/** private constructor that is initializes a course catalog, student directory,
	 * and creates a registrar when called upon in the instance method */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}
	
	/** Creates a registrar from information in properties file 
	 * @throws IllegalArgumentException if the Registrar cannot be created
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/** Method takes a String and hashes the password
	 * @param pw is a String of the password
	 * @return String of the hashed password
	 * @throws IllegalArgumentException if it cannot hash password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/** This method is a public method that will get an instance of a 
	 * RegistrationManager. This method will instantiate the RegistrationManager. 
	 * @return an RegistrationManager object
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/** Gets the CourseCatalog
	 * @return CourseCatalog of all courses 
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/** Gets the StudentDirectory
	 * @return StudentDirectory of all students
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/** Gets the FacultyDirectory
	 * @return FacultyDirectory of all faculty
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	/** This method allows user to login if they input the correct id and password.
	 * @param id is a String of the id
	 * @param password is a String of the password
	 * @return boolean that returns true if you can login and false if you cant  
	 * @throws IllegalArgumentException if the User does not exist
	 */
	public boolean login(String id, String password) {
		if(currentUser != null) {
			return false;
		}
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
			digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
					return true;
			}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
			
			Student s = studentDirectory.getStudentById(id);
			if(s != null) {
				try {
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (s.getPassword().equals(localHashPW)) {
						currentUser = s;
							return true;
					}
				} catch (NoSuchAlgorithmException e) {
						throw new IllegalArgumentException();
				} 
			}
			
			Faculty f = facultyDirectory.getFacultyById(id);
			if(f != null) {
				try {
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (f.getPassword().equals(localHashPW)) {
						currentUser = f;
							return true;
					}
				} catch (NoSuchAlgorithmException e) {
						throw new IllegalArgumentException();
				} 
			}
			if(currentUser == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
				
			return false;
	}
	/** Call this method to log out of CurrentUser by making CurrentUser null */
	public void logout() {
		currentUser = null;
	}
	
	/**
	 * This method gets the current user
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	/** Calls a CourseCatalog or StudentDirectory and calls upon newCourseCatlog
	 * and newStudentDirectory to clear the arrays
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * This method adds a course to the faculties schedule
	 * @param c is the course that will be added to the faculty members schedule
	 * @param f is the faculty member that the class will be added to
	 * @return boolean true if the class can be added
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
		return f.getSchedule().addCourseToSchedule(c);
	}
	
	/**
	 * This method removes a course to the faculties schedule
	 * @param c is the course that will be removed from the faculty members schedule
	 * @param f is the faculty member that the class will be removed from
	 * @return boolean true if the class can be removed
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
		return f.getSchedule().removeCourseFromSchedule(c);
	}
	
	/**
	 * This method resets the faculties schedule
	 * @param f is the faculty member that the class will be reset
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
		f.getSchedule().resetSchedule();
		
	}
	
	/** Class has a Registrar constructor that creates a user that is a Registrar
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password 
		 * in the registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
}
