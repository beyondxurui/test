package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能：单例模式与双重检查(Double-Check idiom)
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:49
 *
 * @auther jlxu
 */
public class Test4 {

    public static void main(String[] args) {
        MyThread4[] myThreads = new MyThread4[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread4();
        }
        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }

}

@Slf4j
class MyThread4 extends Thread {
    @Override
    public void run() {
        int hash = Singleton4.getSingleton().hashCode();
        log.info("hashCode :{}", hash);
        //少加一层检查不行
        //422938148
        //422938148
        //34876888
        //422938148
        //422938148
        //422938148
        //422938148
        //422938148
        //422938148
    }
}
