import java.util.Set;

public interface RelationshipAnalyzer {
    RelationshipGrpah parse(String script);

    Set<String> getMutualFriends(String name1, String name2);
}
