package com.jxjd.dao;

import com.jxjd.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {


}
