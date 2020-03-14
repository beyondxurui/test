package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能： while语句轮询 来检测某一个条件
 * 缺陷：线程B 不断地通过 while语句轮询 来检测某一个条件，这样会导致CPU的浪费
 * 创建时间：2020年03月14日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 9:32
 *
 * @auther jlxu
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        MyList list = new MyList();
        ThreadA threadA = new ThreadA("A", list);
        ThreadB threadB = new ThreadB("B", list);
        threadA.start();
        threadB.start();

        //1）不加while（true）
        //,,,,,
        //添加了1个元素
        //添加了2个元素
        //添加了3个元素

        //2）加
        //,,,,,
        //
        //
        //,,,
        // 添加了1个元素
        //添加了2个元素
        //java.lang.InterruptedException
        //	at com.jlxu.demo.concurrency.communication.ThreadB.run(Test.java:87)
        //添加了3个元素

        //ps：TODO:
        //TODO:加不加while区别：因为是volatile,实现同步和可见性(并没有加锁)
        //TODO:当线程启动后，会执行run方法 加了while就一致执行，直到发生异常
        //TODO:what 异常？throw？ 代码层面的理解：如果一个方法throw 了异常，那么该方法就不执行接下来的,且方法结束
        //TODO:但是线程B的方法异常，并不影响线程A方法执行（ps的第一行已经提到了）

    }
}

class MyList {
    private volatile List<String> list = new ArrayList<>();             //volatile回顾，TODO:第一时间没反应过来，为什么要volatile，原因是：还是没理解线程安全问题

    public void add() {
        list.add("abc");
    }

    public int size() {
        return list.size();
    }
}

@Slf4j
class ThreadA extends Thread {
    private MyList list;

    public ThreadA(String name, MyList list) {
        super(name);
        this.list = list;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 3; i++) {
                list.add();
                log.info("添加了" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

@Slf4j
class ThreadB extends Thread {
    private MyList list;

    public ThreadB(String name, MyList list) {
        super(name);
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
                log.info(",,,,,");
                if (list.size() == 2) {
                    log.info("==2了，线程b要退出了");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
