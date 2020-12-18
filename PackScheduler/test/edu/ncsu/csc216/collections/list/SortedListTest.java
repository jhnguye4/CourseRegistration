package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests SortedList.
 * @author Jonathan Nguyen
 */
public class SortedListTest {
	/**
	 * Testing if SortedList is constructed properly and testing capacity.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		assertEquals(10, list.size());
		list.add("pineapple");
		assertEquals(11, list.size());
		
	}
	/**
	 * Testing add function of SortedList and checking if correct Strings are in the sorted array.
	 * Also tested duplicates and null.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list
		list.add("peach");
		list.add("apple");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("peach", list.get(2));
		
		//Test adding a null element
		try {
			list.add(null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(3, list.size());
		}
		
		//Test adding a duplicate element
		try {
			list.add("banana");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(3, list.size());
		}
	}
	/**
	 * Testing get function of SortedList and getting String from indexes that are outside the range.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try{
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//Add some elements to the list
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		list.add("pineapple");
		
		//Test getting an element at an index < 0
		try{
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(11, list.size());
		}
		//Test getting an element at size
		try{
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("watermelon", list.get(list.size() - 1));
		}
		
		
	}
	/**
	 * Testing remove function of SortedList by removing Strings from certain parts of array.
	 * Tested removing from an empty list and indexes outside of range.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Add some elements to the list - at least 4
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		list.add("pineapple");
		
		/*
		 * Sorted order
		 * 0. apple
		 * 1. banana
		 * 2. blackberry
		 * 3. blueberry
		 * 4. cantalope
		 * 5. kiwi
		 * 6. mango
		 * 7. peach
		 * 8. pineapple
		 * 9. strawberry
		 * 10. watermelon
		 */
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(11, list.size());
		}
		
		//TODO Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(11, list.size());
			assertEquals("watermelon", list.get(list.size() - 1));
		}
		
		//Test removing a middle element
		assertEquals(11, list.size());
		list.remove(1);
		assertFalse(list.contains("banana"));
		assertEquals(10, list.size());
		
		//Test removing the last element
		list.remove(list.size() - 1);
		assertFalse(list.contains("watermelon"));
		assertEquals(9, list.size());

		//Test removing the first element
		list.remove(0);
		assertFalse(list.contains("apple"));
		assertEquals(8, list.size());
		//Test removing the last element
		list.remove(list.size() - 1);
		assertFalse(list.contains("strawberry"));
		assertEquals(7, list.size());
	}
	/**
	 * Testing indexOf function of SortedList where it should return index of String we are looking for
	 * or returns -1 if String is not in array.
	 * Tested indexOf null where it will throw an Exception.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		try {
			assertEquals(-1, list.indexOf("apple"));
		} catch(NullPointerException e) {
			assertEquals(0, list.size());
		}
		//Add some elements
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		list.add("pineapple");
		/*
		 * Sorted order
		 * 0. apple
		 * 1. banana
		 * 2. blackberry
		 * 3. blueberry
		 * 4. cantalope
		 * 5. kiwi
		 * 6. mango
		 * 7. peach
		 * 8. pineapple
		 * 9. strawberry
		 * 10. watermelon
		 */
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(1, list.indexOf("banana"));
		assertEquals(10, list.indexOf("watermelon"));
		assertEquals(-1, list.indexOf("pear"));
		
		//Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(11, list.size());
		}
	}
	/**
	 * Testing clear function of SortedList, where it should make the SortedArray empty.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		list.add("pineapple");
		/*
		 * Sorted order
		 * 0. apple
		 * 1. banana
		 * 2. blackberry
		 * 3. blueberry
		 * 4. cantalope
		 * 5. kiwi
		 * 6. mango
		 * 7. peach
		 * 8. pineapple
		 * 9. strawberry
		 * 10. watermelon
		 */
		
		// Clear the list
		list.clear();
		//Test that the list is empty
		assertEquals(0, list.size());
	}
	/**
	 * Testing isEmpty function of SortedList, where it should return True if empty and False if not.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//Add at least one element
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		list.add("pineapple");
		/*
		 * Sorted order
		 * 0. apple
		 * 1. banana
		 * 2. blackberry
		 * 3. blueberry
		 * 4. cantalope
		 * 5. kiwi
		 * 6. mango
		 * 7. peach
		 * 8. pineapple
		 * 9. strawberry
		 * 10. watermelon
		 */
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}
	
	/**
	 * Testing contains function of SortedList, where it should return True if array contains String and False if not.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("apple"));
		//Add some elements
		list.add("banana");
		list.add("apple");
		list.add("kiwi");
		list.add("strawberry");
		list.add("mango");
		list.add("blueberry");
		list.add("watermelon");
		list.add("peach");
		list.add("cantalope");
		list.add("blackberry");
		list.add("pineapple");
		/*
		 * Sorted order
		 * 0. apple
		 * 1. banana
		 * 2. blackberry
		 * 3. blueberry
		 * 4. cantalope
		 * 5. kiwi
		 * 6. mango
		 * 7. peach
		 * 8. pineapple
		 * 9. strawberry
		 * 10. watermelon
		 */
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("pear"));
		assertTrue(list.contains("kiwi"));
		assertFalse(list.contains("tomato"));
	}
	/**
	 * Testing equals function of SortedList, where it should return True if two lists are the same and False if not.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("blackberry");
		list2.add("apple");
		list2.add("banana");
		list2.add("blackberry");
		list3.add("blueberry");
		list3.add("cantalope");
		list3.add("kiki");
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list1));
		assertFalse(list3.equals(list2));
	}
	/**
	 * Testing hashCode function of SortedList, where hashCode is equal if they have same mapping.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("blackberry");
		list2.add("apple");
		list2.add("banana");
		list2.add("blackberry");
		list3.add("blueberry");
		list3.add("cantalope");
		list3.add("kiki");
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 