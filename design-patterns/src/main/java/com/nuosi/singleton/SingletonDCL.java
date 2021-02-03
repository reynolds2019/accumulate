package com.nuosi.singleton;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name SingletonDCL
 * @desc 使用DCL和关键字volatile保障线程安全
 * @date 2021/1/18 21:27
 */
public class SingletonDCL {
    private static volatile SingletonDCL instance = null;

    private SingletonDCL() {}

    public static SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
