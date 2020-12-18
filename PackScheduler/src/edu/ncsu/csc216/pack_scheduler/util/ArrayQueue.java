/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This class is a Queue made from a array list. A queue removes from the top and adds from the bottom.
 * It implements the Queue interface
 * @author jhnguye4
 * @param <E> this is generic object that the list is being made out of
 *
 */
public class ArrayQueue<E> implements Queue<E> { 
	
	/**private field that sets the capacity for the array*/
	private int capacity;
	/**private field that keeps track of size of the array*/
	private int size;
	/**private fied of ArrayList that will be called upon in push pop methods*/
	private ArrayList<E> array;
	
	/**
	 *Constructor method for ArrayQueue that has one parameter that sets the capacity for the list 
	 * @param capacity the max number of values in this list
	 */
	public ArrayQueue(int capacity) {
		setCapacity(capacity);
		array = new ArrayList<E>();
		Object[] buffer = new Object[capacity];
	    for (int k = 0; k < size; k++) {
	       buffer[k] = array.list[k];
	    }
	    array.list = buffer;
	}
	
	/**
	 * Adds the element to the bottom of the queue
	 * @param element is the object that is added to the bottom of the queue
	 * @throws IllegalArgumentException if if there is no room (capacity has been reached)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(Object element) {
		if(size == capacity) {
			throw new IllegalArgumentException("Capacity has been reached.");
		}
		array.add(size, (E) element);
		size++;
		
	}
	/**
	 * Removes and returns the element at the top of the queue
	 * @return E object that is removed
	 * @throws NoSuchElementException if the Queue is empty, an NoSuchElementException is thrown
	 */
	@Override
	public E dequeue() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		size--;
		return array.remove(0);
	}
	/**
	 * Checks if queue is empty
	 * @return true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Returns the number of elements in the queue
	 * @return number of elements
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 *  Sets the queue’s capacity
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
