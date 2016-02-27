/*
 * Jason Langevin
 */

package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<Node<E>> {

	Node<E> head;
	Node<E> tail;
	int size;

	public DoublyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}

	public DoublyLinkedList(Node<E> head, Node<E> tail, int size){
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		Node<E> newNode = new Node<E>(element);
		
		if (size == 0) {
			head = newNode;
			tail = newNode;
			size++;
		}
		else{
			newNode.rightNode = head;
			head.leftNode = newNode;
			this.head = newNode;
			size++;
		}

	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		if (size == 0) {
			Node<E> newNode = new Node<E>(o);
			head = newNode;
			tail = newNode;
			newNode.data = o;
			size++;
		} else {
			Node<E> newNode = new Node<E>(o);
			tail.rightNode = newNode;
			newNode.leftNode = tail;
			this.tail = newNode;
			size++;
		}

	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if (index == 0){
			addFirst(element);
			return;
		}
		if (index == size){
			addLast(element);
			return;
		}
		
		Node<E> newElement = new Node<E>(element);
		Iterator<Node<E>> iterator = this.iterator();
		int count = 0;
		while (iterator.hasNext()){
			if (count + 1 == index){
				Node<E> insertionPoint = iterator.next();
				newElement.rightNode = insertionPoint.rightNode;
				insertionPoint.rightNode.leftNode = newElement;
				insertionPoint.rightNode = newElement;
				newElement.leftNode = insertionPoint;
				size++;
				return;
			}
			count++;
			iterator.next();
		}

	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if (index == 0){
			return getFirst();
		}
		if (index == size - 1){
			return getLast();
		}
		
		int count = 0;
		Iterator<Node<E>> iterator = this.iterator();
		while (iterator.hasNext()){
			if (count == index){
				return iterator.next().data;
			}
			else{
				iterator.next();
				count++;
			}
		}
		return null;
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		if (size == 1){
			E temp = head.data;
			head = null;
			size--;
			return temp;
		}
		
		Node<E> removedElement = head;
		head = head.rightNode;
		head.leftNode = null;
		size--;
		return removedElement.data;
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		if (size == 1){
			E temp = tail.data;
			tail = null;
			size--;
			return temp;
		}
		Node<E> removedElement = tail;
		tail = tail.leftNode;
		tail.rightNode = null;
		size--;
		return removedElement.data;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if (index == 0){
			return removeFirst();
		}
		if (index == size - 1){
			return removeLast();
		}
		
		Iterator<Node<E>> iterator = this.iterator();
		int count = 0;
		while (iterator.hasNext()){
			if (count == index){
				Node<E> removedElement = iterator.next();
				removedElement.leftNode.rightNode = removedElement.rightNode;
				removedElement.rightNode.leftNode = removedElement.leftNode;
				size--;
				return removedElement.data;
			}
			count++;
			iterator.next();
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		Iterator<Node<E>> iterator = this.iterator();
		int count = 0;
		while (iterator.hasNext()){
			if (element == iterator.next().data){
				return count;
			}
			count++;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		Iterator<Node<E>> iterator = this.iterator();
		int lastIndex = -1;
		int count = 0;
		while (iterator.hasNext()){
			if (iterator.next().data == element){
				lastIndex = count;
			}
			count++;
		}
		return lastIndex;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {

		head = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		if (size == 0){
			return new Object[0];
		}
		
		Object[] array = new Object[size];
		Iterator<Node<E>> iterator = this.iterator();
		int count = 0;
		while (iterator.hasNext()){
			array[count] = iterator.next().data;
			count++;
		}
		return array;
	}

	@Override
	public Iterator<Node<E>> iterator() {
		Iterator<Node<E>> iterator = new Iterator<Node<E>>() {

			Node<E> currentNode = head;

			@Override
			public boolean hasNext() {
				
				if (currentNode == null)
					return false;
				return true;
			}

			@Override
			public Node<E> next() {
				if (currentNode.rightNode != null){
					currentNode = currentNode.rightNode;
					return currentNode.leftNode;
				}
				else{
					Node<E> temp = currentNode;
					currentNode = null;
					return temp;
				}
			}
			
		};
		return iterator;
	}
	
	public static void main(String args[]) {
		DoublyLinkedList<Integer> filledList;
		filledList = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 100; i++) {
			filledList.add(i - 1, i);
		}
		filledList.addLast(101);
		int count = 1;
		Iterator<Node<Integer>> iterator = filledList.iterator();
		while (iterator.hasNext()){
			System.out.print(iterator.next().data + " ");
		}
	}

}
