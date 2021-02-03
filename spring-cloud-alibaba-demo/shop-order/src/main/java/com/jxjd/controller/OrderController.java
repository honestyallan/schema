package com.jxjd.controller;

import com.alibaba.fastjson.JSON;
import com.jxjd.domain.Order;
import com.jxjd.domain.Product;
import com.jxjd.service.OrderService;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private OrderService orderService;

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/order/prod/{pid}")
  public Order order(@PathVariable Integer pid) {
    //自定义负载均衡
   /* List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
    int index = new Random().nextInt(instances.size());
    ServiceInstance instance =instances.get(index);*/
    //1.代码可读性不好
    //2.代码风格不统一
    Product product = restTemplate
        .getForObject("http://service-product/product/" + pid, Product.class);
    log.info("查询到{}号商品,信息是{}", pid, JSON.toJSONString(product));
    Order order = new Order();
    order.setUid(1);
    order.setUsername("nacos user");
    order.setPid(pid);
    order.setPname(product.getPname());
    order.setPprice(product.getPprice());
    order.setNumber(1);
    orderService.createOrder(order);
    log.info("创建订单成功，订单信息：{}", JSON.toJSONString(order));
    return order;
  }
}
