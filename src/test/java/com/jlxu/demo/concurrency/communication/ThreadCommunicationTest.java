package com.jlxu.demo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能
 * 创建时间：2020年03月14日
 * 文件名称：ThreadCommunicationTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 0:07
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadCommunicationTest {
    @Test
    public void threadCommunicationTest() {
        //摘要：最典型的例子就是生产者-消费者问题
        // 一. 引子，介绍实现的逻辑
        //实现案例（实现方式）
        /**
         *  while语句轮询 来检测某一个条件
         *  缺陷：缺陷：线程B 不断地通过 while语句轮询 来检测某一个条件，这样会导致CPU的浪费
         *  怎么解决：需要一种机制来减少 CPU资源 的浪费，而且还能实现多个线程之间的通信，即 wait/notify 机制
         *  {@link com.jlxu.demo.concurrency.communication.Test}
         */
        //二. wait/notify 机制
        //TODO:在这之前，线程间通过共享数据来实现通信，即多个线程主动地读取一个共享数据，通过 同步互斥访问机制 保证线程的安全性。

        // 等待/通知机制 主要由 Object类 中的以下三个方法保证：
        //1、wait()、notify() 和 notifyAll()
        //TODO：上述三个方法均非Thread类中所声明的方法，而是Object类中声明的方法。原因是每个对象都拥有monitor（锁），所以让当前线程等待某个对象的锁，当然应该通过这个对象来操作，而不是用当前线程来操作，因为当前线程可能会等待多个线程的锁，如果通过线程来操作，就非常复杂了。

        // 1) wait()
        //让 当前线程 (Thread.concurrentThread() 方法所返回的线程) 释放对象锁并进入等待（阻塞）状态。  TODO:当前线程释放锁并等待
        //(1) 方法声明 (2) 方法作用  (3) 方法使用条件
        //(4) TODO:异常    运行时（不受检查）异常  受检查异常 (中断阻塞线程，抛 InterruptedException并终止线程，释放锁，释放CPU)
        //2) notify()
        //唤醒"一个"正在等待相应对象锁的线程，使其进入就绪队列，以便在当前线程释放锁后竞争锁，进而得到CPU的执行。  //TODO:在当前线程释放锁后竞争锁，指的是调用notify()的线程执行完？  不是见总结部分
        //，，，（4）异常：运行时（不受检查）异常
        //3) notifyAll()
        //唤醒所有正在等待相应对象锁的线程，使它们进入就绪队列，以便在当前线程释放锁后竞争锁，进而得到CPU的执行。
        //，，，（4）异常：运行时（不受检查）异常）
        //总结 TODO:
        //1:本地方法final修饰
        //2:调用某个对象的 wait() 方法能让 当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）；
        //3:调用某个对象的 notify() 方法能够唤醒 一个正在等待这个对象的monitor的线程，如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程；
        //4:调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程。
        //ps：TODO:释放的那个锁（monitor）（那个对象的锁）？ 调用这个对象的notify()/notifyAll()，才起到唤醒作用，被唤醒的线程，然后竞争锁，得到CPU的执行

        //2、方法调用与线程状态关系
        //每个锁对象都有两个队列，一个是就绪队列，一个是阻塞队列
        //见图
        /**
         * 使用举例
         * {@link com.jlxu.demo.concurrency.communication.Test2}
         *
         */

        /**
         * 多个同类型线程的场景（wait 的条件发生变化）（两种处理方式if和while）
         * {@link com.jlxu.demo.concurrency.communication.Test3}
         */
        //三. Condition
        //Condition是在java 1.5中出现的，它用来替代传统的Object的wait()/notify()实现线程间的协作，它的使用依赖于 Lock   TODO：代替
        //必须要注意的是，Condition 的 await()/signal() 使用都必须在lock保护之内，也就是说，必须在lock.lock()和lock.unlock之间才可以使用
        /**
         * Condition 实现了一种分组机制，将所有对临界资源进行访问的线程进行分组，以便实现线程间更精细化的协作，例如通知部分线程。
         * 即：当满足某条件时，把某个/某写个分组线程唤醒
         * {@link com.jlxu.demo.concurrency.communication.Test4}
         */

        //四. 生产者-消费者模型
        //”等待/通知机制“ 最经典的应用就是 生产者-消费者模型。下面以多生产者-多消费者问题为背景，分别运用两种模式 —— synchronized+wait-notify模式和Lock+Condition模式实现 wait-notify 机制。
        //Case 1： 传统实现方式
        /**
         * TODO:注意this指的是谁
         * {@link com.jlxu.demo.concurrency.communication.Test5}
         */
        //TODO:在多个同类型线程（多个生产者线程或者消费者线程）的场景中，为防止wait的条件发生变化而导致线程异常终止，我们在阻塞线程被唤醒的同时还必须对wait的条件进行额外的检查，即 使用 while 循环代替 if 条件；
        //TODO:在多个同类型线程（多个生产者线程或者消费者线程）的场景中，为防止生产者(消费者)唤醒生产者(消费者)，保证生产者和消费者互相唤醒，需要 使用 notify 替代 notifyAll.  ===> ？
        //Case 2： 使用 Condition 实现方式
        /**
         * TODO:注意锁是那个
         * {@link com.jlxu.demo.concurrency.communication.Test6}
         */
        //ps:两种方式同样的效果粒度不同？TODO:
        //TODO：看打印日志回顾  自己二、的总结中ps 释放的是那个锁（那个对象的锁）

        //五.线程间的通信：管道
        //PipedInputStream类 与 PipedOutputStream类 用于在应用程序中创建管道通信
        //在 Java 的 JDK 中，提供了四个类用于线程间通信：
        //字节流：PipedInputStream 和 PipedOutputStream;  字符流：PipedReader 和 PipedWriter;
        //ps:类似于生成者和消费者  知道in和out大小关系的含义
        /**
         * 线程间的通信：管道（PipedInputStream和PipedOutputStream的实现原理类似于"生产者-消费者"原理）
         * {@link com.jlxu.demo.concurrency.communication.Test7}
         */
        //六. 方法 join() 的使用
        //1). join() 的定义  假如在main线程中调用thread.join方法，则main线程会等待thread线程执行完毕或者等待一定的时间
        //根据以上源代码可以看出，join()方法是通过wait()方法 (Object 提供的方法) 实现的。当 millis == 0 时，会进入 while(isAlive()) 循环，并且只要子线程是活的，宿主线程就不停的等待。 wait(0) 的作用是让当前线程(宿主线程)等待，
        // 而这里的当前线程是指 Thread.currentThread() 所返回的线程。所以，
        // TODO:虽然是子线程对象(锁)调用wait()方法，但是阻塞的是宿主线程。
        //2). join() 使用实例及原理
        /**
         * join()
         * {@link com.jlxu.demo.concurrency.communication.Test8}
         */
        //TODO:当 main线程 运行到 thread1.join() 时，main线程会获得”线程对象thread1的锁“(wait 意味着拿到该对象的锁)。只要 thread1线程 存活， 就会调用该”对象锁“的wait()方法阻塞 main线程。那么，main线程被什么时候唤醒呢？
        //TODO：通过案例终于明白和Thread.sleep（）和在那个线程中执行有关

        //IO回顾（基础加博客中地址），如果有必要，回顾加深入pio  TODO:
        //结合生成者和消费看案例


    }
}
