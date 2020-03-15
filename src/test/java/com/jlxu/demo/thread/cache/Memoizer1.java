package com.jlxu.demo.thread.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能:装饰器
 * 创建时间：2020年03月01日
 * 文件名称：Memoizer1
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 7:49
 *
 * @auther jlxu
 */
@Slf4j
public class Memoizer1<A, V> implements Computable<A, V> {
    //    private final Map<A, V> cache = new HashMap<>();
    private final Map<A, V> cache = new ConcurrentHashMap<>();//版本二-3：性能好
    private final Computable<A, V> computable;

    public Memoizer1(Computable computable) {
        this.computable = computable;
    }

    @Override
    //public synchronized V compute(A arg) throws InterruptedException {//版本二-1：synchronized粒度太大，性能差
    public V compute(A arg) throws Exception {
        log.info("userId ===> {}", arg);
        V result = cache.get(arg);
        //synchronized (this) {//版本二-2：synchronized粒度大，性能差，怎样办 ===> 两个字委托   ConcurrentHashMap(线程安全的，HashMap线程不安全)
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
            log.info("cache ===> {}", JSON.toJSONString(cache));
        }
        //}
        return result;
    }
}