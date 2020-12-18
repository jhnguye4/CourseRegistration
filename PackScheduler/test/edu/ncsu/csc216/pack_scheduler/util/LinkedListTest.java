/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ListIterator;

import org.junit.Test;


/**
 * This class tests the linked list class
 * @author jhnguye4
 *
 */
public class LinkedListTest {
	private LinkedList<String> s;
	/**
	 * Tests the constructor of the linked list 
	 */
	@Test
	public void testLinkedList() {
		s = new LinkedList<String>();
		assertEquals(0, s.size());
	}
	/**
	 * tests the add function 
	 */
	@Test
	public void testAdd() {
		s = new LinkedList<String>();
		s.add("abc");
		s.add("cdf");
		s.add("efg");
		s.add("hij");
		assertEquals("abc", s.get(0));
		assertEquals(4, s.size());
		ListIterator<String> list = s.listIterator();
		while(list.hasNext()) {
			System.out.println(list.next());
		}
	}
	/**
	 * Tests adding a duplicate
	 */
	@Test
	public void testAddDuplicate() {
		s = new LinkedList<String>();
		try {
			s.add("abc");
			s.add("cdf");
			s.add("efg");
			s.add("abc");
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
		
	}
	/**
	 * Tests adding a null
	 */
	@Test
	public void testAddNull() {
		s = new LinkedList<String>();
		try {
			s.add("abc");
			s.add("cdf");
			s.add("efg");
			s.add(null);
			fail();
		} catch(NullPointerException e) {
			System.out.println("Duplicate");
		}
		
	}
	/**
	 * Test adding at an index
	 */
	@Test
	public void testAddIndex() {
		s = new LinkedList<String>();
		try {
			s.add(0, "abc");
			assertEquals("abc", s.get(0));
			s.add(1, "cdf");
			assertEquals("cdf", s.get(1));
			s.add(1, "efg");
			assertEquals("efg", s.get(1));
			assertEquals("cdf", s.get(2));
			s.add(3, "hij");
			assertEquals("hij", s.get(3));
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
		
	}
	/**
	 * Test adding at end
	 */
	@Test
	public void testAddEnd() {
		s = new LinkedList<String>();
		try {
			s.add(0, "abc");
			assertEquals("abc", s.get(0));
			s.add(1, "cdf");
			assertEquals("cdf", s.get(1));
			s.add(1, "efg");
			assertEquals("efg", s.get(1));
			assertEquals("cdf", s.get(2));
			s.add(3, "hij");
			assertEquals("hij", s.get(3));
			s.add("123");
			assertEquals("123", s.get(4));

			ListIterator<String> list = s.listIterator();
			while(list.hasNext()) {
				System.out.println(list.next());
			}
			int count = 0;
			while(list.hasPrevious()) {
				count++;
				list.previous();
			}
			assertEquals(5, count);
			
			
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
		
	}
	
	/**
	 * Test adding one 
	 */
	@Test
	public void testAddOne() {
		s = new LinkedList<String>();
		try {
			s.add(0, "abc");
			assertEquals("abc", s.get(0));
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
		
	}
	/**
	 * Testing remove method
	 */
	@Test
	public void testRemove() {
		s = new LinkedList<String>();
		try {
			s.add("abc");
			s.add("cdf");
			s.add("efg");
			assertEquals(3, s.size());
			
			ListIterator<String> list = s.listIterator();
			while(list.hasNext()) {
				list.next();
				list.remove();
			}
			assertEquals(0, s.size());
			
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
	}
	/**
	 * Test removing whole list
	 */
	@Test
	public void testRemovePrevious() {
		s = new LinkedList<String>();
		try {
			s.add("abc");
			s.add("cdf");
			s.add("efg");
			assertEquals(3, s.size());
			
			ListIterator<String> list = s.listIterator();
			while(list.hasNext()) {
				list.remove();
			}
			assertEquals(0, s.size());
			
		} catch(IllegalArgumentException e) {
			System.out.println("Duplicate");
		}
	}
	
	/**
	 * Tests set method
	 */
	@Test
	public void testSet() {
		s = new LinkedList<String>();
		s.add("abc");
		s.add("cdf");
		assertEquals(0, s.listIterator(1).previousIndex());
		assertEquals(1, s.listIterator(1).nextIndex());
		s.add("efg");
		s.set(0, "xyz");
		ListIterator<String> list = s.listIterator();
		while(list.hasNext()) {
			System.out.println(list.next());
		}
	}
	/**
	 * Test set method at middle of list
	 */
	@Test
	public void testSet2() {
		s = new LinkedList<String>();
		s.add("abc");
		s.add("cdf");
		assertEquals(0, s.listIterator(1).previousIndex());
		assertEquals(1, s.listIterator(1).nextIndex());
		s.add("efg");
		s.set(1, "xyz");
		ListIterator<String> list = s.listIterator();
		while(list.hasNext()) {
			System.out.println(list.next());
		}
	}
	
	/**
	 * Test setting null object
	 */
	@Test
	public void testSetNull() {
		s = new LinkedList<String>();
		s.add("abc");
		s.add("cdf");
		s.add("efg");
		try {
			s.set(0, null);
			fail();
		} catch(NullPointerException e) {
			System.out.println("Cant set null");
		}
		
		ListIterator<String> list = s.listIterator();
		while(list.hasNext()) {
			System.out.println(list.next());
		}
	}
	
	
}
