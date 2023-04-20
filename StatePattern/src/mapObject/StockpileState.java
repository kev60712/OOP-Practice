package mapObject;

public class StockpileState extends TemplateState{

    public StockpileState(Role role) {
        super(role);
        this.countDown = 2;
    }

    @Override
    public void damage(int damage){
        this.role.setHp(this.role.getHp() - damage);
        exitState();
    }

    @Override
    public void countDown(){
        this.countDown--;
        if (this.countDown == 0){
            updateToEruptingState();
        }
    }

    public void updateToEruptingState(){
        System.out.println(this.role.getName() + " is now Erupting State!!");
        this.role.updateState(new EruptingState(this.role));
    }
}
