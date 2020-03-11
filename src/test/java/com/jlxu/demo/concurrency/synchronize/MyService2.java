package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年03月10日
 * 文件名称：MyService2
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 19:45
 *
 * @auther jlxu
 */
@Slf4j
public class MyService2 {

    private String lock = "23";

    public void test() throws InterruptedException {
        synchronized (lock) {
            log.info(" current thread name: {}", Thread.currentThread().getName() + " begin");
            lock = "234";
            Thread.currentThread().sleep(20);
            log.info(" current thread name: {}", Thread.currentThread().getName() + " end");
        }
    }
}
