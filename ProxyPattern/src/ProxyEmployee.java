import java.util.ArrayList;
import java.util.List;

public class ProxyEmployee extends RealEmployee{

    private ProxyDatabase proxyDatabase;

    public ProxyEmployee(ProxyDatabase proxyDatabase) {
        this.proxyDatabase = proxyDatabase;
    }

    @Override
    public List<Employee> getSubordinates() {
        return proxyDatabase.getSubordinatesByEmployeeId(id);
    }

    @Override
    public void setSubordinates(List<Employee> subordinates) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "ProxyEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
