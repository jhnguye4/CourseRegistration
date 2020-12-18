/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * create a Stack interface and implement ArrayStack and LinkedStack. Each c
 * oncrete class will implement the interface and delegate to either ArrayList or LinkedAbstractList.
 * @author jhnguye4
 * @param <E> is the object that the list will be made from
 *
 */
public interface Stack<E> {
	/**
	 * Adds the element to the top of the stack
	 * @param element is the object that is added to the top of the stack
	 * @throws IllegalArgumentException if if there is no room (capacity has been reached)
	 */
	void push(E element);
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @return element that is removed
	 * 
	 */
	E pop();
	
	/**
	 * Checks if stack is empty
	 * @return true if stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack
	 * @return number of elements
	 */
	int size();
	
	/**
	 *  Sets the stack’s capacity
	 *  If the actual parameter is negative or if it is less than the number of elements in the stack, 
	 *  an IllegalArgumentException is thrown
	 *  
	 * @param capacity is the capacity that will be set
	 */
	void setCapacity(int capacity);
}
