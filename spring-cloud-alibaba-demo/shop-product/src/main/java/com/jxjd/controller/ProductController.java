package com.jxjd.controller;

import com.alibaba.fastjson.JSON;
import com.jxjd.domain.Product;
import com.jxjd.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

  @Autowired
  private ProductService productService;
  @RequestMapping("/product/{pid}")
  public Product product(@PathVariable Integer pid){
    log.info("商品查询pid：{}",pid);

    Product product =productService.findByPid(pid);
    log.info("商品信息：{}", JSON.toJSONString(product));
    return product;

  }
}
