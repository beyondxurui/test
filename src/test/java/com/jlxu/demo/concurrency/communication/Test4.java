package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能： condition   Condition 实现了一种分组机制，将所有对临界资源进行访问的线程进行分组，以便实现线程间更精细化的协作，例如通知部分线程。
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test4 {

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA4 threadA4 = new ThreadA4(service);
        threadA4.setName("A");
        threadA4.start();

        ThreadB4 threadB4 = new ThreadB4(service);
        threadB4.setName("B");
        threadB4.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.signalAll_A();//TODO:方法能获得锁 那么即await方法也会释放锁

        //begin awaitA时间为1584158921077 ThreadName=B
        //begin awaitA时间为1584158921092 ThreadName=A
        //signalAll_A时间为1584158924093 ThreadName=main
        //end awaitA时间为1584158924093 ThreadName=A
    }
}

@Slf4j
class MyService {
    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA() {
        lock.lock();
        try {
            log.info("begin awaitA时间为" + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionA.await();
            log.info("  end awaitA时间为" + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        lock.lock();
        try {
            log.info("begin awaitA时间为" + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionB.await();
            log.info("  end awaitA时间为" + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A() {
        lock.lock();
        try {
            log.info("  signalAll_A时间为" + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B() {
        lock.lock();
        try {
            log.info("  signalAll_A时间为" + System.currentTimeMillis()
                    + " ThreadName=" + Thread.currentThread().getName());
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }
}

@Slf4j
class ThreadA4 extends Thread {
    private MyService service;

    public ThreadA4(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}

@Slf4j
class ThreadB4 extends Thread {
    private MyService service;

    public ThreadB4(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
