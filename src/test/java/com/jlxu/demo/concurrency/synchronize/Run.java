package com.jlxu.demo.concurrency.synchronize;

/**
 * 功能 锁的是对象而非引用
 * 创建时间：2020年03月10日
 * 文件名称：Run
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 20:29
 *
 * @auther jlxu
 */

//测试
public class Run {
    public static void main(String[] args) throws InterruptedException {

        //临界资源
        MyService service = new MyService();

        //线程A
        ThreadA a = new ThreadA(service);
        a.setName("A");

        //线程B
        ThreadB b = new ThreadB(service);
        b.setName("B");

        a.start();
        System.out.println(Thread.currentThread().getName());
//        Thread.sleep(50);  //这里的线程是main线程 存在50毫秒  Thread.currentThread().sleep(50)效果一样
        b.start();
        //（1）Thread.sleep(50)不注释
        //A begin 1583843866671
        //B begin 1583843866714
        //A   end 1583843868671
        //B   end 1583843868714
        //由上述结果可知，线程 A、B 是异步的。
        // 因为50毫秒过后， 线程B 取得的锁对象是 “456”，而 线程A 依然持有的锁对象是 “123”。
        // 所以，这两个线程是异步的。若将上述语句 “Thread.sleep(50);”
        //（2）Thread.sleep(50)注释
        //A begin 1583843991650
        //B begin 1583843991651
        //A   end 1583843993651
        //B   end 1583843993652
        //TODO: 结果一样的  why why why


    }
}

//资源类
class MyService {
    private String lock = "123";

    public void testMethod() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " begin "
                        + System.currentTimeMillis());
                lock = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "   end "
                        + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//线程B
class ThreadB extends Thread {

    private MyService service;

    public ThreadB(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

//线程A
class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service) {
        super();
        this.service = service;
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
