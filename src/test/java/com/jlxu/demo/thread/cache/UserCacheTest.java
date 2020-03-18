package com.jlxu.demo.thread.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:高效可伸缩缓存
 * 创建时间：2020年03月01日
 * 文件名称：UserCacheTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 7:08
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class UserCacheTest {
    //版本一测试
    @Test
    public void testVersionOne() throws InterruptedException {
        Thread thread = Thread.currentThread();
        UserCostStatComputeVersionOne cost = new UserCostStatComputeVersionOne();
        TaskCache taskCache1 = new TaskCache(cost, "23");
        TaskCache taskCache2 = new TaskCache(cost, "24");
        Thread thread1 = new Thread(taskCache1);
        Thread thread2 = new Thread(taskCache2);
        thread1.start();
        thread2.start();
        //（第一次时间长）
        thread.sleep(9000);//3秒不够

        //console ===>
        //07:26:27.939 [Thread-1] INFO com.jlxu.demo.thread.cache.UserCostStatComputeVersionOne - cache ===> {"23":23}
        //07:26:27.941 [Thread-1] INFO com.jlxu.demo.thread.cache.TaskCache - cache ===> 23
        //07:26:30.942 [Thread-2] INFO com.jlxu.demo.thread.cache.UserCostStatComputeVersionOne - cache ===> {"23":23,"24":24}
        //07:26:30.942 [Thread-2] INFO com.jlxu.demo.thread.cache.TaskCache - cache ===> 24
        //ps:代码不复用
    }

    //版本二测试
    @Test
    public void testVersionTwo() throws Exception {
        Memoizer1 memoizer1 = new Memoizer1(new UserCostStatComputerVersionTwo());
        memoizer1.compute("23");
        memoizer1.compute("24");
        //版本二-1
        //08:00:08.976 [main] INFO com.jlxu.demo.thread.cache.Memoizer1 - userId ===> 23
        //08:00:12.062 [main] INFO com.jlxu.demo.thread.cache.Memoizer1 - cache ===> {"23":23}
        //08:00:12.062 [main] INFO com.jlxu.demo.thread.cache.Memoizer1 - userId ===> 24
        //08:00:15.072 [main] INFO com.jlxu.demo.thread.cache.Memoizer1 - cache ===> {"23":23,"24":24}

        //版本二-3
        //同上
    }

    //终极版测试
    @Test
    public void testFinalVersion() throws Exception {
        Memoizer2 memoizer2 = new Memoizer2(new UserCostStatComputerVersionTwo());
        memoizer2.compute("23");
        memoizer2.compute("23");

        //09:10:59.956 [main] INFO com.jlxu.demo.thread.cache.Memoizer2 - userId ===> 23
        //09:10:59.961 [main] INFO com.jlxu.demo.thread.cache.Memoizer2 - future is null userId : 23   一次就对咯
        //09:11:03.573 [main] INFO com.jlxu.demo.thread.cache.Memoizer2 - cache ===> {"23":{"cancelled":false,"done":true}}
        //09:11:03.574 [main] INFO com.jlxu.demo.thread.cache.Memoizer2 - userId ===> 23
        //09:11:03.574 [main] INFO com.jlxu.demo.thread.cache.Memoizer2 - cache ===> {"23":{"cancelled":false,"done":true}}
    }

    //TODO:泛型，装饰者，map，为什么加锁性能差,JDK提供的同步容器，并发容器，同步工具类

    //ps 告诉缓存用于项目中总结
    //1）泛型  +3
    //2）如何使用缓存？
    //3）如何使用Spring IOC管理缓存 （参考启动类的bean或restTemplate,思考缓存相关的类要不要使用使用IOC？）
    //1：类的加载（方法不调用是不会执行的）+3
    //2：本案例中，缓存相关的类没有使用IOC
    //3：缓存如何优化，  多种类的缓存怎么处理 ？ 如客户的缓存
    // 因为是泛型 可以把V设计成map，A对应类别（用户，客户，，，）， V对应的用户的类别对应的数据，而不是只类别某个信息
    //4：同时添加没处理（缓存存在的最大意义）
    //5：缓存的使用场景不限于用户体系，如一些不变的数据统计，可以使用中间件（如Redis），那更好
}
