package main.java;

import java.util.Random;

public class Dice {
	
	private static final int NO_VALUE = -1;
	
	private boolean locked = false;
	private int value = NO_VALUE;
	
	
	/**
	 * Rolls a dice
	 * @return An integer between 1 and 6 (both inclusive)
	 */
	public int roll () {
		final int NUMBER_OF_FACES = 6;
		final int RECTIFICATION_RANDOM = 1;

		if (!locked) {
			value = (new Random().nextInt(NUMBER_OF_FACES)) + RECTIFICATION_RANDOM;
		}
		
		return value;
	}
	
	
	/**
	 * Locks the dice.
	 * Calling this method will prevent the dice from being rolled.
	 * When a dice is locked using this method, calling the roll() method won't roll the dice and it will keep its value until unlocked.
	 */
	public void lock () {
		locked = true;
	}
	
	
	/**
	 * Unlocks the dice.
	 * When a dice is locked, it won't roll even if the roll() method is called. Calling the unlock method will unlock the dice.
	 */
	public void unlock () {
		locked = false;
	}
	
	
	/**
	 * Toggles the locked state.
	 * If the state is`locked, it will toggle to unlocked.
	 */
	public void toggleLock () {
		locked = !locked;
	}
	
	
	/**
	 * Allows to know whether or not the instance is in a locked state.
	 * @return Whether or not the instance is in a locked state
	 */
	public boolean isLocked () {
		return locked;
	}
	
	
	/**
	 * Gets the value of the <code>Dice</code> instance
	 * @return the value of the Dice
	 */
	public int getValue () {
		return value;
	}
}
