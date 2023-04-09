public class WaterBallSubscriber extends ChannelSubscriber {

    public WaterBallSubscriber(String name) {
        super(name);
    }

    @Override
    public void action(Video video) {
        if (video.getLength() > 3){
            video.like(this);
        }
    }
}
