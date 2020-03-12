package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能 tryLock(long time, TimeUnit unit)
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 21:39
 *
 * @auther jlxu
 */
@Slf4j
public class Test3 {
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Test3 test = new Test3();
        MyThread thread1 = new MyThread(test, "A");
        MyThread thread2 = new MyThread(test, "B");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
        //TODO:结果不唯一
        //TODO：知道 tryLock(long time, TimeUnit unit)和Thread 的interrupt()含义
        //1）thread2.interrupt()注释
        //time=1584023145688线程A得到了锁...
        //线程B放弃了对锁的获取...

        //2）thread2.interrupt()注释
        //time=1584023216301线程A得到了锁...
        //time=1584023218300 ,线程 B被中断...

    }

    public void insert(Thread thread) throws InterruptedException {
        if (lock.tryLock(4, TimeUnit.SECONDS)) {
            try {
                log.info("time=" + System.currentTimeMillis() + "线程" + thread.getName() + "得到了锁...");
                long now = System.currentTimeMillis();
                while (System.currentTimeMillis() - now < 5000) {
                    // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,
                    // 此处用这种方法空转5秒
                }
            } finally {
                lock.unlock();
            }
        } else {
            log.info("线程" + thread.getName() + "放弃了对锁的获取...");
        }

    }
}

@Slf4j
class MyThread extends Thread {

    private Test3 test = null;

    public MyThread(Test3 test, String name) {
        super(name);
        this.test = test;
    }

    @Override
    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            log.info("time=" + System.currentTimeMillis() + " ,线程 " + Thread.currentThread().getName() + "被中断...");
        }
    }
}