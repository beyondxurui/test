package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能： wait()
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test2 {

    private static Object object = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();

        //线程Thread-0获取到了锁...
        //线程Thread-0阻塞并释放锁...
        //线程Thread-1获取到了锁...
        //线程Thread-1唤醒了正在wait的线程...
        //线程Thread-1执行完成...
        //线程Thread-0执行完成...
    }

    @Slf4j
    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                log.info("线程" + Thread.currentThread().getName()
                        + "获取到了锁...");
                try {
                    log.info("线程" + Thread.currentThread().getName()
                            + "阻塞并释放锁...");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程" + Thread.currentThread().getName()
                        + "执行完成...");
            }
        }
    }

    @Slf4j
    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                log.info("线程" + Thread.currentThread().getName()
                        + "获取到了锁...");
                object.notify();
                log.info("线程" + Thread.currentThread().getName()
                        + "唤醒了正在wait的线程...");
                log.info("线程" + Thread.currentThread().getName()
                        + "执行完成...");
            }
        }
    }
}

