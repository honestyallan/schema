package com.jxjd.service.impl;

import com.jxjd.dao.ProductDao;
import com.jxjd.domain.Product;
import com.jxjd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductDao productDao;
  @Override
  public Product findByPid(Integer pid) {
    return productDao.findById(pid).get();
  }
}
