/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package main.java;

import logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
	
	private int[] combinations = { AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE };
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
	
	
	/**
	 * Returns the number of points for a Three-Of-A-Kind with the passed dices
	 * @param faces Number of iterations of each dice face
	 * @return The number of points attributed for a Three-Of-A-Kind
	 */
	private int getPointsThreeOfAKind (int[] faces) {
		// TODO: add support of different methods
		
		// By default, returns the sum of all dices
		return IntStream.of(faces).sum();
	}
	
	
	/**
	 * Returns the number of points for a Four-Of-A-Kind with the passed dices
	 * @param faces Number of iterations of each dice face
	 * @return The number of points attributed for a Four-Of-A-Kind
	 */
	private int getPointsFourOfAKind (int[] faces) {
		// TODO: add support of different methods
		
		// By default, returns the sum of all dices
		return IntStream.of(faces).sum();
	}
	
	
	/**
	 * Checks if the combination of faces matches a Three-Of-A-Kind or may match higher combinations that needs at least 3 identical faces
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices can be used for a Three-Of-A-Kind or maybe higher combinations that needs at least 3 identical faces
	 *
	 * TODO: make this method private
	 */
	public boolean checkThreeOfAKind (int[] faces) {
		if (faces.length != 6) {
			return false;
		}
		
		// Check for each face that has at least 3 occurrences
		for (int face : faces) {
			if (face >= 3)
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * Checks if the combination of faces matches a Four-Of-A-Kind or may match a yahtzee
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices can be used for a Four-Of-A-Kind or maybe a yahtzee
	 *
	 * TODO: make this method private
	 */
	public boolean checkFourOfAKind (int[] faces) {
		if (faces.length != 6) {
			return false;
		}
		
		// Check for each face that has at least 4 occurrences
		for (int face : faces) {
			if (face >= 4)
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * Checks if the combination of faces matches a Full-House
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices matches a Full-House
	 *
	 * TODO: make this method private
	 */
	public boolean checkFullHouse (int[] faces) {
		if (faces.length != 6 || !checkThreeOfAKind(faces)) {
			return false;
		}
		
		// Looking for a pair
		for (int face : faces) {
			if (face == 2) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Checks iif the combination of faces matches a Small Straight
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices matches a Small Straight
	 *
	 * TODO: make this method private
	 */
	public boolean checkSmallStraight (int[] faces) {
		if (faces.length != 6 || faces[2] < 1 || faces[3] < 1) {
			return false;
		}
		
		// Check for 1, 2, 3, 4
		if (faces[0] == 1 && faces[1] == 1) {
			return true;
		}
		
		// Check for 2, 3, 4, 5
		if (faces[1] == 1 && faces[4] == 1) {
			return true;
		}
		
		// Check for 3, 4, 5, 6
		return (faces[4] == 1 && faces[5] == 1);
	}
	
	
	/**
	 * Checks if the combination of faces matches a Large Straight
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices matches a Large Straight
	 *
	 * TODO: make this method private
	 */
	public boolean checkLargeStraight (int[] faces) {
		if (faces.length != 6 || faces[1] < 1 || faces[2] < 1 || faces[3] < 1 || faces[4] < 1) {
			return false;
		}
		
		return (faces[0] == 1 || faces[5] == 1);
	}
	
	
	/**
	 * Checks if the combination of faces matches a Yahtzee
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices matches a Yahtzee
	 *
	 * TODO: make this method private
	 */
	public boolean checkYahtzee (int[] faces) {
		for (int face : faces) {
			if (face == 5) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static int[] count (Dice[] dices) {
		int[] faces = {0, 0, 0, 0, 0};
		
		// TODO: implement this method
		
		return faces;
	}
	
	
	/**
	 * Lists all the available options of a player without knowing his dices
	 * @return An ArrayList of strings listing all the available options of a player (without knowing his dices)
	 */
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
	
	
	/**
	 * Lists all the possible options of a player, knowing his dices
	 * @param values Number of iterations of each dice face
	 * @return An ArrayList of strings listing all the possible options of a player (knowing his dices)
	 */
	public ArrayList<String> getPossibles (int[] values) {
		ArrayList<String> possibles = new ArrayList<>();
		ArrayList<String> availables = getAvailable();
		
		int[] faces = {0, 0, 0, 0, 0, 0};
		
		// Checking if there are only 5 dices
		if (values.length != 5) {
			return null;
		}
		
		// Counting the values of the dices
		for (int i = 0; i < 5; i++) {
			if (values[i] >= 1 && values[i] <= 6) {
				faces[values[i] - 1]++;
			}
		}
		
		// Checking for the simple faces
		for (int i = 0; i < 6; i++) {
			if (availables.contains(simpleNames[i])) {
				possibles.add(simpleNames[i] + ": " + faces[i]);
			}
		}
		
		for (int i = 0; i < 6; i++) {
			// If there are at least 3 occurences of a face
			if (faces[i] >= 3) {
				// From now on, we can consider that we have a Three-of-a-kind going on
				if (availables.contains(combinationsNames[0])) {
					possibles.add(combinationsNames[0] + ": " + IntStream.of(faces).sum());
				}
				
				// Now, we check for a Full-house
				if (availables.contains(combinationsNames[1])) {
					// Looking for a pair (in addition to the Three-of-a-kind)
					for (int j = 0; j < 6; j++) {
						if (j == i)  // No need to check the same value twice
							continue;
						
						if (faces[j] == 2) {  // If pair found (Hence, if Full-house)
							possibles.add(combinationsNames[1] + ": 25");
						}
					}
				}
				
			
				// Now we check for a Four-of-a-kind
				if (availables.contains(combinationsNames[2]) && faces[i] >= 4) {
					possibles.add(combinationsNames[2] + ": " + IntStream.of(faces).sum());
				}
				
				// Now we check for a Yahtzee
				if (availables.contains(combinationsNames[5]) && faces[i] == 5) {
					possibles.add(combinationsNames[5] + ": 50");
				}
			}
		}
		
		
		return possibles;
	}
	
}
