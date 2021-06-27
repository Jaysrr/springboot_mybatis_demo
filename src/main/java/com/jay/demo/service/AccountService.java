package com.jay.demo.service;

import com.github.pagehelper.PageInfo;
import com.jay.demo.pojo.Account;
import com.jay.demo.utils.JsonResult;

import java.util.List;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-23 22:33
 **/
public interface AccountService {


    Account findByLoginNameAndPassword(String loginName, String password);

    PageInfo<Account> findByPage(Integer pageNum, Integer pageSize);

    JsonResult deleteById(Integer id);

    Integer add(Account account);

    Integer updatePassword(String newPassword, Account account);
}
