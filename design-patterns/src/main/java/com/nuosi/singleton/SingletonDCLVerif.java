package com.nuosi.singleton;

import java.util.concurrent.SynchronousQueue;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name SingletonDCLVerif
 * @desc 模拟DCL没有使用volatile产生的问题。
 * @date 2021/1/26 15:00
 */
public class SingletonDCLVerif {
    /*volatile的验证难以模拟*/
    private static volatile SingletonDCLVerif instance = null;

    private SingletonDCLVerif(boolean isWait) {
        if (isWait) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static SingletonDCLVerif getInstance(boolean isWait) {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                /*获取单例对象引用不一致*/
                instance = new SingletonDCLVerif(isWait);
                /*使用DCL获取单例对象引用一致*/
                /*if (instance == null) {
                    instance = new SingletonDCLVerif(isWait);
                }*/
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        /*线程间传递SingletonDCLVerif对象*/
        SynchronousQueue<SingletonDCLVerif> queue = new SynchronousQueue<SingletonDCLVerif>();
        /*延时实例化对象*/
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                SingletonDCLVerif obj2 = SingletonDCLVerif.getInstance(true);
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
                SingletonDCLVerif obj1 = SingletonDCLVerif.getInstance(false);
                System.out.println("obj1 = " + obj1);
                try {
                    SingletonDCLVerif obj2 = queue.take();
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
