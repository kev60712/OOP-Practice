package map.state;

import map.Role;

public class NormalState extends TemplateState{

    public NormalState(Role role) {
        super(role);
    }

    @Override
    public void takeTurn(){
        this.role.roundStart();
    }

}
