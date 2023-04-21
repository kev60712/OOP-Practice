package map.state;

import map.Role;

abstract class TemplateState implements State{

    protected int countDown;

    protected Role role;

    public TemplateState(Role role) {
        this.role = role;
    }

    @Override
    public void takeTurn(){
        stateEffect();
        this.role.roundStart();
        countDown();
    }

    @Override
    public void attack() {
        this.role.defaultAttack();
    }

    @Override
    public void damage(int damage) {
        this.role.setHp(this.role.getHp() - damage);
    }

    @Override
    public void stateEffect() {
        // Do Nothing
    }

    @Override
    public void exitState(){
        role.updateState(new NormalState(this.role));
        System.out.println(this.role.getName() + " is now Normal State!!");
    }

    public void countDown(){
        this.countDown--;
        if (this.countDown == 0){
            exitState();
        }
    }


}
