package com.jlxu.demo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：Lock https://blog.csdn.net/justloveyou_/article/details/54972105
 * 创建时间：2020年03月11日
 * 文件名称：LockTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 23:43
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class LockTest {
    @Test
    public void lockTest() {
        //零、回顾
        //synchronized 的中断方法回顾（单独调用interrupt方法可以使得 处于阻塞状态的线程 抛出一个异常，也就是说，它可以用来中断一个正处于阻塞状态的线程）  重入性回顾
        //同步和异步的概念 ps:现在想想也不是很难吧

        //一. synchronized 的局限性 与 Lock 的优点
        //（1）synchronized 回顾
        //如果一个代码块被synchronized关键字修饰，当一个线程获取了对应的锁，并执行该代码块时，其他线程便只能一直等待直至占有锁的线程释放锁
        //（2）占有锁的线程释放锁一般会是以下三种情况之一
        //1：占有锁的线程执行完了该代码块，然后释放对锁的占有；
        //2：占有锁线程执行发生异常，此时JVM会让线程自动释放锁；
        //3：占有锁线程进入 WAITING 状态从而释放锁，例如在该线程中调用wait()方法等。  TODO:
        //（3）synchronized 是Java语言的内置特性，可以轻松实现对临界资源的同步互斥访问，
        //（4）那么，为什么还会出现Lock呢？试考虑以下三种情况：
        //1：一种机制可以不让等待的线程一直无期限地等待下去  解决方案：tryLock(long time, TimeUnit unit)) 或者 能够响应中断 (解决方案：lockInterruptibly())）
        //2：一种机制来使得当多个线程都只是进行读操作时，线程之间不会发生冲突  (解决方案：ReentrantReadWriteLock)
        //3：得知线程有没有成功获取到锁 (解决方案：ReentrantLock)
        //（5）特点和注意点
        //特点：事实上，Lock 是 java.util.concurrent.locks包 下的接口，Lock 实现提供了比 synchronized 关键字 更灵活、更广泛、粒度更细 的锁操作，它能以更优雅的方式处理线程同步问题。也就是说，Lock提供了比synchronized更多的功能
        //注意点 1：synchronized是Java的关键字，因此是Java的内置特性，是基于JVM层面实现的，其经过编译之后，会在同步块的前后分别形成 monitorenter 和 monitorexit 两个字节码指令；而Lock是一个Java接口，是基于JDK层面实现的，通过这个接口可以实现同步访问；
        //注意点 2：采用synchronized方式不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而 Lock则必须要用户去手动释放锁 (发生异常时，不会自动释放锁)，如果没有主动释放锁，就有可能导致死锁现象。

        //二. java.util.concurrent.locks包下常用的类与接口

        //1、Lock
        //1）lock() 如果锁已被其他线程获取，则进行等待
        //2）tryLock() & tryLock(long time, TimeUnit unit)  论如何都会立即返回（在拿不到锁时不会一直在那等待）。
        //3）lockInterruptibly()    方法比较特殊，当通过这个方法去获取锁时，如果线程 正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。
        //注意：由于lockInterruptibly()的声明中抛出了异常，所以lock.lockInterruptibly()必须放在try块中或者在调用lockInterruptibly()的方法外声明抛出 InterruptedException，但推荐使用后者，原因稍后阐述。

        //2、ReentrantLock  ReentrantLock是唯一实现了Lock接口的类，并且ReentrantLock提供了更多的方法
        //栗子
        //1、ReentrantLock的lock.lock();
        /**
         * ReentrantLock
         * @see com.jlxu.demo.concurrency.lock.Test
         */
        //2、ReentrantLock的tryLock()
        /**
         * tryLock(long time, TimeUnit unit)
         * @see com.jlxu.demo.concurrency.lock.Test2
         */
        //3、ReadWriteLock的tryLock(long time, TimeUnit unit)
        /**
         * 注意要结核：Thread的interrupt
         * @see com.jlxu.demo.concurrency.lock.Test3
         */
        //3、ReadWriteLock的lockInterruptibly()
        /**
         * 注意要结核：Thread的interrupt
         * @see com.jlxu.demo.concurrency.lock.Test4
         */
        //3、ReadWriteLock
        //ReadWriteLock也是一个接口，在它里面只定义了两个方法

        //4、ReentrantReadWriteLock
        //ReentrantReadWriteLock  实现了 ReadWriteLock 接口( 注意，ReentrantReadWriteLock 并没有实现 Lock 接口 )，其包含两个很重要的方法：readLock() 和 writeLock() 分别用来获取读锁和写锁，并且这两个锁实现了Lock接口
        //栗子：假如有多个线程要同时进行读操作的话
        /**
         * 注意要结核：使用synchronized
         * @see com.jlxu.demo.concurrency.lock.Test5
         */
        /**
         * 注意要结核：，ReentrantReadWriteLock 的 readLock().lock()
         * @see com.jlxu.demo.concurrency.lock.Test6
         */

        //我们可以看到，线程A和线程B在同时进行读操作，这样就大大提升了读操作的效率。
        // 不过要注意的是，如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程也会一直等待释放写锁。

        //5、Lock 和 Synchronized 的选择
        //区别
        //1）  JDK层面   JVM层面
        //2）synchronized 在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁
        //3）Lock 可以让等待锁的线程响应中断，而使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
        //4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到；
        //5）Lock可以提高多个线程进行读操作的效率。
        //TODO:在性能上来说，如果竞争资源不激烈，两者的性能是差不多的。而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。

        //三. 锁的相关概念介绍
        //1、可重入锁
        //如果锁具备可重入性，则称作为 可重入锁 。像 synchronized 和 ReentrantLock ”都是可重入锁“，
        // 可重入性实际上表明了 TODO:锁的分配粒度：基于线程的分配，而不是基于方法调用的分配。
        //2、可中断锁
        //3、公平锁
        //公平锁即 尽量 以请求锁的顺序来获取锁。比如，同是有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该所，这种就是公平锁。
        // 而非公平锁则无法保证锁的获取是按照请求锁的顺序进行的，这样就可能导致某个或者一些线程永远获取不到锁。
        //在Java中，synchronized就是非公平锁（抢占锁），它无法保证等待的线程获取锁的顺序。而对于ReentrantLock 和 ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为 公平锁（协同式线程调度）。
        /**
         *Case : 公平锁
         *@see com.jlxu.demo.concurrency.lock.Test5
         */
        /**
         *Case : 公平锁
         *@see com.jlxu.demo.concurrency.lock.Test5
         */

        //在ReentrantLock类中定义了很多方法  参见参考地址
        //4.读写锁
    }
}
