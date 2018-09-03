/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018.
 */

package test.java;

import logger.Logger;
import main.java.Player;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
	
	// Enough players to test all possible cases
	private Player player1 = null;
	private Player player2 = null;
	private Player player3 = null;
	
	
	@BeforeEach
	void init() {
		// Logger.setVerboseDebug();
		
		Logger.logVerboseDebug("======= Begin BeforeEach ======");
		
		// Creating new players using the different constructors
		player1 = new Player("Player 1");
		player2 = new Player("John Doe");
		player3 = new Player("John Doe");
		
		Logger.logVerboseDebug("====== End BeforeEach ======");
	}
	
	@AfterEach
	void clean () {
		Logger.logVerboseDebug("====== Begin AfterEach ======");
		
		// Destroy existing players (if any) to avoid having too much recorded players
		if (player1 != null) {
			Logger.logVerboseDebug("Deleting player1");
			player1.delete();
			player1 = null;
		}
		
		if (player2 != null) {
			Logger.logVerboseDebug("Deleting player2");
			player2.delete();
			player2 = null;
		}
		
		if (player3 != null) {
			Logger.logVerboseDebug("Deleting player3");
			player3.delete();
			player3 = null;
		}
		
		Logger.logVerboseDebug("====== End AfterEach ======");
	}
	
	
	@Test
	void getName() {
		assertEquals("Player 1", player1.getName());  // Should generate the player name "Player 1 by default"
		assertEquals("John Doe", player2.getName());  // Should handle not taken name
		assertNotEquals("John Doe", player3.getName());  // Should handle taken name
	}
	
	@Ignore
	void getTotalScore() {
		// TODO: implement this test by adding some points to the players
	}
	
	@Test
	void getNumberOfPlayers() {
		assertEquals(3, Player.getNumberOfPlayers());
	}
	
	@Test
	void equals() {
		assertEquals(player1, player1, "player1 should be equal to player1");
		assertNotEquals(player2, player1, "player2 should not be equal to player1");
	}
	
	@Test
	void delete() {
		int prevNumberOfPlayers = Player.getNumberOfPlayers();
		
		// Ensuring the player exists
		assertFalse(Player.checkAvailableName(player1.getName()));
		
		// Deleting the player and performing the necessary tests
		player1.delete();
		assertTrue(Player.checkAvailableName(player1.getName()));
		assertEquals(prevNumberOfPlayers - 1, Player.getNumberOfPlayers());
	}
	
	@Test
	void checkAvailableName() {
		assertFalse(Player.checkAvailableName("John Doe"), "'John Doe' should not be available as a name (since there is already an existing player with that name)");
	}
	
	
	@Test
	void clear () {
		if (Player.getNumberOfPlayers() <= 0)
			fail("Unable to test the clear() method with no players stored");
		
		// Actually clearing the players
		Player.clear();
		
		assertEquals(Player.getNumberOfPlayers(), 0, "The number of players should be 0");
	}
}