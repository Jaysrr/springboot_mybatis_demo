package com.jay.demo.filter;

import com.jay.demo.pojo.Account;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springboot_mybatis_demo
 * @description: 拦截器，用于鉴权，分配资源等功能
 * @author: Jaysrr
 * @create: 2021-06-23 23:23
 **/

@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {
    //无需任何状态,可以直接访问的urls
    private final String[] IGNORE_URIS = {
            "/index",
            "/account/validateAccount",
            "/account/login",
            "/account/register",
            "/css/",
            "/js/",
            "/images",
    };

    // 所作的拦截的工作
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        //判断这个uri是否是需要拦截的
        boolean pass = canPass(uri);
        if (pass) {
            //能通过，说明不需拦截
            filterChain.doFilter(request, response);
            return; //必须return,否则还会继续往下
        }
        //否则需要拦截,在拦截前,看看是否登录
        Account loginAccount = (Account) request.getSession().getAttribute("account");
        System.out.println("session's loginAccount = " + loginAccount);

        if (null == loginAccount) {
            System.out.println("进来 null == loginAccount 了");
            response.sendRedirect("/account/login");  //注意,由于这里是uri,所以对应的是后端请求接口,
            return; //必须return,否则还会继续往下
        }


        System.out.println("----filter----" + uri);
        filterChain.doFilter(request, response);
    }

    private boolean canPass(String uri) {
        // 判断 访问的URI 起始部分 是否包含 Ignore
        // 下级目录资源也能访问
        for (String ignoreUri : IGNORE_URIS) {
            if (uri.startsWith(ignoreUri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 加载 Filter 启动之前需要的资源
        System.out.println("---------------AccountFilter Init....");

        Filter.super.init(filterConfig);
    }


}
