package com.jay.demo.service;

import com.jay.demo.pojo.Goods;
import com.jay.demo.request.GoodsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-23 10:19
 **/
public interface GoodsService {


    List<Goods> findByGoodsName(String goodsName, Integer page, Integer size);

    Integer add(GoodsRequest goodsRequest);

    Integer update(Long id, GoodsRequest goodsRequest);

    Integer delete(Long id);
}
