package com.jlxu.demo.map.linkedhashmap;

import com.jlxu.demo.designmode.decorator.demo1.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:LinkedHashMap测试
 * 创建时间：2020年03月06日
 * 文件名称：LinkedHashMapTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/6 16:55
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class LinkedHashMapTest {
    @Test
    public void linkedHashMapTest() {
        //（需要掌握的知识）
        //和HashMap的异同  同：（1）继承HashMap （1）线程不安全对象
        // 异：（1）结构不一样：一个是哈希表，一个是哈希表加双向链表
        // （2）一个可以随机访问 一个在添加/获取时做了处理 处理如下
        //取（get）的具体说明：link,,LinkedHashMap重写HashMap，加了个e.recordAccess(this)（注意这里的recordAccess方法，
        //存（put）addEntry(hash, key, value, i);  // LinkedHashMap重写了HashMap中的createEntry方法
        //存取部分： 如果链表中元素的排序规则是按照插入的先后顺序排序的话，该方法什么也不做；如果链表中元素的排序规则是按照访问的先后顺序排序的话，则将e移到链表的末尾处）
        //LinkedHashMap重写了HashMap 的迭代器，它使用其维护的双向链表进行迭代输出。
        //ps(1):上面总结看源码

        //问题
        //(1):怎么比较对象？=====> TODO: 识符有继承或者实现关系  定义（属性和方法（特别是构造））
        //TODO（2）transfer方法? （3）以及HashMap的transfer方法 ?   （4）RUL算法？LRU结构？（recordAccess方法） 实现了解   （5）LinkedHashMap 有序性原理分析部分源码 Entry<K,V> nextEntry()
        //ps  TODO:源码部分问题，等到看jdk1.8的时候走debug看

        //冲哈希问题
        //void transfer(HashMap.Entry[] newTable) {
        //        int newCapacity = newTable.length;
        //        // 与HashMap相比，借助于双向链表的特点进行重哈希使得代码更加简洁
        //        for (Entry<K,V> e = header.after; e != header; e = e.after) {//TODO:header.after一个节点（见图，有点绕：桶是Entry（就是链表：由节点组成））（Entry（链表））
        //            int index = indexFor(e.hash, newCapacity);   // 计算每个Entry所在的桶
        //            // 将其链入桶中的链表
        //            e.next = newTable[index];//TODO:同HashMap注释
        //            newTable[index] = e;
        //        }
        //    }
        //ps TODO:重写了HashMap的冲哈希，区别就是  HashMap桶（Entry:链表（由节点组成））没有把桶中节点连接起来
        //TODO:总之：面向对象还是不行   虽然叫桶：由链表组成（是一个节点），但其实还是节点
    }
}
