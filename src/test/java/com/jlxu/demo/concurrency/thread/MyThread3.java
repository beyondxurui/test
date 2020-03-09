package com.jlxu.demo.concurrency.thread;

import com.sun.imageio.plugins.common.I18N;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年03月08日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/3/8 20:37
 */
@Slf4j
public class MyThread3 extends Thread {
    public void run() {
        int i = 0;
//        while (i < Integer.MAX_VALUE) {  太大
        while (i < 1000) {
            log.info(i + " while循环");
            i++;
        }
    }
}
