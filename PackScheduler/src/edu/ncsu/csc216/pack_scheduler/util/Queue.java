/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for Queue where all methods are implemented in the LinkedQueue and ArrayQueue class
 * @author jhnguye4
 * @param <E> object that the list is going to be created with
 *
 */
public interface Queue<E> {
	/**
	 * Adds the element to the bottom of the queue
	 * @param element is the object that is added to the bottom of the queue
	 * @throws IllegalArgumentException if if there is no room (capacity has been reached)
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element at the top of the queue
	 * @return E object that is removed
	 * 
	 */
	E dequeue();
	
	/**
	 * Checks if queue is empty
	 * @return true if stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the queue
	 * @return number of elements
	 */
	int size();
	
	/**
	 *  Sets the queue’s capacity
	 *  If the actual parameter is negative or if it is less than the number of elements in the stack, 
	 *  an IllegalArgumentException is thrown
	 *  
	 * @param capacity is the capacity that will be set
	 */
	void setCapacity(int capacity);
	
}
