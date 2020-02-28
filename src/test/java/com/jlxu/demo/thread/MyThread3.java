package com.jlxu.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 功能:实现多线程方式三：实现Callable接口
 * 创建时间：2020年02月28日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/2/28 20:43
 *
 * @auther jxlu
 */
@Slf4j
public class MyThread3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        String name = Thread.currentThread().getName();
        log.info(name + "线程执行：发送请求信号");
        return "hello world";
    }
}
