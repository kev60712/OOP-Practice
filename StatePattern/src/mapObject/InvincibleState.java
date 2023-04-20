package mapObject;

public class InvincibleState extends TemplateState{

    public InvincibleState(Role role) {
        super(role);
        this.countDown = 2;
    }

    @Override
    public void damage(int damage) {
        System.out.println(this.role.getName() + " is Invincible!");
    }
}
