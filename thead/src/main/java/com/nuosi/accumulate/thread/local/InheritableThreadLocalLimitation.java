package com.nuosi.accumulate.thread.local;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal的局限性：线程没有被创建，没有被初始化时，不会同步父线程数据。
 * 原理是：创建子线程时将父线程中的本地变量值拷贝了一份到自己这来，拷贝的时机是子线程创建时。
 * TTL通过修饰Runnable和Callable而非Thread来解决跨线程传参问题
 */
public class InheritableThreadLocalLimitation {
    static class Person {
        public Integer age = 1;
    }

    @Test
    public void limitation(){
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

    @Test
    public void limitationProblem(){
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

    @Test
    public void limitationSolution(){
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
