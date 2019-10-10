package com.btyc.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author ams
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
