package com.jlxu.demo.thread.three;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:重入（继承）
 * 创建时间：2020年02月29日
 * 文件名称：Widget
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 21:27
 *
 * @auther jlxu
 */
@Slf4j
public class Widget {
    public synchronized void doSomething() {
        log.info("Widget中的this ===> {}", this);//Widget中的this ===> com.jlxu.demo.thread.three.LoggingWidget@47e2e487
        log.info("小熊美美哒！！！");
    }
}
