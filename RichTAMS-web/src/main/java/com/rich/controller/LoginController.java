package com.rich.controller;

import com.rich.pojo.Emp;
import com.rich.pojo.Result;
import com.rich.service.EmpService;
import com.rich.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("用户准备登录，用户信息为{}",emp);
        Emp emp1=empService.login(emp);

        //判断是否登录成功
        if(emp1!=null){
            Map<String,Object> map=new HashMap<>();
            map.put("id",emp1.getId());
            map.put("name",emp1.getName());
            map.put("username",emp1.getUsername());

            String jwt= JwtUtils.generateJwt(map);
            System.out.println("jwt如下："+jwt);
            log.info("用户登录成功，用户名：{}",emp1.getUsername());
            return Result.success(jwt);//下发JWT令牌
        }

        log.info("用户登录失败，用户名或密码错误");
        return Result.error("Error : 用户名或密码错误");
    }
}
