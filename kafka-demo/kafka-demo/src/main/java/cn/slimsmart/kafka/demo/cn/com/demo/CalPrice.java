package cn.slimsmart.kafka.demo.cn.com.demo;

/**
 * Created by admin on 2017/4/12.
 */
public interface CalPrice {

    /**
     * 策略方法
     */
    //根据原价返回一个最终的价格
    Double calPrice(Double orgnicPrice);
}
