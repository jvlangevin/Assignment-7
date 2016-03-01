package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class BalancedSymbolCheckerTest {

	@Test
	public void checkFileClass1() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class1.java"));
	}
	
	@Test
	public void checkFileClass2() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class2.java"));
	}
	
	@Test
	public void checkFileClass3() throws FileNotFoundException {
		String output = "No errors found. All symbols match.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class3.java"));
	}
	
	@Test
	public void checkFileClass4() throws FileNotFoundException {
		String output = "ERROR: File ended before closing comment.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class4.java"));
	}
	
	@Test
	public void checkFileClass5() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class5.java"));
	}
	
	@Test
	public void checkFileClass6() throws FileNotFoundException {
		String output = "No errors found. All symbols match.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class6.java"));
	}
	
	@Test
	public void checkFileClass7() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class7.java"));
	}
	
	@Test
	public void checkFileClass8() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class8.java"));
	}
	
	@Test
	public void checkFileClass9() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class9.java"));
	}
	
	@Test
	public void checkFileClass10() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class10.java"));
	}
	
	@Test
	public void checkFileClass11() throws FileNotFoundException {
		String output = "ERROR: Unmatched symbol at the end of file. Expected }.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class11.java"));
	}
	
	@Test
	public void checkFileClass12() throws FileNotFoundException {
		String output = "No errors found. All symbols match.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class12.java"));
	}
	
	@Test
	public void checkFileClass13() throws FileNotFoundException {
		String output = "No errors found. All symbols match.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class13.java"));
	}
	
	@Test
	public void checkFileClass14() throws FileNotFoundException {
		String output = "No errors found. All symbols match.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class14.java"));
	}
	
	@Test (expected = FileNotFoundException.class)
	public void checkMissingFile() throws FileNotFoundException {
		String output = "No errors found. All symbols match.";
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("thisIsNotARealFileName.txt"));
	}
	
	
	
	//TODO - perhaps we should write in a conditional if a string isn't closed?
	@Test 
	public void checkUncompletedString() throws FileNotFoundException {
		String output = "No errors found. All symbols match."; //I expected this but am unsure if correct
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class15.java"));
	}
	
	
	@Test 
	public void checkStringWithStringLiteralInside() throws FileNotFoundException {
		String output = "No errors found. All symbols match."; 
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/Class16.java"));
	}
	
	
	@Test 
	public void checkEmptyFile() throws FileNotFoundException {
		String output = "No errors found. All symbols match."; 
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/emptyFile.java"));
	}
	
	@Test 
	public void checkTwoCharacter() throws FileNotFoundException {
		String output = "No errors found. All symbols match."; //I expected this but am unsure if correct
		
		BalancedSymbolChecker test = new BalancedSymbolChecker();
		assertEquals(output, test.checkFile("A7_examples/A7_examples/twocharacterChar.java"));
	}

}
