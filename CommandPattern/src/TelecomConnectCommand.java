public class TelecomConnectCommand implements Command {

    private Telecom telecom;

    public TelecomConnectCommand(Telecom telecom) {
        this.telecom = telecom;
    }
    @Override
    public void execute() {
        telecom.connect();
    }
    @Override
    public void undo() {
        telecom.disconnect();
    }
}
