package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能:多线程环境下饿汉单例线程安全测试
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:49
 *
 * @auther jlxu
 */
public class Test {

    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread();
        }
        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }

}

@Slf4j
class MyThread extends Thread {
    @Override
    public void run() {
        int hash = Singleton.getSingleton().hashCode();
        log.info("hashCode :{}", hash);
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
        //hashCode :856529417
    }
}
