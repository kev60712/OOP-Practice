import map.Character;
import map.GameMap;
import map.Monster;

public class Main {

    public static void main(String[] args) {
        int round = 0;
        GameMap gameMap = new GameMap(10, 5);
        Character character = gameMap.getCharacter();
        while (!character.isDead() && !gameMap.isAllMonsterDead()){
            System.out.println("Round " + round++);
            gameMap.displayMap();
            character.takeTurn();
            gameMap.displayMap();
            for (Monster monster : gameMap.getMonsterList()){
                monster.takeTurn();
            }
        }
        gameMap.displayMap();
        if (character.isDead()) {
            System.out.println("Game Over");
        } else {
            System.out.println("You Win");
        }
    }
}
