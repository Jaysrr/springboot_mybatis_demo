package com.jay.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: springboot_mybatis_demo
 * @description: 自己创的pojo
 * @author: Jaysrr
 * @create: 2021-06-22 19:11
 **/
@Data
public class Order {

    Long id;
    String goodsName;
    String orderNo;
    Long count;
    BigDecimal multi;
    String intro;
    String creator;
    Date createTime;
    String updater;
    Date updateTime;
    Integer status;
    Short type;


}
