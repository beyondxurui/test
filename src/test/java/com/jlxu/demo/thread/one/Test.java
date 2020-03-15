package com.jlxu.demo.thread.one;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:Runnable实现资源共享
 * 创建时间：2020年03月15日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/15 9:24
 *
 * @auther jlxu
 */
public class Test {

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

@Slf4j
class MyRunnable implements Runnable {

    private static final List<Integer> list = new ArrayList<>(30);

    @Override
    public void run() {

        while (list != null && list.size() < 30) {
            list.add(list.size());
            log.info("买了第" + (list.size() + "张票"));
        }
    }
}


