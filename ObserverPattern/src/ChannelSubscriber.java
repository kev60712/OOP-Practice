import java.util.HashSet;
import java.util.Set;

abstract class ChannelSubscriber {

    private String name;
    protected Set<Channel> subscribeChannel = new HashSet<>();

    public ChannelSubscriber(String name) {
        this.name = name;
    }

    abstract void action(Video video);

    public String getName() {
        return name;
    }
}
