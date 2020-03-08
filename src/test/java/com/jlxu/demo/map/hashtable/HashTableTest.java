package com.jlxu.demo.map.hashtable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能
 * 创建时间：2020年03月08日
 * 文件名称：HashTableTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/8 15:34
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class HashTableTest {
    @Test
    public void hashTableTest(){
        //1、介绍Hashtable和HashMap的数据结构
        //我们知道，在Java中最常用的两种结构是数组和链表，
        // 其中，数组的特点是：寻址容易，插入和删除困难；而链表的特点是：寻址困难，插入和删除容易。
        // Hashtable和HashMap综合了两者的特性，是一种寻址容易、插入和删除也容易的数据结构。实际上，Hashtable和HashMap本质上都是一个 链表数组,见保存桌面的图（图名：HashMap图1(jdk1.6)）
        //2、Hashtable，HashMap和ConcurrentHashMap的区别
        //（2）扩容时机不同，扩容的方式不同
        //在插入目标K/V对前，先检查是否需要扩容（不同于HashMap的插入后检查是否需要扩容） 看源码可知
        //Hashtable：扩到原始容量的2倍再加1   HashMap：原始容量的2倍   ConcurrentHashMap同HashMap
        //（3）容量不同
        //Hashtable：2的幂次方（散列均匀，见表格栗子分析）   HashMap：（不需要2的幂次方）  ConcurrentHashMap同HashMap
        //Hashtable 默认容量：11    HashMap默认容量：16    ConcurrentHashMap同HashMap
        //（安全性不同）
        //Hashtable：（多线程环境下）安全   HashMap：（多线程环境下）不安全  看Entry的属性（前者：final K key ，后者：K key）应该能猜到（读取时同步的（加锁处理了））
        //（4）是否允许null
        //HashMap：最多允许一个key为null，允许多个value为null  Hashtable：都不允许
        //（5）定位桶位
        //HashMap中用于定位桶位的Key的hash的计算过程要比Hashtable复杂一点（和是否允许null有关系，具体不了解）


        //HashMap、Hashtable 与ConcurrentHashMap的联系与区别  （2、中已经总结这里不再）
        //（1） HashMap和Hashtable的实现模板不同：虽然二者都实现了Map接口，
        // 但HashTable继承于Dictionary类，而HashMap是继承于AbstractMap。
        // Dictionary是是任何可将键映射到相应值的类的抽象父类，
        // 而AbstractMap是基于Map接口的骨干实现，它以最大限度地减少实现此接口所需的工作。
        //（2）HashMap和Hashtable的地位不同：在并发环境下，Hashtable虽然是线程安全的，
        // 但是我们一般不推荐使用它，因为有比它更高效、更好的选择ConcurrentHashMap；而单线程环境下，
        // HashMap拥有比Hashtable更高的效率(Hashtable的操作都是同步的，导致效率低下)，所以更没必要选择它了。
        //ps  Hashtable:我tm存在就是个错误
        //（3）ConcurrentHashMap和Hashtable对键值限制一样，不允许null


    }
}
