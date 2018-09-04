/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package inputs;

import java.util.ArrayList;
import java.util.Scanner;

public final class IntInput {
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
	 * @return the value enter by the user
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
	
	
	/**
	 * Asks the user to type one or more integer(s) using his keyboard
	 * @param message Message to display (advertise) the user of the awaited action
	 * @return The list of all the integers typed by the user
	 */
	public static ArrayList<Integer> askMultiple (String message) {
		ArrayList<Integer> values = new ArrayList<>();
		boolean ok = false;
		Scanner kb = new Scanner(System.in);
		
		do {
			try {
				System.out.print(message);
				
				String line = kb.nextLine();
				
				String[] lineElements = line.split(" ");
				
				for (String element : lineElements) {
					try {
						int val = Integer.parseInt(element);
						values.add(val);
					} catch (Exception ignored) {}
				}
				
				ok = true;
			} catch (Exception ignored) {
				values.clear();  // Clear all the stored values before asking them again (just to be sure)
				kb.nextLine();  // Avoid being stuck in an infinite loop
				ok = false;  // Not needed but clearer
				
				System.out.println("Sorry, the values you entered were not all recognized, please try again...");
			}
		} while (!ok);
		
		return values;
	}
	
	
	
}
