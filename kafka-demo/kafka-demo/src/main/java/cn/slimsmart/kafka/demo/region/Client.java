package cn.slimsmart.kafka.demo.region;

/**
 * Created by admin on 2017/4/12.
 */
public class Client {
    public static void main(String[] args) {
        Player player = new Player();
        player.buy(10000D);
        System.out.println("玩家需要付钱：" + player.calLastAmount());
        player.buy(20001D);
        System.out.println("玩家需要付钱：" + player.calLastAmount());
        player.buy(30000D);
        System.out.println("玩家需要付钱：" + player.calLastAmount());
        player.buy(2000D);
        System.out.println("玩家需要付钱：" + player.calLastAmount());
    }
}
