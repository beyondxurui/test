package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能：同步延迟加载 — 使用内部类实现延迟加载
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:49
 *
 * @auther jlxu
 */
public class Test3 {

    public static void main(String[] args) {
        MyThread3[] myThreads = new MyThread3[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread3();
        }
        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }

}

@Slf4j
class MyThread3 extends Thread {
    @Override
    public void run() {
        int hash = Singleton3.getSingleton().hashCode();
        log.info("hashCode :{}", hash);
    }
}
