package mapObject;

public class TeleportState extends TemplateState {

    public TeleportState(Role role) {
        super(role);
        this.countDown = 1;
    }

    @Override
    public void exitState(){
        this.role.map.moveObjToRandomPosition(this.role);
        role.updateState(new NormalState(this.role));
        System.out.println(this.role.getName() + " is now Normal State!!");
    }
}
