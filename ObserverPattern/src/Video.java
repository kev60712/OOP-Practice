public class Video {

    private Channel channel;
    private String title;
    private String description;
    private float length;
    private int likeTotal = 0;

    public Video(String title, String description, float length) {
        this.title = title;
        this.description = description;
        this.length = length;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void like(ChannelSubscriber channelSubscriber){
        likeTotal += 1;
        System.out.println(String.format("%s 對影片 \"%s\" 按讚。", channelSubscriber.getName(), this.title));
    }
}
