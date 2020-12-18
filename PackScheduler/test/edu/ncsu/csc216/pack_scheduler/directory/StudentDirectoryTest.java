package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	
	/** Valid course records for personal tests */
	private final String validTestFile2 = "test-files/student_records2.txt";
	/** Test first name */
	private static final String FIRST_NAME2 = "Jonathan";
	/** Test last name */
	private static final String LAST_NAME2 = "Nguyen";
	/** Test id */
	private static final String ID2 = "jhnguye4";
	/** Test email */
	private static final String EMAIL2 = "jhnguye4@ncsu.edu";
	/** Test password */
	private static final String PASSWORD2 = "pw15";
	/** Test max credits */
	private static final int MAX_CREDITS2 = 10;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	/**
	 * Resets course_records2.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp2() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records2.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records2.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}
	
	/**
	 * Personal Test for StudentDirectory().
	 */
	@Test
	public void testStudentDirectory2() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("jhnguye4"));
		assertEquals(0, sd.getStudentDirectory().length);
		assertTrue(sd.addStudent("Nguyen", "Jonathan", "jhnguye4", "jhnguye4@ncsu.edu", "pw15", "pw15", 15));
		assertEquals(1, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("jhnguye4"));
		assertEquals(0, sd.getStudentDirectory().length);
		
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}
	/**
	 * Personal Test for StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory2() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile2);
		assertEquals(3, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
	}
	
	/**
	 * Personal Tests for StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile2() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile2);
		assertEquals(3, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
	}
	
	/**
	 * Personal Tests for StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent2() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME2, LAST_NAME2, ID2, EMAIL2, PASSWORD2, PASSWORD2, MAX_CREDITS2);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME2, studentDirectory[0][0]);
		assertEquals(LAST_NAME2, studentDirectory[0][1]);
		assertEquals(ID2, studentDirectory[0][2]);
		
		//Test invalid Student with same id
		sd.addStudent(FIRST_NAME2, LAST_NAME2, ID2, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertEquals(1, sd.getStudentDirectory().length);
		
		//Test invalid Student with "" password
		try {
			sd.addStudent(FIRST_NAME2, LAST_NAME2, ID2, EMAIL, "", "", MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(1, sd.getStudentDirectory().length);
		}
		
		//Test invalid Student with null password
		try {
			sd.addStudent(FIRST_NAME2, LAST_NAME2, ID2, EMAIL, null, null, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(1, sd.getStudentDirectory().length);
		}
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
				
		//Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}
	
	/**
	 * Personal Test for StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent2() {
		StudentDirectory sd = new StudentDirectory();
				
		//Add students and remove
		sd.loadStudentsFromFile(validTestFile2);
		assertEquals(3, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("jhnguye4"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(2, studentDirectory.length);
		assertEquals("Smith", studentDirectory[0][0]);
		assertEquals("John", studentDirectory[0][1]);
		assertEquals("jsmith", studentDirectory[0][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory2() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Jonathan", "Nguyen", "jhnguye4", "jhnguye4@ncsu.edu", "pw", "pw", 15);
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertEquals(2, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records2.txt");
		checkFiles("test-files/expected_student_records2.txt", "test-files/actual_student_records2.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
