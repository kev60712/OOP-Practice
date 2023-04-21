package map.state;

import map.object.Role;

public class HealingState extends TemplateState {

    public HealingState(Role role) {
        super(role);
        this.countDown = 5;
    }

    @Override
    public void stateEffect() {
        System.out.println(this.role.getName() + " is healing 30 HP!");
        if (this.role.getHp() + 30 >= this.role.getMaxHp()){
            this.role.setHp(this.role.getMaxHp());
            exitState();
        } else{
            this.role.setHp(this.role.getHp() + 30);
        }
    }
}
