package com.jlxu.demo.map.concurrenthashmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:ConCurrentHashMapTest测试    https://blog.csdn.net/justloveyou_/article/details/72783008
 * 创建时间：2020年03月07日
 * 文件名称：ConCurrentHashMapTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/7 21:10
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ConCurrentHashMapTest {

    @Test
    public void conCurrentHashMapTest() {
        //TODO: 效果是加一，和精度有关系
        int i = 1;
        ++i;
        log.info("i ===> {}", i);

        //问题和总结
        //（1）容量(指定initialCapacity的话，看源码，算的， 不指定是空的)和指定段数目/并发级别（默认16）
        //（2）段和桶的区别===> 见博主五中的图（桌面另存了，名：ConCurrentHashMap示意图） Segment实例：段  HashBucket：桶
        //(3)java里面的安全怎么理解？
        //3-1 单线程  不存在不安全，都是安全的
        //3-2 多线程  读/非结构变化（更新）操作 能不能同步（线程1操作对象，线程2是否可见） 写/结果性变化（曾删）操作就很复杂了 后续补充
        //（4） put方法运算符部分真的不懂   TODO:后续有时间要处理（**）
        //（5）TODO:文章的重排序指的是扩容？
        //（6）多看看put（*） remove（* ，克隆代码没体现吧） clear方法
        //（7）机制：1：volatile 2：HashEntry不变性 3：value为null加锁
        //1和2：减少了请求同一个锁的频率（读操作一般不需要加锁就能够成功获得值）
        //3减少了持有同一个锁的时间  //ps所以提高性能
        //(8)ConCurrentHashMap中的HashEntry和HashMap中的Entry区别不大  区别两个字：属性修饰符由差异（具体见参考地址）
        //扩容时机：段中元素的数量超过这个值（ transient int threshold;  ）就会对Segment进行扩容 冲哈希2倍
        //扩容（运算符）和冲哈希   TODO
        //(9)rehash TODO: 三个方面看源码桶中的头节点（这个不一定对），非头节点，jdk优化了一定效率  具体看源码
        //(10)跨段处理（TODO:个人感觉不是安全时安全，不是十分的同步，因为统计结束还是执行了代码，可能不了解RETRIES_BEFORE_LOCK有关系吧）  重试中的 k < RETRIES_BEFORE_LOCK
        //（11） 图
        //（12）在理想状态下，ConcurrentHashMap 可以支持 16 个线程执行并发写操作（如果并发级别设为16），及任意数量线程的读操作。
    }
}
