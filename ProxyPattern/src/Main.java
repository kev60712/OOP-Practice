import java.io.FileNotFoundException;

public class Main {

    /**
     id name age subordinateIds
     1 waterball 25
     2 fixiabis 15 1,3
     3 fong 7 1
     4 cc 18 1,2,3
     5 peterchen 3 1,4
     6 handsomeboy 22 1
     */
    public static void main(String[] args) throws FileNotFoundException {
        Database database = new ProxyDatabase("1qaz2wsx", new RealDatabase());
        System.out.println("Get Employee id = 5: " + database.getEmployeeById(1));
        System.out.println("Get Employee id = 100: " + database.getEmployeeById(100));
        System.out.println("Get Employee id = 5 subordinates: " + database.getEmployeeById(5).getSubordinates());

        database = new ProxyDatabase("apple", new RealDatabase());
        System.out.println("Get Employee id = 5: " + database.getEmployeeById(1));
    }
}
