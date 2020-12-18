/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This is a custom LinkedAbstractList that does not allow for null elements or 
 * duplicate elements by the equals() method. This list also has a capacity.
 * This class will be implemented in the CourseRoll class
 * @author jhnguye4
 * @param <E> The type in the LinkedAbstractList
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> { 
	/**private state for a ListNode of type E*/
	private ListNode front;
	/**private state for size of the list*/
	private int size = 0;
	/**private state for the capacity of the list*/
	private int capacity;
	/**private field that will point to last node in list*/
	private ListNode back;
	/**Constructor of the LinkedAbstractList that has one parameter.
	 * The parameter will indicate the capacity of the linked list and will
	 * be used to set limits to how many students can enroll in a class.
	 * The size will be set to 0 and the capacity will be set in the setter method to
	 * error check and throw exceptions.
	 * @param capacity is the size of the list
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		setCapacity(capacity);

	}
	
	/**
	 * This method is used to check if the capacity inputted is valid.
	 * It will throw an IllegalArgumentException if the capacity inputted is less than 0
	 * 
	 * @param capacity is the size of the list
	 * @throws IllegalArgumentException if the capacity is less than 0 or capacity is 
	 * less than size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * This method is used to add an object at the indicated index of the list.
	 * It will throw exceptions if invalid parameters are inputed. This method will
	 * also check if the parameters inputed are duplicates to values already on the list
	 * @param index is the index that the object will be stored
	 * @param object is the object being stored in the list
	 * @throws NullPointerException is thrown if the object is null
	 * @throws IndexOutOfBoundsException if the index inputed is outside the range
	 * @throws IllegalArgumentException if the size is larger than the capacity
	 */
	@Override
	public void add(int index, E object) {
		if (object == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if(size >= capacity) {
			return;
		}
		ListNode current = front;
		while (current != null) {
			if(current.data.equals(object)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if(index == 0) {
			front = new ListNode(object, front);
			back = front;
		}
		else if (index == size) {
			ListNode newBack = new ListNode(object, null);
			back.next = newBack;
			back = newBack;
		}
		else if (front != null) {
			current = front;
			while (current != null && index > 1) {
				current = current.next;
				index--;
			}
			if (current != null) {
				current.next = new ListNode(object, current.next);
			}
		}
		size++;
	}
	
	/**
	 * This method is used to remove an object from the list. User inputs the index
	 * of where they would like to remove the object. The value returned is the value that is 
	 * removed.
	 * @param index is the value where the object will be removed
	 * @return E is the object that is removed
	 */
	@Override
	public E remove(int index) {
		ListNode current = front;
		ListNode previous = null;
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int k = 0; k < index; k++) {
			if (current == null) {
			    throw new IndexOutOfBoundsException();
			}
			previous = current;
			current = current.next;
		}
		if(current == null ) {
			throw new IndexOutOfBoundsException();
		}
		E value = current.data;
		if (current == front) {
			front = front.next;
		}
		else {
			previous.next = current.next;
		}
		if(index == size - 1) {
			back = previous;
		}
		
		
		size--;
		return value;
		
	}
	/**
	 * This method sets an object at the index indicated and will return 
	 * the object that it replaced
	 * @param index is the index where you will replace the object
	 * @param object Is the object that will be replaced with at the specified index
	 * @return E is the object that was being replaced 
	 */
	@Override
	public E set(int index, E object) {
		E previous = get(index);
		remove(index);
		add(index, object);
		return previous;
	}
	
	
	/**
	 * This method gets the information of the object at the specified index
	 * and returns the object
	 * @param index of the object you want to get
	 * @return E is the object that will be returned
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int k = 0; k < index; k++) {
			if (current == null) {
			    throw new IndexOutOfBoundsException();
			}
			current = current.next;
		}
		if(current == null ) {
			throw new IndexOutOfBoundsException();
		}
		E value = current.data;
		return value;
	}

	/**
	 * This method gets the size of the list.
	 * @return size which is an int and the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * This is an inner class that is used in add/remove methods where it is used
	 * to traverse through the linked list
	 * 
	 */
	public class ListNode { 
		/**This is a private field that hold the data of the object*/
		private E data;
		/**This is a private field for the Node to know the next object*/
		private ListNode next;
		
		/**
		 * This method is one of the constructors that only has one parameter
		 * @param data is the object E that will be stored in the field
		 */
		public ListNode(E data) {
			this.data = data;
		}
		/**
		 * This method is the other constructor that only has two parameter
		 * @param data is the object E that will be stored in the field
		 * @param next is the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
	}
	


}
