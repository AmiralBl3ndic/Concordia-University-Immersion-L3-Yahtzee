/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package inputs;

import java.util.Scanner;

public class IntInput {
	/**
	 * Asks the user to type an integer using his keyboard
	 * @param message Message to display (advertise) the user of the awaited action
	 * @return The value typed by the user
	 */
	public static int askInt (String message) {
		int value = 0;
		boolean ok = false;
		Scanner sc = new Scanner(System.in);
		
		do {
			try {
				System.out.print(message);
				value = sc.nextInt();
				ok = true;
			} catch (Exception ignored) {
				System.out.println("Awaiting an integer, please try again...");
				sc.nextLine();  // Escaping the '\n'
				ok = false;
			}
		} while (!ok);
		
		return value;
	}
	
	
	/**
	 * Asks the user to type an integer using his keyboard
	 * @param message Message to display (advertise) the user of the awaited action
	 * @param min Minimum allowed value (inclusive)
	 * @param max Maximum allowed value (inclusive)
	 * @return
	 */
	public static int askInt (String message, int min, int max) {
		int value = min - 1;
		
		while (value < min || value > max) {
			value = askInt(message);
			
			if (value < min) {
				System.out.printf("The value you entered is too small, awaiting for at least %d...\n", min);
			} else if (value > max) {
				System.out.printf("The value you entered is too big, awaiting for at most %d...\n", max);
			}
		}
		
		return value;
	}
	
	
	
	/**
	 * Asks the user to type an integer using his keyboard, and awaiting for a minimum value
	 * @param message Message to display (advertise) the user of the awaited action
	 * @param min Minimum allowed value (inclusive)
	 * @return The value typed by the user
	 */
	public static int askIntMin (String message, int min) {
		int value = min - 1;
		
		do {
			value = askInt(message);
			
			if (value < min) {
				System.out.printf("The value you entered is too small, awaiting for at least %d...\n", min);
			}
		} while (value < min);
		
		return value;
	}
	
	
	/**
	 * Asks the user to type an integer using his keyboard, and awaiting for a maximum value
	 * @param message Message to display (advertise) the user of the awaited action
	 * @param max Maximum allowed value (inclusive)
	 * @return The value typed by the user
	 */
	public static int askIntMax (String message, int max) {
		int value = max + 1;
		
		do {
			value = askInt(message);
			
			if (value > max) {
				System.out.printf("The value you entered is too big, awaiting for at most %d...\n", max);
			}
		} while (value > max);
		
		return value;
	}
}
