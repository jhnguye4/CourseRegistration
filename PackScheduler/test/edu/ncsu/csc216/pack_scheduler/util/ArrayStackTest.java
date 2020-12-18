/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * This class tests the ArrayStack class
 * @author jhnguye4
 *
 */
public class ArrayStackTest {

	/**
	 * Tests the constructor for LinkedStack
	 */
	@Test
	public void testLinkedStack() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPushSingle() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		stack.push(1);
		assertEquals(1, stack.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPushMultiple() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		assertEquals(4, stack.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testPopSingle() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		assertEquals(4, stack.size());
		stack.pop();
		assertEquals(3, stack.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testPopMultiple() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		assertEquals(4, stack.size());
		stack.pop();
		assertEquals(3, stack.size());
		stack.pop();
		assertEquals(2, stack.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testPopLast() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		assertEquals(4, stack.size());
		stack.pop();
		assertEquals(3, stack.size());
		stack.pop();
		assertEquals(2, stack.size());
		stack.pop();
		stack.pop();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests multiple adds and pops
	 */
	@Test
	public void testInterleaved() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		stack.push(1);
		stack.pop();
		stack.push(2);
		stack.push(3);
		stack.pop();
		stack.push(4);
		assertEquals(2, stack.size());
	}
	
	/**
	 * Testing removing an empty list
	 */
	@Test
	public void testRemoveEmpty() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		try{
			stack.pop();
			fail();
		} catch(EmptyStackException e) {
			System.out.println("stack is empty");
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#isEmpty()}.
	 */
	@Test
	public void testCapacity() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(3);
		try{
			stack.push(1);
			stack.push(2);
			stack.push(3);
			stack.push(4);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Capacity is reached");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#isEmpty()}.
	 */
	@Test
	public void testInvalidCapacity() {
		try{
			ArrayStack<Integer> stack = new ArrayStack<Integer>(3);
			stack.push(1);
			stack.push(2);
			stack.push(3);
			stack.setCapacity(1);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Capacity");
		}
		
		try {
			new ArrayStack<Integer>(-1);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Capacity");
		}
	}

}
