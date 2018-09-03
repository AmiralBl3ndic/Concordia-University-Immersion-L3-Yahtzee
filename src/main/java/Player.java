
/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018.
 */

package main.java;

import logger.Logger;

import java.util.*;
import java.util.Random;

/**
 * Class that represents a player
 * It can store a player's _name, his total score and his score board
 */
public class Player {
	private static ArrayList<Player> _players = new ArrayList<>();
	private static int _currentPlayerIndex = -1;
	private static Player _currentPlayer = null;
	
	private Scores _scores = new Scores();
	
	private String _name;
	
	
	/**
	 * Default constructor.
	 * <br />
	 * Sets the _name of the player to "Player x" where x is the number of players (at least 1).
	 * <br />
	 * Sets the total score to 0.
	 *  <br /><br />
	 * /!\ Warning: Using this method is discouraged since it produces a random _name which may not be easy to read and recognize for the players.
	 */
	private Player () {
		
		if (checkAvailableName("Player " + getNumberOfPlayers())) {
			_name = "Player " + getNumberOfPlayers();  // Name based upon the current number of players
		} else {
			// Trying to get a random number x that will not be used as a "Player x" for a _name
			Random rd = new Random();
			int randomNumber = rd.nextInt(Integer.MAX_VALUE);
			while (!checkAvailableName("Player " + randomNumber))
				randomNumber = rd.nextInt(Integer.MAX_VALUE);
			
			_name = "Player " + randomNumber;
		}
		
		Logger.logVerboseDebug("Adding player " + _name + " to players list");
		_players.add(this);
		
		if (_currentPlayerIndex == -1) {
			_currentPlayerIndex = 0;
			_currentPlayer = this;
		}
	}
	
	
	/**
	 * Constructor by _name.
	 * <br />
	 * Sets the _name of the player to the passed _name.
	 * @param playerName Name to give to the player
	 */
	private Player (String playerName) {
		Logger.logVerboseDebug("Creating a new instance of Player");
		
		// Check if passed _name is available
		if (!checkAvailableName(playerName)) {  // If _name is not available, generating a random _name of the form "Player x"
			
			Logger.logVerboseDebug("Name", playerName, "not available, generating a random _name instead");
			
			// Trying to get a random number x that will not be used as a "Player x" for a _name
			Random rd = new Random();
			int randomNumber = rd.nextInt(Integer.MAX_VALUE);
			while (!checkAvailableName("Player " + randomNumber))
				randomNumber = rd.nextInt(Integer.MAX_VALUE);
			
			_name = "Player " + randomNumber;
			
			Logger.logVerboseDebug("Name", "Player " + randomNumber, "has been used instead");
		} else {  // If _name is available
			_name = playerName;
			
			Logger.logVerboseDebug("Name", playerName, "will be used as the _name of the new Player instance");
		}
		
		Logger.logVerboseDebug("Adding player " + _name + " to players list");
		_players.add(this);
		
		if (_currentPlayerIndex == -1) {
			_currentPlayerIndex = 0;
			_currentPlayer = this;
		}
	}
	
	
	/**
	 * Checks whether a given _name is available (not already in use by another player).
	 * @param newName Name to check
	 * @return false if the _name is not available, true otherwise
	 */
	public static boolean checkAvailableName (String newName) {
		Logger.logVerboseDebug("Checking for already existing _name (" + newName + ")");
		
		// Exploring the list of all players and checking their names
		for (Player player : _players) {
			if (player.get_name().equals(newName))
				return false;
		}
		
		return true;
	}
	
	
	/**
	 * Gets the _name of the player.
	 * @return Name of the current Player instance
	 */
	public String get_name() {
		return _name;
	}
	
	/**
	 * Ask the player to enter his _name.
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
		return 0;
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
		// Checks the instance type and the _name of the instance if of type Player
		return obj instanceof Player && ((Player) obj).get_name().equals(_name);
	}
	
	
	/**
	 * Deletes a player by resetting its data and removing it from the list of players.
	 * <br />
	 * Use this method as a destructor for the Player class.
	 */
	public void delete () {
		Logger.logVerboseDebug("Deleting player \"" + _name + "\"");
		
		_players.removeIf(player -> player == this);
		
		if (!_players.contains(_currentPlayer)) {
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
			_currentPlayerIndex = (_currentPlayerIndex + 1) % _players.size();
			_currentPlayer = _players.get(_currentPlayerIndex);
		}
	}
	
	
	/**
	 * Deletes all the players
	 */
	public static void clear () {
		if (_players.size() > 0) {
			// To avoid referencing no longer existing objects
			_currentPlayer = null;
			_currentPlayerIndex = -1;
			
			// Actually clearing the `Player` ArrayList
			_players.clear();
		}
	}
	
	
	/**
	 * Get a reference to the current player
	 * @return A reference to the current player
	 */
	public static Player getCurrent () {
		return _currentPlayer;
	}
	
	
	/**
	 * Pseudo-constructor to allow easier use of static class
	 * @return Whether or not the new Player has been registered
	 */
	public static boolean createPlayer () {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Creating a new player\n");
		
		String name;
		
		do {
			System.out.print("Player's name (minimum 3 characters): ");
			name = kb.nextLine();
		} while (name.length() < 3);
		
		return createPlayer(name);
	}
	
	
	public static boolean createPlayer (String name) {
		if (!checkAvailableName(name)) {
			return false;
		}
		
		new Player(name);
		
		return true;
	}
}
