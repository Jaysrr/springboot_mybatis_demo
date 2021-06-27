package com.jay.demo.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-23 11:20
 **/

@Data
public class GoodsRequest {

    private String name;

//    private String code; //需要随机生成

    private String type;

    private String brand;

    private BigDecimal price;

    private String intro;

    private Long warehouse;
//    private String creater; 单点登陆下的用户

//    private Date ctime;
//
//    private String updater; 单点登陆下的用户
//
//    private Date utime;

//    private Boolean status; //添加之后，设置为可用，1代表可用状态

//    private String picture;


}
