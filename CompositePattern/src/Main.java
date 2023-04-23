import log.*;
import log.exporter.CompositeExporter;
import log.exporter.ConsoleExporter;
import log.exporter.FileExporter;
import log.layout.StandardLayout;

import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Logger root = new Logger(Logger.Level.DEBUG,
                new ConsoleExporter(),
                new StandardLayout());
        Logger gameLogger = new Logger(Logger.Level.INFO, root, "app.game",
                new CompositeExporter(
                        new ConsoleExporter(),
                        new CompositeExporter(
                                new FileExporter("game.log"),
                                new FileExporter("game.backup.log")
                        )
                )
        );
        Logger aiLogger = new Logger(Logger.Level.TRACE,
                gameLogger, "app.game.ai", new StandardLayout());

        // 配置剛定義好的三個日誌器
        LogFactory.declareLoggers(root, gameLogger, aiLogger);

//        String filePath = System.getProperty("user.dir") + "/CompositePattern/src/resource/logger_setting.json";
//        LogFactory.loadLoggersConfig(filePath);

        // 創建遊戲物件，並執行遊戲
        Game game = new Game();
        game.start();
    }
}
