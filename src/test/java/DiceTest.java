package test.java;

import main.java.Dice;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
	
	@org.junit.jupiter.api.Test
	void roll() {
		// Since this method involves random numbers, we run it a sufficient number of times to ensure it works
		for (int i = 0; i < 500; i++) {
			int val = Dice.roll();
			
			assertTrue(val <= 6, "should be smaller or equal to 6");
			assertTrue(val >= 1, "should be greater or equal to 1");
		}
	}
}