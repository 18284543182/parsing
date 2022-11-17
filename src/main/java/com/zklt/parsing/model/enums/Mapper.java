package com.zklt.parsing.model.enums;

import com.zklt.parsing.model.entity.Message;

import java.lang.annotation.*;

/**
 * @author jhzhu
 * @date 2022/11/17 17:09
 * @Description: 解析映射
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapper {

    String type() default "";

    Class<? extends Message> getAction();

}
