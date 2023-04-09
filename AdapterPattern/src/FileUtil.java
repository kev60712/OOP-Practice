import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class FileUtil {

    public static String readFile(String filePath) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }
}
