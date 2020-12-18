/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;




/**
 * This class is used to test the CourseCatalog class.
 * @author jhnguye4
 *
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";

	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws IOException if input file cannot be found
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the CourseCatalog is initialized to an empty list
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.testNewCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		//Test that if there are courses in the catalog, they 
		//are removed after calling newCourseCatalog().
		CourseCatalog cc = new CourseCatalog();
		
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(8, cc.getCourseCatalog().length);
		
		cc.newCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.loadCoursessFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
				
		//Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(8, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourse() {
		CourseCatalog cc = new CourseCatalog();
		
		//Test valid Course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 15, MEETING_DAYS, START_TIME, END_TIME);
		String [][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		
		cc.addCourseToCatalog("CSC226", "Java2", "001", 4, "jtetter", 10, "MW");
	}
	
	/**
	 * Tests CourseCatalog.removeCourseFromFile().
	 */
	@Test
	public void testRemoveCourse() {
		CourseCatalog cc = new CourseCatalog();
				
		//Add courses and remove
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(8, cc.getCourseCatalog().length);
		assertTrue(cc.removeCourseFromCatalog("CSC116", "001"));
		String [][] courseCatalog = cc.getCourseCatalog();
		assertEquals(7, courseCatalog.length);
		assertEquals("CSC116", courseCatalog[0][0]);
		assertEquals("002", courseCatalog[0][1]);
		assertEquals("Intro to Programming - Java", courseCatalog[0][2]);
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		
		//Attempt to get a course that doesn't exist
		assertNull(cc.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
//		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 15, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(4, cc.getCourseFromCatalog("CSC216", "001").getCredits());
	}
	
	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		//Add a courses
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100);
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "spbalik", 10, "MW", 1120, 1310);
		cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445);
		cc.addCourseToCatalog("CSC216", "Programming Concepts - Java", "002", 4, "jtking", 10, "MW", 1330, 1445);
		cc.addCourseToCatalog("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", 10, "MWF", 935, 1025);
		
		assertEquals(5, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_catalog.txt", "test-files/actual_course_records.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
