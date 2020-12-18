package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;


import java.util.NoSuchElementException;

import org.junit.Test;
/**
 * Tests the LinkedQueue class
 * @author jhnguye4
 *
 */
public class LinkedQueueTest {

	/**
	 * Tests the constuctor for LinkedQueue
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	public void testQueueSingle() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
		queue.enqueue(1);
		assertEquals(1, queue.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#push(java.lang.Object)}.
	 */
	@Test
	public void testQueueMultiple() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(4, queue.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testDequeueSingle() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(4, queue.size());
		queue.dequeue();
		assertEquals(3, queue.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testDequeueMultiple() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(4, queue.size());
		queue.dequeue();
		assertEquals(3, queue.size());
		queue.dequeue();
		assertEquals(2, queue.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testDequeueLast() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		assertEquals(4, queue.size());
		queue.dequeue();
		assertEquals(3, queue.size());
		queue.dequeue();
		assertEquals(2, queue.size());
		queue.dequeue();
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#pop()}.
	 */
	@Test
	public void testInterleaved() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		queue.enqueue(1);
		queue.dequeue();
		queue.enqueue(2);
		queue.enqueue(3);
		queue.dequeue();
		queue.enqueue(4);
		assertEquals(2, queue.size());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#isEmpty()}.
	 */
	@Test
	public void testRemoveEmpty() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
		try{
			queue.dequeue();
			fail();
		} catch(NoSuchElementException e) {
			System.out.println("queue is empty");
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedStack#isEmpty()}.
	 */
	@Test
	public void testCapacity() {
		try{
			LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			queue.enqueue(4);
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
			LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			queue.setCapacity(1);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Capacity");
		}
		
		try {
			new LinkedQueue<Integer>(-1);
			fail();
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid Capacity");
		}
	}
}
