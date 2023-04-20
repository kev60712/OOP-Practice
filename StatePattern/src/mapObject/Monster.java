package mapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Role{

    public Monster(String name, Coord coord, GameMap map) {
        super(name,"M", coord, map);
        this.maxHp = 1;
        this.hp = 1;
        this.state = new NormalState(this);
    }

    @Override
    protected void roundStart() {
        List<Coord> surroundingCoords = this.map.getSurroundingCoord(this.coord);
        List<Coord> emptyCoords = new ArrayList<>();
        for (Coord coord : surroundingCoords) {
            MapObject mapObject = this.map.getObject(coord);
            if (mapObject instanceof Character) {
                Character character = (Character) mapObject;
                System.out.println(this.name + " attacks " + character.name);
                this.attack();
                return;
            }
            if (mapObject == null) {
                emptyCoords.add(coord);
            }
        }
        if (emptyCoords.size() > 0) {
            this.move();
        }
    }

    @Override
    protected void move() {
        List<Coord> surroundingCoords = this.map.getSurroundingCoord(this.coord);
        List<Coord> availableCoords = new ArrayList<>();
        for (Coord coord : surroundingCoords) {
            MapObject mapObject = this.map.getObject(coord);
            if (mapObject == null || mapObject instanceof Treasure) {
                availableCoords.add(coord);
            }
        }
        if (availableCoords.size() > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(availableCoords.size());
            Coord newCoord = availableCoords.get(randomIndex);
            MapObject mapObject = this.map.getObject(newCoord);
            if (mapObject instanceof Treasure) {
                Treasure treasure = (Treasure) mapObject;
                treasure.effect(this);
            }
            this.map.moveObject(this, newCoord);
            System.out.println(this.name + " moves to " + newCoord);
        }
    }

    @Override
    protected void defaultAttack() {
        this.map.getCharacter().damage(1);
        System.out.println(this.name + " attacks " + this.map.getCharacter().name);
    }

}
