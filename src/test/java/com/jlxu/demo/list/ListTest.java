package com.jlxu.demo.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:常用List测试    https://blog.csdn.net/justloveyou_/article/details/52955619
 * 创建时间：2020年03月06日
 * 文件名称：ListTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/6 20:54
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ListTest {
    //List 特性  移步到地址中看


    //arrayList测试
    @Test
    public void arrayListTest() {
        //列表结构  ===>  动态数据

        //ArrayList不是线程安全的，只能用在单线程环境下，多线程环境下可以考虑用 Collections.synchronizedList(List l) 函数返回一个线程安全的ArrayList类，也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。
        //当容量不够时，每次增加元素，都要将原来的元素拷贝到一个新的数组中，
        // 扩充容量的方法ensureCapacity。ArrayList在每次增加元素（可能是1个，也可能是一组）时，
        // 都要调用该方法来确保足够的容量。当容量不足以容纳当前的元素个数时，就设置新的容量为旧的容量的1.5倍加1，
        // 如果设置后的新容量还不够，则直接新容量设置为传入的参数（也就是所需的容量）。之后，用 Arrays.copyof()
        // 方法将元素拷贝到新的数组。从中可以看出，当容量不够时，每次增加元素，都要将原来的元素拷贝到一个新的数组中
        //ensureCapacity  Arrays.copyof()
        //copyOf的底层还是需要System.arraycopy()实现的
        //总结：ArrayList 基于数组实现，可以通过下标索引直接查找到指定位置的元素，因此查找效率高，但每次插入或删除元素，就要大量地移动元素，插入删除元素的效率低；


        //ps:（1）读源码时，如过遇到多种情况，可以要看到底时那个地方调用的很重要
        //栗子：Arrays的 copyOf方法有需要地方调用，如果是ArrayList里调用的(Object)newType == (Object)Object[].class 为true,即走true的逻辑
        //（2）fail-fast机制：遍历时候 结构被改变（代码造成的是一个bug）
        //（3）如果可预知数据量的多少，可在构造ArrayList时指定其容量  （如防疫的统计接口）
        //了解transient和native关键字

        //补充
        //（1） 扩容时机 如添加的时候 通过size+1确定是否要扩容，需要就判断正常时1.5
    }

    @Test
    public void linkedListTest() {
        //LinkedList 本质是一个双向链表，可用作List，Queue，Stack和Deque；  TODO: why  后续处理
        //LinkedList 并未实现 RandomAccess 接口；
        //相对于ArrayList，LinkedList有更好的增删效率，更差的随机访问效率；
        //ps：TODO:博客（jdk1.6）种获取节点的源码没看懂
        //  TODO：链表的图  如果是个空的链表会形成一个闭环
    }
}
