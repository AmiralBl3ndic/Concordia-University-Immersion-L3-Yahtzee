package logger;

class LogLevel {
	private static LoggingLevel loggingLevel = LoggingLevel.DEFAULT;
	
	/**
	 * Getter for the logging level
	 * @return Current logging level - LoggingLevel.(DEBUG | QUIET | DEFAULT | VERBOSE)
	 */
	static LoggingLevel getLoggingLevel() {
		return loggingLevel;
	}
	
	/**
	 * Setter for the logging level
	 * @param level Logging level to set - LoggingLevel.(DEBUG | QUIET | DEFAULT | VERBOSE)
	 */
	static void setLoggingLevel (LoggingLevel level) {
		loggingLevel = level;
	}
}