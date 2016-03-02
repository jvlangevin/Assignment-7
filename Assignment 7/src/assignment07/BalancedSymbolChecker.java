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
	boolean activeCharacter = false;
	boolean activeString = false;
	
	
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
			activeCharacter = false;
			
			String thisLine = scanner.nextLine();
			lineNumber++;
			int colNumber = 0; // Keeps track of the column of a given character in the line
			
			// Loops through each character in the current line
			for (int i = 0; i < thisLine.length(); i++){
				
				colNumber++;
				char currentChar = thisLine.charAt(i);
				
				
				/*------------------------------------------------------------------------------
				  string handling such as "word word word" ; checks if char is double quotation				
				--------------------------------------------------------------------------------*/

				if (currentChar =='\"' && !blockComment && !activeCharacter){
					if (i != 0){
						/*
						 * If the current character is preceded by a '\', then proceed to the
						 * next character 
						 */
						if (thisLine.charAt(i - 1) == '\\'){
							continue;
						}
					}
					
					/*
					 * If there is no active string, then the current character signifies the
					 * beginning of a string literal
					 */
					if (!activeString){
						activeString = true;
						continue;
					}
					/*
					 * If there is a string currently active, then the current character signifies
					 * the end of a string literal
					 */
					else{
						activeString = false;
						continue;
					}
				}
				
				
				
				/*------------------------------------------------------------------------------
				  string literal handling such as ']' or '\n' ; checks if char is double quotation
				--------------------------------------------------------------------------------*/
				
				if(currentChar == '\'' && !activeString && !blockComment)
				{
					if (i != 0){
						/*
						 * If the current character is preceded by a '\', then proceed to the
						 * next character 
						 */
						if (thisLine.charAt(i - 1) == '\\'){
							continue;
						}
					}
					
					/*
					 * If the variable activeCharacter is set to false, then the current character
					 * signifies the beginning of a character variable declaration					 * 
					 */
					if (!activeCharacter){
						activeCharacter = true;
						continue;
					}
					/*
					 * If the variable activeCharacter is set to true, then the current character
					 * signifies the end of a character variable declaration
					 */
					else{
						activeCharacter = false;
						continue;
					}

					
				}
				
				
				
				/*------------------------------------------------------------------------------
				  part handling comments such as // or block comments like /* and it's ending
				--------------------------------------------------------------------------------*/
				if(currentChar == '/' && activeString == false && activeCharacter == false)
				{
					
					
				
					if (i < thisLine.length() - 1){
						/* 
						 * If the character following the '/' is a '*', then a comment block has 
						 * been started, and we proceed to the next character
						 */
						if (thisLine.charAt(i + 1) == '*'){
							this.blockComment = true;
							continue;
						}
						/*
						 * If the character following the '/' is another '/', then we have reached
						 * a line comment, and the rest of the line is ignored
						 */
						if (thisLine.charAt(i + 1) == '/'){
							break;
						}
					}
				}
				
				/*
				 * If the current character is a '*' and the character following it is a '/', 
				 * then we have reached the end of a comment block, and regular symbol checking
				 * resumes
				 */
				if (currentChar == '*' && activeString == false && activeCharacter == false){
					if (i < thisLine.length() - 1){
						if (thisLine.charAt(i + 1) == '/'){
							this.blockComment = false;
							continue;
						}
					}
				}
				
				// If the current character is within a comment block, proceed to the next character
				if (this.blockComment){
					continue;
				}
				
				// If the current character is within a string, proceed to the next character
				if (this.activeString){
					continue;
				}
				
				/*
				 * If the current character is being defined as a character variable, proceed to
				 * the next character
				 */
				if (this.activeCharacter){
					continue;
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

		// If the end of the file is reached and there is an unclosed comment block
		if (this.blockComment){
			return unfinishedComment();
		}
		
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
	protected static boolean isLeftSymbol(char inputChar){
		
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
	protected static boolean isRightSymbol(char inputChar){
		
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
