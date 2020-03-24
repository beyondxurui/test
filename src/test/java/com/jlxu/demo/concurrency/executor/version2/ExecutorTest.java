package com.jlxu.demo.concurrency.executor.version2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能: Java并发编程：Callable、Future和FutureTask   https://www.cnblogs.com/dolphin0520/category/1426288.html
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
        /**
         * @see com.jlxu.demo.concurrency.executor.Test
         * @see com.jlxu.demo.concurrency.executor.Test2
         */

    }
}
