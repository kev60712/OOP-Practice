public class FireBallSubscriber extends ChannelSubscriber {

    public FireBallSubscriber(String name) {
        super(name);
    }

    @Override
    public void action(Video video) {
        if (video.getLength() <= 1){
            Channel channel = video.getChannel();
            channel.unsubscribe(this);
            subscribeChannel.remove(channel);
        }
    }
}
