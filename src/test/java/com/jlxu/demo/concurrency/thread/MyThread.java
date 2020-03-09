package com.jlxu.demo.concurrency.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年03月08日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/3/8 20:37
 */
@Slf4j
public class MyThread extends Thread {
    public void run() {
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
//            Thread.yield();  //让线程重回就绪状态
            count++;
        }
        long end = System.currentTimeMillis();
        log.info("time difference value ===>{}", end - start);//Thread.yield()是否注释  （1）没注释：8   （2）注释：0
    }
}
