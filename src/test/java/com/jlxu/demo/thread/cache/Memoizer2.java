package com.jlxu.demo.thread.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 功能:装饰器,相同key数据，避免重复计算（终极版）
 * 创建时间：2020年03月01日
 * 文件名称：Memoizer1
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 7:49
 *
 * @auther jlxu
 */
@Slf4j
public class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();//TODO：volatile和并发容器使用的区别？
    //并发容器==>ConcurrentHashMap,同步工具===>Future
    //取代了原先的synchronize，实现了线程安全性的委托。
    private final Computable<A, V> computable;

    public Memoizer2(Computable computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws Exception {
        log.info("userId ===> {}", arg);
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                log.info("future is null userId : {}", arg);
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                //创建future(进源码看构造)
                FutureTask<V> ft = new FutureTask<>(callable);
                // use putIfAbsent to avoid multi thread compute the same value
                //为什么使用putIfAbsent而不是put
                //如果有两个都是计算userID=1的线程，同时调用put方法，那么返回的结果都不会为null，后面还是会创建两个任务去计算相同的值。
                //而putIfAbsent，当map里面已经有对应的值了，则会返回已有的值，否则，会返回null，这样就可以解决相同的值计算两次的问题。
                future = cache.putIfAbsent(arg, ft);
                if (future == null) {  //TODO： 如何没有获得执行结果（这里没有用线程池，没有调用执行方法所有需要这段代码，手动执行任务中的compute方法）
                    future = ft;
                    ft.run();
                }
            }
            try {
                log.info("cache ===> {}", JSON.toJSONString(cache));
                return future.get();
            } catch (CancellationException e) {//线程被中断
                // remove cache and go into next loop to retry
                cache.remove(arg);
                e.printStackTrace();
            } catch (ExecutionException e) {
                // throw it and then end
                // 然后进入下一个循环，重新计算，直到计算成功，或者抛出ExecutionException。
                e.printStackTrace();
                throw e;
            }
        }
    }
}