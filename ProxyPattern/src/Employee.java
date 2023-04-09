import java.util.ArrayList;
import java.util.List;

public interface Employee {

    Integer getId();

    String getName();

    Integer getAge();

    List<Employee> getSubordinates();

    void setId(int id);

    void setName(String name);

    void setAge(int age);

    void setSubordinates(List<Employee> subordinates);

}
