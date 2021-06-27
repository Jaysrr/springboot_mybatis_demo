package com.jay.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-23 22:19
 **/
@Controller
public class IndexController {
    @GetMapping("/index")
    public String login() {
        return "/index";
    }
}
