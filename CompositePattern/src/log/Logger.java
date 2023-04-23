package log;

import log.exporter.Exporter;
import log.layout.Layout;

public class Logger {

    private String name;
    private Level level;
    private Exporter exporter;
    private Layout layout;
    private Logger parent;

    public enum Level implements Comparable<Level>{

        TRACE("TRACE", 0), INFO("INFO", 1), DEBUG("DEBUG", 2),
        WARN("WARN", 3), ERROR("ERROR", 4);

        public final String name;
        public final int level;

        Level(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public boolean isGreaterOrEqual(Level level) {
            return this.level >= level.level;
        }
    }

    // For root logger
    public Logger(Level levelThreshold, Exporter exporter, Layout layout) {
        this.level = levelThreshold;
        this.exporter = exporter;
        this.layout = layout;
        this.name = "root";
    }

    // For child logger
    public Logger(String name, Logger parent){
        this.name = name;
        this.parent = parent;
        this.level = parent.level;
        this.exporter = parent.exporter;
        this.layout = parent.layout;
    }

    public Logger(Level level, Logger parent, String name, Exporter exporter) {
        this.name = name;
        this.level = level;
        this.parent = parent;
        this.exporter = exporter;
        this.layout = parent.layout;
    }

    // For child logger
    public Logger(Level level, Logger parent, String name, Layout layout) {
        this.name = name;
        this.level = level;
        this.layout = layout;
        this.parent = parent;
        this.exporter = parent.exporter;
    }


    public void exportLog(String log, Level level) {
        if (level.isGreaterOrEqual(this.level)) {
            exporter.exportLog(layout.format(this, level, log));
        }
    }

    public void info(String s) {
        exportLog(s, Level.INFO);
    }

    public void trace(String s) {
        exportLog(s, Level.TRACE);
    }

    public void debug(String s) {
        exportLog(s, Level.DEBUG);
    }

    public void warn(String s) {
        exportLog(s, Level.WARN);
    }

    public void error(String s) {
        exportLog(s, Level.ERROR);
    }

    // Getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Logger getParent() {
        return parent;
    }

    public void setParent(Logger parent) {
        this.parent = parent;
    }
}
