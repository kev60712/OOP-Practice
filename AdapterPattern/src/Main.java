import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    /** Relationship.txt
     A: B C D
     B: A D E
     C: A E G K M
     D: A B K P
     E: B C J K L
     F: Z
     Q: S
     */
    public static void main(String[] args) throws FileNotFoundException {
        RelationshipAnalyzer analyzer = new RelationshipAnalyzerAdapter();
        String filePath = System.getProperty("user.dir") + "/AdapterPattern/src/relationship.txt";
        String script = FileUtil.readFile(filePath);
        RelationshipGrpah relationshipGrpah = analyzer.parse(script);
        System.out.println("Get A & B mutual friends: " + analyzer.getMutualFriends("A", "B"));
        System.out.println("Does A and L have connection? " + relationshipGrpah.hasConnection("A", "L"));
        System.out.println("Does A and Q have connection? " + relationshipGrpah.hasConnection("A", "Q"));
    }
}
