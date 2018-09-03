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
		Random roll = new Random();
		
		value = roll.nextInt(6)+1;
	
		return value;
	}
}
