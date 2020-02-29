package com.jlxu.demo.thread.three;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年02月29日
 * 文件名称：LoggingWidget
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 21:29
 *
 * @auther jlxu
 */
@Slf4j
public class LoggingWidget extends Widget {
    public synchronized void doSomething() {
        log.info("LoggingWidget中的this ===> {}", this);//LoggingWidget中的this ===> com.jlxu.demo.thread.three.LoggingWidget@47e2e487
        log.info("LoggingWidget中的super ===> {}", super.toString());//为什么必须toString
        //LoggingWidget中的super ===> com.jlxu.demo.thread.three.LoggingWidget@47e2e487
        super.doSomething();
    }
}
