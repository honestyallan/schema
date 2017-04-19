package cn.slimsmart.kafka.demo.cn.com.demo;

/**
 * Created by admin on 2017/4/12.
 */
@PriceRegion(max=20000)
public class Vip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.9;
    }
}
