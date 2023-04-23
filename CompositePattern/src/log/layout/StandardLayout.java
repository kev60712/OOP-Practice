package log.layout;

import log.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StandardLayout implements Layout{

    private String timeFormat = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public String format(Logger logger, Logger.Level logLevel, String msg) {
        String time = new SimpleDateFormat(timeFormat).format(new Date());
        String log = String.format("%s |-%s %s - %s", time, logLevel, logger.getName(), msg);
        return log;
    }

}
