package com.jxjd.service.impl;

import com.jxjd.dao.OrderDao;
import com.jxjd.domain.Order;
import com.jxjd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDao orderDao;
  @Override
  public void createOrder(Order order) {
    orderDao.save(order);
  }
}
