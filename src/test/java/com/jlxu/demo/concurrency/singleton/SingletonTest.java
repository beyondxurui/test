package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 :单例测试
 * 创建时间：2020年03月14日
 * 文件名称：SingletonTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:08
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class SingletonTest {
    @Test
    public void singletonTest() {
        //一. 单例模式概述  ps:单例的好处或作用
        //综上所述，单例模式就是为确保一个类只有一个实例，并为整个系统提供一个全局访问点的一种方法
        //二. 单例模式及其单线程环境下的经典实现
        //1、单例模式理论基础
        //定义： 确保一个类只有一个实例，并为整个系统提供一个全局访问点 (向整个系统提供这个实例)。
        //类型： 创建型模式
        //结构： x
        //三要素：私有的构造方法；指向自己实例的私有静态引用；以自己实例为返回值的静态的公有方法。
        // 2、单线程环境下的两种经典实现  饿汉和懒汉
        /**
         * @see com.jlxu.demo.concurrency.singleton.Singleton
         * @see com.jlxu.demo.concurrency.singleton.Singleton2
         */
        //TODO:回顾类的加载
        //总之，从速度和反应时间角度来讲，饿汉式（又称立即加载）要好一些；从资源利用效率上说，懒汉式（又称延迟加载）要好一些。
        //3、单例模式的优点  在内存中只有一个对象，节省内存空间； 避免频繁的创建销毁对象，可以提高性能； 避免对共享资源的多重占用，简化访问； 为整个系统提供一个全局访问点。
        //4、单例模式的使用场景  其核心在于为整个系统提供一个唯一的实例
        // 场景（不限于以下）：有状态的工具类对象； 频繁访问数据库或文件的对象；
        //5、单例模式的注意事项  我们必须使用"单例类提供的公有工厂方法得到单例对象"，而不应该使用反射来创建，否则将会实例化一个新对象。
        //三. 多线程环境下单例模式的实现
        //为什么说饿汉式单例天生就是线程安全的？
        //传统的懒汉式单例为什么是非线程安全的？
        //怎么修改传统的懒汉式单例，使其线程变得安全？
        //线程安全的单例的实现还有哪些，怎么实现？
        //双重检查模式、Volatile关键字 在单例模式中的应用
        //ThreadLocal 在单例模式中的应用
        /**
         * @see com.jlxu.demo.concurrency.singleton.Test
         * @see com.jlxu.demo.concurrency.singleton.Test2
         */
        //TODO:== equals hashCode回顾     哈希 哈希表，hashCode equals区别以及关系？
        //以下基于jdk1.6
        //TODO：哈希：就是把任意长度的输入(又叫做预映射， pre-image)，通过散列算法，变换成固定长度的输出(int)，该输出就是散列值。（一种压缩） 如在hashMap中，哈希用于确定元素在桶中断位子
        //TODO：哈希表（数组加链表，基于jdk1.6）
        //TODO：hashCode 由对象的内存地址和equals决定  待定
        //TODO：如果equals比相等（看Object对象和String对象的equals,以及equals存在的寓意），由于上面可知，hashCode必相等，反之不一定

        //3、实现线程安全的懒汉式单例的几种正确姿势
        //1）方法加synchronized  ===>运行效率会很低，因为同步块的作用域有点大
        //2）:synchronized(Singleton2.class){  // 使用 synchronized 块，临界资源的同步互斥访问    和使用synchronized方法的版本相比，基本没有任何效率上的提高
        //3）使用内部类实现延迟加载
        /**
         * 使用内部类实现延迟加载
         * @see com.jlxu.demo.concurrency.singleton.Test3
         */


        //四. 单例模式与双重检查(Double-Check idiom)
        /**
         *  单例模式与双重检查
         * @see com.jlxu.demo.concurrency.singleton.Test4
         */
        //TODO: new Singleton3() 是一个非原子操作。代码行singleton3 = new Singleton3(); 的执行过程可以形象地用如下3行伪代码来表示：
        //memory = allocate();        //1:分配对象的内存空间
        //ctorInstance(memory);       //2:初始化对象
        //singleton3 = memory;        //3:使singleton3指向刚分配的内存地址

        //五. 单例模式 与 ThreadLocal

    }
}
