package analyzer;

import java.util.ArrayList;
import java.util.List;

public class SuperFriend {

    private String name;
    private List<SuperFriend> superFriends = new ArrayList<>();

    public SuperFriend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<SuperFriend> getSuperFriends() {
        return superFriends;
    }
}
