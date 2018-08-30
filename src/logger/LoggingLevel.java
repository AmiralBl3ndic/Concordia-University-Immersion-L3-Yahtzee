package logger;

/**
 * Logging levels
 * Available logging levels:
 * - VERBOSE [VERBOSE, DEFAULT, QUIET]
 * - DEFAULT [DEFAULT, QUIET]
 * - QUIET [QUIET]
 * - DEBUG [DEBUG, DEFAULT, QUIET]
 * - VERBOSE_DEBUG [VERBOSE_DEBUG, VERBOSE, DEFAULT, QUIET, DEBUG]
 */
enum LoggingLevel {
	/** The most verbose
	 * Messages levels that will be displayed are: QUIET, DEFAULT, VERBOSE
	 */
	VERBOSE,
	
	/** Default logging level
	 * Messages levels that will be displayed are: QUIET, DEFAULT
	 */
	DEFAULT,
	
	/**
	 * The least verbose, when
	 * Messages levels that will be displayed are: QUIET
	 */
	QUIET,
	
	/**
	 * Use only for debugging
	 * Messages that will be displayed are: DEBUG, QUIET, DEFAULT
	 */
	DEBUG,
	
	/**
	 * Use only for debugging, the most verbose of all
	 * Messages that will de displayed are: DEBUG, QUIET, DEFAULT, VERBOSE
	 */
	VERBOSE_DEBUG
}
