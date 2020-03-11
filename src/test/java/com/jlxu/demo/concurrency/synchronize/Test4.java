package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能  class 对象锁
 * 创建时间：2020年03月10日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 21:04
 *
 * @auther jlxu
 */
@Slf4j
public class Test4 {
    public static void main(String[] args) {
        final InsertData4 insertData = new InsertData4();

        // 启动线程 1
        new Thread() {
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();
        // 启动线程 1
        new Thread() {
            @Override
            public void run() {
                insertData.insert1();
            }
        }.start();

        //22:29:06.813 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData4 - 执行insert
        //22:29:06.813 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData4 - 执行insert1
        //22:29:06.820 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData4 - insert1执行完毕
        //22:29:11.820 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData4 - insert执行完毕

    }
}

@Slf4j
class InsertData4 {
    //非 static synchronized 方法
    public synchronized void insert() {
        try {
            log.info("执行insert");
            Thread.sleep(5000);
            log.info("insert执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //static synchronized
    public synchronized static void insert1() {
        log.info("执行insert1");
        log.info("insert1执行完毕");
    }

}