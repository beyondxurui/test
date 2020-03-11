package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 synchronized测试  https://blog.csdn.net/justloveyou_/article/details/54381099
 * 创建时间：2020年03月09日
 * 文件名称：SynchronizedTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/9 23:58
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class SynchronizedTest {
    @Test
    public void synchronizedTest() throws InterruptedException {
        //一：线程安全问题
        /**
         *  {@link com.jlxu.demo.concurrency.synchronize.Test}
         */
        //(1)线程安全问题
        //共享（意味着该资源可以由多个线程同时访问）  可变（意味着该资源可以在其生命周期内被修改）  TODO:共享怎么理解：Java内存模型
        //(2)如何解决线程安全问题
        //实际上，所有的并发模式在解决线程安全问题时，采用的方案都是 序列化访问临界资源
        //即多个线程同时访问一个资源时，会导致程序运行结果并不是想看到的结果。这里面，这个资源被称为：临界资源 (TODO)
        //(3)什么是序列化访问临界资源？
        //即在”同一时刻，只能有一个线程访问临界资源“，也称作 "同步互斥访问"。换句话说，就是在访问临界资源的代码前面加上一个锁，当访问完临界资源后释放锁，让其他线程继续访问。
        //（4）如何解决线程安全问题
        //在 Java 中，提供了两种方式来实现同步互斥访问：synchronized 和 Lock

        //二：synchronized
        /**
         * synchronized方法
         * {@link com.jlxu.demo.concurrency.synchronize.Test2}
         */
        /**
         * synchronized 同步块
         * {@link com.jlxu.demo.concurrency.synchronize.Test3}
         * synchronized代码块 比 synchronized方法 的粒度更细一些，使用起来也灵活得多(看时间日志)
         * 实例同步方法 与 synchronized(this)同步块 是互斥的（TODO:指的是同一个对象才互拆）
         */
        //（1）synchronized 同步方法或者同步块
        //　　在 Java 中，提供了两种方式来实现同步互斥访问：synchronized 和 Lock。
        //（2）锁到底怎么理解   加锁了 等执行完才释放， 具体看案例（遍历的案例）
        /**
         * class 对象锁
         * {@link com.jlxu.demo.concurrency.synchronize.Test4}
         *特别地，每个类也会有一个锁，静态的 synchronized方法 就是以Class对象作为锁。另外，它可以用来控制对 static 数据成员 （static 数据成员不专属于任何一个对象，是类成员） 的并发访问。并且，如果一个线程执行一个对象的非static synchronized 方法，另外一个线程需要执行这个对象所属类的 static synchronized 方法，也不会发生互斥现象。因为访问 static synchronized 方法占用的是类锁，而访问非 static synchronized 方法占用的是对象锁，所以不存在互斥现象
         */
        //（3）类（Class）锁和对象锁
        //（4）互斥和不互斥的几种情况   什么是互斥（A在执行，B一定在执行）  关键在于：释放使用synchronized，释放是同一把锁
        //（5）看一下 synchronized 关键字到底做了什么事情(synchronized的反编译)
        //从反编译获得的字节码可以看出，synchronized 代码块实际上多了 monitorenter 和 monitorexit 两条指令。
        // monitorenter指令执行时会让对象的锁计数加1，而monitorexit指令执行时会让对象的锁计数减1，其实这个与操作系统里面的PV操作很像，操作系统里面的PV操作就是用来控制多个进程对临界资源的访问。
        // 对于synchronized方法，执行中的线程识别该方法的 method_info 结构是否有 ACC_SYNCHRONIZED 标记设置，然后它自动获取对象的锁，调用方法，最后释放锁。如果有异常发生，线程自动释放锁。
        /**
         * synchronized方法
         * {@link com.jlxu.demo.concurrency.synchronize.Test5}
         * 可重入锁最大的作用是避免死锁
         */
        //（6）可重入性
        //是什么？ TODO:理解：博主的栗子感觉不好（因为加synchronized,cpu没有释放）    sleep方法其他线程是自己
        //一般地，当某个线程请求一个由其他线程持有的锁时，发出请求的线程就会阻塞。然而，由于 Java 的内置锁是可重入的，因此如果某个线程试图获得一个已经由它自己持有的锁时，那么这个请求就会成功
        //作用：避免死锁
        //（7）内置锁与字符串常量  TODO: String 类型的参数都是 “AA”，两个线程持有相同的锁，所以 线程B 始终得不到执行，造成死锁。进一步地，所谓死锁是指
        //由于字符串常量池的原因,不可变"对象"

        //三：锁的是对象而非引用
        /**
         *  锁的是对象而非引用
         *  {@link com.jlxu.demo.concurrency.synchronize.Run}
         *  ps:通过查看日志  案例有问题
         */
        //（1）ps锁的理解  copy reference 使用 放在 单行注释下即可
        // 锁：为了解决多线程访问临界资源安全问题的一种实现   任务对象都可以“作为”锁
        // 看看继承关系一个栗子（动物  狗，猫  狗，猫“是”动物 ，实践“像”都可以）
        //（2）锁的是对象而非引用  TODO(**)
        //在将任何数据类型作为同步锁时，需要注意的是，是否有多个线程将同时去竞争该锁对象：
        //1).若它们将同时竞争同一把锁，则这些线程之间就是同步的；  TODO(**)
        //2).否则，这些线程之间就是异步的。TODO(**)  （常看到的===>异步要求高)
        MyService2 myService = new MyService2();//临界资源
        MyThread7 a = new MyThread7(myService);
        MyThread7 b = new MyThread7(myService);
        a.start();
//        Thread.currentThread().sleep(50);
        b.start();
        //(1): Thread.currentThread().sleep(50);  不注释异步的
        //current thread name: Thread-1 begin
        //注释  同步
        //current current thread name: Thread-2 begin
        //TODO:  日志有问题，也不知道为什么（不可以在@Test下）

        //四：总结
        //用一句话来说，synchronized 内置锁  是一种 对象锁 (锁的是对象而非引用)，  作用粒度是对象 ，可以用来实现对  临界资源的同步互斥访问 ，是 可重入 的。特别地，对于 临界资源 有：
        //若该资源是静态的，即被 static 关键字修饰，那么访问它的方法必须是同步且是静态的，synchronized 块必须是 class锁；
        //若该资源是非静态的，即没有被 static 关键字修饰，那么访问它的方法必须是同步的，synchronized 块是实例对象锁；
        //关键字synchronized 主要包含两个特征
        // 1:互斥性(保证在同一时刻，只有一个线程可以执行某一个方法或某一个代码块)
        // 2:可见性(保证线程工作内存中的变量与公共内存中的变量同步，使多线程读取共享变量时可以获得最新值的使用)

    }
}
