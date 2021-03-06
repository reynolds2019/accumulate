package com.nuosi.lowcode.dto;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name DtoUtilTest
 * @desc TODO
 * @date 2021/3/6 18:22
 */
public class DtoUtilTest {
    Var var;

    @Before
    public void before(){
        var = new Var();
        var.setId("goods_name");
        var.setName("货物名称");
        var.setType("string");
    }

    @Test
    public void testToString(){
        System.out.println("toString:" + DtoUtil.toString(var));
        Assert.assertTrue(true);
    }

    @Test
    public void testToString1(){
        System.out.println("toString1:" + DtoUtil.toString1(var));
        Assert.assertTrue(true);
    }

    @Test
    public void testToString2(){
        System.out.println("toString2:" + DtoUtil.toString2(var));
        Assert.assertTrue(true);
    }

    @After
    public void after(){}
}
