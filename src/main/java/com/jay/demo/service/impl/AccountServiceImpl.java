package com.jay.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jay.demo.dao.AccountMapper;
import com.jay.demo.pojo.Account;
import com.jay.demo.pojo.AccountExample;
import com.jay.demo.service.AccountService;
import com.jay.demo.utils.JsonResult;
import com.jay.demo.utils.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springboot_mybatis_demo
 **/

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public Account findByLoginNameAndPassword(String loginName, String password) {
        List<Account> accountList = null;
        try {
            String encodePwdByMd5 = Md5Util.encodeByMd5(password);

            AccountExample accountExample = new AccountExample();
            accountExample.createCriteria()
                    .andNameEqualTo(loginName)
                    .andPasswordEqualTo(encodePwdByMd5);

            accountList = accountMapper.selectByExample(accountExample);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList.size() == 0 ? null : accountList.get(0);
    }

    @Override
    public PageInfo< Account > findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.selectByExample(new AccountExample());
        return new PageInfo<>(accountList, 3); //3代表导航栏只显示3个页
        // 下标
    }

    @Override
    public JsonResult deleteById(Integer id) {
        int row = accountMapper.deleteByPrimaryKey(id);
        if (row == 1) {
            return JsonResult.ok();
        } else {
            return JsonResult.error("删除出错");
        }
    }

    @Override
    public Integer add(Account account) {
        //写注册业务.. 简写了下给密码加密, 其余略
        try {
            account.setPassword(Md5Util.encodeByMd5(account.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountMapper.insertSelective(account);
    }

    @Override
    public Integer updatePassword(String newPassword, Account account) {
        Account newAccount = new Account();
        BeanUtils.copyProperties(account, newAccount);
        try {
            newAccount.setPassword(Md5Util.encodeByMd5(newPassword));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accountMapper.updateByPrimaryKeySelective(newAccount);
    }



}
