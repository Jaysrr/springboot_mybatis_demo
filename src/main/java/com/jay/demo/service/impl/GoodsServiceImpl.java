package com.jay.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.jay.demo.dao.GoodsMapper;
import com.jay.demo.pojo.Goods;
import com.jay.demo.pojo.GoodsExample;
import com.jay.demo.request.GoodsRequest;
import com.jay.demo.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-22 19:48
 **/

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> findByGoodsName(String goodsName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize); //aop
        GoodsExample example = new GoodsExample();
        example.createCriteria()
                .andNameEqualTo(goodsName);
        List<Goods> goodsList = goodsMapper.selectByExample(example);

        return goodsList;
    }

    @Override
    public Integer add(GoodsRequest goodsRequest) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsRequest, goods);

        goods.setCode(UUID.randomUUID().toString());
        goods.setCreater("这里需要是单点登陆下的这个用户的名称");
        goods.setCtime(new Date());
        goods.setUpdater("这里需要是单点登陆下的这个用户的名称");
        goods.setUtime(new Date());
        goods.setStatus(true);
        goods.setPicture("真实应该是从oss或者DFS返回的url");

        return goodsMapper.insertSelective(goods);
    }

    @Override
    public Integer update(Long id, GoodsRequest goodsRequest) {
        Goods goods =  goodsMapper.selectByPrimaryKey(id);
        System.out.println(goods.toString());

        goods.setName(goodsRequest.getName());
        goods.setBrand(goodsRequest.getBrand());
        goods.setIntro(goodsRequest.getIntro());
        goods.setPrice(goodsRequest.getPrice());
        goods.setType(goodsRequest.getType());
        goods.setWarehouse(goodsRequest.getWarehouse());

        return goodsMapper.updateByPrimaryKeySelective(goods);

    }

    @Override
    public Integer delete(Long id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }


}
