import analyzer.SuperRelationAnalyzer;

import java.util.HashSet;
import java.util.Set;

public class RelationshipAnalyzerAdapter implements RelationshipAnalyzer{

    SuperRelationAnalyzer relationAnalyzer = new SuperRelationAnalyzer();
    Set<String> nameRecord = new HashSet<>();

    @Override
    public RelationshipGrpah parse(String script) {
        String convertedStr = convertScript(script);
        relationAnalyzer.init(convertedStr);
        return new RelationshipGraphImpl(script);
    }

    @Override
    public Set<String> getMutualFriends(String name1, String name2) {
        Set<String> mutualFriends = new HashSet<>();
        for (String name : nameRecord) {
            if (name.equals(name1) || name.equals(name2)) {
                continue;
            }
            if (relationAnalyzer.isMutualFriend(name, name1, name2)) {
                mutualFriends.add(name);
            }
        }
        return mutualFriends;
    }

    private String convertScript(String script) {
        Set<String> record = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        String[] line = script.split("\n");
        for (String s : line) {
            String[] str = s.split(": ");
            String name1 = str[0];
            String[] friends = str[1].split(" ");
            record.add(name1);
            nameRecord.add(name1);
            for (String friend : friends) {
                if (record.contains(friend)) {
                    continue;
                }
                nameRecord.add(friend);
                sb.append(name1).append(" -- ").append(friend).append("\n");
            }
        }
        return sb.toString();
    }

}
