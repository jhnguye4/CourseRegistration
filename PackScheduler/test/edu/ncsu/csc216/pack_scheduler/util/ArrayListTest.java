/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the custom array list class
 * @author jhnguye4
 *
 */
public class ArrayListTest {
	private ArrayList<Student> a;

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#size()}.
	 */
	@Test
	public void testSize() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		a.add(0, s1);
		a.add(1, s2);
		a.add(2, s3);
		assertEquals(3, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#ArrayList()}.
	 */
	@Test
	public void testArrayList() {
		a = new ArrayList<Student>();
		assertEquals(0, a.size());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#get(int)}.
	 * Test for index out of bounds exception
	 */
	@Test
	public void testGetInt() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		a.add(0, s1);
		assertEquals("jsmith@ncsu.edu", a.get(0).getEmail());
		try {
			a.get(3);
			fail();
		} catch(IndexOutOfBoundsException e ) {
			System.out.println("Out of Range");
		}
		try {
			a.get(-1);
			fail();
		} catch(IndexOutOfBoundsException e ) {
			System.out.println("Out of Range");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.add(2, s3);
		assertEquals(4, a.size());
		assertEquals("john", a.get(0).getFirstName());
	}
	/**
	 * Test method for adding 11 Students so that array uses growArray.
	 */
	@Test
	public void testAddToGrow() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("will", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s6 = new Student("tyler", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s7 = new Student("michael", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s8 = new Student("brandon", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s9 = new Student("david", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s10 = new Student("adrian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s11 = new Student("kevin", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.add(2, s3);
		a.add(s5);
		a.add(s6);
		a.add(s7);
		a.add(s8);
		a.add(s9);
		a.add(s10);
		a.add(s11);
		assertEquals(11, a.size());
		assertEquals("kevin", a.get(10).getFirstName());
	}
	/**
	 * Test method for adding student that is duplicate where it will fail and throw
	 * a IllegalArgumentException.
	 */
	@Test
	public void testAddDuplicate() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.add(2, s3);
		try {
			a.add(4, s5);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
	}
	/**
	 * Test method for adding a null student and will throw a NullPointerException.
	 */
	@Test
	public void testAddNull() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.set(1, s3);
		try {
			a.add(3, null);
			fail();
		} catch(NullPointerException e) {
			System.out.println("null");
		}
	}
	
	/**
	 * Test method for adding a student outside of the index range.
	 */
	@Test
	public void testAddOutOfRange() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("brian", "nguyen", "bnguyen", "bnguyen@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.set(1, s3);
		try {
			a.add(6, s5);
			fail();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Out of Range");
		}
		try {
			a.add(-1, s5);
			fail();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Out of Range");
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.add(2, s3);
		assertEquals(4, a.size());
		assertEquals("john", a.get(0).getFirstName());
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("matthew", a.get(2).getFirstName());
		assertEquals("brian", a.get(3).getFirstName());
		a.remove(1);
		assertEquals("matthew", a.get(1).getFirstName());
		assertEquals(3, a.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSetIntE() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.set(1, s3);
		assertEquals("matthew", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		assertEquals(3, a.size());
		assertEquals("john", a.get(0).getFirstName());
	}
	/**
	 * Test method for setting a student that is already in the array. Test should fail and 
	 * catch a IllegalArgumentException
	 */
	@Test
	public void testSetDuplicate() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.set(1, s3);
		try {
			a.set(0, s5);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
	}
	/**
	 * Test method for setting a null object where it will catch a NullPointerException.
	 */
	@Test
	public void testSetNull() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.set(1, s3);
		try {
			a.set(0, null);
			fail();
		} catch(NullPointerException e) {
			System.out.println("null");
		}
	}
	/**
	 * Test method for setting a student that has an index out of bounds. Test
	 * should catch a IndexOutOfBoundsException
	 */
	@Test
	public void testSetOutOfRange() {
		a = new ArrayList<Student>();
		Student s1 = new Student("john", "smith", "jsmith", "jsmith@ncsu.edu", "123", 13);
		Student s2 = new Student("adam", "king", "aking", "aking@ncsu.edu", "pw", 13);
		Student s3 = new Student("matthew", "park", "mpark", "mpark@ncsu.edu", "pw1", 13);
		Student s4 = new Student("brian", "little", "blittle", "blittle@ncsu.edu", "pw2", 13);
		Student s5 = new Student("brian", "nguyen", "bnguyen", "bnguyen@ncsu.edu", "pw2", 13);
		a.add(0, s1);
		a.add(1, s4);
		assertEquals("brian", a.get(1).getFirstName());
		a.add(1, s2);
		assertEquals("adam", a.get(1).getFirstName());
		assertEquals("brian", a.get(2).getFirstName());
		a.set(1, s3);
		try {
			a.set(6, s5);
			fail();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Out of Range");
		}
		try {
			a.set(-1, s5);
			fail();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Out of Range");
		}
	}
}
