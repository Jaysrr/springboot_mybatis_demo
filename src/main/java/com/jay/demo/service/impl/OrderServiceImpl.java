package com.jay.demo.service.impl;

import com.jay.demo.pojo.Order;
import com.jay.demo.dao.OrderMapper;
import com.jay.demo.response.RespObject;
import com.jay.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-22 19:48
 **/

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public RespObject add(Order order) {
        if (order.getCreateTime() == null) {
            order.setCreateTime(new Date());
        }

        order.setUpdateTime(new Date());

        Integer rst = orderMapper.insert(order);
        RespObject respObject = new RespObject();
        if (rst != null) {
            respObject.setRespRst(rst);
        }
        return respObject;
    }

    @Override
    public RespObject update(Order order) {
        if (order.getUpdateTime() != null) {
            order.setUpdateTime(new Date());
            order.setUpdater("本该是单点登录的那个user,的名称");
        }

        Integer rst = orderMapper.update(order);
        RespObject respObject = new RespObject();
        if (rst != null) {
            respObject.setRespRst(rst);
        }
        return respObject;
    }

}
