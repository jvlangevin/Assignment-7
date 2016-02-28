package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author ??
 */
public class BalancedSymbolChecker {
	
	LinkedListStack<Character> charStack;
	
	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * @throws FileNotFoundException 
	 */
	public String checkFile(String filename) throws FileNotFoundException{
		
		charStack = new LinkedListStack<>();
		Scanner scanner = new Scanner(new File(filename));
		int lineNumber = 0; // Keeps track of which line of the file the Scanner is reading
		
		while (scanner.hasNextLine()){
			
			String thisLine = scanner.nextLine();
			lineNumber++;
			int column = 0; // Keeps track of the column of a given character in the line
			
			// Loops through each character in the current line
			for (int i = 0; i < thisLine.length(); i++){
				
				column++;
				char currentChar = thisLine.charAt(i);
				
				// If the current character is {, [, or (, it is pushed onto the stack 
				if (currentChar == '{' || currentChar == '[' || currentChar == '('){
					charStack.push(currentChar);
				}
				
				// Checks if the current character is }, ], or )
				if (currentChar == '}' || currentChar == ']' || currentChar == ')'){
					
					// If the stack is empty, that means the current character does not have a 
					// matching symbol
					if (charStack.isEmpty()){
						
						if (currentChar == '}'){
							scanner.close();
							return unmatchedSymbolAtEOF('{');
						}
						
						else if (currentChar == ']'){
							scanner.close();
							return unmatchedSymbolAtEOF('[');
						}
						
						else{
							scanner.close();
							return unmatchedSymbolAtEOF('(');
						}
					}
					
					// If the character at the top of the stack is not the matching symbol
					// of the current element
					
					if (charStack.peek() == '{' && currentChar != '}'){
						scanner.close();
						return unmatchedSymbol(lineNumber, column, currentChar, '}');
					}
					
					else if (charStack.peek() == '[' && currentChar != ']'){
						scanner.close();
						return unmatchedSymbol(lineNumber, column, currentChar, ']');
					}
					
					else if (charStack.peek() == '(' && currentChar != ')'){
						scanner.close();
						return unmatchedSymbol(lineNumber, column, currentChar, ')');
					}
					
					// If the element at the top of the stack is the matching symbol of the
					// current character, the top element is popped, and the scanner continues
					// reading the file
					else{
						charStack.pop();
					}
				}
			}
		}
		scanner.close();

		// If the end of the file is reached and the stack is empty, then all of the symbols match
		if (charStack.isEmpty()){
			return allSymbolsMatch();
		}
		
		// If the end of the file is reached and the stack is not empty, then the file contains
		// an unmatched symbol
		else{
			if (charStack.peek() == '{'){
				return unmatchedSymbolAtEOF('}');
			}
			else if (charStack.peek() == '['){
				return unmatchedSymbolAtEOF(']');
			}
			else{
				return unmatchedSymbolAtEOF(')');
			}
		}
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
	
}