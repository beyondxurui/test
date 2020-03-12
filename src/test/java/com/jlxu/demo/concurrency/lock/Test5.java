package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能 假如有多个线程要同时进行读操作的话，先看一下synchronized达到的效果
 * 创建时间：2020年03月12日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/12 21:39
 *
 * @auther jlxu
 */
@Slf4j
public class Test5 {
    public static void main(String[] args) {
        final Test5 test = new Test5();
        new Thread("A") {
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();
        new Thread("B") {
            @Override
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

        //线程B开始读操作...
        //线程B读操作完毕...
        //线程A开始读操作...
        //线程A读操作完毕...

        //ps：连续的
    }

    private void get(Thread thread) {
        long start = System.currentTimeMillis();
        log.info("线程" + thread.getName() + "开始读操作...");
        while (System.currentTimeMillis() - start <= 5) {
            log.info("线程" + thread.getName() + "正在进行读操作...");
        }
        log.info("线程" + thread.getName() + "读操作完毕...");
    }
}
