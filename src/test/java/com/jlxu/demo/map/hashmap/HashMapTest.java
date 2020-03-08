package com.jlxu.demo.map.hashmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：HashMap测试类  https://blog.csdn.net/justloveyou_/article/details/62893086
 * 创建时间：2020年03月01日
 * 文件名称：HashMapTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 22:51
 *
 * @auther jlxu
 */
@Slf4j
@RunWith(SpringRunner.class)
public class HashMapTest {
    @Test
    public void hashMapTest() {
        //hash方法的作用？/为什么不用equals方法
        //为什么重新计算key的hash值
        //怎么才能保证元素均匀分布到table的每个桶中呢？  ===> 想一想（结合取模的定义和结果https://blog.csdn.net/sinat_41152339/article/details/81054379?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task），再想一想就知道了
        //位与运算符（&）相当于取模  （TODO:暂时知道结果）
        //为什么HashMap的动态数组的长度是2的幂次方   见参考中的图（TODO：暂时结果论）
        //以上这些达到的效果是：不同的hash值发生碰撞的概率比较小，这样就会使得数据在table数组中分布较均匀，查询速度也较快
        //性能（速度&空间决定）
        //Entry<K，V>是链表吗？  是的  TODOhttps://blog.csdn.net/kyi_zhu123/article/details/52769469

        //博主的总结
        //因此，总的来说，HashMap 的底层数组长度总是2的n次方的原因有两个，即当 length=2^n 时：
        //不同的hash值发生碰撞的概率比较小，这样就会使得数据在table数组中分布较均匀，空间利用率较高，查询速度也较快；
        // h&(length - 1) 就相当于对length取模，而且在速度、效率上比直接取模要快得多，即二者是等价不等效的，这是HashMap在速度和效率上的一个优化。

        //补充
        //（1）Map扩容操作的核心在于重哈希。所谓重哈希是指重新计算原HashMap中的元素在新table数组中的位置并进行复制处理的过程
        //ps:HashMap添加和读取效率都挺高的，如果添加的时候要扩容resize,resize有重哈希耗时（实践种如果有条件的话，使用HashMap可以指定容量）
        //（2）随着HashMap中元素的数量越来越多，发生碰撞的概率将越来越大，所产生的子链长度就会越来越长，
        // 这样势必会影响HashMap的存取速度。为了保证HashMap的效率，系统必须要在某个临界点进行扩容处理，
        //（扩容时机） 该临界点就是HashMap中“元素”的数量在数值上等于threshold（table数组长度*加载因子）。但是，不得不说，扩容是一个非常耗时的过程，因为它需要重新计算这些元素在新table数组中的位置并进行复制处理。
        // 所以，如果我们能够提前预知HashMap 中元素的个数，那么在构造HashMap时预设元素的个数能够有效的提高HashMap的性能。我们直接看其源码：
        //栗子
        //如果决定使用HashMap(根据需求来) 可以确定元素的个数为18 public HashMap(int initialCapacity)方法可知 根据（initialCapacity（初始容量，即动态数组的大小，默认是16））和默认的负载因子0.75f 它会把阀值给出来
        //src[j] = null;   // src 回收  TODO：什么，看看垃圾回收?
        // TODO：单向链表是从对尾拿数据的？（案例HashMap的并发案例 线程1的key(3) 指向线程二的key(3)）  ps:好像不是从队尾，多看看案例想想
        //do {} while() 冲哈希有用到

        //重哈希源码不懂问题
        // // 将原数组 table 赋给数组 src
        //        Entry[] src = table;
        //        int newCapacity = newTable.length;
        //        // 将数组 src 中的每条链重新添加到 newTable 中
        //        for (int j = 0; j < src.length; j++) {
        //            Entry<K,V> e = src[j];
        //            if (e != null) {
        //                src[j] = null;   // src 回收
        //                // 将每条链的每个元素依次添加到 newTable 中相应的桶中
        //                do {
        //                    Entry<K,V> next = e.next;
        //                    // e.hash指的是 hash(key.hashCode())的返回值;
        //                    // 计算在newTable中的位置，注意原来在同一条子链上的元素可能被分配到不同的子链
        //                    int i = indexFor(e.hash, newCapacity);//确定新桶
        //                    e.next = newTable[i];TODO：从第一个理解就行了（newTable[i]是个新桶 为null ，结合下面一行代码，放入第一节点，因为都是从头方节点，所以放入的节点的下一个节点应该为null，后面依次类推（注意放的都是头部））
        //                    newTable[i] = e;直接把节点放到桶中
        //                    e = next;//赋值下个节点
        //                } while (e != null);
        //            }
        //        }


    }
}
