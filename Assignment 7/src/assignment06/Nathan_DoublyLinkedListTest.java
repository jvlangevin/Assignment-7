package assignment06;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;


/**
 * General test case file for each method
 * @author Nathan Novak u0347907
 * @Author Joseph Horne
 *
 */
public class Nathan_DoublyLinkedListTest {

	@Test
	public void addFirstAddLastStringGetBoth() {
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		myList.addLast("End");
		
		
		assertEquals("Start", myList.getFirst());
		assertEquals("End", myList.getLast());
		
	}
	

	@Test
	public void addFirstAddLastIntGetBoth() {
		Nathan_DoublyLinkedList<Integer> myList = new Nathan_DoublyLinkedList<>();
		
		myList.addFirst(5);
		myList.addLast(6);
		
		
		assertEquals(5, (int)myList.getFirst());
		assertEquals(6, (int)myList.getLast());
		
	}
	
	@Test
	public void addAndGetOneStringNodeWithIndex(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		myList.addLast("End");
		
		myList.add(0, "Middle");
		
		assertEquals("Middle", myList.get(0));
		
	}
	
	@Test
	public void addMultipleStringsWithZeroIndex(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		myList.addLast("End");
		
		myList.add(0, "Middle1");
		myList.add(0, "Middle2");
		myList.add(0, "Middle3");
		myList.add(0, "Middle4");
		myList.add(0, "Middle5");
		
		assertEquals("Middle5", myList.get(0));
		
	}

	@Test
	public void addMultipleStringsIndexGreaterThan2(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		myList.addLast("End");
		
		myList.add(0, "Middle1");
		myList.add(0, "Middle2");
		myList.add(0, "Middle3");
		myList.add(0, "Middle4");
		myList.add(0, "Middle5");
		myList.add(6, "Answer");
		
		assertEquals("Answer", myList.get(6));
		
	}
	
	@Test
	public void getSizeWithFourElements(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		myList.addLast("End");
		
		myList.add(0, "Middle1");
		myList.add(0, "Middle2");
		
		assertEquals(4, myList.size());
		
	}
	
	@Test
	public void addFourElementsThenClearTestIsEmpty(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		myList.addLast("End");
		
		myList.add(0, "Middle1");
		myList.add(0, "Middle2");
		
		myList.clear();
		assertEquals(true, myList.isEmpty());
		
	}
	
	@Test
	public void changeBinaryToArray(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		Object[] arrayTest = myList.toArray();
		
		assertEquals("Start", arrayTest[0]);
		assertEquals("End", arrayTest[6]);
		assertEquals(7, arrayTest.length);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void changeEmptyBinaryToArrayGetException(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		Object[] arrayTest = myList.toArray();
		
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getItemButIndexOutOfBounds(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		myList.get(8);
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void addItemButIndexOutOfBounds(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.add(7, "Middle5");

		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void getFirstButNoFirst(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.getFirst();
		
	}
	
	@Test
	public void testIteratorHasNext(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		Iterator<String> myIterator = myList.iterator();
		
		assertEquals(true, myIterator.hasNext());
		
		myIterator.next();
		myIterator.next();
		myIterator.next();
		myIterator.next();
		myIterator.next();
		myIterator.next();
		myIterator.next();
		
		assertEquals(false, myIterator.hasNext());
		
	}
	
	@Test
	public void testIteratorNext(){
		
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		Iterator<String> myIterator = myList.iterator();
		
		assertEquals("Start", myIterator.next());
		assertEquals("Middle1", myIterator.next());
		assertEquals("Middle2", myIterator.next());
		assertEquals("Middle3", myIterator.next());
		assertEquals("Middle4", myIterator.next());
		assertEquals("Middle5", myIterator.next());
		assertEquals("End", myIterator.next());
		
	}
	
	@Test
	public void testIteratorRemove(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		Iterator<String> myIterator = myList.iterator();
		
		myIterator.next();
		myIterator.remove();
		
		assertEquals("Middle1", myList.getFirst());
		
	}
	
	@Test
	public void removeFirst(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		assertEquals(7, myList.size());
		assertEquals("Start", myList.getFirst());
		
		myList.removeFirst();
		
		
		assertEquals(6, myList.size());
		assertEquals("Middle1", myList.getFirst());
	}
	
	@Test
	public void removeLast(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		assertEquals(7, myList.size());
		assertEquals("End", myList.getLast());
		
		myList.removeLast();
		
		
		assertEquals(6, myList.size());
		assertEquals("Middle5", myList.getLast());
	}
	
	@Test
	public void removeWithIndex(){
		
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		assertEquals(7, myList.size());
		assertEquals("Middle3", myList.get(3));
		
		myList.remove(3);
		
		assertEquals(6, myList.size());
		assertEquals("Middle4", myList.get(3));
		
	}
	
	
	@Test (expected = NullPointerException.class)
	public void testIteratorRemoveTwice(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		Iterator<String> myIterator = myList.iterator();
		
		myIterator.next();
		myIterator.remove();
		myIterator.remove();
		
		
	}
	
	@Test 
	public void testIteratorNextTwiceRemoveTwice(){
		Nathan_DoublyLinkedList<String> myList = new Nathan_DoublyLinkedList<String>();
		
		myList.addFirst("Start");
		
		
		myList.add(1, "Middle1");
		myList.add(2, "Middle2");
		myList.add(3, "Middle3");
		myList.add(4, "Middle4");
		myList.add(5, "Middle5");
		myList.addLast("End");
		
		Iterator<String> myIterator = myList.iterator();
		
		myIterator.next();
		myIterator.next();
		myIterator.remove();
		myIterator.remove();
		
		assertEquals("Middle2", myList.getFirst());
		
	}
	
	

}
