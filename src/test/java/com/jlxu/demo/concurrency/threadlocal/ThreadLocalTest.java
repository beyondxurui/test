package com.jlxu.demo.concurrency.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 ThreadLocal测试  https://blog.csdn.net/justloveyou_/article/details/54613085
 * 创建时间：2020年03月11日
 * 文件名称：ThreadLocalTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 0:23
 *
 * @auther jxlu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadLocalTest {
    @Test
    public void threadLocalTest() {
        //一ThreadLocal的理解
        //（1）ThreadLocal 概述
        //   ThreadLocal 又名 线程局部变量 ，是 Java 中一种较为特殊的线程绑定机制，可以为每一个使用该变量的线程都提供一个变量值的副本（TODO:这句话看源码，源码看懂了就行了）
        // ，并且每一个线程都可以独立地改变自己的副本，而不会与其它线程的副本发生冲突，“一般而言”，通过 ThreadLocal 存取的数据总是与当前线程相关，也就是说，
        // JVM 为每个运行的线程“绑定了私有的本地实例存取空间”，从而为多线程环境常出现的并发访问问题提供了一种 隔离机制 。
        //   如果一个某个变量要被某个线程 独享，那么我们就可以通过ThreadLocal来实现线程本地存储功能。
        //（2）ThreadLocal 在 JDK 中的定义
        ///* ThreadLocal values pertaining to this thread. This map is maintained
        //     * by the ThreadLocal class. */
        //    ThreadLocal.ThreadLocalMap threadLocals = null;
        //摘出三条要点：  ps：同上面的 TODO：
        //1：每个线程都有关于该 ThreadLocal变量 的私有值（每个线程都有一个独立于其他线程的上下文来保存这个变量的值，并且对其他线程是不可见的。）
        //2：独立于变量的初始值  （ThreadLocal 可以给定一个初始值，这样每个线程就会获得这个初始化值的一个拷贝，并且每个线程对这个值的修改对其他线程是不可见的。）
        //3：TODO:将某个类的状态与线程相关联   （我们从JDK中对ThreadLocal的描述中可以看出，ThreadLocal的一个重要作用是就是将类的状态与线程关联起来，这个时候通常的解决方案就是在这个类中定义一个 private static ThreadLocal 实例。）
        //（3）应用场景
        //类 ThreadLocal 主要解决的就是为每个线程绑定自己的值，以方便其处理自己的状态。
        //栗子如下
        /**
         * 以下类用于生成对每个线程唯一的局部标识符。线程 ID 是在第一次调用 uniqueNum.get() 时分配的，在后续调用中不会更改。
         *{@link com.jlxu.demo.concurrency.threadlocal.UniqueThreadIdGenerator}
         */

        //二. 深入分析ThreadLocal类
        //看源码步骤回顾  属性 方法（构造和其他重要方法（get() set() setInitialValue()  initialValue() createMap(t, value)））
        //栗子如下
        /**
         * 下面通过一个例子来证明通过ThreadLocal能达到在每个线程中创建变量副本的效果：
         *{@link com.jlxu.demo.concurrency.threadlocal.Test}
         */

        //三. ThreadLocal的应用场景
        //（1）管理数据库连接
        //（2）Session
        //（3）Thread-per-Request (TODO:一个请求对应一个服务器线程)
        //　“在经典Web交互模型中，请求的处理基本上采用的都是“一个请求对应一个服务器线程”的处理方式”，因此就可以将请求设置成类似ThreadLocal<Request>的形式，这样，当某个服务器线程来处理请求时，就可以独享该请求的处理了。
        //ps TODO:后续补web知识

        //四. ThreadLocal 一般使用步骤
        //ThreadLocal 使用步骤一般分为三步：
        //1：创建一个 ThreadLocal 对象 threadXxx，用来保存线程间需要隔离处理的对象 xxx；
        //2：提供一个获取要隔离访问的数据的方法 getXxx()，在方法中判断，若 ThreadLocal对象为null时候，应该 new() 一个隔离访问类型的对象(TODO:相当于多例，不同的线程都是不一样的)；
        //3：在线程类的run()方法中，通过getXxx()方法获取要操作的数据，这样可以保证每个线程对应一个数据对象，在任何时刻都操作的是这个对象，不会交叉。
        String s = ThreadLocalUtil.threadLocal.get();
        if (s != null) {
            log.info("s = {}" + s);
        } else {
            log.info("s = null");
        }
    }
}
