package cn.slimsmart.kafka.demo.region;

/**
 * Created by admin on 2017/4/12.
 */
@PriceRegion(min=20000,max=30001)
public class SuperVip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.8;
    }
}
