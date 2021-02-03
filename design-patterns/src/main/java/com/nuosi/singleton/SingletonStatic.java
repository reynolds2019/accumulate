package com.nuosi.singleton;

import java.util.concurrent.SynchronousQueue;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name Singleton
 * @desc JVM在类加载时保证了线程安全性问题，以及instance的唯一性
 * @date 2021/1/18 21:27
 */
public class SingletonStatic {
    static int count = 0; //第二次调用getInstance方法延时
    private SingletonStatic() {
        //must, otherwise JVM create default constructor(public)
        if (count==0) {
            count++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static SingletonStatic getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static SingletonStatic instance = new SingletonStatic();
    }

    /**
     * @name main
     * @version 0.1.0
     * @desc 测试Singleton相关逻辑
     * @param args
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/1/26 14:51
     */
    public static void main(String[] args) {
        /*线程间传递SingletonDCLVerif对象*/
        SynchronousQueue<SingletonStatic> queue = new SynchronousQueue<SingletonStatic>();
        /*延时实例化对象*/
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                SingletonStatic obj2 = SingletonStatic.getInstance();
                System.out.println("obj2 = " + obj2);
                try {
                    queue.put(obj2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        /*立即实例化对象*/
        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                SingletonStatic obj1 = SingletonStatic.getInstance();
                System.out.println("obj1 = " + obj1);
                try {
                    SingletonStatic obj2 = queue.take();
                    System.out.println("obj1==obj2 : " + (obj1 == obj2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(run1).start();
        new Thread(run2).start();
    }
}
