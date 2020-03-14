package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能：单例模式 与 ThreadLocal
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:49
 *
 * @auther jlxu
 */
public class Test5 {

    public static void main(String[] args) {
        MyThread5[] myThreads = new MyThread5[50];
        for (int i = 0; i < 50; i++) {
            myThreads[i] = new MyThread5();
        }
        for (int i = 0; i < 50; i++) {
            myThreads[i].start();
        }
    }

}

@Slf4j
class MyThread5 extends Thread {
    @Override
    public void run() {
        int hash = Singleton5.getSingleton().hashCode();
        log.info("hashCode :{}", hash);
    }
}
