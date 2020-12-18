/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This is class is a custom implementation of an array list that does not allow 
 * for null elements or duplicates defined by equals() method.
 * 
 * @author jhnguye4
 * @param <E> is the object that is being passed through when the ArrayList is created. 
 */
public class ArrayList<E> extends AbstractList<E> { 
	/**
	 * a constant value for initializing the list size. The value should be 10
	 */
	public static final int INIT_SIZE = 10;
	/**
	 * An array type of E
	 */
	public Object[] list;
	/**
	 * The size of the list
	 */
	public int size = 0;

	/**
	 * Constructor of the array list that creates an object array and then casts to
	 * an array of type e.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[])new Object[INIT_SIZE];
	}
	
	/**
	 * Returns the element at the index and the return type is E
	 * It will throw an IndexOutOfBoundsException if the index inputed is invalid
	 * @param index is the index where you want to find E
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		@SuppressWarnings("unchecked")
		E e = (E)list[index];
		return e;
	}

	/**
	 * This method is used to return the size of the array. It is useful in testing and making sure the array
	 * is the right size.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Adds to the array by placing the object at the index indicated and shifting all the other indexes to the left
	 * @param index is the index where user wants to input the new object
	 * @param object is the Object E that will be placed in the index indicated
	 * @throws NullPointerException if the object is null
	 * @throws IndexOutOfBoundsException if the index indicated is out of range 
	 * @throws IllegalArgumentException if there is a duplicate in the array
	 */
	@Override
	public void add(int index, E object) {
		if(object == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if(size() > 0) {
			for(int i = 0; i < size() ; i++) {
				if (list[i].equals(object)) {
					throw new IllegalArgumentException();
				}
			}
		}
		growArray();
		for(int i = size; i > index ; i--) {
			list[i] = list[i - 1];
		}
		size++;
		list[index] = object; 

	}
	/**
	 * This method is used to double the capacity of the array if the size of the array equals the max 
	 * the array can hold. This method is called in the add method.
	 */
	private void growArray() {
		if (size == INIT_SIZE) {
		      int capacity = INIT_SIZE * 2;
		      Object[] buffer = new Object[capacity * 2];
		      for (int k = 0; k < size; k++) {
		         buffer[k] = list[k];
		      }
		         list = buffer;
		}
	}
	/**
	 * Removes the element at the specified index and will shift all the elements to the right 
	 * @param index is the index where user wants to remove the object
	 * @throws IndexOutOfBoundsException if the index indicated is out of range 
	 */
	@Override
	public E remove(int index) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if (size > 0) {
			@SuppressWarnings("unchecked")
			E e = (E)list[index];
			for(int i = index; i < size; i++) {
				list[i] = list[i + 1];	
			}
			list[size - 1] = null;
			size--;
			return e;
		}
		return null;
	}
	/**
	 * This method is used to set an object E at the index indicated. 
	 * @param index is the index where user wants to set the new object
	 * @param object is the Object E that will be placed in the index indicated
	 * @throws NullPointerException if the object is null
	 * @throws IndexOutOfBoundsException if the index indicated is out of range 
	 * @throws IllegalArgumentException if there is a duplicate in the array
	 */
	@Override
	public E set(int index, E object) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if(object == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size() ; i++) {
			if (list[i].equals(object)) {
				throw new IllegalArgumentException();
			}
		}
		@SuppressWarnings("unchecked")
		E prev = (E) list[index];
		list[index] = object;
		return prev;
		
	}
	

}
