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
	
	
	/**
	 * Locks the dice
	 * Calling this method will prevent the dice from being rolled
	 * When a dice is locked using this method, calling the `roll` method won't roll the dice and it will keep its value until unlocked
	 */
	public void lock () {
		locked = true;
	}
	
	
	/**
	 * Unlocks the dice
	 * When a dice is locked, it won't roll even if the `roll` method is called. Calling the `unlock` method will unlock the dice
	 */
	public void unlock () {
	
	}
}
