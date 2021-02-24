package com.nuosi.packageinfo;

import java.lang.annotation.Annotation;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name BizLocal
 * @desc TODO
 * @date 2021/2/24 11:34
 */
public class BizLocal {

    public static void main(String[] args) {
        //可以通过I/O操作或配置项获得包名
        String pkgName = "com.nuosi.packageinfo";
        Package pkg = Package.getPackage(pkgName);
        //获得包上的注解
        Annotation[] annotations = pkg.getAnnotations();
        //遍历注解数组
        for(Annotation an:annotations){
            if(an instanceof PkgAnnotation){
                System.out.println("Hi,I'm the PkgAnnotation");
            }
        }
    }
    
}
