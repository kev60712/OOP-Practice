import java.util.ArrayList;
import java.util.List;

public class RealEmployee implements Employee{

    protected int id;
    protected String name;
    protected int age;
    protected List<Employee> subordinates = new ArrayList<>();

    public RealEmployee() {
    }

    public RealEmployee(int id, String name, int age, List<Employee> subordinateIds) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subordinates = subordinateIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", subordinates=" + subordinates +
                '}';
    }
}
