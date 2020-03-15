package com.jlxu.demo.concurrency.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能： 深入剖析Java线程池与Executor框架(一) : 任务的抽象
 * 创建时间：2020年03月15日
 * 文件名称：ExecutorTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/15 16:24
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ExecutorTest {
    @Test
    public void executorTest() {
        //摘要
        //事实上，线程扮演的是Worker的角色，是Task的执行者。从Java的角度来看，
        // Thread实现了Worker的角色，Runable、Callable以及FutureTask都可以扮演Task的角色。
        // 其中，Runable应该是我们最为熟悉的封装任务的方式，但其天生就存在一个显著的不足：
        // 在执行完任务之后，无法获取执行结果。也就是说，若程序需要获取另一个线程的执行结果，
        // 就得通过共享变量或者线程间通信的方式来实现，这是相当麻烦的。但在Java 1.5之后，
        // Executor框架的推出使得使用Java进行并发编程变得简单而有效。
        // Callable与FutureTask作为Executor框架的一部分，可以更好的完成对任务的封装，
        // 使得我们可以轻松实现对任务执行过程的控制和对任务执行结果的获取。
        //一. 并发的真相
        //并发，见文知意，即多个进程或线程同时执行，其“需要操作系统的支持”。广为大家所使用的Windows、Linux和Uinux都是支持多线程、多进程的操作系统。DOS就不支持多进程、多线程模式了，它是一个单进程、单线程操作系统，所有的程序都是串行执行的，即在同一个时间点只能有一个进程在执行。
        //支持多进程、多线程的操作系统的CPU真的那么神通广大，能够同时执行多个程序吗？当然不是了。事实是这样的，CPU的运算速度很快，一秒钟至少可以进行数亿次运算，因此，CPU把自己的时间分成一个个很小的时间片，时间片a执行程序A一小段时间，下一个时间片b执行程序B，再下一个时间片C又执行程序。这样，虽然有数十个线程，但一样可以在很短的时间内把它们通通都执行一遍。由于CPU的执行速度太快了，我们人类根本感受不到这种上下文切换带来的停顿，因此看起来就像是在同一时间执行所有的线程一样。但事实上，在一个时间点上，CPU只能被一个线程所使用。当然，如果一个机器是多CPU或者是单CPU多核的，那么就可以实现真正意义上的并发。
        //线程是CPU执行的基本单位，是最小粒度的并发。在实践中，我们常常把耗时的操作单独封装成一个任务交给所创建的线程去执行，这样就不用一直阻塞在主线程上，从而提高资源利用率和程序执行效率。由此可见，对任务的封装是并发编程的第一步。常见的任务封装方式有Runnable、Callable和FutureTask三种，下面一一进行介绍。
        //二. Runnable与Callable
        //三. Future与FutureTask
        //1、Future接口
        //2、FutureTask实现类
        //四. 最佳使用组合
        //什么情况下使用以及如何使用Callable、Future以及FutureTask呢？想必最为典型的使用场景就是线程池了
        //1、使用Callable+Future获取执行结果
        //2、使用Callable+FutureTask获取执行结果

    }
}
