package com.rich.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect // AOP 切面类
public class TimeAspect {

    // 切点表达式,指定// 代理的是service包下的所有类的所有方法
    @Around("execution(* com.rich.service.*.*(..))")
    //@Around("@annotation(com.rich.annotation.RunTimeLog)")//凡是有RunTime注解的方法都会被拦截
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //ProceedingJoinPoint 是 Spring AOP 框架中的一个接口，它代表了一个可以被拦截的方法执行点
        // 记录方法开始执行的时间
        long begin = System.currentTimeMillis();

        // 执行目标原始方法
        Object result = joinPoint.proceed();

        // 记录方法执行结束的时间
        long end = System.currentTimeMillis();

        // 打印日志，记录方法名和执行时间
        log.info(joinPoint.getSignature() + "方法耗时：" + (end - begin)+"ms");

        // 方法已经执行过了，要代替连接点返回目标方法的执行结果
        return result;
    }
}
