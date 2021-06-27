package com.jay.demo.dao;

import com.jay.demo.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface OrderMapper {

    List<Order> findAll();

    Integer insert(Order order);

    Integer update(Order order);
}
