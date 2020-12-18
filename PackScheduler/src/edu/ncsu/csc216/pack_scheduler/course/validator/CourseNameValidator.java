/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * CourseNameValidator uses the state pattern to check if the class name being passed in 
 * is valid or not.
 * 
 * @author jhnguye4
 *
 */
public class CourseNameValidator {
	
	/** validEndState returns true if state is valid */
	private boolean validEndState;
	/** letterCount is the counter for number of letters in a string */
	private int letterCount = 0;
	/** digitCount is the counter for number of digits in string */
	private int digitCount = 0;
	/** instatiate the initial state */
	private State stateInitial = new InitialState();
	/** instatiate the current state */
	private State currentState;
	/** Instantiate the letter state */
	private State stateLetter = new LetterState();
	/** Instantiate the number state */
	private State stateDigit = new NumberState();
	/** Instantiate the suffix state */
	private State stateSuffix = new SuffixState();
	
	
	/** 
	 *  This method checks the current state of the character is passed through and then
	 *  calls upon the inner class depending on which state it is in. If the character is 
	 *  a letter then it will call upon onLetter() method, if character is a digit then it 
	 *  will call upon onDigit() method, if character is anything else then it will call
	 *  upon onOther();
	 *  @param courseName is the String name of the course
	 *  @return boolean if String is calid then it will return true
	 *  @throws InvalidTransitionException if the character has an invalid transition
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		validEndState = false;
		currentState = stateInitial;
		for (int i = 0; i < courseName.length(); i++) {
			Character character = courseName.charAt(i);
			if(!Character.isLetter(character) && !Character.isDigit(character)) {
				currentState.onOther();
			}
			if (currentState == stateInitial) {
				if(Character.isLetter(character)) {
					currentState.onLetter();
					currentState = stateLetter;
					continue;
				}
				if(Character.isDigit(character)) {
					currentState.onDigit();
				}
			}
			if(currentState == stateLetter) {
				if(Character.isLetter(character)) {
					currentState.onLetter();
					currentState = stateLetter;
					continue;
				}
				if(Character.isDigit(character)) {
					currentState.onDigit();
					currentState = stateDigit;
					continue;
				}
				else {
					currentState.onOther();
				}
			}
			if(currentState == stateDigit) {
				if(Character.isLetter(character)) {
					currentState.onLetter();
					currentState = stateSuffix;
					validEndState = true;
					continue;
				}
				if(Character.isDigit(character)) {
					currentState = stateDigit;
					currentState.onDigit();
					continue;
				}
				else {
					currentState.onOther();
				}
			}
			if(currentState == stateSuffix) {
				if(Character.isLetter(character)) {
					currentState.onLetter();
				}
				if(Character.isDigit(character)) {
					currentState.onDigit();
				}
				else {
					currentState.onOther();
				}
			}
		}
		letterCount = 0;
		digitCount = 0;
		return validEndState;
	}
	/** This class has all the methods that must be implemented in the inner classes */
	public abstract class State {
		/** abstract method that is called if character is on a letter
		 * @throws InvalidTransitionException if transition is invalid*/
		abstract void onLetter() throws InvalidTransitionException;
		/** abstract method that is called if character is on a digit
		 * @throws InvalidTransitionException if transition is invalid
		 */
		abstract void onDigit() throws InvalidTransitionException;
		/** abstract method that is called if character is on a letter or digit
		 * @throws InvalidTransitionException if transition is invalid
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}	
	
	/** 
	 * InitialState is called upon for the first character of the String
	 */
	private class InitialState extends State { 
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter. letterCount is incremented in this state
		 * @throws InvalidTransitionException if there is a letter in this state
		 */
		@Override
		void onLetter() {
			letterCount++;
		}
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter.
		 * @throws InvalidTransitionException if there is a digit in this state
		 */
		@Override
		void onDigit() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name must start with a letter.");
		}

	}
	/** 
	 * LetterState is called upon when character is a letter in the string.
	 * The purpose of this class is that if it is in a letter state then it will
	 * access these two methods that are implemented from the interface.
	 */
	private class LetterState extends State {
		/** 
		 * Constant used for bounds of letterCount
		 */
		private static final int MAX_PREFIX_LETTERS = 4;
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter. letterCount is incremented if it is less
		 * than the constant, else it throws an exception.
		 * @throws InvalidTransitionException if there is a letter in this state
		 */
		@Override
		void onLetter() throws InvalidTransitionException{
			if(letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter. increments digitCount if it is less than constant
		 * @throws InvalidTransitionException if there is a digit in this state
		 */
		@Override
		void onDigit(){
			if(letterCount <= MAX_PREFIX_LETTERS) {
				digitCount++;
			}
		}


	}
	/** 
	 * NumberState is called upon if character is a digit in the string.
	 * The purpose of this class is that if it is in a number state then it will
	 * access these two methods that are implemented from the interface.
	 */
	private class NumberState extends State { 
		/** 
		 * Constant used for bounds of digitCOunt
		 */
		private static final int COURSE_NUMBER_LENGTH = 3;
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter. Throws exception if digitcount is less than 
		 * constant and is a letter
		 * @throws InvalidTransitionException if there is a letter in this state
		 */
		@Override
		void onLetter() throws InvalidTransitionException{
			if(digitCount < COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter. increments digitCount if it is less than constant
		 * @throws InvalidTransitionException if there is a digit in this state
		 */
		@Override
		void onDigit() throws InvalidTransitionException{
			if(digitCount > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
				
			}
			if(digitCount == COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
				
			}
			if(digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
				if(digitCount == COURSE_NUMBER_LENGTH) {
					validEndState = true;
				}
			}
		}

	}
	/** 
	 * SuffixState is called upon for the character after the last digit in the String.
	 * The purpose of this class is that if it is in a suffix state then it will
	 * access these two methods that are implemented from the interface.
	 */
	private class SuffixState extends State {
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter.
		 * @throws InvalidTransitionException if there is a letter in this state
		 */
		@Override
		void onLetter() throws InvalidTransitionException{
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		/** 
		 * This method is implemented from abstract class State where it is called
		 * upon if character is a letter.
		 * @throws InvalidTransitionException if there is a digit in this state
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}

		
	}
}

