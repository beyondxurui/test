package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能 假如有多个线程要同时进行读操作的话，ReentrantReadWriteLock 的 readLock().lock()
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 21:39
 *
 * @auther jlxu
 */
@Slf4j
public class Test6 {
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final Test6 test = new Test6();
        new Thread("A") {
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();
        new Thread("B") {
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();


        //ps：不连续的
    }

    private void get(Thread thread) {
        rw.readLock().lock();

        try {
            long start = System.currentTimeMillis();
            log.info("线程" + thread.getName() + "开始读操作...");
            while (System.currentTimeMillis() - start <= 5) {
                log.info("线程" + thread.getName() + "正在进行读操作...");
            }
            log.info("线程" + thread.getName() + "读操作完毕...");
        } finally {
            rw.readLock().unlock();
        }
    }
}
