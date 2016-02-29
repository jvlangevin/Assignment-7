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
	LinkedListStack<Character> specialStack;
	boolean lineComment = false;
	boolean blockComment = false;
	boolean activeString = false;
	boolean activeStringLiteral = false;
	
	
	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * @throws FileNotFoundException 
	 */
	public String checkFile(String filename) throws FileNotFoundException{
		
		charStack = new LinkedListStack<>();
		specialStack = new LinkedListStack<>();
		
		Scanner scanner = new Scanner(new File(filename));
		int lineNumber = 0; // Keeps track of which line of the file the Scanner is reading
		
		while (scanner.hasNextLine()){
			
			//if we are starting a new line we are no longer in a string or string literal
			activeString = false;
			activeStringLiteral = false;
			
			String thisLine = scanner.nextLine();
			lineNumber++;
			int colNumber = 0; // Keeps track of the column of a given character in the line
			
			// Loops through each character in the current line
			for (int i = 0; i < thisLine.length(); i++){
				
				colNumber++;
				char currentChar = thisLine.charAt(i);
				
				
				/*------------------------------------------------------------------------------
				-------------------------------------------------------------------------------- 
				  string handling such as "word word word" ; checks if char is double quotation
				
											LOGIC:
				1. if character is a " and we are not in a string literal such as '"', 
				   check to see if our special stack has a " on the top. if we are in a
				   string literal, character is ignored
				2. if this is not a string literal, and the special stack has " on top, it means
				   we are closing out a string and we turn our activeString boolean
				   to false (see notes after 3) and pop the top " to match the string set
				3. if the special stack does not have " on top, we are not in a string
				   so we turn our activeString boolean to true 
				   
				Notes:
				
					When we are in a string (activeString = true) then no characters get logged until
					we end the line or find another " character to pop the " off the special stack
				
				--------------------------------------------------------------------------------*/
				if(currentChar == '\"' && activeStringLiteral == false)
				{
				
					if(specialStack.peek() == '\"')
					{
						activeString = false;
						specialStack.pop();
					}
					else{
						activeString = true;
						specialStack.push(currentChar);
					}
				}
				
				/*------------------------------------------------------------------------------
				-------------------------------------------------------------------------------- 
				  string literal handling such as ']' or '\n' ; checks if char is double quotation
				
											LOGIC:
				1. if character is a ' and we are not in a string such as " Nathan's code"
				   check to see if our special stack has a ' on the top. if we are in a
				   string, character is ignored
				2. if this is not a string, and the special stack has ' on top, it means
				   we are closing out a string literal and we turn our activeStringLiteral boolean
				   to false (see notes after 3) and pop the top ' to match the stringLiteral set
				3. if the special stack does not have ' on top, we are not in a string literal
				   so we turn our activeString boolean to true 
				   
				Notes:
				
					When we are in a string literal (activeStringLiteral = true) then no characters get 
					logged until we end the line or find another ' character to pop the ' off 
					the special stack
				
				--------------------------------------------------------------------------------*/
				
				if(currentChar == '\'' && activeString == false)
				{
					
					//if character is a '
					if(specialStack.peek() == '\'')
					{
						activeStringLiteral = false;
						specialStack.pop();
					}
					//if previous special character in stack is a backslash get rid of it but do not change activeStringLiteral
					else if(specialStack.peek() == '\\')
					{
						specialStack.pop();
					}
					else{
						activeStringLiteral = true;
						specialStack.push(currentChar);
					}
					
				}
				
				
				
				/*------------------------------------------------------------------------------
				-------------------------------------------------------------------------------- 
				  part handling comments such as // or block comments like /* and it's ending
				
											LOGIC:
				1. if character is a / and we are not in a string/string literal such as "/" or '/'
				   check to see if our special stack has a / on the top. 
				2. if this is not a string/string literal, and the special stack has / on top, it means
				   we are starting a line comment and the rest of the line is ignored
				3. if the prior character in special stack is a * then we are closing block comments
				   (blockComment = false). 
				   
				Notes:
				
					When we are in a string literal (activeStringLiteral = true) then no characters get 
					logged until we end the line or find another ' character to pop the ' off 
					the special stack
				
				--------------------------------------------------------------------------------*/
				if(currentChar == '/' && activeString == false && activeStringLiteral == false)
				{
					
					//if it's a line comment (like //this is a comment ) then the rest of the line is ignored
					if(specialStack.peek() == '/')
					{
						//get the previous '/' out of special stack and set i to line length so we can move on to the next line
						specialStack.pop();
						i = thisLine.length();
					}
					
					//if it's the end of a block comment (like "/* block comment */") then we go back to checking for our main symbols
					else if(specialStack.peek() == '*')
					{
						this.blockComment = false;
					}
					
					
						
				}
				
				// If the current character is {, [, or (, it is pushed onto the stack 
				if (isLeftSymbol(currentChar)){
					charStack.push(currentChar);
				}
				
				// Checks if the current character is }, ], or )
				if (isRightSymbol(currentChar)){
					
					// If the stack is empty, then no closing symbol should be expected at
					// this point
					if (charStack.isEmpty()){
						scanner.close();
						return unmatchedSymbol(lineNumber, colNumber, currentChar, ' ');
					}
					
					// If the character at the top of the stack is not the matching symbol
					// of the current element
					
					if (charStack.peek() == '{' && currentChar != '}'){
						scanner.close();
						return unmatchedSymbol(lineNumber, colNumber, currentChar, '}');
					}
					
					else if (charStack.peek() == '[' && currentChar != ']'){
						scanner.close();
						return unmatchedSymbol(lineNumber, colNumber, currentChar, ']');
					}
					
					else if (charStack.peek() == '(' && currentChar != ')'){
						scanner.close();
						return unmatchedSymbol(lineNumber, colNumber, currentChar, ')');
					}
					
					// If the element at the top of the stack is the matching symbol of the
					// current character, the top element is popped, and reading of the file
					// is resumed
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
	 * Helper method. If the character is one of our left character sets, returns true.
	 * @param inputChar
	 * @return
	 */
	private static boolean isLeftSymbol(char inputChar){
		
		if (inputChar == '{' || inputChar == '[' || inputChar == '(')
		{
			return true;
		}
		
		return false;
	
	}

	
	/**
	 * Helper method. If the character is one of our right character sets, returns true.
	 * @param inputChar
	 * @return
	 */
	private static boolean isRightSymbol(char inputChar){
		
		if (inputChar == '}' || inputChar == ']' || inputChar == ')')
		{
			return true;
		}
		
		return false;
	
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