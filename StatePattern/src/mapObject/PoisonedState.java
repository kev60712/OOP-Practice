package mapObject;

public class PoisonedState extends TemplateState{

        public PoisonedState(Role role) {
            super(role);
            countDown = 3;
        }

        @Override
        public void stateEffect() {
            System.out.println(this.role.getName() + " is poisoned 15 HP!");
            this.role.damage(15);
        }
}
