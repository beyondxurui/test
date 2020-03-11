package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能 synchronized代码块
 * 创建时间：2020年03月10日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 21:04
 *
 * @auther jlxu
 */
@Slf4j
public class Test3 {
    public static void main(String[] args) {
        final InsertData3 insertData3 = new InsertData3();
        final InsertData31 insertData31 = new InsertData31();
        new Thread() {
            @Override
            public void run() {
                long begin = System.currentTimeMillis();
                insertData3.insert(Thread.currentThread());
                log.info("InsertData3 time {}", System.currentTimeMillis() - begin);//InsertData31 time 6
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                long begin = System.currentTimeMillis();
                insertData31.insert(Thread.currentThread());
                log.info("InsertData31 time {}", System.currentTimeMillis() - begin);//InsertData31 time 6
            }
        }.start();
        //22:05:29.604 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData31 - Thread-1插入数据0
        //22:05:29.603 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData3 - Thread-0插入数据0
        //22:05:29.609 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData31 - Thread-1插入数据1
        //22:05:29.609 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData3 - Thread-0插入数据1
        //22:05:29.609 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData31 - Thread-1插入数据2
        //22:05:29.609 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData3 - Thread-0插入数据2
        //22:05:29.609 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData31 - Thread-1插入数据3
        //22:05:29.609 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData3 - Thread-0插入数据3
        //22:05:29.609 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData3 - Thread-0插入数据4
        //22:05:29.609 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData31 - Thread-1插入数据4
    }
}

@Slf4j
class InsertData3 {
    private List<Integer> arrayList = new ArrayList<>();
    private Object object = new Object();

    public void insert(Thread thread) {
        synchronized (object) {
            for (int i = 0; i < 5; i++) {
                log.info(thread.getName() + "插入数据" + i);
                arrayList.add(i);
            }
        }
    }
}

@Slf4j
class InsertData31 {
    private List<Integer> arrayList = new ArrayList<>();

    public void insert(Thread thread) {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                log.info(thread.getName() + "插入数据" + i);
                arrayList.add(i);
            }
        }
    }
}