package edu.ncsu.csc216.pack_scheduler.io;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;


/**
 * 
 * Tests StudentRecordIO.
 * @author jhnguye4
 *
 */
public class FacultyRecordIOTest {
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Valid expected course records */
	private final String expectedValidTestFile = "test-files/expected_full_faculty_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	
	/**
	 * Resets student_records.txt for use in other tests.
	 * @throws IOException if input file cannot be found
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests readValidStudentRecords().
	 */
	@Test
	public void testReadValidFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());
			LinkedList<Faculty> expectedFaculty = FacultyRecordIO.readFacultyRecords(expectedValidTestFile);
			assertEquals(8, expectedFaculty.size());

			for (int i = 0; i < faculty.size(); i++) {
				assertEquals(faculty.get(i).toString(), expectedFaculty.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	/**
	 * Tests readInvalidStudentRecords().
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		edu.ncsu.csc216.pack_scheduler.util.LinkedList<Faculty> faculty;
		try {
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Tests writeStudentRecords()
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "0[ï¿½Rï¿½ï¿½\"ï¿½ï¿½uï¿½ï¿½ï¿½\\7Xï¿½Fï¿½ï¿½ï¿½9ï¿½{-Oï¿½Fï¿½apï¿½", 3));    
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}

		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_student_records.txt");
	}
	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File(expFile));
			Scanner actScanner = new Scanner(new File(actFile));

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
