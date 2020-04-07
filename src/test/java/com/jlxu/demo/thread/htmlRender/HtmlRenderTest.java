package com.jlxu.demo.thread.htmlRender;

import com.jlxu.demo.thread.htmlRender.renderer.CompletionServiceRenderer;
import com.jlxu.demo.thread.htmlRender.renderer.FutureRenderer;
import com.jlxu.demo.thread.htmlRender.renderer.SingleThreadRenderer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:
 * 创建时间：2020年03月22日
 * 文件名称：HtmlRenderTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 23:20
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class HtmlRenderTest {
    //进入目录前的思考   要不要采用并发任务  并发任务怎么处理和优化
    //思路  不必造轮子（思维），用搜索引擎，看博文，看源码学习
    //怎么就异构进行了？  TODO: debug 上网查，源码
    //集合 书**，源码，百度（单个百度）以及其他博主的优秀并发篇
    //take poll含义，take、poll方法，其实是委托给这个阻塞队列去实现的” ?
    //要如何设计任务并行的方案，让哪些任务跟哪些任务并行执行？  解（什么？）耦设计规范？  由谁去执行

    //ps博文中的其他问题

    private static final String DEFAULT_SOURCE = "<html><h1>Hello World</h1><image src='hi.png'></image></html>";

    @Test
    public void test_singleThreadRenderer() throws Exception {
        new SingleThreadRenderer().renderPage(DEFAULT_SOURCE);
    }

    @Test
    public void test_futureRenderer() throws Exception {
        new FutureRenderer().renderPage(DEFAULT_SOURCE);
    }

    @Test
    public void test_completionServiceRenderer() throws Exception {
        new CompletionServiceRenderer().renderPage(DEFAULT_SOURCE);
    }

    //问题
    //1）Future是异步任务,看案例的日志就可以了
    //2）异构任务？     见案例
    //3）同构任务？     每个操作创建一个任务 见案例
    //4）CompletionService中的队列的done方法（源码：cancel时候调用，默认没有构造体）？  TODO
    //5）Lambda表达式  （）-> xx  （）=> {}   TODO:TODO:https://blog.csdn.net/justloveyou_/article/details/89066782
    //6）CompletionService获取任务（take）和Future获取任务 （get）的区别？
    //说明: 进入源码都是阻塞的（还有其他获取任务的方法，如poll，具体见博文和源码）
    //建立一个任务处理  可以建立多个任务处理操作（有几个要处理的建立几个任务）    这么一分析，区别就是为了提供性能，合适的场景下，同构任务，选中前者处理
    //但是，感觉后者也可以这样吧，那不是又没区别了   TODO：明天试试，但是一定要结合博文
    // 前者的获取任务结构和后者有什么区别？

    //问题解答
    //2）TODO:我的理解：异步是要充分利用cpu（单核），一般是和线程关联的， 如这里，把任务放到线程池中，线程池中的线程和主线程看谁能获取cpu的执行权力
    //   （特俗清空下（不是请求，想提高执行效率）有的时候会让主线程睡眠一定时间，这里不用，因为take方法是阻塞方法） 后面再理解吧
    // TODO:对阻塞的理解？  那个线程执行的阻塞方法，那个线程阻塞   在线程体没有执行完成的时候，主线程一直阻塞等待，执行完则直接返回结果
    //6）TODO:https://blog.csdn.net/jdsjlzx/article/details/52912701   案例没补充
    //  通过博文的案例结果可以知道，获取两个方式（Future：未来任务和CompletionService：完成服务）的take获取任务都是阻塞，
    //  不过前者是按照顺序拿的  后者有就拿
    //
    //  （任务完成通过内部类（QueueingFuture）的done方法把完成的任务添加到任务队列BlockingQueue<Future<V>>中的）
    //  TODO：知道是任务完成时候执行重写Future的done方法添加到队列中的，但是代码中国不知道什么不知道什么时候   走debug
    //  通过debug： TODO:done的入口在，执行任务时候，启动线程，执行Future 的 run方法中的  set(result)中的  finishCompletion()中的  done()执行（重写就执行，没重写执行的是空方法体，相当于什么多没做）
    //  TODO:ps:看源码中的方法你会了吗？   所有的方法执行都对应具体的类（给定的参数/传入的参数对应）  自己看源码要记录问题   然后debug看原阿门的时候解答问题
    //  获取任务结果的区别
    //  CompletionService 可以从唯一的实现类ExecutorCompletionService实例的take方法中获取任务结果，take方法是从指定是队列 LinkedBlockingQueue实例的take方法中获取
    //  FutureTask 是可以该实例的get中report(s)  中的Object x = outcome（任务结果或异常）;   那么这个outcome什么时候赋值的呢？  在set(result)方法中，在outcome = v;finishCompletion（）前面的  outcome = v;


    //ps
    //1）CompletableFuture？       可参考，没细看，感觉讲的不怎么样，到时候自行搜索并集合源码   https://www.toutiao.com/a6647113748802699779/?iid=57728868033&app=news_article&group_id=6647113748802699779&timestamp=1547701603
    //2）https://blog.csdn.net/jdsjlzx/article/details/52912701  这个案例中  TODO:  为什么一个按照顺序一个不按照顺序
    //3）test_completionServiceRenderer的案例中的同构异步，像不像并发  哈哈

}
