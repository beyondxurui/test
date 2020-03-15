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

}
