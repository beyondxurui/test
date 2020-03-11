package com.jlxu.demo.concurrency.synchronize;

/**
 * 功能
 * 创建时间：2020年03月10日
 * 文件名称：MyThread7
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 19:42
 *
 * @auther jlxu
 */
public class MyThread7 extends Thread {
    private MyService2 myService;

    public MyThread7(MyService2 myService) {//构造初始化   TODO:（+2）
        super();
        this.myService = myService;
    }

    public void run() {
        try {
            myService.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
