package com.rich.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 指定该注解的生命周期为运行时
@Retention(RetentionPolicy.RUNTIME)
//指定value数组的元素类型为TYPE, METHOD，可以作用于类、方法
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RunTimeLog {
}