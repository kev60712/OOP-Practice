package map.object;

import map.GameMap;
import map.state.State;

public abstract class Role extends MapObject{

    protected String name;
    protected int maxHp;
    protected int hp;
    protected State state;

    public Role(String name, String icon, Coord coord, GameMap map) {
        super(icon, coord, map);
        this.name = name;
    }

    public abstract void roundStart();

    public abstract void move();

    public abstract void defaultAttack();

    public void takeTurn(){
        displayInformation();
        this.state.takeTurn();
    }

    public void attack(){
        this.state.attack();
    }

    public void damage(int damage) {
        this.state.damage(damage);
        if (isDead()) {
            this.destroy();
        }
    }

    public void destroy() {
        this.map.removeObject(this);
        System.out.println(String.format("%s is dead.", this.name));
    }

    public void updateState(State state){
        this.state = state;
        System.out.println(String.format("%s is now %s ", this.name, state.getClass().getSimpleName()));
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    protected void displayInformation() {
        System.out.println(String.format("Name: %s, HP: %s, State: %s",
                this.name, this.hp, this.state.getClass().getSimpleName()));
    }

    // Getters and Setters
    public int getMaxHp() {
        return maxHp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
