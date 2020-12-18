/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This class is Stack made from a array list. A stack removes and adds from the top.
 * It implements the Stack interface
 * @author jhnguye4
 * @param <E> this is generic object that the list is being made out of
 *
 */
public class ArrayStack<E> implements Stack<E> { 
	/**private field that sets the capacity for the array*/
	private int capacity;
	/**private field that keeps track of size of the array*/
	private int size;
	/**private fied of ArrayList that will be called upon in push pop methods*/
	private ArrayList<E> array;
	
	/**
	 *Constructor method for ArrayStacked that has one parameter that sets the capacity for the list 
	 * @param capacity the max number of values in this list
	 */
	public ArrayStack(int capacity) {
		setCapacity(capacity);
		array = new ArrayList<E>();
		Object[] buffer = new Object[capacity];
	    for (int k = 0; k < size; k++) {
	       buffer[k] = array.list[k];
	    }
	    array.list = buffer;
	}
	/**
	 * Adds the element to the top of the stack
	 * @param element is the object that is added to the top of the stack
	 * @throws IllegalArgumentException if if there is no room (capacity has been reached)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void push(Object element) {
		if(size == capacity) {
			throw new IllegalArgumentException("Capacity has been reached");
		}
		array.add(0, (E) element);
		size++;
		
	}
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @return element that is removed
	 * @throws EmptyStackException if stack is empty
	 */
	@Override
	public E pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		size--;
		return array.remove(0);
	}
	/**
	 * Checks if stack is empty
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the number of elements in the stack
	 * @return number of elements
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 *  Sets the stack’s capacity
	 *  If the actual parameter is negative or if it is less than the number of elements in the stack, 
	 *  an IllegalArgumentException is thrown
	 *  
	 * @param capacity is the capacity that will be set
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		
	}

}
