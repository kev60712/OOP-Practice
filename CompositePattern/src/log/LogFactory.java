package log;

import log.exporter.CompositeExporter;
import log.exporter.ConsoleExporter;
import log.exporter.Exporter;
import log.exporter.FileExporter;
import log.layout.Layout;
import log.layout.StandardLayout;
import org.json.JSONArray;
import org.json.JSONObject;
import util.FileUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogFactory {

    private static Map<String, Logger> loggerMap = new HashMap<String, Logger>();

    public static void declareLoggers(Logger... loggers){
        for (Logger logger : loggers) {
            loggerMap.put(logger.getName(), logger);
        }
    }

    public static Logger getLogger(String name) {
        return loggerMap.get(name);
    }

    // TODO: Implement this method
    public static void loadLoggersConfig(String configPath) throws FileNotFoundException {
        String jsonStr = FileUtil.readFile(configPath);
        JSONObject jsonObject = new JSONObject(jsonStr).getJSONObject("loggers");
        List<JSONObject> childLoggers = new ArrayList();

        // Create root logger
        Logger root = new Logger();
        root.setName("root");
        for (String key : jsonObject.keySet()) {
            if (key.equals("levelThreshold")) {
                String level = jsonObject.getString(key);
                root.setLevel(Logger.Level.valueOf(level));
            } else if (key.equals("exporter")) {
                JSONObject exporterJson = jsonObject.getJSONObject(key);
                Exporter exporter = createExporter(exporterJson);
                root.setExporter(exporter);
            } else if (key.equals("layout")) {
                String layoutStr = jsonObject.getString(key);
                Layout layout = createLayout(layoutStr);
                root.setLayout(layout);
            } else {
                childLoggers.add(jsonObject.getJSONObject(key));
            }
        }
        loggerMap.put("root", root);

        // Create Child Loggers

    }

    public static Layout createLayout(String layoutStr){
        if (layoutStr.equals("standard")) {
            return new StandardLayout();
        }
        return null;
    }

    public static Exporter createExporter(JSONObject exporterJson){
        String exporterType = exporterJson.getString("type");
        if (exporterType.equals("console")) {
            return new ConsoleExporter();
        } else if (exporterType.equals("file")) {
            String fileName = exporterJson.getString("fileName");
            return new FileExporter(fileName);
        } else if (exporterType.equals("composite")) {
            List<Exporter> exporters = new ArrayList<>();
            JSONArray childExporters = exporterJson.getJSONArray("children");
            for (Object child: childExporters) {
                JSONObject childExporterJson = (JSONObject) child;
                Exporter exporter = createExporter(childExporterJson);
                exporters.add(exporter);
            }
            return new CompositeExporter(exporters);
        }
        return null;
    }

}
