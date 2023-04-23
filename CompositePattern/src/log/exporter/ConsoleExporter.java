package log.exporter;

public class ConsoleExporter implements Exporter{

    @Override
    public void exportLog(String log) {
        System.out.println(log);
    }

}
