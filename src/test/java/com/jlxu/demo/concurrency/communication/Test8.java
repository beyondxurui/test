package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 功能： join()
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test8 {

    public static void main(String[] args) {
        log.info("进入线程" + Thread.currentThread().getName());
        Test8 test = new Test8();
        Thread8 thread1 = test.new Thread8();
        thread1.start();
        try {
            log.info("线程" + Thread.currentThread().getName() + "等待");
            thread1.join();
            log.info("线程" + Thread.currentThread().getName() + "继续执行");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //进入线程main
        //线程main等待
        //进入线程Thread-0
        //线程Thread-0执行完毕
        //线程main继续执行

    }

    //读线程
    class Thread8 extends Thread {
        @Override
        public void run() {
            log.info("进入线程" + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            log.info("线程" + Thread.currentThread().getName() + "执行完毕");
        }
    }

}




