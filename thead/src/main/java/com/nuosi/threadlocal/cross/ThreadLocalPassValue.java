package com.nuosi.threadlocal.cross;

import org.junit.Test;

/**
 * @name ThreadLocalPassValue
 * @version 0.1.0
 * @desc 演练ThreadLocal跨线程传值问题
 * @author nuosi fsofs@163.com
 * @date 2021/2/3 0:26
 */
public class ThreadLocalPassValue {

    /**
     * @name acrossThreadsPassValueFail
     * @version 0.1.0
     * @desc 主线程ThreadLocal中有值，子线程没有
     * @param
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 0:27
     */
    @Test
    public void acrossThreadsPassValueFail(){
        final ThreadLocal<String> mainThreadLocal = new ThreadLocal<String>();
        mainThreadLocal.set("main thread");
        System.out.println("level one. thread name:" + Thread.currentThread().getName()
                + "; value:" + mainThreadLocal.get());

        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("level two. thread name:" + Thread.currentThread().getName()
                        + "; value:" + mainThreadLocal.get());
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
     * @name acrossThreadsPassValueSuccess
     * @version 0.1.0
     * @desc 主线程ThreadLocal中有值，子线程也有
     * @param
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 0:30
     */
    @Test
    public void acrossThreadsPassValueSuccess(){
        final ThreadLocal<String> mainThreadLocal = new InheritableThreadLocal<String>();
        mainThreadLocal.set("main thread");
        System.out.println("level one. thread name:" + Thread.currentThread().getName()
                + "; value:" + mainThreadLocal.get());

        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("level two. thread name:" + Thread.currentThread().getName()
                        + "; value:" + mainThreadLocal.get());
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
     * @name acrossMultiThreadsPassValueSuccess
     * @version 0.1.0
     * @desc 多级线程共享一级线程ThreadLocal中的值
     * @param
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 0:53
     */
    @Test
    public void acrossMultiThreadsPassValueSuccess(){
        final ThreadLocal<String> mainThreadLocal = new InheritableThreadLocal<String>();
        mainThreadLocal.set("main thread");
        System.out.println("level one. thread name:" + Thread.currentThread().getName()
                + "; cross value:" + mainThreadLocal.get());
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("level two. thread name:" + Thread.currentThread().getName()
                        + "; value:" + mainThreadLocal.get());
                new Thread(new Runnable(){
                    public void run() {
                        System.out.println("level three. thread name:" + Thread.currentThread().getName()
                                + "; value:" + mainThreadLocal.get());
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
