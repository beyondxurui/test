package com.jlxu.demo.thread.tomcatrequest.betterDesign;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

/**
 * 功能
 * 创建时间：2020年03月22日
 * 文件名称：TaskExecutionWebServer
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 12:24
 *
 * @auther
 */
@Slf4j
public class TaskExecutionWebServer {
    private static final Executor exec = ExecutorFactory.newExecutor();

    public static void main(String[] args) throws IOException {

        log.info("The executor you are using is {}", exec);
        ServerSocket socket = new ServerSocket(8081);
        while (true) {
            final Socket connection = socket.accept();
            Runnable r = () -> handleRequest(connection);
            exec.execute(r);
//            return;  //TODO: return一加，程序结束了，和位子无关，但是再次请求为什么进不去了
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
        System.out.println("current thread is " + Thread.currentThread().getName());
        System.out.println("execute task");

        //current thread is pool-1-thread-3
        //execute task
        //current thread is pool-1-thread-2
        //execute task
        //current thread is pool-1-thread-1
        //execute task
        //请求    http://localhost:8081/
        //current thread is pool-1-thread-4
        //current thread is pool-1-thread-5
        //execute task
        //execute task
        //ps TODO:后续看JDK线程池源码看看是什么源码 请求一次有两次执行
    }

    //性能问题n连问
    //“你现在是每个请求过来，都创建一条线程。有没有考虑过，如果一万个请求同时过来，会怎么样，十万、一百万、一千万呢？”
    //我们的处理器才多少个，这样肯定会有很多条线程处于无所事事的状态。”  TODO
    //“不仅如此，线程的创建和销毁都是需要时间的，给一个非常轻量级的请求，创建一条线程去处理，有可能创建和销毁线程消耗的时间，比请求处理的时间还长，你说这样划算么？
    //“啊，还有一点，线程占内存，创建过多的线程还会导致内存溢出！”
    //怎么办呢？  “你需要一个池子”，哆啦故作神秘的说。

    //线程池？  TODO:把自己当轮子哥了吗（思维）
    //JDK线程的介绍

    //总结
    //1）生产者消费者模式   将任务创建和任务执行解耦   这也是JDK各种线程池都遵循的设计思想
    //2）线程池
    //是因为它既解决了单线程低吞吐量、响应慢的缺点，又解决了为每个任务创建一条线程所带来的资源管理的问题
    //相比单线程，线程池使用了多线程来处理请求，提升了吞吐量和响应速度；
    //相比无限制创建线程的方案，线程池控制了线程的数量，使线程数量维持在合理的水平，充分发挥CPU的作用，也防止线程过多占用内存；
    //同时，线程池提前创建好了线程，省去了请求过来时创建线程的时间。
    //3）“池”的思想。软件中的“池”，可以理解为计划经济时代的工厂
    //你要提前生产东西，这样当老百姓或者政府有需要的时候，可以马上提供，但是你又不能无限制的生产，毕竟资源就那么多，都被你拿去生产了，其他人怎么办。数据库连接池是这样，线程池也是如此。

    //后记
    //这篇文章的目的是想让读者了解，为什么要使用线程池。篇幅原因，很多东西没有在这篇文章中分享。
    //比如，虽说文章名称叫《如何像Tomcat一样处理请求》，但是实际上Tomcat处理请求时，除了使用线程池，其他的逻辑肯定会更为复杂；
    //又比如，你想不想知道，请求到了Tomcat后，是如何来到我们的代码中的呢，假设你使用的是SpringMVC；
    //Tomcat、Servlet、SpringMVC/Struts，它们之间又是什么关系？
    //各种线程池的作用是什么，比如什么时候要使用newCachedThreadPool、什么时候要使用newSingleThreadExecutor，它们的内部实现是怎么样的？
    //等等这些问题，读者有兴趣都可以先去研究一下，我也将在后面的文章中和大家分享。

}
