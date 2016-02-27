/*
 * Jason Langevin
 */

package assignment06;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest extends DoublyLinkedList<Integer> {

	DoublyLinkedList<Integer> emptyList;
	DoublyLinkedList<Integer> filledList;
	DoublyLinkedList<Integer> singleElementList;
	DoublyLinkedList<Integer> filledListFirstElementMissing;
	DoublyLinkedList<Integer> filledListMiddleElementMissing;
	DoublyLinkedList<Integer> filledListDuplicateMiddleElement;
	DoublyLinkedList<Integer> filledListDuplicateMiddleElements;
	Iterator<Node<Integer>> iterator;

	@Before
	public void setUp() throws Exception {
		emptyList = new DoublyLinkedList<Integer>();

		filledList = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 100; i++) {
			filledList.add(i - 1, i);
		}

		singleElementList = new DoublyLinkedList<Integer>();
		singleElementList.addFirst(1);

		filledListFirstElementMissing = new DoublyLinkedList<Integer>();
		for (int i = 2; i <= 100; i++) {
			filledListFirstElementMissing.add(i - 2, i);
		}

		filledListMiddleElementMissing = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 49; i++) {
			filledListMiddleElementMissing.add(i - 1, i);
		}
		for (int i = 51; i <= 100; i++) {
			filledListMiddleElementMissing.add(i - 2, i);
		}

		filledListDuplicateMiddleElement = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 100; i++) {
			filledListDuplicateMiddleElement.add(i - 1, i);
		}
		filledListDuplicateMiddleElement.add(50, 50);
		
		filledListDuplicateMiddleElements = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 100; i++) {
			filledListDuplicateMiddleElements.add(i - 1, i);
		}
		filledListDuplicateMiddleElements.add(50, 50);
		filledListDuplicateMiddleElements.add(50, 50);
		filledListDuplicateMiddleElements.add(50, 50);
		filledListDuplicateMiddleElements.add(50, 50);
		}

	@After
	public void clear() {
		filledList.clear();
		singleElementList.clear();
		filledListFirstElementMissing.clear();
		filledListMiddleElementMissing.clear();
		filledListDuplicateMiddleElement.clear();
	}

	@Test
	public void addFirstToEmptyList() {
		emptyList.addFirst(1);
		assertEquals(1, (int) emptyList.get(0));
	}

	@Test
	public void addFirstToFilledList() {
		filledListFirstElementMissing.addFirst(1);
		iterator = filledListFirstElementMissing.iterator();
		int count = 1;
		while (iterator.hasNext()) {
			assertEquals(count, (int)iterator.next().data);
			count++;
		}
	}

	@Test
	public void addLastToEmptyList() {
		emptyList.addLast(1);
		assertEquals(1, (int) emptyList.get(0));
	}

	@Test
	public void addLastToFilledList() {
		filledList.addLast(101);
		int count = 1;
		iterator = filledList.iterator();
		while (iterator.hasNext()) {
			assertEquals(count, (int)iterator.next().data);
			count++;
		}
	}

	@Test
	public void addToEmptyList() {
		emptyList.add(0, 1);
		assertEquals(1, (int) emptyList.get(0));
	}

	@Test
	public void addToStartOfFilledList() {
		filledListFirstElementMissing.add(0, 1);
		int count = 1;
		iterator = filledListFirstElementMissing.iterator();
		while (iterator.hasNext()) {
			assertEquals(count, (int)iterator.next().data);
			count++;
		}
	}

	@Test
	public void addToMiddleOfFilledList() {
		filledListMiddleElementMissing.add(49, 50);
		int count = 1;
		iterator = filledListMiddleElementMissing.iterator();
		while (iterator.hasNext()) {
			assertEquals(count, (int)iterator.next().data);
			count++;
		}
	}

	@Test
	public void addToEndOfFilledList() {
		filledList.add(100, 101);
		int count = 1;
		iterator = filledList.iterator();
		while (iterator.hasNext()) {
			assertEquals(count, (int)iterator.next().data);
			count++;
		}
	}

	@Test
	public void getFirstFromSingleElementList() {
		assertEquals(1, (int) singleElementList.getFirst());
	}

	@Test
	public void getFirstFromFilledList() {
		assertEquals(1, (int) filledList.getFirst());
	}

	@Test
	public void getLastFromSingleElementList() {
		assertEquals(1, (int) singleElementList.getLast());
	}

	@Test
	public void getLastFromFilledList() {
		assertEquals(100, (int) filledList.getLast());
	}

	@Test
	public void getFromSingleElementList() {
		assertEquals(1, (int) singleElementList.get(0));
	}

	@Test
	public void getRandomElementFromFilledList() {
		int randomInt = (int) (Math.random() * 100);
		assertEquals(randomInt, (int) filledList.get(randomInt - 1));
	}

	@Test
	public void removeFirstFromSingleElementList() {
		assertEquals(1, (int) singleElementList.removeFirst());
		assertTrue(singleElementList.size == 0);
	}

	@Test
	public void removeFirstFromFilledList() {
		assertEquals(1, (int) filledList.removeFirst());
		iterator = this.iterator();
		int count = 2;
		while (iterator.hasNext()) {
			assertEquals(count, (int) iterator.next().data);
			count++;
		}
	}

	@Test
	public void removeLastFromSingleElementList() {
		assertEquals(1, (int) singleElementList.removeLast());
		assertTrue(singleElementList.size == 0);
	}

	@Test
	public void removeLastFromFilledList() {
		assertEquals(100, (int) filledList.removeLast());
		iterator = this.iterator();
		int count = 1;
		while (iterator.hasNext()) {
			assertEquals(count, (int) iterator.next().data);
			count++;
		}
	}

	@Test
	public void removeFromSingleElementList() {
		assertEquals(1, (int) singleElementList.remove(0));
		assertTrue(size == 0);
	}

	@Test
	public void removeRandomElementFromFilledList() {
		int randomInt = (int) (Math.random() * 100) + 1;
		assertEquals(randomInt - 1, (int) filledList.remove(randomInt - 2));
		assertFalse(contains(filledList, randomInt));
	}

	@Test
	public void indexOfElementInSingleElementList(){
		assertEquals(0, singleElementList.indexOf(1));
	}
	
	@Test
	public void indexOfFirstInstanceOfDuplicateElementInFilledList(){
		assertEquals(49, filledListDuplicateMiddleElements.indexOf(50));
	}
	
	@Test
	public void lastIndexOfElementInSingleElementList(){
		assertEquals(0, singleElementList.lastIndexOf(1));
	}
	
	@Test
	public void lastIndexOfDuplicateElementInFilledList(){
		assertEquals(53, filledListDuplicateMiddleElements.lastIndexOf(50));
	}
	
	@Test
	public void testSizeMethod(){
		assertEquals(0, emptyList.size);
		assertEquals(1, singleElementList.size);
		assertEquals(100, filledList.size);
		assertEquals(99, filledListFirstElementMissing.size);
		assertEquals(99, filledListMiddleElementMissing.size);
		assertEquals(101, filledListDuplicateMiddleElement.size);
		assertEquals(104, filledListDuplicateMiddleElements.size);
	}
	
	@Test
	public void testIsEmptyMethod(){
		assertTrue(emptyList.isEmpty());
		assertFalse(filledList.isEmpty());
	}
	
	@Test
	public void testClearMethod(){
		filledList.clear();
		assertEquals(null, filledList.head);
		assertTrue(filledList.size == 0);
	}
	
	@Test
	public void emptyListToArray(){
		Object[] testArray = new Object[0];
		assertArrayEquals(testArray, emptyList.toArray());
	}
	
	@Test
	public void singleElementListToArray(){
		Object[] testArray = new Object[1];
		testArray[0] = 1;
		assertArrayEquals(testArray, singleElementList.toArray());
	}
	
	@Test
	public void filledListToArray(){
		Object[] testArray = new Object[100];
		for (int i = 1; i <= 100; i++){
			testArray[i - 1] = i;
		}
		assertArrayEquals(testArray, filledList.toArray());
	}
	
	@Test
	public void iteratorHasNextMethodOnEmptyArray(){
		assertFalse(emptyList.iterator().hasNext());
	}
	
	@Test
	public void iteratorNextMethodOnFilledArray(){
		iterator = filledList.iterator();
		int count = 1;
		while (iterator.hasNext()){
			assertEquals(count, (int)iterator.next().data);
			count++;
		}
	}
	
	/**
	 * 
	 * @param list
	 * @param element
	 * @return True if the specified element is in the specified list,
	 *  returns false otherwise
	 */
	private boolean contains(DoublyLinkedList<Integer> list, int element) {
		Iterator<Node<Integer>> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().data == element) {
				return false;
			}
		}
		return true;
	}
}
