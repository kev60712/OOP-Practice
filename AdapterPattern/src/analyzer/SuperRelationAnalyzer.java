package analyzer;

import analyzer.SuperFriend;

import java.util.*;
import java.util.stream.Collectors;

public class SuperRelationAnalyzer {

    private Map<String, SuperFriend> userMap = new HashMap<>();
    private Set<SuperFriend> users = new HashSet<>();


    public void init(String script){
        String[] line = script.split("\n");
        for (String s : line) {
            String[] str = s.split(" -- ");
            String name1 = str[0];
            String name2 = str[1];
            SuperFriend user1 = userMap.getOrDefault(name1, new SuperFriend(name1));
            SuperFriend user2 = userMap.getOrDefault(name2, new SuperFriend(name2));
            user1.getSuperFriends().add(user2);
            user2.getSuperFriends().add(user1);
            userMap.put(name1, user1);
            userMap.put(name2, user2);
            users.add(user1);
            users.add(user2);
        }
    }

    public boolean isMutualFriend(String targetName, String name1, String name2){
        Set<String> mutualFriends = new HashSet<>();
        for (SuperFriend user : users) {
            if (user.getName().equals(name1) || user.getName().equals(name2)) {
                mutualFriends.addAll(user.getSuperFriends().stream()
                        .map(SuperFriend::getName)
                        .collect(Collectors.toList()));
            }
        }
        return mutualFriends.contains(targetName);
    }

}
