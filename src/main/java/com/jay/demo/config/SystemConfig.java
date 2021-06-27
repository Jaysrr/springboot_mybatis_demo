package com.jay.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: springboot_mybatis_demo
 * @description: 自定义系统设置
 * @author: Jaysrr
 * @create: 2021-06-27 00:29
 **/
@Component
@Data
public class SystemConfig {

    @Value(value = "${config.systemName}") //这一行是从yml中取得的
    private String systemName;


}
