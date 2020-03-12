package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能 lockInterruptibly()
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 21:39
 *
 * @auther jlxu
 */
@Slf4j
public class Test4 {
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Test4 test = new Test4();
        MyThread2 thread1 = new MyThread2(test, "A");
        MyThread2 thread2 = new MyThread2(test, "B");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);//Thread.sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
        //TODO:结果不唯一
        //1）注释
        //time=1584024211519线程A得到了锁...
        //A执行finally...
        //线程 A释放了锁
        //time=1584024221537线程B得到了锁...
        //B执行finally...
        //线程 B释放了锁
        //2）不注释
        //time=1584024001353线程A得到了锁...
        //time=1584024003348 ,线程 B被中断...
        //A执行finally...
        //线程 A释放了锁

        //ps:TODO:需要搭配thread2.interrupt()使用
    }

    public void insert(Thread thread) throws InterruptedException {
        //TODO:注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将 InterruptedException 抛出
        //位子1）
        lock.lockInterruptibly();
        try {
            //位子2）
            // 注意，如果将获取锁放在try语句块里，则必定会执行finally语句块中的解锁操作。
            // 若线程在获取锁时被中断，则再执行解锁操作就会导致异常，因为该线程并未获得到锁。
            //lock.lockInterruptibly();
            //22:46:15.208 [B] INFO com.jlxu.demo.concurrency.lock.Test4 - B执行finally...
            //Exception in thread "B" java.lang."IllegalMonitorStateException"
            //	at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
            //	at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
            //	at java.util.concurrent.locks.ReentrantLock.unlock(ReentrantLock.java:457)
            //	at com.jlxu.demo.concurrency.lock.Test4.insert(Test4.java:72)
            //	at com.jlxu.demo.concurrency.lock.MyThread2.run(Test4.java:93)

            log.info("time=" + System.currentTimeMillis() + "线程" + thread.getName() + "得到了锁...");
            long now = System.currentTimeMillis();
            for (; ; ) {//这种循环  哎嘿
                if (System.currentTimeMillis() - now > 10000) {
                    break;
                }
                //插入数据
            }
            //TODO:不catch 而是抛出
        } finally {
            log.info(Thread.currentThread().getName() + "执行finally...");
            lock.unlock();
            log.info("线程 " + thread.getName() + "释放了锁");
        }


    }
}

@Slf4j
class MyThread2 extends Thread {

    private Test4 test = null;

    public MyThread2(Test4 test, String name) {
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