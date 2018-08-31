package logger;


/**
 * Basic logger that allows to set a logging level
 * Useful for debugging or developing tools that needs multiple levels of logging (Default, Verbose, etc.)
 */
public class Logger {
	/**
	 * Determines whether or not the current logging level allows to display the message
	 * @return Whether or not the current logging level allows to display the message
	 */
	private static boolean canLogDefault () {
		LoggingLevel level = LogLevel.getLoggingLevel();
		return (level == LoggingLevel.VERBOSE || level == LoggingLevel.DEFAULT || level == LoggingLevel.DEBUG || level == LoggingLevel.VERBOSE_DEBUG);
	}
	
	/**
	 * Determines whether or not the current logging level allows to display the message
	 * @return Whether or not the current logging level allows to display the message
	 */
	private static boolean canLogVerbose () {
		LoggingLevel level = LogLevel.getLoggingLevel();
		return (level == LoggingLevel.VERBOSE || level == LoggingLevel.VERBOSE_DEBUG);
	}
	
	/**
	 * Determines whether or not the current logging level allows to display the message
	 * @return Whether or not the current logging level allows to display the message
	 */
	private static boolean canLogDebug () {
		LoggingLevel level = LogLevel.getLoggingLevel();
		return (level == LoggingLevel.VERBOSE_DEBUG || level == LoggingLevel.DEBUG);
	}
	
	/**
	 * Logs a message, not regarding the logging level
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 */
	private static void _log (Object... objects) {
		for (Object obj : objects)
			System.out.print(obj + " ");
		System.out.print("\n");
	}
	
	/**
	 * Logs a message
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 */
	public static void log (Object... objects) {
		if (canLogDefault())
			_log(objects);
	}
	
	/**
	 * Logs a message when
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 * @see Logger
	 *
	 */
	public static void logDefault (Object... objects) {
		log(objects);
	}
	
	/**
	 * Sets the logging level to DEBUG
	 * @see LoggingLevel
	 */
	public static void setDebug () { LogLevel.setLoggingLevel(LoggingLevel.DEBUG); }
	
	/**
	 * Sets the logging level to VERBOSE
	 * @see LoggingLevel
	 */
	public static void setVerbose () { LogLevel.setLoggingLevel(LoggingLevel.VERBOSE); }
	
	/**
	 * Sets the logging level to QUIET
	 * @see LoggingLevel
	 */
	public static void setQuiet () { LogLevel.setLoggingLevel(LoggingLevel.QUIET); }
	
	/**
	 * Sets the logging level to VERBOSE_DEBUG
	 * @see LoggingLevel
	 */
	public static void setVerboseDebug () { LogLevel.setLoggingLevel(LoggingLevel.VERBOSE_DEBUG); }
	
	/**
	 * Sets the logging level to DEFAULT
	 * @see LoggingLevel
	 */
	public static void setDefault () { LogLevel.setLoggingLevel(LoggingLevel.DEFAULT); }
	
	
	/**
	 * Logs a message only when logging level is set to VERBOSE
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 * @see LoggingLevel
	 */
	public static void logVerbose (Object... objects) {
		if (canLogVerbose())
			_log( objects);
	}
	
	/**
	 * Logs a message whatever the logging level is
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 * @see LoggingLevel
	 */
	public static void logQuiet (Object... objects) {
		_log(objects);
	}
	
	/**
	 * Logs a message only when logging level is set to DEBUG, DEFAULT, QUIET
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 * @see LoggingLevel
	 */
	public static void logDebug (Object... objects) {
		if (canLogDebug()){
			System.out.print("[Debug] -  ");
			_log(objects);
		}
	}
	
	/**
	 * Logs a message only when logging level is set to VERBOSE_DEBUG
	 * @param objects Messages and/or objects to be logged, they will be logged together, separated by spaces and no line breaks
	 */
	public static void logVerboseDebug (Object... objects) {
		if (LogLevel.getLoggingLevel() == LoggingLevel.VERBOSE_DEBUG) {
			System.out.print("[Debug] -  ");
			_log(objects);
		}
	}
}
