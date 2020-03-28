package com.jlxu.demo.thread.pool.sourceCode;

/**
 * 功能：线程池源码解读    https://www.cnblogs.com/dolphin0520/p/3932921.html   TODO:非jdk1.8
 * 创建时间：2020年03月24日
 * 文件名称：SourceCodeTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/24 10:31
 *
 * @auther jlxu
 */
public class SourceCodeTest {

    //线程池任务和线程的区别  FutureTask
    //TODO：线程池创建的线程放在Workers工作组中 任务放在队列中 （通过源码）

    // 线程相关对象
    // 1:interface ExecutorService extends Executor       Executor就一个方法executor   执行任务
    // 2:abstract class AbstractExecutorService implements ExecutorService    submit方法看AbstractExecutorService
    // 3:class ThreadPoolExecutor extends AbstractExecutorService  execute方法这里，可以看ThreadPoolExecutor中的extends方法
    // 4:Executors工具类 静态方法创建线程池，，，   其他三类，看源码
    // 5:Future  FutureTask<V> implements RunnableFuture<V>    RunnableFuture<V> extends Runnable, Future<V> 处理异步任务结果  TODO:可以处理任务，且任务也可以作为创建线程(构造器的一个参数)
    // TODO：见 com.jlxu.demo.concurrency.executor.version2.ExecutorTest


    //问题
    //1）executor   和submit的区别
    // TODO：（AbstractExecutorService implements ExecutorService）都是提交任务，获得任务执行接口 submit调用了execute方法
    //        具体提交的任务立马创建线程执行，还是放到队列中，看条件的 具体见源码
    //submit  源码：提交一个返回值的任务以供执行，并返回一个Future，表示该任务的未决结果。Future的{@code get}方法将在成功完成后返回任务的结果。
    //submits使用案例
    //2）什么时候把任务加道队列？  异步到下面的源码分析里面  TODO:ThreadPoolExecutor实例的execute方法执行时，工作线程数大于核心线程数时（队列没有满）
    /**
     * @see com.jlxu.demo.concurrency.executor.Test
     * @see com.jlxu.demo.concurrency.executor.Test2
     */
    //executor 源码：在将来的某个时候执行给定的命令。命令可以在新线程、池化的线程或调用的线程中执行，具体由{@code Executor}实现决定。
    //executor使用案例   和线程池结合
    //
    /**
     * @see com.jlxu.demo.thread.pool.tomcatrequest.betterDesign.TaskExecutionWebServer
     */

    //源码分析
    // TODO:分两部分，一个是博文的源码分析回顾  一个是jdk1.8源码分析
    //  https://blog.csdn.net/programmer_at/article/details/79799267  参考一
    //  https://blog.csdn.net/qwe6112071/article/details/87446052     参考二


    //其他问题  TODO:
    //1）：Callable+Future和Callable+FutureTask的区别
    //2）Callable+Future和Callable+FutureTask的debug
    //  案例地址 com.jlxu.demo.concurrency.executor包下
    //  总结位子在：包下ExecutorTest类中总结


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
    //TODO：https://www.jianshu.com/p/87bff5cc8d8c  占小狼   哈哈（后续看看）

    //ps
    //1）基于JVM 的cas(乐观锁)的AtomicInteger   后续
    //2）非原子操作的二次检查
    //3）运算符
    //4）必要的清理
    //5）线程池的业务逻辑是实现  TODO:后续在看

}
