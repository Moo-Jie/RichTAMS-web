package com.rich.filter;

import com.alibaba.fastjson.JSONObject;
import com.rich.pojo.Result;
import com.rich.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*") //拦截所有请求
@Slf4j
public class loginCheckFilter implements Filter {
    //实现doFilter过滤即可，构造和销毁方法都是默认实现
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("过滤器被执行");
        //1.获取请求路径
        String url = request.getRequestURI();
        log.info("被实施过滤的url:{}",url);
        //2.判断是否为登录请求
        if(url.contains("/login")){
            log.info("为登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        //3.获取token
        String token = request.getHeader("token");
        //4.判断token是否为空
        if(token == null) {
            log.info("token为空，拦截");
            Result notLogin = Result.error("NOT_LOGIN");
            //以josn的格式返回给前端
            String json = JSONObject.toJSONString(notLogin); //fastjson是阿里巴巴提供的用于实现对象和json的转换工具类
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("令牌解析失败!");
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return;
        }
        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
    }
}
