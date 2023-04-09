import java.util.ArrayList;
import java.util.List;

public class ProxyDatabase implements Database{

    private RealDatabase database;
    private String password;

    public ProxyDatabase(String password, RealDatabase database) {
        this.password = password;
        this.database = database;
    }

    @Override
    public Employee getEmployeeById(int id) {
        checkPassword();
        Employee employee = database.getEmployeeById(id);
        if (employee != null) {
            ProxyEmployee proxyEmployee = new ProxyEmployee(this);
            proxyEmployee.setId(employee.getId());
            proxyEmployee.setName(employee.getName());
            proxyEmployee.setAge(employee.getAge());
            return proxyEmployee;
        }
        return null;
    }

    public List<Employee> getSubordinatesByEmployeeId(int id) {
        checkPassword();
        Employee employee = database.getEmployeeById(id);
        List<Employee> employees = employee.getSubordinates();
        List<Employee> proxyEmployees = new ArrayList<>();
        for (Employee e : employees) {
            ProxyEmployee proxyEmployee = new ProxyEmployee(this);
            proxyEmployee.setId(e.getId());
            proxyEmployee.setName(e.getName());
            proxyEmployee.setAge(e.getAge());
            proxyEmployees.add(proxyEmployee);
        }
        return proxyEmployees;
    }

    public void checkPassword(){
        if (!password.equals("1qaz2wsx")) {
            throw new RuntimeException("Wrong password");
        }
    }
}
