// AppLogger.java
// Logging class for the CreativeWebOrderSystem

import java.io.IOException;
import java.util.logging.*;

public class AppLogger {

    private static Logger logger = Logger.getLogger("CreativeWebLogger");

    public static Logger getLogger() {
        return logger;
    }

    static {
        try {
            // Create handler for writing logs to file
            FileHandler fileHandler = new FileHandler("systemlog.txt", true);

            // Custom formatter
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Add handler to logger
            logger.addHandler(fileHandler);

            // Log level
            logger.setLevel(Level.INFO);

            // Prevent duplicate console logs
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

