/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package main.java;

import java.util.ArrayList;
import java.util.List;

public class Scores {
	private final static int AVAILABLE = -1;  // #NoMagicNumbers
	
	private int[] simples = { AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE };
	private final String[] simpleNames = {
		"Ones",
		"Twos",
		"Threes",
		"Fours",
		"Fives",
		"Sixs"
	};
	
	private int[] combinations = {
		AVAILABLE,
		AVAILABLE,
		AVAILABLE,
		AVAILABLE,
		AVAILABLE,
		AVAILABLE,
		AVAILABLE
	};
	private final String[] combinationsNames = {
		"Three-of-a-kind",
		"Full-house",
		"Four-of-a-kind",
		"Small straight",
		"Large straight",
		"Yahtzee",
		"Chance"
	};
	
	
	public int getAces () { return simples[0]; }
	public int getTwos () { return simples[1]; }
	public int getThrees () { return simples[2]; }
	public int getFours () { return simples[3]; }
	public int getFives () { return simples[4]; }
	public int getSixs () { return simples[5]; }
	public int getThreeOfAKind () { return combinations[0]; }
	public int getFullHouse () { return combinations[1]; }
	public int getFourOfAKind () { return combinations[2]; }
	public int getSmallStraight () { return combinations[3]; }
	public int getLargeStraight () { return combinations[4]; }
	public int getYahtzee () { return combinations[5]; }
	public int getChance () { return combinations[6]; }
	
	
	public ArrayList<String> getAvailable () {
		ArrayList<String> availables = new ArrayList<>();
		
		// Getting through all variables and check their values to see if they are available
		for (int i = 0; i < simples.length; i++) {
			if (simples[i] == AVAILABLE)
				availables.add(simpleNames[i]);
		}
		
		// We do it twice to order the list the same way the physical score sheet is ordered
		for (int i = 0; i < simples.length; i++) {
			if (combinations[i] == AVAILABLE)
				availables.add(combinationsNames[i]);
		}
		
		return availables;
	}
}
