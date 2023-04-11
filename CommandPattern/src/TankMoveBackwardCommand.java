public class TankMoveBackwardCommand implements Command {

    private Tank tank;

    public TankMoveBackwardCommand(Tank tank) {
        this.tank = tank;
    }
    @Override
    public void execute() {
        tank.moveBackward();
    }
    @Override
    public void undo() {
        tank.moveForward();
    }
}
