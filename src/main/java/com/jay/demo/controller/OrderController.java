package com.jay.demo.controller;

import com.jay.demo.pojo.Order;
import com.jay.demo.response.RespObject;
import com.jay.demo.service.OrderService;
import com.jay.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-22 19:09
 **/

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public JsonResult list() {
        List<Order> list = orderService.findAll();
        return !list.isEmpty() ? JsonResult.ok(list) : JsonResult.error();
    }

    @PostMapping("/add")
    public JsonResult addOrder(@RequestBody Order order) {
        RespObject resp = orderService.add(order);
        return JsonResult.ok(resp);
    }

    @PostMapping("/update")
    public JsonResult updateOrder(@RequestBody Order order) {
        RespObject resp = orderService.update(order);
        return JsonResult.ok(resp);
    }


}
