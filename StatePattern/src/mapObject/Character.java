package mapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Character extends Role {

    public Character(String name, Coord coord, String icon, GameMap map) {
        super(name, icon, coord, map);
        this.coord = coord;
        this.icon = icon;
        this.maxHp = 300;
        this.hp = 300;
        this.state = new NormalState(this);
    }

    @Override
    protected void roundStart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Attack or Move? (a/m)");
        String decision = scanner.nextLine();
        if (decision.equals("a")) {
            this.attack();
        } else if (decision.equals("m")) {
            this.move();
        }
    }

    @Override
    protected void move() {
        Coord coord = null;
        Scanner scanner = new Scanner(System.in);
        String icon = this.icon;
        while (true){
            System.out.println("Which direction? (u/d/l/r)");
            String direction = scanner.nextLine();
            if (direction.equals("u")) {
                icon = "↑";
                coord = new Coord(this.coord.getX() - 1, this.coord.getY());
            } else if (direction.equals("d")) {
                icon = "↓";
                coord = new Coord(this.coord.getX() + 1, this.coord.getY());
            } else if (direction.equals("l")) {
                icon = "←";
                coord = new Coord(this.coord.getX(), this.coord.getY() - 1);
            } else if (direction.equals("r")) {
                icon = "→";
                coord = new Coord(this.coord.getX(), this.coord.getY() + 1);
            } else {
                System.out.println("Invalid direction");
            }

            if (map.isInMap(coord)) {
                this.icon = icon;
                if (map.getObject(coord) == null) {
                    this.map.moveObject(this, coord);
                    break;
                } else if (map.getObject(coord) instanceof Treasure) {
                    Treasure treasure = (Treasure) map.getObject(coord);
                    treasure.effect(this);
                    this.map.moveObject(this, coord);
                    break;
                } else {
                    System.out.println("Touch");
                    break;
                }
            }
        }
    }

    @Override
    protected void defaultAttack() {
        List<MapObject> objectInFront = new ArrayList<>();
        if (this.icon.equals("↑")){
            for (int x = coord.getX(); x > -1; x--){
                objectInFront.add(this.map.getObject(new Coord(x, this.coord.getY())));
            }
        }else if (this.icon.equals("→")){
            for (int y = coord.getY(); y < this.map.getWidth(); y++){
                objectInFront.add(this.map.getObject(new Coord(this.coord.getX(), y)));
            }
        }else if (this.icon.equals("↓")){
            for (int x = coord.getX(); x < this.map.getHeight(); x++){
                objectInFront.add(this.map.getObject(new Coord(x, this.coord.getY())));
            }
        }else if (this.icon.equals("←")){
            for (int y = coord.getY(); y > -1; y--){
                objectInFront.add(this.map.getObject(new Coord(this.coord.getX(), y)));
            }
        }
        for (MapObject mapObject : objectInFront) {
            if (mapObject instanceof Monster) {
                ((Monster) mapObject).damage(30);
            }else if (mapObject instanceof Obstacle || mapObject instanceof Treasure){
                break;
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.map.setCharacter(null);
    }
}
