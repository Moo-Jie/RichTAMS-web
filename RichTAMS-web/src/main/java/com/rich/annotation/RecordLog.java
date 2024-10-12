package com.rich.annotation;

import java.lang.annotation.*;

/**
 * 自定义Log注解
 */
@Target({ElementType.METHOD})//Target 目标
@Documented
@Retention(RetentionPolicy.RUNTIME)//Retention 保留
public @interface RecordLog {
}