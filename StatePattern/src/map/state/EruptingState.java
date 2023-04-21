package map.state;

import map.Character;
import map.Monster;
import map.Role;

import java.util.ArrayList;
import java.util.List;

public class EruptingState extends TemplateState {

    public EruptingState(Role role) {
        super(role);
        this.countDown = 3;
    }

    @Override
    public void attack(){
        if (this.role instanceof Character){
            List<Monster> monsterList = new ArrayList<>(this.role.getMap().getMonsterList());
            for (Monster monster : monsterList){
                monster.damage(50);
            }
            System.out.println(this.role.getName() + " cause all monsters 50 damage!!");
        }else if (this.role instanceof Monster){
            Character character = this.role.getMap().getCharacter();
            character.damage(50);
            System.out.println(this.role.getName() + " cause " + character.getName() + " 50 damage!!");
        }
    }

    @Override
    public void exitState(){
        role.updateState(new TeleportState(this.role));
        System.out.println(this.role.getName() + " is now Teleporting State!!");
    }
}
