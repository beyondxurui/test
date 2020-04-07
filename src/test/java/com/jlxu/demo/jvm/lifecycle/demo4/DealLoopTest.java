package com.jlxu.demo.jvm.lifecycle.demo4;

/**
 * 功能：在一个类的<clinit>()方法中有耗时很长的操作，就可能造成多个线程阻塞，在实际应用中这种阻塞往往是隐藏的
 * 创建时间：2020年04月04日
 * 文件名称：DealLoopTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 22:51
 *
 * @auther jlxu
 */
public class DealLoopTest {
    static {
        System.out.println("DealLoopTest...");

    }

    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread()
                        + "init DeadLoopClass");
                while (true) {
                    // 模拟耗时很长的操作
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        Thread thread = new Thread(r);
        Thread thread2 = new Thread(r);
        thread.start();
        thread2.start();

        //DealLoopTest...
        //Thread[Thread-0,5,main] start
        //Thread[Thread-1,5,main] start
        //Thread[Thread-0,5,main]init DeadLoopClass

        //ps:久久不能退出

    }
}
