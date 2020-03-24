package com.jlxu.demo.concurrency.queue.block;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：阻塞队列
 * 创建时间：2020年03月24日
 * 文件名称：BlockQueueTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/24 14:02
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class BlockQueueTest {
    @Test
    public void blockQueueTest() {
        //1）阻塞队列和非阻塞队列的区别
        //使用非阻塞队列的时候有一个很大问题就是：它不会对当前线程产生阻塞，那么在面对类似消费者-生产者的模型时，就必须额外地实现同步策略以及线程间唤醒策略
        //ps:回顾线程之间通信的栗子就行了
        //2）源码分析（自己）
        //3）阻塞队列和非阻塞队列的具体方法比较   TODO:
        //TODO：添加和移除的具体方法有些细节未处理
        //4）阻塞队列的实现原理
        //ReentrantLock 回顾  lockInterruptibly 含义？ 获取锁，能响应中断   意义/为什么要用？  注意哪些？
        //Entry 和 Node的区别  TODO
        //ps:队列就是一种数据结构,具体是什么数据结果，去看源码，是否阻塞，看是否采用了同步措施
        //案例（单个同类型的）
        //1)下面先使用Object.wait()和Object.notify()、非阻塞队列实现生产者-消费者模式：
        /**
         * @see com.jlxu.demo.concurrency.queue.block.Test
         */
        //2）阻塞队列实现的生产者-消费者模式
        /**
         * @see com.jlxu.demo.concurrency.queue.block.Test2
         */
        //有没有发现，使用阻塞队列代码要简单得多，不需要再单独考虑同步和线程间通信的问题。
        //在并发编程中，一般推荐使用阻塞队列，这样实现可以尽量地避免程序出现意外的错误。
        //TODO:阻塞队列使用最经典的场景就是socket客户端数据的读取和解析，读取数据的线程不断将数据放入队列，然后解析线程不断从队列取数据解析。还有其他类似的场景，只要符合生产者-消费者模型的都可以使用阻塞队列。
        //后续看


        //回顾之前案例的问题
        //1）为什么下满的案例(多个同类型) 不继续添加添加到队列呢？
        /**
         * @see com.jlxu.demo.concurrency.communication.Test5
         * @see com.jlxu.demo.concurrency.communication.Test6
         * ps:没有while，线程结束了啊
         */

        //TODO:生成者消费怎么实现
        //非阻塞队列  Object的wait 和notify
        //非阻塞队列  重入锁Lock
        //阻塞队列
    }
}
