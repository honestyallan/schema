package cn.slimsmart.kafka.demo.region;

/**
 * Created by admin on 2017/4/12.
 */
@PriceRegion(max = 10000)
public class Orgnic implements CalPrice {

    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice;
    }
}
