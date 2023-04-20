package mapObject;

public class AcceleratedState extends TemplateState {


    public AcceleratedState(Role role) {
        super(role);
        this.countDown = 3;
    }

    @Override
    public void takeTurn(){
        for (int i = 0; i < 2; i++) {
            this.role.roundStart();
        }
        countDown();
    }

    @Override
    public void damage(int damage){
        this.role.setHp(this.role.getHp() - damage);
        exitState();
    }
}
