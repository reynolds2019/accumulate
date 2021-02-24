/**
 * @name package-info
 * @version 0.1.0
 * @desc 作用：描述和记录本包信息
 * 1、为标注在包上Annotation提供便利；
 * 2、声明友好类和包常量；
 * 3、提供包的整体注释说明。
 * @author nuosi fsofs@163.com
 * @date 2021/2/24 11:33
 */
/*
* 1.提供包的整体注释说明。
*/
/** 2.为标注在包上Annotation提供便利。 */
@PkgAnnotation
package com.nuosi.packageinfo;

/** 3.声明友好类和包常量(通常不使用)*/
class PkgConst{
    static final String PACAKGE_CONST="const";
}
class PkgClass{
    public void hello(){};
}