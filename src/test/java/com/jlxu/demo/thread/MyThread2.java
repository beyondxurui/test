package com.jlxu.demo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:实现多线程方式二：实现Runable接口，重写run方法
 * 创建时间：2020年02月28日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/2/28 20:43
 *
 * @auther jxlu
 */
@Slf4j
public class MyThread2 implements Runnable {


    @Override
    public void run() {
        String name = Thread.currentThread().getName();//获取当前线程
        //所有：日志是一样的
        log.info(name + "线程,执行了：发出请求信号");
    }
}
