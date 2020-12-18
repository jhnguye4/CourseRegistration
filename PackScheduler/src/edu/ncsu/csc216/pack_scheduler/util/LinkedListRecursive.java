/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Custom implementation of a recursive linked list that doesn’t 
 * allow for null elements or duplicate elements as defined by the equals() method.
 * @author jhnguye4
 * @param <E> is the generic object that the list will be made from
 *
 */
public class LinkedListRecursive<E> {
	/**Private field that is used to keep track of size of the linked list*/
	private int size;
	/**Private field that is a ListNode*/
	private ListNode head;
	
	/**Constructor method for LinkedRecursive list that makes the head null*/
	public LinkedListRecursive() {
		head = null;
		size = 0;
	}
	
	/**
	 * This method returns true if the size is 0
	 * @return true if size is 0
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the size of the linked list
	 * @return size of list
	 */
	public int size() {
		return size;
	}
	
	/**
	 *  Method checks that the element isn’t already in the list (an IllegalArgumentException is thrown if 
	 *  the element already exists) and handles the special case of adding a node to an empty list. If 
	 *  the list is not empty, then the public method transfers the flow of control to the private 
	 *  ListNode.add(E element) method, which completes the recursion to add to element to the end of the list.
	 * @param object is the object that is added if it is not a duplicate in the list
	 * @return true if the object is added
	 */
	public boolean add(E object) {
		if(object == null) {
			throw new NullPointerException();
		}
		if(head == null) {
			head = new ListNode(object, null);
			size++;
			return true;
		}
		ListNode current = head;
		while(current != null) {
			if(current.data.equals(object)) {
				throw new IllegalArgumentException();
			} 
			current = current.next;
		}
		head.add(object);
		size++;
		return true;
	}
	
	/**
	 * If the element is added to the middle or end of the list, then the public method transfers the flow of
	 * control to the private ListNode.add(int index, E element) method, which completes the recursion to add 
	 * to element at the appropriate location.
	 * @param index is the position that the object will be added
	 * @param object is the object that will be placed at that position
	 */
	public void add(int index, E object) {
		if(object == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = head;
		while(current != null) {
			if(current.data.equals(object)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if(head == null) {
			head = new ListNode(object, null);
		}
		else if(index == 0) {
			head = new ListNode(object, head);
		}
		else {
			head.add(index, object);
		}
		size++;
	}
	/**
	 * This method gets the object at the specified index
	 * @param index is the position where the object is
	 * @return returns the object at the position
	 */
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index for get.");
		}
		if(index == 0) {
			return head.data;
		}
		return head.get(index);
	}
	/**
	 * Then the public method transfers the flow of control to the private ListNode.remove(E element) 
	 * method, which completes the recursion to remove to element at the appropriate location.
	 * @param object is the object that will be removed
	 * @return true if the object can be removed
	 */
	public boolean remove(E object) {
		if(object == null) {
			return false;
		}
		if(head == null) {
			return false;
		}
		if(head != null && head.data.equals(object)) {
			head = head.next;
			size--;
			return true;
		}
		return head.remove(object);
	}
	/**
	 *The public method transfers the flow of control to the private ListNode.remove(int idexx) method, 
	 * which completes the recursion to remove to element at the appropriate location. 
	 * @param index where object the is to be removed
	 * @return the object that is removed
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index for remove.");
		}
		if(head == null) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			E prev = head.data;
			head = head.next;
			size--;
			return prev;
		}
		return head.remove(index);
	}
	
	/**
	 * The public method transfers the flow of control to the private ListNode.set(int idx, E element) method,
	 * which completes the recursion to set to element at the appropriate location.	
	 * @param index is the position of the object you want to set
	 * @param object is the object that will be replaced at the specified index
	 * @return previous object that is replaced
	 */
	public E set(int index, E object) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index for set.");
		}
		if(object == null) {
			throw new NullPointerException();
		}
		if(index == 0) {
			E prev = head.data;
			head.data = object;
			return prev;
		}
		return head.set(index, object);
	}
	
	/**
	 * The public method transfers the flow of control to the private ListNode.contains(E element) method, which
	 * completes the recursion to check the elements in the list.
	 * @param object that is checked is in the list
	 * @return true if the object is in the list
	 */
	public boolean contains(E object) {
		if(head == null) {
			return false;
		}
		if(object == null) {
			throw new NullPointerException();
		}
		return head.contains(object);
	}
	/**
	 * This is the inner class that creates a ListNode and has other methods that are used 
	 * for recursion
	 * @author jhnguye4
	 *
	 */
	private class ListNode {
		/**Private field for data in the node*/
		private E data;
		/**Private field for next node in the list*/
		private ListNode next;
		
		/**
		 * Constructor that assigns parameters to the associated fields
		 * @param data is the data for the node
		 * @param next is the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * This method is called in the LinkedListRecursive class in the add function
		 * @param index is the index that the object will be added to
		 */
		public void add(E object) {
			if(next == null) {
				next = new ListNode(object, next);
			} else {
				next.add(object);
			}
		}
		
		/**
		 * This method is called in the LinkedListRecursive class in the add function
		 * @param index is the index that the object will be added to
		 * @param object is the object that will be added at the specified index
		 */
		public void add(int index, E object) {
			if(index == 1) {
				next = new ListNode(object, next); 
			} else {
				next.add(index - 1, object);
			}
		}
		/**
		 * This method is called in the LinkedListRecursive.get class
		 * @param index is the index that the user wants to get the object from
		 * @return the object at the specified index
		 */
		public E get(int index) {
			E object = null; 
			if(index == 0) {
				object = data;
			} else {
				object = next.get(index - 1);
			}
			return object;
		}
		
		/**
		 * This method is called in the LinkedListRecursive.remove class that removes an object at the specified index
		 * @param index is the index that the user wants to remove
		 * @return the object that is removed
		 */
		public E remove(int index) {
			E prev = null;
			if(next != null) {
				if(index == 1) {
					prev = next.data;
					next = next.next;
					size--;
				} else {
					next.remove(index - 1);
				}
			
			}
			return prev;
		}
		
		/**
		 * This method is called in the LinkedListRecursive.remove class that removes a specific object
		 * @param object is the object that the user wants to remove
		 * @return true if the object is removed
		 */
		public boolean remove(E object) {
			if(next != null) {
				if(next.data.equals(object)) {
					next = next.next;
					size--;
					return true;
				} else {
					next.remove(object);
				}
			}
			return false;
		}
		/**
		 * This method is called in the LinkedListRecursive.set class to set an object at a specific position
		 * @param index is the position where the object will be set
		 * @param object is the object that will replace current object
		 * @return object that was replaced
		 */
		public E set(int index, E object) {
			E prev = null;
			if(index == 1) {
				prev = next.data;
				next.data = object;
			} else {
				next.set(index - 1, object);
			}
			
			return prev;
		}
		/**
		 * This method is called in LinkedListRecursive.contains to check if the list contains an object
		 * @param object is the object that is checked is in the list
		 * @return true if the object is in the list.
		 */
		public boolean contains(E object) {
			if(next.data.equals(object)) {
				return true;
			}
			
			return next.contains(object);
		}
	}
}
