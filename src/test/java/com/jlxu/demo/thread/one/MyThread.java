package com.jlxu.demo.thread.one;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:实现多线程方式一：继承Thread，重写run方法
 * 创建时间：2020年02月28日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/2/28 20:43
 *
 * @auther jxlu
 */
@Slf4j
public class MyThread extends Thread {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();//获取当前线程
        String name2 = MyThread.currentThread().getName();//MyThread:当前线程
        //所有：日志是一样的
        log.info(name + "线程,执行了：发出请求信号");
        log.info(name2 + "线程,执行了：发出请求信号");
    }
}
