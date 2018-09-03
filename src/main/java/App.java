package main.java;

import logger.Logger;
import inputs.IntInput;

import java.util.Scanner;

public class App {
	public static void main(final String[] args) {
		playConsole();
	}
	
	
	public static void startMenu() {
		final int CHOICE_UNSET = -1;
		final int QUIT = 0, PLAY = 1, RULES = 2;
		
		Scanner sc = new Scanner(System.in);
		
		int user_choices = CHOICE_UNSET;
		
		System.out.println("------------------------------------");
		System.out.println("--------------- YAHTZEE ------------");
		System.out.println("------------------------------------");
		
		
		// Looping through user choices so that he can read the rules before playing
		do {
			
			user_choices = IntInput.askInt(
				"Select the action you would like to do:\n" +
				"\t0 - Quit\n" +
				"\t1 - Play\n" +
				"\t2 -Check Yahtzee rules\n> ",
				QUIT, RULES);
			
			switch (user_choices) {
				case PLAY:
					Logger.log("This section has not been implemented yet");
					break;
					
					
				case RULES:
					diplayRules();
					break;
					
					
				case QUIT:
					Logger.log("Goodbye!");
					break;
					
					
				default:
					Logger.log("Wow! You tricked the program! You are not supposed to read this since this line is theoretically unreachable");
			}
		} while (user_choices != QUIT);
	}

	
	static void diplayRules () {
		System.out.println("\n\n========== Yahtzee Rules ==========\n");
		System.out.println("Yahtzee can be played in solitary or by a group. The game consists of 13 rounds. \n" +
				"In each round, you roll the dices and then score the roll in one of 13 categories. \n" +
				"You must score once in each category - which means that towards the end of the game you may have to settle for scoring zero in some categories. \n" +
				"The score is determined by a different rule for each category. \n" +
				"The object of the game is to maximize your total score (of course :-)\n");

		System.out.println("\n========== Scoreboard ==========\n");
		
		System.out.println("------ Upper board ------");
		System.out.println("Aces: Sum the number of aces");
		System.out.println("Twos: Sum the number of twos, then multiply by 2");
		System.out.println("Threes: Sum the number of threes, then multiply by 3");
		System.out.println("Fours: Sum the number of fours, then multiply by 4");
		System.out.println("Fives: Sum the number of fives, then multiply by 5");
		System.out.println("Sixes: Sum the number of sixes, then multiply by 6");
		System.out.println("If the sum of the scores of the upper board is greater than or equal to 63, then you get to score 37 bonus points\n");
		
		System.out.println("------ Lower board ------");
		System.out.println("Three Of A Kind: 3 dices are identical - Addition of the 5 dices");
		System.out.println("Four Of A Kind: 4 dices are identical - Addition of the 5 dices");
		System.out.println("Full House: 3 dices are identical + 2 dice are identical - 20 points");
		System.out.println("Small straight: Four dices whose numbers follow each other  - 30 points");
		System.out.println("Large straight: The five dices follow each other - 40 points");
		System.out.println("Yahtzee: All 5 dices are identical - 50 points");
		System.out.println("Chance: no condition, sum all 5 dices\n");
		
		System.out.println("\n\n");
	}
	
	
	/**
	 * Method that is called when running the program in a console / terminal
	 */
	private static void playConsole () {
		startMenu();
		
		// TODO: implement game / assemble bricks here
	}
}
