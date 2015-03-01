package cz.izar.game;


public class Log {
	
	private static final Logger defaultLogger = new Logger();

	public static void info(String message) {
		getLogger().info(message);
	}
	public static void warn(String message) {
		getLogger().warn(message);
	}
	public static void error(String message) {
		getLogger().error(message);
	}
	public static void log(String message, LogLevel logLevel) {
		getLogger().log(message, logLevel);
	}


	public static Logger getLogger() {
		return defaultLogger;
	}
	

	public static class Logger {
		public void info(String message) {
			log(message, LogLevel.INFO);
		}
		public void warn(String message) {
			log(message, LogLevel.WARNING);
		}
		public void error(String message) {
			log(message, LogLevel.ERROR);
		}
		public void log(String message, LogLevel logLevel) {
//			PrintStream stream = (logLevel == LogLevel.INFO)
//				? System.out
//				: System.err;
			System.out.println("[" + logLevel.toString() + "] " + message);
		}
	}
	
	
	
	public static enum LogLevel {
		ERROR,
		WARNING,
		INFO
	}

}
