package com.jlxu.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能:实现多线程的方式以及区别
 * 创建时间：2020年02月28日
 * 文件名称：RealizeThread
 * 版本：1.0.0
 * 最后修改时间：2020/2/28 20:41
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class RealizeThread {

    /*实现多线程方式一：*/
    @Test
    public void theadTest() {
        MyThread myThread = new MyThread();
        //为什么是main myThread.run();   错误开启线程方式  ====>main线程,执行了：发出请求信号
        //参考地址  ===>   https://blog.csdn.net/woshizisezise/article/details/79938915

        myThread.start();  // Thread-1线程,执行了：发出请求信号
    }

    /*实现多线程方式二：*/
    @Test
    public void runableTest() {
        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        Thread thread2 = new Thread(myThread2);
        //thread.run(); main线程,执行了：发出请求信号
        thread.start();// Thread-1线程,执行了：发出请求信号
        thread2.start();
    }

    //方式一和方式二的区别：方式二可以实现资源的共享   如买票例子
    //参考地址   https://blog.csdn.net/wd916913/article/details/6954317  注：文字描述有点问题，理解意思就行
    //最后总结：继承局限性，面向对象设计，健壮性  TODO

    /*实现多线程方式三：*/
    @Test
    public void callableTest() {
        MyThread3 myThread = new MyThread3();
        ExecutorService thradPool = Executors.newSingleThreadExecutor();
        Future<String> future = thradPool.submit(myThread);
        try {
            log.info("线程执行完成");
            log.info(future.get());
        } catch (InterruptedException e) {
            log.error("线程执行失败：" + e);
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.error("线程执行失败：" + e);
            e.printStackTrace();
        }
    }
    //参考地址：https://blog.csdn.net/qq_41776884/article/details/81193344

}
