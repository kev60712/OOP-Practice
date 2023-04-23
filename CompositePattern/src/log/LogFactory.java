package log;

import java.util.HashMap;
import java.util.Map;

public class LogFactory {

    private static Map<String, Logger> loggerMap = new HashMap<String, Logger>();

    public static void declareLoggers(Logger... loggers){
        for (Logger logger : loggers) {
            loggerMap.put(logger.getName(), logger);
        }
    }

    public static Logger getLogger(String name) {
        return loggerMap.get(name);
    }

    public static void loadLoggersConfig(String configPath) {

    }
}
