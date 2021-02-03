package com.nuosi.lowcode.entity;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name ApachePropertyUtils
 * @desc 对比PropertyUtils和BeanUtils
 * @date 2021/2/3 23:38
 */
public class ApachePropertyUtils {

    @Test
    public void comparePropertyUtilsAndBeanUtils() throws Exception {
        User user = new User();
        BeanUtils.setProperty(user, "age", "18");
        System.out.println(BeanUtils.getProperty(user, "age"));
        System.out.println(BeanUtils.getProperty(user, "age").getClass().getName());

        try {
            // 不会基本类型自动包装，所以报错IllegalArgumentException
            PropertyUtils.setProperty(user, "age", "18");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
