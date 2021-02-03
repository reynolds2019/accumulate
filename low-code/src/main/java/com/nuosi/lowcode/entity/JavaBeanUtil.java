package com.nuosi.lowcode.entity;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name JavaBeanUtil
 * @desc 基于Introspector做JavaBean转换器
 * @date 2021/2/3 21:56
 */
public class JavaBeanUtil {

    @Test
    public void javaBeanTransformMapTest() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("reynolds");
        user.setAge(18);
        user.setEmailAddress("fsofs@163.com");
        System.out.println(JavaBeanUtil.javaBeanTransformMap(user));
    }

    @Test
    public void mapTransformJavaBeanTest() {
        Map userMap = new HashMap();
        userMap.put("userId",1);
        userMap.put("userName","reynolds");
        userMap.put("age",18);
        userMap.put("emailAddress","fsofs@163.com");
        System.out.println(JavaBeanUtil.mapTransformJavaBean(userMap, User.class));
    }

    public static Map<String, Object> javaBeanTransformMap(Object bean) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor des : descriptors) {
                String fieldName = des.getName();
                Method getter = des.getReadMethod();
                Object fieldValue = getter.invoke(bean, new Object[]{});
                System.out.println("fieldName=" + fieldName + " getter=" + getter + " fieldValue=" + fieldValue);
                if (!fieldName.equalsIgnoreCase("class")) {
                    result.put(fieldName, fieldValue);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <Type> Type mapTransformJavaBean(Map<String, Object> propMap, Class<Type> clazz ) {
        Type bean = null;
        try {
            bean = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor des : descriptors) {
                String fieldName = des.getName();
                if (propMap.containsKey(fieldName)) {
                    Method setter = des.getWriteMethod();
                    setter.invoke(bean, new Object[]{propMap.get(fieldName)});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
