package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能： while语句轮询 来检测某一个条件
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test3 {

    public static void main(String[] args) {
        String lock = "";
        ThreadSubtract subtract1Thread = new ThreadSubtract("subtract1Thread", lock);
        subtract1Thread.start();
        ThreadSubtract subtract21Thread = new ThreadSubtract("subtract21Thread", lock);
        subtract21Thread.start();

        try {
            Thread.sleep(1000);  //在那个线程里面执行，就睡眠那个线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadAnd addThread = new ThreadAnd("addThread", lock);
        addThread.start();

        //if处理
        //wait begin ThreadName=subtract1Thread
        //wait begin ThreadName=subtract21Thread
        //wait   end ThreadName=subtract21Thread
        //list size=0
        //wait   end ThreadName=subtract1Thread
        //Exception in thread "subtract1Thread" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //	at java.util.ArrayList.remove(ArrayList.java:496)
        //	at com.jlxu.demo.concurrency.communication.ThreadSubtract.run(Test3.java:79)

        //while处理
        //wait begin ThreadName=subtract1Thread
        //wait begin ThreadName=subtract21Thread
        //wait   end ThreadName=subtract21Thread
        //list size=0
        //wait   end ThreadName=subtract1Thread
        //wait begin ThreadName=subtract1Thread
    }
}

class ValueObject {
    public static final List<String> list = new ArrayList<>();//这里不用volatile 因为用内置锁（同步互斥）
    //没有volatile，这里是测试而已
}

@Slf4j
class ThreadAnd extends Thread {
    private String lock;

    public ThreadAnd(String name, String lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {

        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();//唤醒所有的wait线程
        }
    }
}

@Slf4j
class ThreadSubtract extends Thread {
    private String lock;

    public ThreadSubtract(String name, String lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
//                if (ValueObject.list.size() == 0) {//id处理
                while (ValueObject.list.size() == 0) {//while处理
                    log.info("wait begin ThreadName=" + Thread.currentThread().getName());
                    lock.wait();
                    log.info("wait   end ThreadName=" + Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                log.info("list size=" + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
