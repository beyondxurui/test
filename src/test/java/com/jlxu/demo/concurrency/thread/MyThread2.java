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
public class MyThread2 extends Thread {
    public void run() {
        log.info("进入睡眠状态");
        try {
            Thread.currentThread().sleep(20000);//当前线程
            log.info("睡眠完毕");
        } catch (InterruptedException e) {
            log.info("得到中断异常");
            e.printStackTrace();
        }
        log.info("run方法执行完毕");
    }
}
