package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能 可重入性
 * 创建时间：2020年03月10日
 * 文件名称：Test5
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 22:10
 *
 * @auther jlxu
 */
@Slf4j
public class Test5 implements Runnable {

    //可重入锁测试
    public synchronized void get() {
        log.info("get() current thread is {}", Thread.currentThread().getName());
        set();
    }

    public synchronized void set() {
        log.info("set() current thread is {}", Thread.currentThread().getName());
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test5 test = new Test5();
        new Thread(test, "Thread-0").start();
        new Thread(test, "Thread-1").start();
        new Thread(test, "Thread-2").start();
        //22:15:21.117 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.Test5 - get() current thread is Thread-0
        //22:15:21.126 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.Test5 - set() current thread is Thread-0
        //22:15:21.126 [Thread-2] INFO com.jlxu.demo.concurrency.synchronize.Test5 - get() current thread is Thread-2
        //22:15:21.126 [Thread-2] INFO com.jlxu.demo.concurrency.synchronize.Test5 - set() current thread is Thread-2
        //22:15:21.126 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.Test5 - get() current thread is Thread-1
        //22:15:21.126 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.Test5 - set() current thread is Thread-1
    }
}
