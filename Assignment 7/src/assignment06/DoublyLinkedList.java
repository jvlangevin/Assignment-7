package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Nathan Novak, Joseph Horne
 * DoubleLinkedList is a series of nodes that contain elements with a non-explicit index
 * Each node points to the next node in the series as well as the last
 * Because of this double link list, you can search/add/remove in either direction
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	
	//number of items that have been put in nodes, not the number of nodes themselves
	private int size;
	
	//because header and tail have to be accessed in other methods, they are class variables
	private Node header;
	private Node tail;
	
	/**
	 * Constructor
	 * When first constructed, the list has an initial node (header node) and an end node (tail node)
	 * Both nodes are empty (they contain no node.item - ), but they contain the necessary pointers
	 * Header and tail are the triggers to know when to stop if searching the linked list.
	 */
	public DoublyLinkedList()
	{
		//on construct, create a header and tail. There is always a header and a tail.
		//note: just because header/tail exist, doesn't mean there is an item in them
		header = new Node();
		tail = new Node();
		
		//on construct, header's nextNode points to tail and tail's previousNode points to header
		//this will change as we add other nodes. Unsure if implementing indexes are necessary traits of node
		header.nextNode = tail;
		tail.previousNode = header;
		
	}

	/**
	 * HELPER CLASS 
	 * Each "node" contains a potential item, an index (maybe?), 
	 * a potential link to a previous node and a potential 
	 * link to the next node, even if it's the last or first node. 
	 * @author Nathan
	 *
	 */
	private class Node{
		
		//the item in the node
		E item; 
		
		//the "index" of this node. unsure at the moment if necessary
		//int index;
		
		//the link to the previous node
		Node previousNode;
		
		//the link to the next node
		Node nextNode;
		
		
		//detaches node from other nodes
		private void freeFloating()
		{
			this.nextNode = null;
			this.previousNode = null;
			size--;
		}
		
	}
	
	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		
		/*--------------------------------------------
		 ---------------------------------------------
		 Logic in steps:
		 1. create a new node. node is unattached.
		 2. attach the node to header (previous node)
		 3. attach the node to the node that was after header originally (next node)
		 4. assign the element to the new node
		 5. detach the node originally after header from header. this node's previous node is now the new node
		 6. detach header from the node originally after header. header's next node is now the new node
		 7. new element was added, increase size.
		 ---------------------------------------------*/
		
		//index logic commented out for the moment; unsure if necessary.
		Node newNode = new Node(); //step 1
		newNode.previousNode = this.header; //step 2
		newNode.nextNode = this.header.nextNode; //step 3
		newNode.item = element; //step 4

		this.header.nextNode.previousNode = newNode;//step 5
		this.header.nextNode = newNode; //step 6
		this.size++;
		
		
		
	}

	
	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		
		/*--------------------------------------------
		 ---------------------------------------------
		 Logic in steps:
		 1. create a new node. node is unattached.
		 2. attach the node to tail (next node)
		 3. attach the node to the node that was before tail originally (previous node)
		 4. assign the element to the new node
		 5. detach the node originally before tail from tail. this node's next node is now the new node
		 6. detach tail from the node originally before tail. tails's previous node is now the new node
		 7. new element was added, increase size.
		 ---------------------------------------------*/
		
		//index logic commented out for the moment; unsure if necessary.
		Node newNode = new Node(); //step 1
		newNode.nextNode = this.tail; //step 2
		newNode.previousNode = this.tail.previousNode; //step 3
		//newNode.index = this.tail.index;
		newNode.item = o; //step 4
		//this.header.index = this.header.index-1;
		this.tail.previousNode.nextNode = newNode;//step 5
		this.tail.previousNode = newNode; //step 6
		this.size++; //step 7
		
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		//if index out of bounds throw exception
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		//we're adding a new node so create a new node
		Node newNode = new Node();
		
		//indexNode is how we transverse the list
		Node indexNode = header;
		
		
		//if the index is less than half the size, transverse list starting at header
		if(index < indexSize()/2)
		{
			indexNode = header;
			for(int i = 0; i < index; i++)
			{
				indexNode = indexNode.nextNode;
				
			}
		}
		//if the index is over half the size or equal to it, transverse list backwards starting at tail
		else{
			indexNode = tail.previousNode;
			for(int i = indexSize(); i >= index; i--)
			{
				indexNode = indexNode.previousNode;
				
			}
		}
		
		//insert node and detach other nodes from each other
		newNode.previousNode = indexNode;
		newNode.item = element;
		newNode.nextNode = indexNode.nextNode;
		
		indexNode.nextNode.previousNode = newNode;
		indexNode.nextNode = newNode;
		
		
		//increase size
		this.size++;
		
		
	}

	
	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		
		
		//if the list is empty, nothing to get, throw exception
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		
		
		//return the item of the node after header
		return this.header.nextNode.item;
	}

	
	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		
		//if list is empty, nothing to get
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		//return the item of the node prior to tail
		return this.tail.previousNode.item;
	}

	
	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		//if index isn't within bounds, throw exception
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		Node indexNode = header.nextNode;
		
		//if index is less or equal to than half of the number of "indexes" start at header
		if(index  <= indexSize()/2){
			for(int i = 0; i < index; i++)
			{
				indexNode = indexNode.nextNode;
				
			}
		}
		
		//if index is greater than than half of the number of indexes, start at tail go in reverse
		else{
			
			indexNode = tail.previousNode;
			for(int i = indexSize(); i > index; i--)
			{
				indexNode = indexNode.previousNode;
				
			}
		}
		
		return indexNode.item;
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		
		//if empty, nothing to remove, throw exception
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		//the node right after header is the first element
		Node returnNode = header.nextNode;
		
		header.nextNode = returnNode.nextNode;
		returnNode.nextNode.previousNode = returnNode.previousNode;
		
		
		//see helper class methods above- disconnects first node
		returnNode.freeFloating();
		
		
		return returnNode.item;
	}

	
	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		//if empty, nothing to return
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		//node prior to tail is one to remove
		Node returnNode = tail.previousNode;
		
		tail.previousNode = returnNode.previousNode;
		returnNode.previousNode.nextNode = returnNode.nextNode;
		
		//disconnect that node
		returnNode.freeFloating();
		
		
		return returnNode.item;
	}

	
	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		//if index outside bounds, throw exception
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		
		Node indexNode = tail;
		if(index < indexSize()/2)
		{
			//if index is less than half of the "index size" start at header
			indexNode = header.nextNode;
			for(int i = 0; i < index; i++)
			{
				indexNode = indexNode.nextNode;
			}
		}
		//if index is greater than "index size"/2 start at tail.
		if(index >= indexSize()/2)
		{
			indexNode = tail.previousNode;
			for(int i = indexSize(); i > index; i--)
			{
				indexNode = indexNode.previousNode;
			}
			
		}
		
		indexNode.previousNode.nextNode = indexNode.nextNode;
		indexNode.nextNode.previousNode = indexNode.previousNode;
		indexNode.freeFloating();
		
		return indexNode.item;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		int indexReturn = 0;
		Node searchNode = header.nextNode;
		
		//starts at header, goes forward, returns once item is found.
		while(this.size > indexReturn)
		{
			
			if(searchNode.item ==element)
			{
				return indexReturn;
			}
			searchNode = searchNode.nextNode;
			indexReturn++;
		}
		//returns -1 if not found
		return -1;
	}

	
	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(Object element) {
		int indexReturn = this.indexSize();
		Node searchNode = tail.previousNode;
		
		//starts at tail and moves backwards
		while(0 < indexReturn)
		{
			
			if(searchNode.item ==element)
			{
				return indexReturn;
			}
			searchNode = searchNode.previousNode;
			indexReturn--;
		}
		
		return -1;
	}

	
	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	//helper method
	private int indexSize()
	{
		return this.size-1;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {

		
		header.nextNode.freeFloating();
		tail.previousNode.freeFloating();
		
		header.nextNode = tail;
		tail.previousNode = header;
		this.size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
					
		if(this.size() == 0)
		{
			throw new NullPointerException();
		}
		
		Object[] thisArray = new Object[size()];
		Node nodeIndex = header.nextNode;
		for(int i = 0; i < size(); i++)
		{
			thisArray[i] = nodeIndex.item;
			nodeIndex = nodeIndex.nextNode;
			
		}
		return thisArray;
	}
	/**
	 * Methods used for the iterator.
	 */
	public Iterator<E> iterator()
	{
		
		   return new Iterator<E>() 
		   {
			   //index node is used to track where we are in the iterator
			   Node indexNode = header;
			   
			   /**
			    * hasNext returns true so if the next node has an element
			    */
			   public boolean hasNext() 
			   {
				   if(indexNode.nextNode != tail)
				   {
					   return true;
				   }
				   return false;

			   };
			   /**
			    * this call removes index node if element is not null\
			    * 1. assigns a new node to represent indexNode and changes indexNode to the prior node
			    * 2. attaches the node after returnNode to node before returnNode
			    * 3. attaches the ndoe before returnNode to node after returnNode
			    * 4. detaches returnNode
			    */
			   public void remove()
			   {
				   if(indexNode != null)
				   {
					   Node removeNode = indexNode;
					   indexNode = indexNode.previousNode;
					   removeNode.nextNode.previousNode = removeNode.previousNode;
					   removeNode.previousNode.nextNode = removeNode.nextNode;
					   removeNode.freeFloating();
				   }
				   else
					   throw new NullPointerException();
			   };
			   
			   
			   /**
			    * Goes to next node and returns it's item
			    * @return
			    */
			   public E next() 
			   {
				   if(indexNode.nextNode != null)
				   {
					   indexNode = indexNode.nextNode;
					   return indexNode.item;
				   }
				   else
					   throw new NullPointerException();

			   };

		   };
		
	}



	

	
	



}
