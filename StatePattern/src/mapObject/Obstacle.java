package mapObject;

public class Obstacle extends MapObject{

    public Obstacle(Coord coord, GameMap map) {
        super("□", coord, map);
    }

    @Override
    void destroy() {
        // No support
    }
}
