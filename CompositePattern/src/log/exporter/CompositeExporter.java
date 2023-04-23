package log.exporter;

import java.util.ArrayList;
import java.util.List;

public class CompositeExporter implements Exporter{

    private List<Exporter> exporters = new ArrayList<Exporter>();

    // Constructor for multiple exporters
    public CompositeExporter(Exporter... exporters) {
        for (Exporter exporter : exporters) {
            this.exporters.add(exporter);
        }
    }

    public CompositeExporter(List<Exporter> exporters) {
        this.exporters = exporters;
    }

    @Override
    public void exportLog(String log) {
        for (Exporter exporter : exporters) {
            exporter.exportLog(log);
        }
    }
}
