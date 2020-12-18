/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the LinkedListRecursive class
 * @author jhnguye4
 *
 */
public class LinkedListRecursiveTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#LinkedListRecursive()}.
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("abc"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add("abc");
		assertEquals("abc", list.get(0));
		list.add("efg");
		assertEquals("efg", list.get(1));
		list.add("hij");
		assertEquals("hij", list.get(2));
		assertEquals(3, list.size());
		assertTrue(list.contains("hij"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "abc");
		assertEquals("abc", list.get(0));
		list.add(0, "efg");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		list.add(2, "hij");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		assertEquals(3, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveE() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "abc");
		list.add(0, "efg");
		list.add(2, "hij");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		
		list.remove("abc");
		assertEquals("efg", list.get(0));
		assertEquals("hij", list.get(1));
		assertEquals(2, list.size());
		
		list.remove("efg");
		assertEquals("hij", list.get(0));
		assertEquals(1, list.size());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "abc");
		list.add(0, "efg");
		list.add(2, "hij");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		
		list.remove(1);
		assertEquals("efg", list.get(0));
		assertEquals("hij", list.get(1));
		assertEquals(2, list.size());
		
		list.remove(0);
		assertEquals("hij", list.get(0));
		assertEquals(1, list.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
	 */
	@Test
	public void testRemoveFalse() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "abc");
		list.add(0, "efg");
		list.add(2, "hij");
		list.add(3, "123");
		list.add(4, "456");
		list.add(5, "789");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		assertEquals("123", list.get(3));
		assertEquals("456", list.get(4));
		assertEquals("789", list.get(5));
		assertEquals(6, list.size());
		
		list.set(3, "369");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		assertEquals("369", list.get(3));
		assertEquals("456", list.get(4));
		assertEquals("789", list.get(5));
		assertEquals(6, list.size());
		
		list.remove(4);
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		assertEquals("369", list.get(3));
		assertEquals("789", list.get(4));
		assertEquals(5, list.size());
		
		list.remove("efg");
		assertEquals("abc", list.get(0));
		assertEquals("hij", list.get(1));
		assertEquals("369", list.get(2));
		assertEquals("789", list.get(3));
		assertEquals(4, list.size());
		
		list.remove("369");
		assertEquals("abc", list.get(0));
		assertEquals("hij", list.get(1));
		assertEquals("789", list.get(2));
		assertEquals(3, list.size());
		
		list.remove(0);
		assertEquals("hij", list.get(0));
		assertEquals("789", list.get(1));
		assertEquals(2, list.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "abc");
		list.add(0, "efg");
		list.add(2, "hij");
		assertEquals("efg", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		
		list.set(1, "123");
		assertEquals("efg", list.get(0));
		assertEquals("123", list.get(1));
		assertEquals("hij", list.get(2));
		
		list.set(1, "abc");
		list.set(0, "123");
		assertEquals("123", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("hij", list.get(2));
		
		list.set(2, "789");
		assertEquals("123", list.get(0));
		assertEquals("abc", list.get(1));
		assertEquals("789", list.get(2));
		
	}

}
