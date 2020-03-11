package com.jlxu.demo.concurrency.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能：通过ThreadLocal能达到在每个线程中创建变量副本的效果
 * 创建时间：2020年03月11日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 20:38
 *
 * @auther jlxu
 */
@Slf4j
public class Test {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) {

        final Test test = new Test();
        test.set();
        log.info("主线程main");//主线程main
        log.info(test.getLong() + "");//1
        log.info(test.getString());//main

        new Thread() {
            @Override
            public void run() {
                test.set();
                log.info("\n子线程 Thread-0 ：");//子线程 Thread-0 ：
                log.info(test.getLong() + "");//11
                log.info(test.getString());//Thread-0
            }
        }.start();

        //ps:回顾了线程的优先级[1,10]和守护线程

    }
}
