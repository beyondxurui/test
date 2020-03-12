package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能 ReentrantLock锁
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 21:39
 *
 * @auther jlxu
 */
@Slf4j
public class Test {
    private final List<Integer> arrayList = new ArrayList<>();// TODO:回顾 加final
    private final Lock lock = new ReentrantLock(); //2） 注意这个地方：lock 被声明为成员变量

    public static void main(String[] args) {
        Test test = new Test();
        new Thread("A") {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
        new Thread("B") {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
        //1）局部变量
        //线程B得到了锁...
        //线程A得到了锁...
        //arrayList size ===> 10
        //线程A释放了锁...
        //arrayList size ===> 10
        //线程B释放了锁...
        //2）
        //线程A得到了锁...
        //arrayList size ===> 5
        //线程A释放了锁...
        //线程B得到了锁...
        //arrayList size ===> 10
        //线程B释放了锁...
    }

    private void insert(Thread thread) {
//        Lock lock = new ReentrantLock();//1）局部变量
        lock.lock();
        try {
            log.info("线程" + thread.getName() + "得到了锁...");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
            log.info("arrayList size ===> {}", arrayList.size());//
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("线程" + thread.getName() + "释放了锁...");
            lock.unlock();
        }
    }
}
