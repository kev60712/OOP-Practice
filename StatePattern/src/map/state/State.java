package map.state;

public interface State {

    void takeTurn();

    void attack();

    void damage(int damage);

    void stateEffect();

    void exitState();

}
