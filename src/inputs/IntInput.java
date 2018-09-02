/*
 * Copyright (c)
 * Camille BRIAND <camille.briand@efrei.net>
 * 2018
 */

package inputs;

import java.util.Scanner;

public class IntInput {
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
}
