package map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameMap {

    private int width;
    private int height;
    private MapObject[][] map;
    private List<Monster> monsterList = new ArrayList<>();
    private Character character;
    private List<Coord> availableCoord = new ArrayList<>();

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new MapObject[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                map[i][j] = null;
                availableCoord.add(new Coord(i, j));
            }
        }
        initCharacter();
        initMonster();
        initTreasure();
        initObstacle();
    }

    private void initCharacter() {
        // Get a random available coordinate
        Random rand = new Random();
        int availableCoordSize = availableCoord.size();
        int index = rand.nextInt(availableCoordSize);
        Coord coord = availableCoord.get(index);

        // Init character
        List<String> iconList = List.of("↑", "→", "↓", "←");
        String icon = iconList.get((int) (Math.random() * 4));
        character = new Character("KEVIN", coord, icon, this);

        // Update map
        map[coord.getX()][coord.getY()] = character;
        availableCoord.remove(index);
    }

    private void initMonster() {
        Random rand = new Random();
        int availableCoordSize = availableCoord.size();
        for (int i = 0; i < 10; i++){
            String name = "Monster" + (i+1);
            int index = rand.nextInt(availableCoordSize);
            Coord coord = availableCoord.get(index);
            Monster monster = new Monster(name, coord, this);
            monsterList.add(monster);

            map[coord.getX()][coord.getY()] = monster;
            availableCoord.remove(index);
            availableCoordSize--;
        }
    }

    private void initObstacle() {
        Random rand = new Random();
        int availableCoordSize = availableCoord.size();
        for (int i = 0; i < 10; i++){
            int index = rand.nextInt(availableCoordSize);
            Coord coord = availableCoord.get(index);

            Obstacle obstacle = new Obstacle(coord, this);

            map[coord.getX()][coord.getY()] = obstacle;
            availableCoord.remove(index);
            availableCoordSize--;
        }
    }

    private void initTreasure() {
        Random rand = new Random();
        int availableCoordSize = availableCoord.size();
        for (int i = 0; i < 10; i++){
            int index = rand.nextInt(availableCoordSize);
            Coord coord = availableCoord.get(index);

            Treasure treasure = getRandomTreasure(coord);

            map[coord.getX()][coord.getY()] = treasure;
            availableCoord.remove(index);
            availableCoordSize--;
        }
    }

    private Treasure getRandomTreasure(Coord coord) {
        List<Treasure.TreasureType> treasureTypeList = new ArrayList<>(List.of(
                Treasure.TreasureType.SuperStar,
                Treasure.TreasureType.SuperStar,
                Treasure.TreasureType.Poison,
                Treasure.TreasureType.Poison,
                Treasure.TreasureType.Poison,
                Treasure.TreasureType.Poison,
                Treasure.TreasureType.Poison,
                Treasure.TreasureType.AcceleratingPotion,
                Treasure.TreasureType.AcceleratingPotion,
                Treasure.TreasureType.AcceleratingPotion,
                Treasure.TreasureType.AcceleratingPotion,
                Treasure.TreasureType.HealingPotion,
                Treasure.TreasureType.HealingPotion,
                Treasure.TreasureType.HealingPotion,
                Treasure.TreasureType.DevilFruit,
                Treasure.TreasureType.DevilFruit,
                Treasure.TreasureType.KingsRock,
                Treasure.TreasureType.KingsRock,
                Treasure.TreasureType.DokodemoDoor,
                Treasure.TreasureType.DokodemoDoor
        ));
        Collections.shuffle(treasureTypeList);
        return new Treasure(coord, this, treasureTypeList.get(0));
    }

    public void displayMap(){
        System.out.println("Game Map:");
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (map[i][j] == null){
                    System.out.print("  ");
                }else{
                    System.out.print(map[i][j].getIcon() + " ");
                }
            }
            System.out.println();
        }
    }

    public MapObject getObject(Coord coord) {
        return map[coord.getX()][coord.getY()];
    }

    public void moveObject(Role role, Coord coord) {
        if (!isCoordEmpty(coord)){
            throw new RuntimeException("Coord is not empty");
        }
        map[role.getCoord().getX()][role.getCoord().getY()] = null;
        availableCoord.add(role.getCoord());

        role.setCoord(coord);
        map[coord.getX()][coord.getY()] = role;
    }

    public void removeObject(MapObject mapObject) {
        if (mapObject instanceof Monster) {
            monsterList.remove(mapObject);
        }
        map[mapObject.getCoord().getX()][mapObject.getCoord().getY()] = null;
        availableCoord.add(mapObject.getCoord());
    }

    public void moveObjToRandomPosition(Role role) {
        Random rand = new Random();
        int availableCoordSize = availableCoord.size();
        int index = rand.nextInt(availableCoordSize);
        Coord coord = availableCoord.get(index);

        moveObject(role, coord);
        availableCoord.remove(index);
    }

    public List<Coord> getSurroundingCoord(Coord coord) {
        List<Coord> surroundingCoord = new ArrayList<>();
        int x = coord.getX();
        int y = coord.getY();
        if (x > 0){
            surroundingCoord.add(new Coord(x - 1, y));
        }
        if (x < height - 1){
            surroundingCoord.add(new Coord(x + 1, y));
        }
        if (y > 0){
            surroundingCoord.add(new Coord(x, y - 1));
        }
        if (y < width - 1){
            surroundingCoord.add(new Coord(x, y + 1));
        }
        return surroundingCoord;
    }

    public boolean isAllMonsterDead() {
        return monsterList.isEmpty();
    }

    public boolean isCoordEmpty(Coord coord) {
        return map[coord.getX()][coord.getY()] == null;
    }

    public boolean isInMap(Coord coord) {
        if (coord == null){
            return false;
        }
        return coord.getX() >= 0 && coord.getX() < height &&
                coord.getY() >= 0 && coord.getY() < width;
    }

    // Getter and Setter
    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character){
        this.character = character;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
