package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能 Case : 公平锁
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 21:39
 *
 * @auther jlxu
 */
@Slf4j
public class Test7 {
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final Service service = new Service(true);//公平锁
//        final Service service = new Service(false);//非公平锁
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("★线程" + Thread.currentThread().getName()
                        + "运行了");
                service.serviceMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (Thread thread : threads) {
            thread.start();
        }

        //ps:TODO:Thread和Runnable创建线程的区别
    }
}

@Slf4j
class Service {
    private ReentrantLock lock;

    Service(boolean isFail) {
        super();
        lock = new ReentrantLock(isFail);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            log.info("ThreadName=" + Thread.currentThread().getName()
                    + "获得锁定");
        } finally {
            lock.unlock();
        }
    }
}
