
/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018.
 */

package main.java;

import inputs.IntInput;
import logger.Logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that represents a player
 * It can store a player's name, his total score and his score board
 */
public class Player {
	private static final int UNSET_PLAYER_INDEX = -1;
	
	private static ArrayList<Player> _players = new ArrayList<>();
	private static int currentPlayerIndex = UNSET_PLAYER_INDEX;
	
	private Scores _scores = new Scores();
	
	private String name;
	
	private Dice[] dices =  {
			new Dice(),
			new Dice(),
			new Dice(),
			new Dice(),
			new Dice()
	};
	
	
	/**
	 * Default constructor.
	 * Sets the name of the player to "Player x" where x is the number of players (at least 1).
	 * Sets the total score to 0.
	 * /!\ Warning: Using this method is discouraged since it produces a random name which may not be easy to read and recognize for the players.
	 */
	public Player () {
		
		if (checkAvailableName("Player " + getNumberOfPlayers())) {
			name = "Player " + getNumberOfPlayers();  // Name based upon the current number of players
		} else {
			// Trying to get a random number x that will not be used as a "Player x" for a name
			Random rd = new Random();
			int randomNumber = rd.nextInt(Integer.MAX_VALUE);
			while (!checkAvailableName("Player " + randomNumber))
				randomNumber = rd.nextInt(Integer.MAX_VALUE);
			
			name = "Player " + randomNumber;
		}
		
		Logger.logVerboseDebug("Adding player " + name + " to players list");
		_players.add(this);
		
		if (currentPlayerIndex == -1) {
			currentPlayerIndex = 0;
		}
	}
	
	
	/**
	 * Constructor by name.
	 * Sets the name of the player to the passed name.
	 * @param playerName Name to give to the player
	 */
	public Player (String playerName) {
		
		Logger.logVerboseDebug("Creating a new instance of Player");
		
		// Check if passed name is available
		if (!checkAvailableName(playerName)) {  // If name is not available, generating a random name of the form "Player x"
			
			Logger.logVerboseDebug("Name", playerName, "not available, generating a random name instead");
			
			// Trying to get a random number x that will not be used as a "Player x" for a name
			Random rd = new Random();
			int randomNumber = rd.nextInt(Integer.MAX_VALUE);
			while (!checkAvailableName("Player " + randomNumber))
				randomNumber = rd.nextInt(Integer.MAX_VALUE);
			
			name = "Player " + randomNumber;
			
			Logger.logVerboseDebug("Name", "Player " + randomNumber, "has been used instead");
		} else {  // If name is available
			name = playerName;
			
			Logger.logVerboseDebug("Name", playerName, "will be used as the name of the new Player instance");
		}
		
		Logger.logVerboseDebug("Adding player " + name + " to players list");
		_players.add(this);
		
		if (currentPlayerIndex == -1) {
			currentPlayerIndex = 0;
		}
	}
	
	
	/**
	 * Checks whether a given name is available (not already in use by another player).
	 * @param newName Name to check
	 * @return false if the name is not available, true otherwise
	 */
	public static boolean checkAvailableName (String newName) {
		Logger.logVerboseDebug("Checking for already existing name (" + newName + ")");
		
		// Exploring the list of all players and checking their names
		for (Player player : _players) {
			if (player.getName().equals(newName))
				return false;
		}
		
		return true;
	}
	
	
	/**
	 * Gets the name of the player.
	 * @return Name of the current Player instance
	 */
	public String getName () {
		return name;
	}
	
	
	/**
	 * Ask the player to enter his name.
	 * @return Name of the player
	 */
	public String askName () {
		Scanner scanner = new Scanner(System.in);
			
		String name; 
		
		System.out.print("Please enter your name : ");
		name = scanner.nextLine();
		return name;
	}
	
	
	/**
	 * Gets the total score of the player.
	 * @return Total score of the current <code>Player</code> instance
	 */
	public int getTotalScore () {
		return _scores.total();
	}
	
	
	/**
	 * Gets the number of players.
	 * @return The number of players currently playing the game
	 */
	public static int getNumberOfPlayers () {
		return _players.size();
	}
	
	
	/**
	 * Determines if two players are equals.
	 * This method only compares players by their names.
	 * @param obj Player instance to compare
	 * @return Whether or not the players are equal
	 */
	@Override
	public boolean equals (Object obj) {
		// Checks the instance type and the name of the instance if of type Player
		return obj instanceof Player && ((Player) obj).getName().equals(name);
	}
	
	
	/**
	 * Deletes a player by resetting its data and removing it from the list of players.
	 * Use this method as a destructor for the Player class.
	 */
	public void delete () {
		Logger.logVerboseDebug("Deleting player \"" + name + "\"");
		
		_players.removeIf(player -> player == this);
		// TODO: handle the Scores property
	}
	
	
	/**
	 * Rolls all the dices that are not in a locked state
	 */
	public void rollDices () {
		for (Dice dice : dices) {
			dice.roll();
		}
	}
	
	
	/**
	 * Asks the user for the dices he wants to lock
	 */
	public void selectDices() {
		// We will subtract 1 to obtain the actual index of the dice
		final int DICE_1 = 1;
		final int DICE_2 = 2;
		final int DICE_3 = 3;
		final int DICE_4 = 4;
		final int DICE_5 = 5;
		
		boolean lockAnotherDice = true;
		final int LOCK_ANOTHER = 1;
		final int SKIP = 0;
		
		ArrayList<Integer> userDicesChoice;
		

		while (lockAnotherDice) {
			displayDices();
			
			userDicesChoice = IntInput.askMultiple("Choose one dice you want to lock (or unlock) (blank for none): ");
			
			for (int choice : userDicesChoice) {
				switch (choice) {
					case DICE_1:
						dices[DICE_1 - 1].toggleLock();
						break;
					
					
					case DICE_2:
						dices[DICE_2 - 1].toggleLock();
						break;
					
					
					case DICE_3:
						dices[DICE_3 - 1].toggleLock();
						break;
					
					
					case DICE_4:
						dices[DICE_4 - 1].toggleLock();
						break;
					
					
					case DICE_5:
						dices[DICE_5 - 1].toggleLock();
						break;
					
					default:
						Logger.log(choice, "is not a valid dice id");
				}
			}
			
			displayDices();
			
			// Asking if need to lock another dice
			lockAnotherDice = IntInput.askInt("Lock another dice? (" + SKIP + " = No, " + LOCK_ANOTHER + " = Yes)\n> ", SKIP, LOCK_ANOTHER) == LOCK_ANOTHER;
		}
	}
	
	
	/**
	 * Resets all the dices in an unlocked state
	 */
	public void resetDices () {
		for (Dice dice : dices) {
			dice.unlock();
		}
	}
	
	
	/**
	 * Checks whether or not the user has at least one locked dice
	 * @return Whether or not the user has at least one locked dice
	 */
	private boolean hasLockedDice () {
		for (Dice dice : dices) {
			if (dice.isLocked()) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Displays the (un)locked dices
	 * Advertises the user that these are the (un)locked dices by displaying a short message before display
	 *
	 * @param locked Dices to display (true = locked dices, false = unlocked dices)
	 */
	private void displayDices (boolean locked) {
		final int START_DISPLAY_INDEX = 1;
		int index = START_DISPLAY_INDEX;
		
		if (locked) {
			if (hasLockedDice()) {
				Logger.log("Locked dices:\n");
				
				for (Dice dice : dices) {
					if (dice.isLocked()) {
						System.out.print("┌---┐  ");
					}
				}
				
				System.out.print("\n");
				
				for (Dice dice : dices) {
					if (dice.isLocked()) {
						System.out.printf("| %d |  ", dice.getValue());
					}
				}
				
				System.out.print("\n");
				
				for (Dice dice : dices) {
					if (dice.isLocked()) {
						System.out.print("└---┘  ");
					}
				}
				
				System.out.print("\n");
				
				for (int i = 0; i < dices.length; i++) {
					if (dices[i].isLocked()) {
						System.out.printf(" [%d]   ", (i + 1));
					}
				}
				
				System.out.println("\n");
			}
		} else {
			Logger.log("Unlocked dices:\n");
			
			for (Dice dice : dices) {
				if (!dice.isLocked()) {
					System.out.print("┌---┐  ");
				}
			}
			
			System.out.print("\n");
			
			for (Dice dice : dices) {
				if (!dice.isLocked()) {
					System.out.printf("| %d |  ", dice.getValue());
				}
			}
			
			System.out.print("\n");
			
			for (Dice dice : dices) {
				if (!dice.isLocked()) {
					System.out.print("└---┘  ");
				}
			}
			
			System.out.print("\n");
			
			for (int i = 0; i < dices.length; i++) {
				if (!dices[i].isLocked()) {
					System.out.printf(" [%d]   ", (i + 1));
				}
			}
			
			System.out.println("\n");
		}
	}
	
	
	/**
	 * Displays the locked and unlocked dices (being precise about their (un)locked state)
	 */
	public void displayDices () {
		displayDices(false);
		Logger.log("-----------------");
		displayDices(true);
	}

	/**
	 * Display all the dices locked and unlocked on the same line
	 */
	public void displayAllDices(){

		for (Dice dice : dices) {
				System.out.print("┌---┐  ");
		}

		System.out.print("\n");

		for (Dice dice : dices) {
				System.out.printf("| %d |  ", dice.getValue());
		}

		System.out.print("\n");

		for (Dice dice : dices) {
				System.out.print("└---┘  ");
		}

		System.out.print("\n");

		for (int i = 0; i < dices.length; i++) {
				System.out.printf(" [%d]   ", (i + 1));
		}

		System.out.println("\n");
	}


	
	
	/**
	 * Sets the current player to the next player in the list.
	 * If the current player is the last player in the list, it will set the first player as the active player.
	 */
	public static void nextPlayer () {
		currentPlayerIndex = (currentPlayerIndex + 1) % _players.size();
	}
	
	
	/**
	 * Adds players one by one
	 * @return Number of registered players
	 */
	public static ArrayList<Player> addPlayers () {
		final int UNSET_STATUS = -1;
		final int STOP_CREATING_PLAYERS = 0;
		final int CREATE_A_NEW_PLAYER = 1;
		final int MINIMUM_NUMBERS_OF_CHARACTERS = 3;
		
		
		Scanner kb = new Scanner(System.in);
		int status = UNSET_STATUS;
		
		// Looping until player decides not to create anymore player(s)
		do {
			System.out.println("Creating a new player\n");
			
			String newPlayerName;
			
			// Looping while the typed name does is not at least 3 characters long
			do {
				System.out.printf("Player's name (minimum %d characters): ", MINIMUM_NUMBERS_OF_CHARACTERS);
				newPlayerName = kb.nextLine();
				
				if (newPlayerName.length() < MINIMUM_NUMBERS_OF_CHARACTERS) {
					Logger.log("We just said the minimum number of characters is", MINIMUM_NUMBERS_OF_CHARACTERS + ". Weren't you listening?");
				}
			} while (newPlayerName.length() < MINIMUM_NUMBERS_OF_CHARACTERS || !checkAvailableName(newPlayerName));
			
			// Actually creating a new player
			Player newPlayer = new Player(newPlayerName);
			
			// Asking if need to create another player
			status = IntInput.askInt("Create another player? (" + STOP_CREATING_PLAYERS + " = No, " + CREATE_A_NEW_PLAYER + " = Yes)\n> ", STOP_CREATING_PLAYERS, CREATE_A_NEW_PLAYER);
			
		} while (status != STOP_CREATING_PLAYERS);
		
		
		// Returning the array of players (converted from ArrayList)
		return _players;
	}
	
	
	/**
	 * Make the instance play its turn (roll dices up to 3 times and select where to store the result)
	 */
	public void play () {
		final int MAXIMUM_ROLLS = 3;
		final int STOP_ROLL = 0;
		final int ROLL_AGAIN = 1;
		
		// Allowing only
		for (int rolls = 1; rolls <= MAXIMUM_ROLLS; rolls++) {
			Logger.log("Dice roll #" + rolls);
			
			rollDices();

			if(rolls < MAXIMUM_ROLLS){
				selectDices();
			}

			
			// Asking if need to re-roll the unlocked dices
			if (rolls < MAXIMUM_ROLLS){
				boolean reRoll = IntInput.askInt("Roll the unlocked dices again? (" + STOP_ROLL + " = No, " + ROLL_AGAIN + " = Yes)\n> ", STOP_ROLL, ROLL_AGAIN) == STOP_ROLL;
				if (reRoll)
					break;
			}
			else {
				System.out.println("Your don't have more roll, you have to choose where you want to put your score");
				break;
			}


		}
		Logger.log("Your dices : ");
		displayAllDices();

		//Asking where to store until actually stored
		while (!_scores.store(dices));

		Logger.log("Your score for now : ");
		_scores.displayScore();


	}
	
	/**
	 * Compute the total score of the instance
	 * @return Total score of the instance
	 */
	public int totalScore(){
		return _scores.total();
	}
}
