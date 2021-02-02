package com.nuosi.threadlocal.cross;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @name InheritableThreadLocalLimitation
 * @version 0.1.0
 * @desc 1)InheritableThreadLocal的局限性：线程没有被创建，没有被初始化时，不会同步父线程数据。
 * 原因：创建子线程时将父线程中的本地变量值拷贝了一份到自己这来，拷贝的时机是子线程创建时。
 * 2)TTL可以解决相同问题。TTL通过修饰Runnable和Callable而非Thread来解决跨线程传参问题
 * @author nuosi fsofs@163.com
 * @date 2021/2/3 1:07
 */
public class InheritableThreadLocalLimitation {
    static class Person {
        public Integer age = 1;
    }

    /**
     * @name singleThreadLimitation
     * @version 0.1.0
     * @desc InheritableThread单线程时继承父线程值失败
     * @param
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 0:49
     */
    @Test
    public void singleThreadFail(){
        final ThreadLocal<Person> personLocal = new InheritableThreadLocal<Person>();
        final ExecutorService pool = Executors.newSingleThreadExecutor();
        personLocal.set(new Person());
        pool.execute(new Runnable() {
            public void run() {
                Person person = personLocal.get();
                System.out.println("线程名：" + Thread.currentThread().getName() + "，数据为：" + person.age + "，对象为：" + person);
            }
        });
        Person newPerson = new Person();
        newPerson.age = 18;
        personLocal.set(newPerson); // 给线程重新绑定值
        pool.execute(new Runnable() {
            public void run() {
                Person person = personLocal.get();
                System.out.println("线程名：" + Thread.currentThread().getName() + "，数据为：" + person.age + "，对象为：" + person);
            }
        });
        try {
            Thread.sleep(1000); // 打印结果需要等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @name multiThreadSuccess
     * @version 0.1.0
     * @desc InheritableThread多线程时继承父线程值成功
     * @param
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 1:10
     */
    @Test
    public void multiThreadSuccess(){
        final ThreadLocal<Person> personLocal = new InheritableThreadLocal<Person>();
        final ExecutorService pool = Executors.newFixedThreadPool(2);
        personLocal.set(new Person());
        pool.execute(new Runnable() {
            public void run() {
                Person person = personLocal.get();
                System.out.println("线程名：" + Thread.currentThread().getName() + "，数据为：" + person.age + "，对象为：" + person);
            }
        });
        Person newPerson = new Person();
        newPerson.age = 18;
        personLocal.set(newPerson); // 给线程重新绑定值
        pool.execute(new Runnable() {
            public void run() {
                Person person = personLocal.get();
                System.out.println("线程名：" + Thread.currentThread().getName() + "，数据为：" + person.age + "，对象为：" + person);
            }
        });
        try {
            Thread.sleep(1000); // 打印结果需要等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @name limitationSolution
     * @version 0.1.0
     * @desc TTL单线程时继承父线程值成功，对比InheritableThread单线程时失败
     * @param
     * @return void
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 1:11
     */
    @Test
    public void multiThreadSuccessByTtl(){
        // 实现类使用TTL
        final ThreadLocal<Person> personLocal = new TransmittableThreadLocal<Person>();
        // 线程池使用TTL包装
        final ExecutorService pool = TtlExecutors.getTtlExecutorService(Executors.newSingleThreadExecutor());
        personLocal.set(new Person());
        pool.execute(new Runnable() {
            public void run() {
                Person person = personLocal.get();
                System.out.println("线程名：" + Thread.currentThread().getName() + "，数据为：" + person.age + "，对象为：" + person);
            }
        });
        Person newPerson = new Person();
        newPerson.age = 18;
        personLocal.set(newPerson); // 给线程重新绑定值
        pool.execute(new Runnable() {
            public void run() {
                Person person = personLocal.get();
                System.out.println("线程名：" + Thread.currentThread().getName() + "，数据为：" + person.age + "，对象为：" + person);
            }
        });
        try {
            Thread.sleep(1000); // 打印结果需要等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
