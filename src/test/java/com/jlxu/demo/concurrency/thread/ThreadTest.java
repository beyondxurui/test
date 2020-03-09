package com.jlxu.demo.concurrency.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 :Thread源码测试
 * 创建时间：2020年03月08日
 * 文件名称：ThreadTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/8 20:35
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadTest {
    // Thread.yield();测试  //让线程重回就绪状态
    @Test
    public void threadTest() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);//处理main线程提前消亡的问题  TODO：当前执行的线程睡眠（**），不一定是main，用在这里一般是main
    }

    //实例方法join()
    @Test
    public void threadTest2() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();//感觉可以用（join（）就是调用join（0）方法）join（0）解决（提前消亡）方法然宿主线程等待
        //当 main线程 运行到 thread1.join() 时，main线程会获得线程对象thread1的锁(wait 意味着拿到该对象的锁)。只要 thread1线程 存活， 就会调用该对象锁的wait()方法阻塞 main线程，直至 thread1线程 退出才会使 main线程 得以继续执行。
        //ps:这句第二个括号后面文字有带你绕，但是还是能理解的（先按照自己最开始的理解的就好，再结合它的，即可）

        //线程的生命周期（TODO:***）
        //当我们需要线程来执行某个子任务时，就必须先创建一个线程。但是线程创建之后，
        // 不会立即进入就绪状态，因为线程的运行需要一些条件（比如程序计数器、Java栈、本地方法栈等），
        // 只有线程运行需要的所有条件满足了，才进入就绪状态。当线程进入就绪状态后，不代表立刻就能获取CPU执行时间，
        // 也许此时CPU正在执行其他的事情，因此它要等待。当得到CPU执行时间之后，线程便真正进入运行状态。
        // 线程在运行状态过程中，可能有多个原因导致当前线程不继续运行下去，
        // 比如用户主动让线程睡眠（睡眠一定的时间之后再重新执行）、用户主动让线程等待，或者被同步块阻塞，
        // 此时就对应着多个状态：time waiting（睡眠或等待一定的时间）、waiting（等待被唤醒）、blocked（阻塞）。
        // 当由于突然中断或者子任务执行完毕，线程就会被消亡。  和图
        //二、上下文切换（TODO:***）
        //以单核CPU为例，CPU在一个时刻只能运行一个线程。
        // CPU在运行一个线程的过程中，转而去运行另外一个线程，这个叫做线程 上下文切换（对于进程也是类似）。
        //三、
        //（1）对wait方法新的理解
        //Object的方法，那个线程对象调用了这个方法，会释放线程（那个线程）占有的锁，别的线程就有机会获取锁（即获取cup的执行权）
        //join方法中调用wait方法
        //同理
        // join方法同样会会让线程交出CPU执行权限；
        //join方法同样会让线程释放对一个对象持有的锁；
        //如果调用了join方法，必须捕获InterruptedException异常或者将该异常向上层抛出。
        //（2）sleep
        //如果调用了sleep方法，必须捕获InterruptedException异常或者将该异常向上层抛出；
        //TODO:sleep方法不会释放锁，TODO:也就是说"如果"当前线程持有对“某个对象”的锁，则即使调用sleep方法，其他线程也无法访问这个对象。
        //TODO:调用sleep方法相当于让线程进入阻塞状态
        //（3）yield
        //调用yield()方法并不会让线程进入阻塞状态，而是让线程重回就绪状态，它只需要等待重新得到 CPU 的执行；
        //TODO:它同样不会释放锁。
        //（4）join
        //假如在main线程中调用thread.join方法，则main线程会等待thread线程执行完毕或者等待一定的时间
        //join方法同样会会让线程交出CPU执行权限；
        //TODO:join方法同样会让线程释放对一个对象持有的锁；
        //如果调用了join方法，必须捕获InterruptedException异常或者将该异常向上层抛出。
        //解释:
        //join()方法是通过wait()方法 (Object 提供的方法) 实现的。当 millis == 0 时，会进入 while(isAlive()) 循环，并且只要子线程是活的，宿主线程就“不停的等待”。 wait(0) 的作用是让当前线程(宿主线程)等待，
        // TODO:而这里的当前线程是指 Thread.currentThread() 所返回的线程。所以，虽然是子线程对象(锁)调用wait()方法，但是阻塞的是宿主线程。
        //由wait必有notify，怎么唤醒main线程，thread1对象锁调用底层C++的notifyAll方法(什么时候没说？)
        //（5）interrupt 方法  TODO：单独调用interrupt方法可以使得 处于阻塞状态的线程 抛出一个异常，也就是说，它可以用来中断一个正处于阻塞状态的线程  不是太懂
        //（6）stop和暂停和恢复的方法 TODO:不懂/不太清晰，尤其是博主关于锁的分析部分
        //（7）上下文切换
        //以单核CPU为例，CPU在一个时刻只能运行一个线程。CPU在运行一个线程的过程中，转而去运行另外一个线程，这个叫做线程 上下文切换（对于进程也是类似）。
    }

    //interrupt 方法  注：不同分类测试，请注释掉其他的分析代码
    @Test
    public void threadTest3() {
        //定义
        //interrupt，顾名思义，即中断的意思。
        // 单独调用interrupt方法可以使得 处于阻塞状态的线程 抛出一个异常，也就是说，它可以用来中断一个正处于阻塞状态的线程；
        // 另外，通过 interrupted()方法 和 isInterrupted()方法 可以停止正在运行的线程

        //（1）阻塞线程中断测试
//        MyThread2 myThread2 = new MyThread2();
//        myThread2.start();
//        try {
//            Thread.currentThread().sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        myThread2.interrupt();
        //日志
        //进入睡眠状态
        //得到中断异常
        //un方法执行完毕
        //ps什么是阻塞状态 看桌面图（名：线程状态图，Thread状态图  三种情况）
        //TODO:IO或同步块什么意思？
        //什么是(线程)同步块和IO阻塞
        //（1）	什么是同步块
        //被Java 同步关键字（synchronized）修饰的代码块
        //栗子
        //    synchronized(this){
        //       this.count += value;
        //    }
        //同步块阻塞我理解：如thread（的run方法调用了一a方法，a方法中有一个同步块 synchronized(this：线程thread，即线程thread持有的锁)）调用了sleep（释放cpu，不释放当前线程（线程thread）持有的锁）
        // ，当线程thread2强到cpu执行权时，执行run含有a方法，那么thread2同步块阻塞  暂且这么理解吧，更具总的（3）好像是这么回事

        //（2）能不能中断处于非阻塞状态的线程呢？
//        MyThread3 myThread3 = new MyThread3();
//        myThread3.start();
//        try {
//            Thread.currentThread().sleep(2000);
//        } catch (InterruptedException e) {
//        }
//        myThread3.interrupt();

        //(3)那么如何终端非阻塞的线程呢？
        //如果配合 isInterrupted()/interrupted() 能够中断正在运行的线程，因为调用interrupt()方法相当于将中断标志位置为true，那么可以通过调用isInterrupted()/interrupted()判断中断标志是否被置位来中断线程的执行
//        MyThread4 myThread4 = new MyThread4();
//        myThread4.start();
//        try {
//            log.info("current thead===>{}", Thread.currentThread().getName()); //TODO:Thread.currentThread()：获取当前执行的线程，可以是任何执行的线程
//            Thread.currentThread().sleep(2000);
//        } catch (InterruptedException e) {
//        }
//        myThread4.interrupt();

        //（4） 改进（3）TODO: 一个是安全（volatile）其他的呢？
        MyThread5 myThread5 = new MyThread5();
        myThread5.start();
        System.out.println();
        try {
            Thread.currentThread().sleep(20000);
        } catch (InterruptedException e) {
        }
        myThread5.interrupt();

        //ps：TODO:isInterrupted()方法判断不懂
    }

    public void test(){
        //总结***
        //（1）TODO:若该操作是静态方法，也就是说，该方法属于类而非具体的某个对象，那么该操作的作用对象就是 currentThread() 方法所返回 Thread 对象；
        //（2）TODO:若该操作是实例方法，也就是说，该方法属于对象，那么该操作的作用对象就是调用该方法的 Thread 对象
        //（3）TODO：线程一旦被阻塞，就会释放 CPU；
        //（4）TODO:当线程出现异常且没有捕获处理时，JVM会自动释放当前线程占用的锁，因此不会由于异常导致出现死锁现象。
        //（5）TODO：对于一个线程，CPU 的释放 与 锁的释放没有必然联系。  ****
        //（6）Thread类 中的方法调用与线程状态关系如下图 TODO?回头仔细看
    }

}
