package cn.slimsmart.kafka.demo.cn.com.demo;

/**
 * Created by admin on 2017/4/12.
 */
@PriceRegion(min=3000,max = 50000)
public class GoldVip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.7;
    }
}
