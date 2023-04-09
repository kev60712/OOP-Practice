import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RealDatabase implements Database {
    private Map<Integer, Employee> employees = new HashMap<>();

    public RealDatabase() throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/ProxyPattern/src/data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Reading data from file: " + filePath);
            String dataText = br.lines().collect(Collectors.joining("\n"));
            convertScriptToEmployees(dataText);
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("Employee id = " + id + " not found!");
        }
        return employee;
    }

    /**
     id name age subordinateIds
     1 waterball 25
     2 fixiabis 15 1,3
     3 fong 7 1
     4 cc 18 1,2,3
     5 peterchen 3 1,4
     6 handsomeboy 22 1
     */
    public void convertScriptToEmployees(String dataText) {
        String[] lines = dataText.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
            int id = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            int age = Integer.parseInt(tokens[2]);
            List<Employee> subordinates = new ArrayList<>();
            if (tokens.length > 3) {
                String[] subordinateIds = tokens[3].split(",");
                for (String subordinateId : subordinateIds) {
                    Employee subordinate = employees.get(Integer.parseInt(subordinateId));
                    if (subordinate == null) {
                        subordinate = new RealEmployee();
                        employees.put(Integer.parseInt(subordinateId), subordinate);
                    }
                    subordinates.add(subordinate);
                }
            }
            Employee employee = employees.getOrDefault(id, new RealEmployee());
            employee.setId(id);
            employee.setAge(age);
            employee.setName(name);
            employee.setSubordinates(subordinates);
            employees.put(id, employee);
        }
    }
}
