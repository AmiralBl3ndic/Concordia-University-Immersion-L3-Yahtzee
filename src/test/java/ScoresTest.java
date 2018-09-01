/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package test.java;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import main.java.Scores;

class ScoresTest {
	
	private Scores scores = new Scores();
	private final int[] notAThreeOfAKind = {2, 1, 1, 1, 0, 0};
	private final int[] threeOfAKind = {3, 1, 1, 0, 0, 0};
	private final int[] fullHouse = {2, 3, 0, 0, 0, 0};
	private final int[] fourOfAKind = {4, 0, 0, 0, 1, 0};
	private final int[] yahtzee = {5, 0, 0, 0, 0, 0};
	private final int[] smallStraight1 = {1, 1, 1, 1, 0, 0};
	private final int[] smallStraight2 = {0, 1, 1, 1, 1, 0};
	private final int[] smallStraight3 = {0, 0, 1, 1, 1, 1};
	private final int[] largeStraight1 = {1, 1, 1, 1, 1, 0};
	private final int[] largeStraight2 = {0, 1, 1, 1, 1, 1};
	private final int[] notEnoughDices = {3, 2, 0, 0};
	
	
	@BeforeEach
	void setup () {
		scores = new Scores();
	}
	
	@AfterEach
	void delete () {
		scores = null;
	}
	
	
	@Test
	void checkThreeOfAKind() {
		assertTrue(scores.checkThreeOfAKind(threeOfAKind), "Should recognize a three of a kind");
		assertFalse(scores.checkThreeOfAKind(notAThreeOfAKind), "Should not recognize a combination that involves no three of a kind");
		assertFalse(scores.checkThreeOfAKind(notEnoughDices), "Should not recognize a combination with not enough dices");
		assertTrue(scores.checkThreeOfAKind(fullHouse), "Should recognize a full house");
		assertTrue(scores.checkThreeOfAKind(fourOfAKind), "Should recognize a four of a kind");
		assertTrue(scores.checkThreeOfAKind(yahtzee), "Should recognize a yahtzee");
		assertFalse(scores.checkThreeOfAKind(smallStraight1), "Should not recognize a small straight #1");
		assertFalse(scores.checkThreeOfAKind(smallStraight2), "Should not recognize a small straight #2");
		assertFalse(scores.checkThreeOfAKind(smallStraight3), "Should not recognize a small straight #3");
		assertFalse(scores.checkThreeOfAKind(largeStraight1), "Should not recognize a large straight #1");
		assertFalse(scores.checkThreeOfAKind(largeStraight2), "Should not recognize a large straight #2");
	}
	
	
	@Test
	void checkFourOfAKind() {
		assertFalse(scores.checkFourOfAKind(notAThreeOfAKind), "Should not recognize any combination that is not at least a four of a kind");
		assertFalse(scores.checkFourOfAKind(threeOfAKind), "Should not recognize a three of a kind");
		assertFalse(scores.checkFourOfAKind(notEnoughDices), "Should not recognize a combination with not enough dices");
		assertFalse(scores.checkFourOfAKind(fullHouse), "Should not recognize a full house");
		assertTrue(scores.checkFourOfAKind(fourOfAKind), "Should recognize a four of a kind");
		assertTrue(scores.checkFourOfAKind(yahtzee), "Should recognize a yahtzee");
		assertFalse(scores.checkFourOfAKind(smallStraight1), "Should not recognize a small straight #1");
		assertFalse(scores.checkFourOfAKind(smallStraight2), "Should not recognize a small straight #2");
		assertFalse(scores.checkFourOfAKind(smallStraight3), "Should not recognize a small straight #3");
		assertFalse(scores.checkFourOfAKind(largeStraight1), "Should not recognize a large straight #1");
		assertFalse(scores.checkFourOfAKind(largeStraight2), "Should not recognize a large straight #2");
	}
	
	
	@Test
	void checkFullHouse() {
		assertFalse(scores.checkFullHouse(notAThreeOfAKind), "Should not recognize any combination that is not a full house");
		assertFalse(scores.checkFullHouse(threeOfAKind), "Should not recognize a three of a kind");
		assertFalse(scores.checkFullHouse(notEnoughDices), "Should not recognize a combination with not enough dices");
		assertTrue(scores.checkFullHouse(fullHouse), "Should recognize a full house");
		assertFalse(scores.checkFullHouse(fourOfAKind), "Should not recognize a four of a kind");
		assertFalse(scores.checkFullHouse(yahtzee), "Should not recognize a yahtzee");
		assertFalse(scores.checkFullHouse(smallStraight1), "Should not recognize a small straight #1");
		assertFalse(scores.checkFullHouse(smallStraight2), "Should not recognize a small straight #2");
		assertFalse(scores.checkFullHouse(smallStraight3), "Should not recognize a small straight #3");
		assertFalse(scores.checkFullHouse(largeStraight1), "Should not recognize a large straight #1");
		assertFalse(scores.checkFullHouse(largeStraight2), "Should not recognize a large straight #2");
	}
	
	
	@Test
	void checkSmallStraight() {
		assertFalse(scores.checkSmallStraight(notAThreeOfAKind), "Should not recognize a random combination of faces");
		assertFalse(scores.checkSmallStraight(threeOfAKind), "Should not recognize a three of a kind");
		assertFalse(scores.checkSmallStraight(fourOfAKind), "Should not recognize a four of a kind");
		assertFalse(scores.checkSmallStraight(yahtzee), "Should not recognize a yahtzee");
		assertFalse(scores.checkSmallStraight(notEnoughDices), "Should not recognize a combination with not enough dices");
		assertTrue(scores.checkSmallStraight(smallStraight1), "Should recognize a small straight #1");
		assertTrue(scores.checkSmallStraight(smallStraight2), "Should recognize a small straight #2");
		assertTrue(scores.checkSmallStraight(smallStraight3), "Should recognize a small straight #3");
		assertTrue(scores.checkSmallStraight(largeStraight1), "Should recognize a large straight #1");
		assertTrue(scores.checkSmallStraight(largeStraight2), "Should recognize a large straight #2");
	}
	
