package com.jlxu.demo.concurrency.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年03月08日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/3/8 20:37
 */
@Slf4j
@Data
public class MyThread5 extends Thread {
        private volatile boolean isStop = false;
//    private boolean isStop = false;

    public void run() {
        int i = 0;

        while (i < 10000) {
            if (isInterrupted()) {
                isStop = true;
                break;
            } else {
                log.info(i + " while循环");
                i++;
            }

        }
    }
}
