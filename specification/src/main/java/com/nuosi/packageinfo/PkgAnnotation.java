package com.nuosi.packageinfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name PkgAnnotation
 * @desc TODO
 * @date 2021/2/24 11:18
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PkgAnnotation {
}