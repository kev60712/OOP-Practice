public class TankMoveForwardCommand implements Command {

    private Tank tank;

    public TankMoveForwardCommand(Tank tank) {
        this.tank = tank;
    }
    @Override
    public void execute() {
        tank.moveForward();
    }
    @Override
    public void undo() {
        tank.moveBackward();
    }
}
