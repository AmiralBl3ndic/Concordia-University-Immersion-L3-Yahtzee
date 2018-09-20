/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package main.java;

import inputs.IntInput;
import logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Class that represents the scoreboard (scores) of a player, it is designed to be attached to the {@link main.java.Player} class
 */
public class Scores {
	private final static int AVAILABLE = -1;  // #NoMagicNumbers

	private static int YAHTZEE_POINTS = 50;
	private static int FULL_HOUSE_POINTS = 20;
	private static int SMALL_STRAIGHT_POINTS = 30;
	private static int LARGE_STRAIGHT_POINTS = 40;
	
	private static final int MINIMUM_UPPER_SCORE_FOR_BONUS = 63;
	private static final int UPPER_BONUS = 37;

	private int[] simples = {AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE};
	public static final String[] simpleNames = {
			"Ones",
			"Twos",
			"Threes",
			"Fours",
			"Fives",
			"Sixes"
	};

	private int[] combinations = {AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE};
	public static final String[] combinationsNames = {
			"Three-of-a-kind",
			"Full-house",
			"Four-of-a-kind",
			"Small straight",
			"Large straight",
			"Yahtzee",
			"Chance"
	};
	
	
	/**
	 * Getter for the score at the {@code Aces}
	 * @return Score at the {@code Aces}
	 */
	public int getAces() { return simples[0]; }
	
	
	/**
	 * Getter for the score at the {@code Twos}
	 * @return Score at the {@code Twos}
	 */
	public int getTwos() { return simples[1]; }
	
	
	/**
	 * Getter for the score at the {@code Threes}
	 * @return Score at the {@code Threes}
	 */
	public int getThrees() { return simples[2]; }
	
	
	/**
	 * Getter for the score at the {@code Fours}
	 * @return Score at the {@code Fours}
	 */
	public int getFours() { return simples[3]; }
	
	
	/**
	 * Getter for the score at the {@code Fives}
	 * @return Score at the {@code Fives}
	 */
	public int getFives() { return simples[4]; }
	
	
	/**
	 * Getter for the score at the {@code Sixes}
	 * @return Score at the {@code Sixes}
	 */
	public int getSixes() { return simples[5]; }
	
	
	/**
	 * Getter for the score at the {@code Three Of A Kind}
	 * @return Score at the {@code Three Of A Kind}
	 */
	public int getThreeOfAKind() { return combinations[0]; }
	
	
	/**
	 * Getter for the score at the {@code Full House}
	 * @return Score at the {@code Full House}
	 */
	public int getFullHouse() { return combinations[1]; }
	
	
	/**
	 * Getter for the score at the {@code Four Of A Kind}
	 * @return Score at the {@code Four Of A Kind}
	 */
	public int getFourOfAKind() { return combinations[2]; }
	
	
	/**
	 * Getter for the score at the {@code Small Straight}
	 * @return Score at the {@code Small Straight}
	 */
	public int getSmallStraight() { return combinations[3]; }
	
	
	/**
	 * Getter for the score at the {@code Large Straight}
	 * @return Score at the {@code Large Straight}
	 */
	public int getLargeStraight() { return combinations[4]; }
	
	
	/**
	 * Getter for the score at the {@code Yahtzee}
	 * @return Score at the {@code Yahtzee}
	 */
	public int getYahtzee() { return combinations[5]; }
	
	
	/**
	 * Getter for the score at the {@code Chance}
	 * @return Score at the {@code Chance}
	 */
	public int getChance() { return combinations[6]; }


	/**
	 * Get the number of points when summing all dices
	 * @param faces Number of iterations of each dice face
	 * @return Number of points when summing all dices
	 */
	private static int sumFaces(int[] faces) {
		final int ACES_INDEX = 0;
		final int TWOS_INDEX = 1;
		final int THREES_INDEX = 2;
		final int FOURS_INDEX = 3;
		final int FIVES_INDEX = 4;
		final int SIXES_INDEX = 5;
		final int SCORE_RECTIFIER = 1;

		int sum = faces[ACES_INDEX];
		sum += faces[TWOS_INDEX] * (TWOS_INDEX + SCORE_RECTIFIER);
		sum += faces[THREES_INDEX] * (THREES_INDEX + SCORE_RECTIFIER);
		sum += faces[FOURS_INDEX] * (FOURS_INDEX + SCORE_RECTIFIER);
		sum += faces[FIVES_INDEX] * (FIVES_INDEX + SCORE_RECTIFIER);
		sum += faces[SIXES_INDEX] * (SIXES_INDEX + SCORE_RECTIFIER);

		return sum;
	}


