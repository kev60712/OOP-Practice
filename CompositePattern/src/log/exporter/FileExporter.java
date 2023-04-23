package log.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter implements Exporter{

    private String fileName;

    public FileExporter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void exportLog(String log) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(getFilePath(), true))){
            bw.write(log);
            bw.newLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath(){
        String filePath = System.getProperty("user.dir") + "/CompositePattern/src/resource/"+ fileName + ".txt";
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
