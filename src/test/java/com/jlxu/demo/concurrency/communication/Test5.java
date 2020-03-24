package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能： 传统实现方式
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test5 {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        P_Thread p_thread1 = new P_Thread(stack, "p1");
        P_Thread p_thread2 = new P_Thread(stack, "p2");
        P_Thread p_thread3 = new P_Thread(stack, "p3");
        P_Thread p_thread4 = new P_Thread(stack, "p4");
        P_Thread p_thread5 = new P_Thread(stack, "p5");
        P_Thread p_thread6 = new P_Thread(stack, "p6");
        p_thread1.start();
        p_thread2.start();
        p_thread3.start();
        p_thread4.start();
        p_thread5.start();
        p_thread6.start();

        C_Thread c_thread1 = new C_Thread(stack, "c1");
        C_Thread c_thread2 = new C_Thread(stack, "c2");
        C_Thread c_thread3 = new C_Thread(stack, "c3");
        C_Thread c_thread4 = new C_Thread(stack, "c4");
        C_Thread c_thread5 = new C_Thread(stack, "c5");
        C_Thread c_thread6 = new C_Thread(stack, "c6");
        C_Thread c_thread7 = new C_Thread(stack, "c7");
        C_Thread c_thread8 = new C_Thread(stack, "c8");
        c_thread1.start();
        c_thread2.start();
        c_thread3.start();
        c_thread4.start();
        c_thread5.start();
        c_thread6.start();
        c_thread7.start();
        c_thread8.start();


        //线程 p1 生产了，队列已满...
        //队列已满，线程 p2 呈wait状态...
        //队列已满，线程 p3 呈wait状态...
        //队列已满，线程 p4 呈wait状态...
        //队列已满，线程 p5 呈wait状态...
        //队列已满，线程 p6 呈wait状态...
        //线程 c1 生产了，队列已空...
        //队列已空，线程 c2 呈wait状态...
        //线程 p6 生产了，队列已满...
        //队列已满，线程 p5 呈wait状态...
        //队列已满，线程 p4 呈wait状态...
        //队列已满，线程 p3 呈wait状态...
        //队列已满，线程 p2 呈wait状态...
        //线程 c2 生产了，队列已空...
        //队列已空，线程 c3 呈wait状态...
        //线程 p2 生产了，队列已满...
        //队列已满，线程 p3 呈wait状态...
        //队列已满，线程 p4 呈wait状态...
        //队列已满，线程 p5 呈wait状态...
        //线程 c3 生产了，队列已空...
        //线程 p5 生产了，队列已满...
        //队列已满，线程 p4 呈wait状态...
        //队列已满，线程 p3 呈wait状态...
        //线程 c4 生产了，队列已空...
        //线程 p3 生产了，队列已满...
        //队列已满，线程 p4 呈wait状态...
        //线程 c5 生产了，队列已空...
        //线程 p4 生产了，队列已满...
        //线程 c6 生产了，队列已空...
        //队列已空，线程 c7 呈wait状态...
        //队列已空，线程 c8 呈wait状态...

        //存在多个线程都在wait

    }
}

@Slf4j
class MyStack {
    private final List list = new ArrayList();

    //生成
    public synchronized void product() {
        try {
            while (list.size() == 1) {
                log.info("队列已满，线程 "
                        + Thread.currentThread().getName() + " 呈wait状态...");
                this.wait();
            }
            list.add("anyString=" + Math.random());
            log.info("线程 " + Thread.currentThread().getName()
                    + " 生产了，队列已满...");
            this.notifyAll();  //防止生产者仅通知生产者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //消费
    public synchronized String consumer() {
        String returnValue = "";
        try {
            while (list.size() == 0) {
                log.info("队列已空，线程 "
                        + Thread.currentThread().getName() + " 呈wait状态...");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            log.info("线程 " + Thread.currentThread().getName()
                    + " 消费了，队列已空...");
            this.notifyAll();  //防止消费者仅通知消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }


}

@Slf4j
class P_Thread extends Thread {
    private MyStack stack;

    public P_Thread(MyStack stack, String name) {
        super(name);
        this.stack = stack;
    }

    @Override
    public void run() {
        stack.product();
    }
}

@Slf4j
class C_Thread extends Thread {
    private MyStack stack;

    public C_Thread(MyStack stack, String name) {
        super(name);
        this.stack = stack;
    }

    @Override
    public void run() {
        stack.consumer();
    }
}
