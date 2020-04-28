package com.nuosi.accumulate.thread.local;

import org.junit.Test;

/**
 * ThreadLocal跨线程无法传参问题
 */
public class ThreadLocalCross {

    @Test
    public void crossThreadProblem(){
        final ThreadLocal<String> mainThreadLocal = new ThreadLocal<String>();
        mainThreadLocal.set("main thread");
        System.out.println("Thread Name:" + Thread.currentThread().getName()
                + "; Cross Value:" + mainThreadLocal.get());

        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Thread Name:" + Thread.currentThread().getName()
                        + "; Cross Value:" + mainThreadLocal.get());
            }
        });
        try{
            thread.start();
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void crossThreadSolution(){
        final ThreadLocal<String> mainThreadLocal = new InheritableThreadLocal<String>();
        mainThreadLocal.set("main thread");
        System.out.println("Thread Name:" + Thread.currentThread().getName()
                + "; Cross Value:" + mainThreadLocal.get());

        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Thread Name:" + Thread.currentThread().getName()
                        + "; Cross Value:" + mainThreadLocal.get());
            }
        });
        try{
            thread.start();
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 3级线程的获取情况验证
     */
    @Test
    public void crossThreadSolution2(){
        /*final ThreadLocal<String> mainThreadLocal = new InheritableThreadLocal<String>();
        mainThreadLocal.set("main thread");
        System.out.println("Thread Name:" + Thread.currentThread().getName()
                + "; Cross Value:" + mainThreadLocal.get());*/
        Thread thread = new Thread(new Runnable() {
            final ThreadLocal<String> mainThreadLocal = new InheritableThreadLocal<String>();
            public void run() {
                mainThreadLocal.set("main thread");
                System.out.println("Thread Name:" + Thread.currentThread().getName()
                        + "; Cross Value:" + mainThreadLocal.get());
                new Thread(new Runnable(){
                    public void run() {
                        System.out.println("Thread Name:" + Thread.currentThread().getName()
                                + "; Cross Value:" + mainThreadLocal.get());
                    }
                }).start();
            }
        });
        try{
            thread.start();
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
