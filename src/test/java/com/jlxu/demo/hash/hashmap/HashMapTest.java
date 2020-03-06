package com.jlxu.demo.hash.hashmap;

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
        //为什么HashMap的动态数据的长度是2的幂次方   见参考中的图（TODO：暂时结果论）
        //以上这些达到的效果是：不同的hash值发生碰撞的概率比较小，这样就会使得数据在table数组中分布较均匀，查询速度也较快
        //性能（速度&空间决定）
        //Entry<K，V>是链表吗？  是的  TODO：https://blog.csdn.net/kyi_zhu123/article/details/52769469

        //博主的总结
        //因此，总的来说，HashMap 的底层数组长度总是2的n次方的原因有两个，即当 length=2^n 时：
        //不同的hash值发生碰撞的概率比较小，这样就会使得数据在table数组中分布较均匀，空间利用率较高，查询速度也较快；
        // h&(length - 1) 就相当于对length取模，而且在速度、效率上比直接取模要快得多，即二者是等价不等效的，这是HashMap在速度和效率上的一个优化。

    }
}
