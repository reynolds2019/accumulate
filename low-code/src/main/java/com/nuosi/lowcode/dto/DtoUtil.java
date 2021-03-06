package com.nuosi.lowcode.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name DtoUtil
 * @desc 针对Dto增强toString方法
 * lombok自带增强toString方法
 * @date 2021/3/6 18:20
 */
public class DtoUtil {

    public static String toString(Object obj){
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.DEFAULT_STYLE);
    }

    public static String toString1(Object obj){
        ToStringBuilder builder = new ToStringBuilder(obj);
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(obj));
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }

    public static String toString2(Object obj){
        if(obj!=null) {
            StringBuilder buff = new StringBuilder("[");
            Class cls = obj.getClass();
            Field[] fields = cls.getDeclaredFields(); //反射获取该对象里面的所有变量
            try {
                for (Field field : fields) {
                    field.setAccessible(true); //强制允许访问私有变量
                    Object value = null;
                    value = field.get(obj);
                    value = value == null ? "null" : value;
                    buff.append(field.getName() + "=\"" + value.toString() + "\","); //变量值装String
                }
                buff.setLength(buff.length()-1);
            } catch (IllegalAccessException e) {
                //抛出异常和编码
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            buff.append("]");
            return buff.toString();
        }else{
            return "null";
        }
    }
}
