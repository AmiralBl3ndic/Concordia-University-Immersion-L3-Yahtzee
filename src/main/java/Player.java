
/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018.
 */

package main.java;

import logger.Logger;
import inputs.IntInput;

import java.util.*;
import java.util.Random;

/**
 * Class that represents a player
 * It can store a player's name, his total score and his score board
 */
public class Player {
	private static ArrayList<Player> _players = new ArrayList<>();
	private static int currentPlayerIndex = -1;
	private static Player currentPlayer = null;
	
	private Scores _scores = new Scores();
	
	private String name;
	private int totalScore;
	
	
	/**
	 * Default constructor.
	 * <br />
	 * Sets the name of the player to "Player x" where x is the number of players (at least 1).
	 * <br />
	 * Sets the total score to 0.
	 *  <br /><br />
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
		
		totalScore = 0;
		
		Logger.logVerboseDebug("Adding player " + name + " to players list");
		_players.add(this);
		
		if (currentPlayerIndex == -1) {
			currentPlayerIndex = 0;
			currentPlayer = this;
		}
	}
	
	
	/**
	 * Constructor by name.
	 * <br />
	 * Sets the name of the player to the passed name.
	 * @param playerName Name to give to the player
	 */
	public Player (String playerName) {
		totalScore = 0;
		
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
			currentPlayer = this;
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
		// TODO: replace the following line by the method of class Scores computing the total score
		return totalScore;
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
	 * <br />
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
	 * <br />
	 * Use this method as a destructor for the Player class.
	 */
	public void delete () {
		Logger.logVerboseDebug("Deleting player \"" + name + "\"");
		
		_players.removeIf(player -> player == this);
		
		if (!_players.contains(currentPlayer)) {
			nextPlayer();
		}
	}
	
	
	/**
	 * Sets the current player to the next player in the list.
	 * <br />
	 * If the current player is the last player in the list, it will set the first player as the active player.
	 */
	public static void nextPlayer () {
		if (_players.size() > 0) {
			currentPlayerIndex = (currentPlayerIndex + 1) % _players.size();
			currentPlayer = _players.get(currentPlayerIndex);
		}
	}
	
	
	/**
	 * Deletes all the players
	 */
	public static void clear () {
		if (_players.size() > 0) {
			// To avoid referencing no longer existing objects
			currentPlayer = null;
			currentPlayerIndex = -1;
			
			// Actually clearing the `Player` ArrayList
			_players.clear();
		}
	}
}
