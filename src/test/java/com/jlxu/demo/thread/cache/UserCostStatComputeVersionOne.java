package com.jlxu.demo.thread.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能:糙版缓存
 * 创建时间：2020年03月01日
 * 文件名称：UserCostStatComputeVersionOne
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 6:43
 *
 * @auther jlxu
 */
@Slf4j
public class UserCostStatComputeVersionOne {
    private final Map<String, BigInteger> cache = new HashMap(); //重温final（修饰引用类型）

    public synchronized BigInteger compute(String userId) throws InterruptedException {//这里要加synchronized防止线程不安全想到了吗？
        //框架（SpringBoot/SpringMVC）本身式多线程，处理线程安全问题，如果创建了多线程（应该）注意线程安全问题

        //逻辑处理
        BigInteger result = cache.get(userId);
        if (result == null) {/*查库*/
            result = doCompute(userId);
            cache.put(userId, result);
        }
        log.info("cache ===> {}", JSON.toJSONString(cache));
        return result;
    }

    private BigInteger doCompute(String userId) throws InterruptedException {
        // assume it cost a long time...
        TimeUnit.SECONDS.sleep(3);
        return new BigInteger(userId);
    }

    //ps:缓存思路都没有，别说糙版了，哎！！！
    //第二次思路记录：想到用户一个类，属性为一种数据结构，（没想到方法，获取的结构的方法）
    //问题
    //1、如果用这个到项目中（单线程环境下，因为 这段代码由线程安全问题），怎么开发===> 把属性放在业务的从原位子
    //2、能够复用吗？（“给原先没有某某功能的函数，加上某某功能，这话听着很熟悉啊…”，装饰者模式    ）
    //3、代码的性能性能好吗？ （一个进入锁域内，其他都得等着） （性能变法容器处理，线程安全委托给它去处理就好了）
    //4、去掉锁用把线程安全问题委托给并发容器处理，那么(由于把synchronize去掉了，)现在不同的用户id可以同时计算，但是也导致了相同用户id也会同时被计算，也就是说同样的数据会被计算多次，这样缓存就没意义了
    //Java早就提供了Future和FutureTask
    //5、实际应用中，这样可以了吗？
    //在实际应用中，只有这些基础知识是不够的，最简单的例子，你不可能每次请求过来都创建线程，这会吃光内存的。所以，你需要一个线程池，来限制线程的数量
}
