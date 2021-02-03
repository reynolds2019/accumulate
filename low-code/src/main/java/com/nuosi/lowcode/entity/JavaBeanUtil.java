package com.nuosi.lowcode.entity;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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
        try {
            System.out.println(JavaBeanUtil.javaBeanTransformMap(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mapTransformJavaBeanTest() {
        Map userMap = new HashMap();
        userMap.put("userId", 1);
        userMap.put("userName", "reynolds");
        userMap.put("age", 18);
        userMap.put("emailAddress", "fsofs@163.com");
        try {
            System.out.println(JavaBeanUtil.mapTransformJavaBean(userMap, User.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> javaBeanTransformMap(Object bean) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();

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

        return result;
    }

    public static <Type> Type mapTransformJavaBean(Map<String, Object> propMap, Class<Type> clazz) throws Exception {
        Type bean = clazz.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor des : descriptors) {
            String fieldName = des.getName();
            if (propMap.containsKey(fieldName)) {
                Method setter = des.getWriteMethod();
                setter.invoke(bean, new Object[]{propMap.get(fieldName)});
            }
        }

        return bean;
    }
}
