package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能：多线程环境下懒汉单例线程安全测试
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:49
 *
 * @auther jlxu
 */
public class Test2 {

    public static void main(String[] args) {
        MyThread2[] myThreads = new MyThread2[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread2();
        }
        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }

}

@Slf4j
class MyThread2 extends Thread {
    @Override
    public void run() {
        int hash = Singleton2.getSingleton().hashCode();
        log.info("hashCode :{}", hash);
        //hashCode :1509621224
        //hashCode :1379545873
        //hashCode :1347156237
        //hashCode :680699774
        //hashCode :316905017
        //hashCode :1015697085
        //hashCode :1186257927
        //hashCode :856529417
        //hashCode :1472769210
        //hashCode :830572430
    }
}
