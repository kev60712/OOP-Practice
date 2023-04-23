package log.layout;

import log.Logger;

public interface Layout {

    String format(Logger logger, Logger.Level logLevel, String msg);

}
