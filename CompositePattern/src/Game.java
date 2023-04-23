import log.LogFactory;
import log.Logger;

import java.util.Arrays;
import java.util.List;

import static log.LogFactory.getLogger;

public class Game {

    // 取得 "app.game" 日誌器，名為 log 屬性
    private Logger log = LogFactory.getLogger("app.game");
    // 四個 AI 玩家，依序命名為 AI 1~4。
    private List<AI> players = List.of(new AI("AI 1"), new AI("AI 2"), new AI("AI 3"), new AI("AI 4"));

    public void start() {
        log.info("The game begins.");

        // 每個 AI 玩家輪流做決策
        for (var ai: players) {
            log.trace(String.format("The player *%s* begins his turn.", ai.getName()));
            ai.makeDecision();
            log.trace(String.format("The player *%s* finishes his turn.", ai.getName()));
        }
        log.debug("Game ends.");
    }
}
