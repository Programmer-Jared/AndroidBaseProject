package com.jared.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName BindEventBus
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description 绑定EventBus注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {

}