package assignment07;

import assignment06.DoublyLinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author ??
 * 
 * @param <E>
 *            -- the type of elements contained in the stack
 */
public class LinkedListStack<E> {

	private DoublyLinkedList<E> stack;

	public LinkedListStack() {
		stack = new DoublyLinkedList<E>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		// FILL IN
	}

	/**
	 * Returns true if the stack contains no elements.
	 */
	public boolean isEmpty() {
		// FILL IN
		return false;
	}

	/**
	 * Returns the item at the top of the stack without removing it from the
	 * stack. Throws NoSuchElementException if the stack is empty.
	 */
	public E peek() throws NoSuchElementException {
		// FILL IN
		return null;
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException {
		// FILL IN
		return null;
	}

	/**
	 * Pushes the input item onto the top of the stack.
	 */
	public void push(E item) {
		// FILL IN
	}

	/**
	 * Returns the number of items in the stack.
	 */
	public int size() {
		return stack.size();
	}
}