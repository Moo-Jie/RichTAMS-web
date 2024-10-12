package com.rich.Exception;

import com.rich.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice// 全局异常处理
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//Handler处理者，管理者
    public Result handleException(Exception e) {
        return Result.error("对不起，系统出现异常，请联系管理员:");
    }
}