package com.jlxu.demo.thread.three.runstart;

/**
 * 功能：线程的run和start方法区别    https://blog.csdn.net/woshizisezise/article/details/79938915
 * 创建时间：2020年03月22日
 * 文件名称：MyThread
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 10:54
 *
 * @auther jlxu
 */
public class RunStartTest {
    public static void main(String[] args) {
        //源码分析

        //
        Thread thread1 = new Thread(new MyThread());
        Thread thread2 = new Thread(new MyThread());
        //Thread实例start
        //thread1.start();
        //thread2.start();
        //0
        //0
        //1
        //1
        //2
        //2
        //3
        //3
        //4
        //4

        //Thread实例的run
        //thread1.run();
        //thread2.run();
        //0
        //1
        //2
        //3
        //4
        //0
        //1
        //2
        //3
        //4

        //Runnable的run方法
        MyThread runnable1 = new MyThread();
        MyThread runnable2 = new MyThread();
        runnable1.run();
        runnable2.run();

        //0
        //1
        //2
        //3
        //4
        //0
        //1
        //2
        //3
        //4

        //ps run是（可能）并发  run是串行的  Thread，Runnable （Callable）实例调用无关

    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(i);
                Thread.sleep(1000);//TODO：睡哈子
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