	@Test
	void checkLargeStraight() {
		assertFalse(scores.checkLargeStraight(notAThreeOfAKind), "Should not recognize a random combination of faces");
		assertFalse(scores.checkLargeStraight(threeOfAKind), "Should not recognize a three of a kind");
		assertFalse(scores.checkLargeStraight(fourOfAKind), "Should not recognize a four of a kind");
		assertFalse(scores.checkLargeStraight(yahtzee), "Should not recognize a yahtzee");
		assertFalse(scores.checkLargeStraight(notEnoughDices), "Should not recognize a combination with not enough dices");
		assertFalse(scores.checkLargeStraight(smallStraight1), "Should not recognize a small straight #1");
		assertFalse(scores.checkLargeStraight(smallStraight2), "Should not recognize a small straight #2");
		assertFalse(scores.checkLargeStraight(smallStraight3), "Should not recognize a small straight #3");
		assertTrue(scores.checkLargeStraight(largeStraight1), "Should recognize a large straight #1");
		assertTrue(scores.checkLargeStraight(largeStraight2), "Should recognize a large straight #2");
	}
	
	@Test
	void checkYahtzee() {
		assertTrue(scores.checkYahtzee(yahtzee), "Should recognize a yahtzee");
		assertFalse(scores.checkYahtzee(notEnoughDices), "Should not recognize any combination with not enough dices");
		assertFalse(scores.checkYahtzee(notAThreeOfAKind), "Should not recognize a random combination of numbers");
		assertFalse(scores.checkYahtzee(threeOfAKind), "Should not recognize a three of a kind");
		assertFalse(scores.checkYahtzee(fullHouse), "Should not recognize a full house");
		assertFalse(scores.checkYahtzee(fourOfAKind), "Should not recognize a four of a kind");
		assertFalse(scores.checkYahtzee(smallStraight1), "Should not recognize a small straight #1");
		assertFalse(scores.checkYahtzee(smallStraight2), "Should not recognize a small straight #2");
		assertFalse(scores.checkYahtzee(smallStraight3), "Should not recognize a small straight #3");
		assertFalse(scores.checkYahtzee(largeStraight1), "Should not recognize a large straight #1");
		assertFalse(scores.checkYahtzee(largeStraight2), "Should not recognize a large straight #2");
	}
	
	@Ignore
	void count() {
	}
}