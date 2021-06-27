package com.jay.demo.service;

import com.jay.demo.pojo.Goods;
import com.jay.demo.pojo.Order;
import com.jay.demo.response.RespObject;

import java.util.List;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-22 19:11
 **/
public interface OrderService {
    List<Order> findAll();

    RespObject add(Order order);

    RespObject update(Order order);

}
