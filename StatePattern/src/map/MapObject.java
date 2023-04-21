package map;

abstract class MapObject {

    protected String icon;
    protected Coord coord;
    protected GameMap map;

    public MapObject(String icon, Coord coord, GameMap map) {
        this.icon = icon;
        this.coord = coord;
        this.map = map;
    }

    abstract void destroy();

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
