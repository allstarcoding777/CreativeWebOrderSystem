// AppLogger.java
// Simple logging class
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("CreativeWebOrderSystem");
            ConsoleHandler handler = new ConsoleHandler();
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
        }
        return logger;
    }
}


