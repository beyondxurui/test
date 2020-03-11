package com.jlxu.demo.concurrency.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能：以下类用于生成对每个线程唯一的局部标识符。线程 ID 是在第一次调用 uniqueNum.get() 时分配的，在后续调用中不会更改。
 * 创建时间：2020年03月11日
 * 文件名称：UniqueThreadIdGenerator
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 19:58
 *
 * @auther jlxu
 */
@Slf4j
public class UniqueThreadIdGenerator {
    private static final AtomicInteger uniqueId = new AtomicInteger(0);
    //TODO：final回顾：final修饰的变量/引用需要初始化  基本类型不能被修改，应用类型内容可变，但引用不能指向其他对象

    private static final ThreadLocal<Integer> uniqueNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return uniqueId.getAndIncrement();  //Atomically increments by one the current value.原子地增加一个当前值。
        }
    };//这段代码的含义是返回一个对象，方法不调用不会

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            String name = "Thread-" + i;
            threads[i] = new Thread(name) {
                public void run() {
                    log.info(Thread.currentThread().getName() + ":" + uniqueNum.get());
                }
            };
            threads[i].start();
        }
        log.info(Thread.currentThread().getName() + ":" + uniqueNum.get());
    }
    //打印结果
    //Thread-4:5
    //Thread-0:1
    //Thread-3:4
    //Thread-2:3
    //main:0
    //Thread-1:2

    //ps:
    // new AtomicInteger(0);通过源码可知：为value(volatile修饰)赋值为0
    // uniqueId.getAndIncrement() 见上  TODO:这个是关键
    // TODO:创建线程，为线程生成唯一的id（因为重写 initialValue()方法了），又因为get方法会调用 initialValue()方法，所有每次加一（uniqueId.getAndIncrement()是原子的保证了数据同步）
    // 打印结果这样：因为线程相互竞争

}
