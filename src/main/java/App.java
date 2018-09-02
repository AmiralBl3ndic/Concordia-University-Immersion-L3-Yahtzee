package main.java;

import logger.Logger;

import java.util.Scanner;

public class App {
	public static void main(final String[] args) {
		displayStartMessage();
	}
	
	
	public static void displayStartMessage() {
		final int CHOICE_UNSET = -1;
		final int QUIT = 0, PLAY = 1, RULES = 2;
		
		Scanner sc = new Scanner(System.in);
		
		
		
		int user_choices = CHOICE_UNSET;
		
		while (user_choices != QUIT && user_choices != PLAY && user_choices != RULES) {
			try {
				System.out.println("------------------------------------");
				System.out.println("--------------- YAHTZEE ------------");
				System.out.println("------------------------------------");
				
				System.out.println("Select the action you would like to do :");
				System.out.println("0- Quit");
				System.out.println("1- Play");
				System.out.println("2- Check Yathzee rules");
				
				user_choices = sc.nextInt();
			} catch (Exception ignored) {
				System.out.println("Choice not recognized, please try again");
				
				sc.nextLine();  // To avoid being stuck in an infinite loop
			}
		}
		
		
		
		do {
			switch (user_choices) {
				case 1:
					System.out.println("Select the action you would like to do :");
					System.out.println("0- Quit");
					System.out.println("1- Play");
					System.out.println("2- Check Yathzee rules");
					user_choices = sc.nextInt();
					break;
					
					
				case 2:
					diplayRules();
					
					System.out.println("Select the action you would like to do :");
					System.out.println("0- Quit");
					System.out.println("1- Play");
					System.out.println("2- Check Yathzee rules");
					user_choices = sc.nextInt();
					break;
			}
		} while (user_choices != 0);
	}

	static void diplayRules(){
		System.out.println("Yahtzee can be played in solitary or by a group. The game consists of 13 rounds. \n" +
				"In each round, you roll the dice and then score the roll in one of 13 categories. \n" +
				"You must score once in each category -- which means that towards the end of the game you may have to settle for scoring zero in some categories. \n" +
				"The score is determined by a different rule for each category. \n" +
				"The object of the game is to maximize your total score (of course :-)\n");

		System.out.println("Combination : It's working for every side of the die - you multiply the number of the side by the number of every dice which have this value");
		System.out.println("Brelan : 3 dice are identical - Addition of the 5 dice");
		System.out.println("Square : 4 dice are identical - Addition of the 5 dice");
		System.out.println("Full : 3 dice are identical + 2 dice are identical - 20 pts");
		System.out.println("Small serie : Four dice whose numbers follow each other  - 30 pts");
		System.out.println("Large serie : The five dice follow each other - 40 pts");
		System.out.println("Yahtzee : All dice are identical - 50 pts");
		System.out.println("Luck : no condition\n");
	}
}
