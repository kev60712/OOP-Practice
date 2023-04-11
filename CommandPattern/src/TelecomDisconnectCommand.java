public class TelecomDisconnectCommand implements Command {

    private Telecom telecom;

    public TelecomDisconnectCommand(Telecom telecom) {
        this.telecom = telecom;
    }
    @Override
    public void execute() {
        telecom.disconnect();
    }
    @Override
    public void undo() {
        telecom.connect();
    }
}
