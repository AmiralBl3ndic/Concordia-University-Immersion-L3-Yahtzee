package main.java;

import logger.Logger;

public class App {
	public static void main (final String[] args) {
		displayStartMessage();
	}
	
	
	public static void displayStartMessage () {
		Logger.logVerboseDebug("Displaying the start message");
		
		Logger.log("Welcome to the Yahtzee program");
		Logger.log("The rules can easily be found online but you can find a copy in the 'rules.txt' file");
		Logger.log("Press the ENTER key when you are ready to play");
	}
}
