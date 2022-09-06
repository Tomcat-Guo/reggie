package com.mimi.filter;

import com.alibaba.fastjson.JSON;
import com.mimi.common.BaseContext;
import com.mimi.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1. 获取本次请求的URI
        String requestURI = request.getRequestURI();

        //2。 定义不需要处理的请求
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/login",
                "/user/loginout",
                "/user/sendMsg"
        };
        // 对不需要处理的请求放行
        boolean check = check(urls, requestURI);
        if (check){

            filterChain.doFilter(request,response);
            return;
        }
        log.info("本次请求{}需要处理",requestURI);
        //3. 对已登录放行
        Long empID = (Long) request.getSession().getAttribute("employee");
        Long userID = (Long) request.getSession().getAttribute("user");
        if (userID!=null){
            log.info("已登录，ID：{}",userID);
            BaseContext.setCurrentId(userID);
            filterChain.doFilter(request,response);
            return;
        }
        if (empID!=null){
            log.info("已登录，ID：{}",empID);
            BaseContext.setCurrentId(empID);
            filterChain.doFilter(request,response);
            return;
        }
        log.info("未登录");
        //4. 拦截，用输入流返回error
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) return true;
        }
        return false;
    }
}
