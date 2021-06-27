package com.jay.demo.controller;

import com.github.pagehelper.PageInfo;
import com.jay.demo.AccountConstants;
import com.jay.demo.config.SystemConfig;
import com.jay.demo.pojo.Account;
import com.jay.demo.response.RespObject;
import com.jay.demo.service.AccountService;
import com.jay.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-23 19:12
 **/
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    SystemConfig systemConfig;


    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/registerPost")
    @ResponseBody
    public JsonResult registerPost(@RequestBody Account account) {
        Integer rst = accountService.add(account);
        return AccountConstants.SUCCESS_NUM.equals(rst)
                ? JsonResult.ok(AccountConstants.SUCCESS) : JsonResult.error();
    }

    //多加这一行注释，为了辨别 是传到master上的
    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("config", systemConfig);

        return "account/Login";
    }

    //    只返数据的版本
    @PostMapping("/validateAccount")
    @ResponseBody
    public JsonResult validateAccount(@RequestParam(required = true) String loginName,
                                      @RequestParam(required = true) String password,
                                      HttpServletRequest request) {
        Account loginAccount = accountService.findByLoginNameAndPassword(loginName, password);
        if (loginAccount == null) {
            return JsonResult.error("登录失败");
        } else {
            request.getSession().setAttribute("account", loginAccount);
            return JsonResult.ok("success");
        }
    }

//    // 返数据，并将这个数据传给前端作为跳转的依据
//    @PostMapping("/validateAccount")
//    @ResponseBody
//    public String validateAccount(@RequestParam(required = true) String loginName,
//                                      @RequestParam(required = true) String password,
//                                      HttpServletRequest request) {
//        Account loginAccount = accountService.findByLoginNameAndPassword(loginName, password);
//        if (loginAccount == null) {
//            return "登录失败";
//        } else {
//            request.getSession().setAttribute("account", loginAccount);
//            return    "success" ;
//        }
//    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "/index";
    }

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "3") Integer pageSize) {
        PageInfo<Account> pageInfo = accountService.findByPage(pageNum, pageSize);

        model.addAttribute("pageInfo", pageInfo);
        return "/account/list";
    }

    @GetMapping("/updatePwd")
    public String updatePwd() {
        return "account/updatePwd";
    }

    /**
     * 1.更新得提示用户
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public JsonResult updatePassword(HttpServletRequest request, String password) {

        Account account = (Account) request.getSession().getAttribute("account");
        System.out.println("account = " + account);

        if (account == null) {
            return JsonResult.error("用户未登录,请检查登录状态");
        }
        Integer rst = accountService.updatePassword(password, account);
        if (AccountConstants.SUCCESS_NUM.equals(rst)) {
            request.getSession().removeAttribute("account");
            return JsonResult.ok(rst);
        } else {
            return JsonResult.error("更新失败");
        }
    }

    /**
     * 1.删除得提示用户
     * 2，通过删除标记，而不是真正删除数据； 修改也是 只做增量，而不是真的修改
     * 那么数据多了，就存的作为历史数据表（或数据库）
     */
    @GetMapping("/deleteById")
    @ResponseBody
    public JsonResult deleteById(@RequestParam Integer id) {
        return JsonResult.ok(accountService.deleteById(id));
    }


    @RequestMapping("/profile")
    public String profile() {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "static/upload/");
            System.out.println(upload.getAbsolutePath());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "account/profile";
    }


    /**
     * 中文字符 上传文件
     *
     * @param filename
     * @param password
     * @return
     */
    @RequestMapping("/fileUploadController")
    public String fileUpload(MultipartFile filename, String password) {
        System.out.println("password:" + password);
        System.out.println("file:" + filename.getOriginalFilename());
        try {

            File path = new File(ResourceUtils.getURL("file:").getPath()); //如果直接存本地,则地址就是下面这行
            File upload = new File(path.getAbsolutePath(), "my_upload_path/"); //这个对应是要上传的真实地址
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(), "static/upload/");//这个对应是要上传的本地映射地址,映射到真实（yml中的）地址

            System.out.println("upload:" + upload);

            filename.transferTo(new File(upload + "/" + filename.getOriginalFilename()));


        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "account/profile";
    }


}
