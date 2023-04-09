public class Main {

    public static void main(String[] args) {
        Channel waterBallAcademy = new Channel("水球軟體學院");
        Channel pewDiePie = new Channel("pewDiePie");
        ChannelSubscriber waterBall = new WaterBallSubscriber("水球");
        ChannelSubscriber fireBall = new FireBallSubscriber("火球");

        waterBallAcademy.subscribe(waterBall);
        pewDiePie.subscribe(waterBall);
        waterBallAcademy.subscribe(fireBall);
        pewDiePie.subscribe(fireBall);

        waterBallAcademy.uploadVideo(new Video( "C1M1S2", "敘述：”這個世界正是物件導向的呢！", 4));
        pewDiePie.uploadVideo(new Video( "Hello guys", "Clickbait", 0.5f));
        waterBallAcademy.uploadVideo(new Video( "C1M1S3", "物件 vs. 類別", 1));
        pewDiePie.uploadVideo(new Video( "Minecraft", "Let’s play Minecraft", 30));
    }
}
