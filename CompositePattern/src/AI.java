import log.LogFactory;
import log.Logger;


public class AI {

    // 取得 "app.game.ai" 日誌器，名為 log 屬性
    private Logger log = LogFactory.getLogger("app.game.ai");
    private String name;
    
    public AI(String name) {
        this.name = name;
    }

    // 模擬 AI 決策，請日誌器撰寫日誌訊息，並做適當的訊息分級。
    public void makeDecision() {
        log.trace(name + " starts making decisions...");
        log.warn( name+ "  decides to give up.");
        log.error("Something goes wrong when AI gives up.");
        log.trace(name + "  completes its decision.");
    }

    public String getName() {
        return name;
    }
}