	/**
	 * Returns the number of points for a Three-Of-A-Kind with the passed dices
	 * @param faces Number of iterations of each dice face
	 * @return The number of points attributed for a Three-Of-A-Kind
	 */
	private static int getPointsThreeOfAKind(int[] faces) {
		if (checkThreeOfAKind(faces)) {
			return sumFaces(faces);
		}
		// By default, returns 0
		return 0;
	}

	
	/**
	 * Returns the number of points for a Four-Of-A-Kind with the passed dices
	 * @param faces Number of iterations of each dice face
	 * @return The number of points attributed for a Four-Of-A-Kind
	 */
	private static int getPointsFourOfAKind(int[] faces) {
		if (checkFourOfAKind(faces)) {
			return sumFaces(faces);
		}
		// By default, returns 0
		return 0;
	}

	
	/**
	 * Returns the number of points for a Chance with the passed dices
	 * @param faces Number of iterations of each dice face
	 * @return The number of points attributed for a Chance
	 */
	private int getPointsChance(int[] faces) {
		return sumFaces(faces);
	}


	/**
	 * Checks if the combination of faces matches a Three-Of-A-Kind or may match higher combinations that needs at least 3 identical faces
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices can be used for a Three-Of-A-Kind or maybe higher combinations that needs at least 3 identical faces
	 */
	public static boolean checkThreeOfAKind(int[] faces) {
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
	 */
	public static boolean checkFourOfAKind(int[] faces) {
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
	 */
	public boolean checkFullHouse(int[] faces) {
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
	 */
	public boolean checkSmallStraight(int[] faces) {
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
	 */
	public boolean checkLargeStraight(int[] faces) {
		if (faces.length != 6 || faces[1] < 1 || faces[2] < 1 || faces[3] < 1 || faces[4] < 1) {
			return false;
		}

		return (faces[0] == 1 || faces[5] == 1);
	}


	/**
	 * Checks if the combination of faces matches a Yahtzee
	 * @param faces Number of iterations of each dice face
	 * @return Whether or not the combination of dices matches a Yahtzee
	 */
	public boolean checkYahtzee(int[] faces) {
		for (int face : faces) {
			if (face == 5) {
				return true;
			}
		}

		return false;
	}
	
	
	/**
	 * Count the iterations of each face of the dices
	 * @param dices Rolled dices
	 * @return Array of integer containing the number of iterations of each face
	 */
	public static int[] count(Dice[] dices) {
		final int INDEX_RECTIFIER = 1;
		int[] faces = {0, 0, 0, 0, 0, 0};

		for (Dice dice : dices) {
			faces[dice.getValue() - INDEX_RECTIFIER]++;
		}

		return faces;
	}


	/**
	 * Lists all the available options of a player without knowing his dices
	 * @return An ArrayList of strings listing all the available options of a player (without knowing his dices)
	 */
	public ArrayList<String> getAvailable() {
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
	public ArrayList<String> getPossibles(int[] values) {
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


	/**
	 * Prints the available "storage" in the console
	 */
	public void displayAvailable() {
		final int START_INDEX = 1;
		int choiceIndex = START_INDEX;

		Logger.log("\n\nAvailable storage:\n");
		
		System.out.println("╔════════════════════════════╗");
		System.out.println("║      AVAILABLE STORAGE     ║");
		System.out.println("╠════════════════════════════╣");
		
		int i = 0;
		
		// Exploring the upper part of the scoreboard to find available choices
		for (i = 0; i < simples.length; i++) {
			if (simples[i] == AVAILABLE) {  // If the current choice is available
				System.out.printf("║     %2d. %15s    ║%n", (i+1), simpleNames[i]);
			}
		}
		
		
		// Exploring the lower part of the scoreboard to find available choices
		for (int j = 0; j < combinations.length; j++) {
			if (combinations[j] == AVAILABLE) {  // If the current choice is available
				System.out.printf("║     %2d. %15s    ║%n", (i + j +1), combinationsNames[j]);
			}
		}
		
		System.out.println("╚════════════════════════════╝");
	}
	
	
	/**
	 * Get the sum of the scores of the upper part of the scoreboard
	 * @return Sum of the scores of the upper part of the scoreboard
	 */
	public int getUpperSum () {
		int sum = 0;
		
		// Exploring all the scores of the upper part of the board
		for (int score : simples) {
			if (score != AVAILABLE) {  // If the score is unset for now, we do not count it
				sum += score;
			}
		}
		
		return sum;
	}
	
	
	/**
	 * Get the value of the {@code bonus} of the upper part of the board
	 * @return Value of the {@code bonus} of the upper part of the board
	 */
	public int getUpperBonus () {
		return getUpperSum() >= MINIMUM_UPPER_SCORE_FOR_BONUS ? UPPER_BONUS : 0;
	}
	
	
	/**
	 * Computes the total number of points for the board
	 * @return The number of points associated to the instance
	 */
	public int total() {
		int sum = 0;

		for (int score : simples) {
			if (score > 0) {
				sum += score;
			}
		}
		
		if (sum >= MINIMUM_UPPER_SCORE_FOR_BONUS)
			sum += UPPER_BONUS;
		
		for (int score : combinations) {
			if (score > 0) {
				sum += score;
			}
		}
		
		return sum;
	}
	
	
	/**
	 * Display the scoreboard of a single player
	 */
	public void displayTable() {
		final int START_INDEX = 1;
		int choiceIndex = START_INDEX;

		// Iterating through the upper part of the board
		for (int i = 0; i < simples.length; i++) {
			Logger.log(choiceIndex++ + ". " + simpleNames[i]);
		}

		// Iterating though the lower part of the board
		for (int i = 0; i < combinations.length; i++) {
			Logger.log(choiceIndex++ + ". " + combinationsNames[i]);
		}
	}
	
	
	/**
	 * Store the combination into the correct place (by asking the user where to store it) if it is available
	 * @param dices Rolled dices
	 * @return Whether or not the score has been stored (available choice or not)
	 */
	public boolean store(Dice[] dices) {
		final int UNSET_CHOICE = -1;
		final int MINIMUM_CHOICE_INDEX = 1;
		final int MAXIMUM_CHOICE_INDEX = simples.length + combinations.length;

		// Combinations Identifiers #NoMagicNumbers #<3Kelly<3
		final int COMBINATION_RECTIFIER = simples.length;
		final int INDEX_RECTIFIER = 1;
		final int ACES = 1;
		final int TWOS = 2;
		final int THREES = 3;
		final int FOURS = 4;
		final int FIVES = 5;
		final int SIXES = 6;
		final int THREE_OF_A_KIND = 7;
		final int FULL_HOUSE = 8;
		final int FOUR_OF_A_KIND = 9;
		final int SMALL_STRAIGHT = 10;
		final int LARGE_STRAIGHT = 11;
		final int YAHTZEE = 12;
		final int CHANCE = 13;


		int[] faces = count(dices);  // Count iterations of each face
		int choice = UNSET_CHOICE;

		// Displaying all the available choices
		displayAvailable();

		// Asking where to store the
		choice = IntInput.askInt("Where do you want to store your combination?\n> ", MINIMUM_CHOICE_INDEX, MAXIMUM_CHOICE_INDEX);

		int score = 0;

		// Analyzing user choice (and recording if available)
		switch (choice) {
			/*
				Upper part of the board
			 */
			case ACES:
				return storeSimpleScore(ACES, faces);

			case TWOS:
				return storeSimpleScore(TWOS, faces);

			case THREES:
				return storeSimpleScore(THREES, faces);

			case FOURS:
				return storeSimpleScore(FOURS, faces);

			case FIVES:
				return storeSimpleScore(FIVES, faces);

			case SIXES:
				return storeSimpleScore(SIXES, faces);
				
			
			/*
			Lower part of the board
			 */

			case THREE_OF_A_KIND:
				if (checkThreeOfAKind(faces)) {
					score = getPointsThreeOfAKind(faces);
				}
				return storeCombinationScore(THREE_OF_A_KIND - COMBINATION_RECTIFIER, score);


			case FULL_HOUSE:
				if (checkFullHouse(faces)) {
					score = FULL_HOUSE_POINTS;
				}
				return storeCombinationScore(FULL_HOUSE - COMBINATION_RECTIFIER, score);

			case FOUR_OF_A_KIND:
				if (checkFourOfAKind(faces)) {
					score = getPointsFourOfAKind(faces);
				}
				return storeCombinationScore(FOUR_OF_A_KIND - COMBINATION_RECTIFIER, score);


			case SMALL_STRAIGHT:
				if (checkSmallStraight(faces)) {
					score = SMALL_STRAIGHT_POINTS;
				}
				return storeCombinationScore(SMALL_STRAIGHT - COMBINATION_RECTIFIER, score);


			case LARGE_STRAIGHT:
				if (checkLargeStraight(faces)) {
					score = LARGE_STRAIGHT_POINTS;
				}
				return storeCombinationScore(LARGE_STRAIGHT - COMBINATION_RECTIFIER, score);


			case YAHTZEE:
				if (checkYahtzee(faces)) {
					score = YAHTZEE_POINTS;
				}
				return storeCombinationScore(YAHTZEE - COMBINATION_RECTIFIER, score);


			case CHANCE:
				score = getPointsChance(faces);
				return storeCombinationScore(CHANCE - COMBINATION_RECTIFIER, score);


			default:
				Logger.log("Wow! You tricked our program, take a cookie as a reward for your bugging skills");
				return false;
		}
	}


	/**
	 * Computes and stores a score for the upper part of the board
	 * @param index Face to store (1 for Aces, 2 for Twos, etc.)
	 * @param faces Number of iterations of each face
	 * @return Whether or not the score has been stored
	 */
	private boolean storeSimpleScore(int index, int[] faces) {
		final int INDEX_RECTIFIER = 1;

		if (simples[index - INDEX_RECTIFIER] == AVAILABLE) {
			simples[index - INDEX_RECTIFIER] = index * faces[index - INDEX_RECTIFIER];  // Computing the score
			return true;
		} else {
			Logger.log("This choice is not available");
			return false;
		}
	}


	/**
	 * Stores the score for the lower part of the board
	 * The score is not computed here in order to keep this method simple
	 * @param index Index of the score to store (1 for Three Of A Kind, 2 for Full House, etc.)
	 * @param score Score to store
	 * @return Whether or not the score has been stored
	 */
	private boolean storeCombinationScore(int index, int score) {
		final int INDEX_RECTIFIER = 1;

		if (combinations[index - INDEX_RECTIFIER] == AVAILABLE) {
			combinations[index - INDEX_RECTIFIER] = score;
			return true;
		} else {
			Logger.log("This choice is not available");
			return false;
		}
	}
	
	
	/**
	 * Displays the scoreboard of a player
	 */
	public void displayScore() {
		// Displaying the upper part of the scoreboard
		System.out.println("╔═══════════════════════════════╗");
		System.out.println("║           UPPER PART          ║");
		System.out.println("╠═══════════════════════════════╣");
		for (int i = 0; i < simples.length; i++) {
			System.out.printf("║  %2d. %15s  ║  %3s  ║%n", (i+1), simpleNames[i], simples[i] == AVAILABLE ? "•" : simples[i]);
		}
		System.out.println("╚═══════════════════════════════╝\n");
		
		
		// Displaying the lower part of the scoreboard
		System.out.println("╔═══════════════════════════════╗");
		System.out.println("║           LOWER PART          ║");
		System.out.println("╠═══════════════════════════════╣");
		for (int i = 0; i < simples.length; i++) {
			System.out.printf("║  %2d. %15s  ║  %3s  ║%n", (i+1), combinationsNames[i], combinations[i] == AVAILABLE ? "•" : combinations[i]);
		}
		System.out.println("╚═══════════════════════════════╝\n");
	}
	
	
	/**
	 * Get a single array of scores ("   " if available)
	 * @return A single array of scores ("   " if the section is available)
	 */
	public String[] getScores () {
		String[] scores = new String[combinations.length + simples.length];
		int index = 0;
		
		// Upper part of the board (simples)
		for (int simple : simples) {
			if (simple == AVAILABLE) {
				scores[index++] = "•";
			} else {
				scores[index++] = "" + simple;
			}
		}
		
		
		// Lower part of the board (combinations)
		for (int combination : combinations) {
			if (combination == AVAILABLE) {
				scores[index++] = "•";
			} else {
				scores[index++] = "" + combination;
			}
		}
		
		return scores;
	}
}
