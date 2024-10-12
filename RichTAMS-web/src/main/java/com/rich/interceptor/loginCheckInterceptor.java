package com.rich.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.rich.pojo.Result;
import com.rich.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
//创建拦截器
public class loginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MVC内部正在执行拦截器......");

        System.out.println("过滤器被执行");
        //1.获取请求路径
        String url = request.getRequestURI();
        log.info("被实施过滤的url:{}",url);

        //2.判断是否为登录请求
        //注册时过滤，此处不必实现

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
            return false ;//拦截
        }
        //5.解析token，如果解析失败，返回错误结果（NOT_LOGIN）具体返回什么遵循开发文档
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
            return false ;//拦截;
        }
        //6.放行
        log.info("令牌合法，放行");
        return true;//放行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器执行完毕，将访问controller");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
