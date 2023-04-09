import java.util.HashSet;
import java.util.Set;

public class Channel {

    private String name;
    private Set<Video> videoStorage = new HashSet<>();
    private Set<ChannelSubscriber> subscribers = new HashSet<>();

    public Channel(String name) {
        this.name = name;
    }

    public void uploadVideo(Video video){
        videoStorage.add(video);
        video.setChannel(this);
        System.out.println("頻道 " + this.name + "上架了一則新影片" + "\""+ video.getTitle() + "\"");
        notifySubscriber(video);
    }

    private void notifySubscriber(Video video) {
        Set<ChannelSubscriber> subscribersCopy = new HashSet<>(subscribers);
        for (ChannelSubscriber subscriber : subscribersCopy) {
            subscriber.action(video);
        }
    }

    public void subscribe(ChannelSubscriber subscriber){
        subscribers.add(subscriber);
        System.out.println(subscriber.getName() + " 訂閱了 " + this.name);
    }

    public void unsubscribe(ChannelSubscriber subscriber){
        System.out.println(String.format("%s 解除訂閱了 %s。", subscriber.getName(), this.name));
        subscribers.removeIf(i -> i.equals(subscriber));
    }
}
