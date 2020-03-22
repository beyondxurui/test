package com.jlxu.demo.thread.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executors;

/**
 * 功能：线程池测试类
 * 创建时间：2020年03月22日
 * 文件名称：ThreadPoolTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 18:09
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadPoolTest {

    @Test
    public void threadPoolTest() {
        Executors.newFixedThreadPool(10);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(1);

        //int corePoolSize,                      核心线程数                                 定制
        //   int maximumPoolSize,                最大线程数                                 定制
        //   long keepAliveTime,                 线程活跃时间（线程从队列中poll任务（请求））   定制
        //   TimeUnit unit,                      活跃时间单位
        //   BlockingQueue<Runnable> workQueue,  对列：
        //   1：无界的阻塞队列（Unbounded queues），比如LinkedBlockingQueue，来多少任务就放多少；
        //   2“有界的阻塞队列（Bounded queues），  比如ArrayBlockingQueue；
        //   3：同步移交（Direct handoffs），      比如SynchronousQueue，这个队列的put方法会阻塞，
        //   直到有线程准备从队列里面take，所以本质上SynchronousQueue并不是Queue，它不存储任何东西，它只是在移交东西
        //   ThreadFactory threadFactory,  线程工厂
        //   RejectedExecutionHandler handler  拒绝策略：如果线程池已经被shutdown了，
        //   或者线程池中使用的是有界队列，而这个队列已经满了，并且线程数已经达到最大线程数，
        //   无法再创建新的线程处理请求，这时候要怎么处理新来的任务？
        //   在和大家一起讨论之后，我们认为至少有这四种策略：AbortPolicy：使用这种策略的线程池，
        //   将在无法继续接受新任务时，给任务提交方抛出RejectedExecutionException，让他们决定要如何处理；
        //   CallerRunsPolicy：这个策略，顾名思义，将把任务交给调用方所在的线程去执行；
        //   DiscardPolicy：直接丢弃掉新来的任务；
        //   DiscardOldestPolicy：丢弃最旧的一条任务，其实就是丢失blockingQueue.poll()返回的那条任务，
        //   要注意，如果你使用的是PriorityBlockingQueue优先级队列作为你的任务队列，
        //   那么这个策略将会丢弃优先级最高的任务，所以一般情况下，PriorityBlockingQueue和DiscardOldestPolicy不会同时使用

        //ps具体看博文
    }
}
