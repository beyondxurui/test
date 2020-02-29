package com.jlxu.demo.thread.three;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：测试synchronize
 * 创建时间：2020年02月29日
 * 文件名称：SynchronizeProcessTest
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 18:35
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class SynchronizeProcessTest {
    public static final int LOOP_TIME = 1000 * 10000;

    //线程不安全测试
    @Test
    public void test_UnThreadSafeCountingProcessor() {
        ThreadCountingProcessor threadCountingProcessor = new ThreadCountingProcessor();
        tunTask(threadCountingProcessor);
    }

    //线程安全测试  执行完控制台没打印（不到count不到20000000就结束了）
    @Test
    public void test_ThreadSafeCountingProcessor() {
        ThreadCountingProcessor threadCountingProcessor = new ThreadCountingProcessor();
        tunTask(threadCountingProcessor);
    }

    //让main线程睡会
    @Test
    public void test_ThreadSafeCountingProcessor2() {
        Thread thread = Thread.currentThread();
        ThreadCountingProcessor threadCountingProcessor = new ThreadCountingProcessor();
        tunTask(threadCountingProcessor, thread);
        //22:07:13.299 [thread-2] INFO com.jlxu.demo.thread.three.ProcessTask - Finally, the count is 18469541
        //22:07:13.346 [thread-1] INFO com.jlxu.demo.thread.three.ProcessTask - Finally, the count is 20000000

        //ps:sleep()和wait的区别
        //https://blog.csdn.net/dao_wolf/article/details/81175089  偏总结
        //https://blog.csdn.net/xyh269/article/details/52613507    细节
        //同步方法和同步代码块  用synchronized修饰的方法和代码块    最好用代码块，因为效率
        //参考：https://blog.csdn.net/zhenwodefengcaii/article/details/54601098?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
    }

    private void tunTask(ThreadCountingProcessor threadCountingProcessor, Thread thread) {
        Thread thread1 = new Thread(new ProcessTask(LOOP_TIME, threadCountingProcessor), "thread-1");
        Thread thread2 = new Thread(new ProcessTask(LOOP_TIME, threadCountingProcessor), "thread-2");
        thread1.start();
        thread2.start();
        try {
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //wait unit all the threads have finished
        if (thread1.isAlive() || thread2.isAlive()) {
            // Tests if this thread is alive. A thread is alive if it has
            //been started and has not yet died.
            //意思：激活还没有死的线程
        }

    }

    private void tunTask(ThreadCountingProcessor threadCountingProcessor) {
        Thread thread1 = new Thread(new ProcessTask(LOOP_TIME, threadCountingProcessor), "thread-1");
        Thread thread2 = new Thread(new ProcessTask(LOOP_TIME, threadCountingProcessor), "thread-2");
        thread1.start();
        thread2.start();
        //wait unit all the threads have finished
        if (thread1.isAlive() || thread2.isAlive()) {
            // Tests if this thread is alive. A thread is alive if it has
            //been started and has not yet died.
            //意思：激活还没有死的线程
        }
    }
    //ps:这里面也要来下，哈哈


    //为什么能保证同一刻最多只有一个线程执行该代码块      ===>   内置锁
    //内置锁接单介绍  ===>
    // 当我们使用synchronize修饰非静态方法时，用的是调用该方法的实例的内置锁，也就是this;  继承关系是怎么样的呢？
    //当我们使用synchronize修饰静态方法时，用的是调用该方法的所在的类对象的内置锁;
    //更多时候，我们使用的是synchronize代码块，我们经常用的是synchronize(this)，也就是把对象实例作为锁。  目前介绍的都是隐式锁，还有显示锁Lock（后续）
    @Test
    public void testWidget() {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();//小熊美美哒！！！
        //ps：既然可以重入，那么是父类实例的内置锁还是子类的呢？  ===> https://blog.csdn.net/weixin_33670713/article/details/92377100

    }
}
