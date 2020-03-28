package com.jlxu.demo.concurrency.executor.version2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能: Java并发编程：Callable、Future和FutureTask
 * https://www.cnblogs.com/dolphin0520/category/1426288.html  参考一
 * https://www.cnblogs.com/xiaoxi/p/8303574.html   参考二
 * 创建时间：2020年03月24日
 * 文件名称：ExecutorTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/24 9:36
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
public class ExecutorTest {
    @Test
    public void executorTest() {
        //（1）、Thread Callable与Runnable区别
        //Thread&Runnable：无返回  run方法无异常
        //Callable  有返回  call有异常（if unable to compute a result）  泛型接口

        //（2）那么怎么使用Callable呢？
        //一般情况下是配合ExecutorService来使用的，在ExecutorService接口中声明了若干个submit方法的重载版本：
        //submit： 提交一个返回值的任务以供执行  TODO:具体见源码

        //3）Future
        // Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
        //泛型接口  TODO:具体源码（回顾原阿门怎么看）和博文
        //总结：1：判断任务是否完成；2：能够中断任务；3：能够获取任务执行结果。

        //4）FutureTask
        //TODO:看类的继承和实现类图和Future即可
        //事实上，FutureTask是Future接口的一个唯一实现类。

        //5）使用实例
        //1：方式一使用Callable+Future获取执行结果
        //2：方式二使用Callable+FutureTask获取执行结果
        //ps 如下  注：通过ExecutorService的源码  如方法（submit和shutdown）  通过FutureTask的源码  如构造方式

        //TODO：案例位置
        //      案例分析：那么它们到底有什么用呢？我们前面说过通过这样的方式去创建线程的话，最大的好处就是能够返回结果，加入有这样的场景，我们现在需要计算一个数据，而这个数据的计算比较耗时，而我们后面的程序也要用到这个数据结果，那么这个时Callable岂不是最好的选择？
        //      我们可以开设一个线程去执行计算，而主线程继续做其他事，而后面需要使用到这个数据时，我们再使用Future获取不就可以了吗？
        /**
         * @see com.jlxu.demo.concurrency.executor.Test
         * @see com.jlxu.demo.concurrency.executor.Test2
         */


        // 问题  案例位子如上
        //1）使用Callable+Future获取执行结果 和使用Callable+FutureTask获取执行结果的区别
        //PS：TODO:看了源码和debug好像没区别 （都会调用FutureTask(Callable<V> callable)构造器）
        //2）Callable+Future和Callable+FutureTask的案例的submit方法（debug）
        //TODO:调用AbstractExecutorService实例的方法一样
        //Future<T> submit(Callable<T> task)  submit给变量callable赋值   线程开发后，获取了cpu执行执行权 调用FutureTask实例的run方法，会后调用变量callable的call（执行执行逻辑）
        //博文   https://blog.csdn.net/programmer_at/article/details/79799267
        //FutureTask.run方法是在线程池中被执行的，而非主线程
        //1. 通过执行Callable任务的call方法；
        //2. 如果call执行成功，则通过set方法保存结果；
        //3. 如果call执行有异常，则通过setException保存异常；

        //配置线程池需要考虑哪些因素  TODO:
        //从任务的优先级，任务的执行时间长短，任务的性质（CPU密集/ IO密集），任务的依赖关系这四个角度来分析。并且近可能地使用有界的工作队列。
        //性质不同的任务可用使用不同规模的线程池分开处理：
        //- CPU密集型：尽可能少的线程，Ncpu+1
        //- IO密集型：尽可能多的线程, Ncpu*2，比如数据库连接池
        //- 混合型：CPU密集型的任务与IO密集型任务的执行时间差别较小，拆分为两个线程池；否则没有必要拆分。
        //参考
        //线程池大小（出时容量？，参考三中说的出时容量）
        //https://www.cnblogs.com/yueruijie/p/11357938.html 参考二 参考一在博文中
        //https://blog.csdn.net/weixin_39683776/article/details/94008730 参考三
        //线程池原理之初始化大小设置问题（暂时了解，别跑偏了）
        //https://blog.csdn.net/weixin_39683776/article/details/94008730
        //计算密集型任务和IO密集型任务的区别  （了解）
        //https://blog.csdn.net/u012611644/article/details/80158578

        //6. 如何监控线程池的状态
        //可以使用ThreadPoolExecutor以下方法：
        //getTaskCount() Returns the approximate total number of tasks that have ever been scheduled for execution.
        //getCompletedTaskCount() Returns the approximate total number of tasks that have completed execution. 返回结果少于getTaskCount()。
        //getLargestPoolSize() Returns the largest number of threads that have ever simultaneously been in the pool. 返回结果小于等于maximumPoolSize
        //getPoolSize() Returns the current number of threads in the pool.
        //getActiveCount()  Returns the approximate number of threads that are actively executing tasks.


        //Reference
        //https://www.jianshu.com/p/87bff5cc8d8c  占小狼   哈哈

        //ps
        //1）基于JVM 的cas(乐观锁)的AtomicInteger   后续
        //2）非原子操作的二次检查
        //3）运算符
        //4），，，
    }
}
