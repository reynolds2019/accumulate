package com.nuosi.lowcode.entity;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name ApacheJavaBeanUtil
 * @desc 基于Apache的BeanUtils工具包做JavaBean转换器
 * 类比BeanUtils、ConvertUtils、PropertyUtils
 * @date 2021/2/3 23:12
 */
public class ApacheJavaBeanUtil {

    @Test
    public void javaBeanTransformMapTest() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("reynolds");
        user.setAge(18);
        user.setEmailAddress("fsofs@163.com");
        try {
            System.out.println(ApacheJavaBeanUtil.javaBeanTransformMap(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mapTransformJavaBeanTest() {
        Map userMap = new HashMap();
        userMap.put("userId",1);
        userMap.put("userName","reynolds");
        userMap.put("age",18);
        userMap.put("emailAddress","fsofs@163.com");
        try {
            System.out.println(ApacheJavaBeanUtil.mapTransformJavaBean(userMap, User.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map javaBeanTransformMap(Object bean) throws Exception {
        BeanMap result = new BeanMap(bean);
        return result;
    }

    public static <Type> Type mapTransformJavaBean(Map<String, Object> propMap, Class<Type> clazz )  throws Exception {
        Type bean = null;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, propMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
