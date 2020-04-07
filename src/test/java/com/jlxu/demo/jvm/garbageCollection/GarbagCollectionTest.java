package com.jlxu.demo.jvm.garbageCollection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Vector;

/**
 * 功能：JVM垃圾回收
 * 创建时间：2020年04月01日
 * 文件名称：GarbagCollectionTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/1 23:35
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class GarbagCollectionTest {
    private static Vector v = new Vector();

    @Test
    public void test() {

        //图解Java 垃圾回收机制
        //一、垃圾回收机制的的意义
        //JVM内存回顾如：内存结构图？含义？  堆和方法区的区别？（上部分 见上）
        //自动内存管理可以解决那两个问题？  内存分配问题？ TODO:2020-4-3日处理
        //什么式垃圾回收？  什么是垃圾回收策略？ 内存分配规则？  TODO:
        //垃圾回收机制的引入有什么好出？   哪些内存需要回收？  什么时候回收？ 如何回收？
        //Stop-the-World指的是？ Stop-the-World发生时和线程的关系？  吞吐量什么意思？==>见提高篇文档
        //内存泄漏的概念？ TODO:


        //二、如何确定一个对象是否可以被回收
        //对象“可以”被回收的是通过算法/策略是实现的，他们是什么？
        //引用计数器和对象实例的关系？ 对象实例？（就是创建的对象）todo:我一般称为实例，一个意是
        //回顾 java中的 = ==的含义 在不可变对象中
        //特别地，当一个对象实例被垃圾收集时，它引用的任何对象实例的引用计数器均减 1，这句话怎么理解？ 要参考上下文
        //计数器的加减？TODO:见博文中描述
        //引用计数器？ 引用计数器的优缺点？  缺点的理解？（逻辑上理解，但是看代码和计数器的加减描述，不懂）TODO:不懂
        //可达性分析算法是什么？见描述            GC root?    可以作为GC root对象有哪些？TODO:

        //三、垃圾收集算法
        //标记清除算法定义？  缺点？
        //复制算法
        //复制算法的定义？  优点？ 什么场景用该算法？（栗子，堆空间中新生代垃圾回收有什么特点）
        //标记整理算法
        //标记整理算法定义？ 复制算法的缺点？（即什么场景下不适合） 引出了该算法   该算法和标记清理算法的区别？
        //分代收集算法
        //定义？为什么要使用该算法/优点/好处？ 定义的栗子非常非常重要 TODO：避免理解错误
        //1）新生代 （Young Generation）
        //新生代的目标？ 一般情况下，所有新生成的对象放在那里？  堆中垃圾回收的整体过程？ FullGC？什么时候触发？ MinorGC? 特点？什么时候触发？
        //2）老年代（Old Generation）
        //老年存放的对象的特点？ 新生代中对象，什么情况下进入老年代？新生代和老年代内存空间比例大概是多少？
        //3）永久代（Permanent Generation）
        //存放的是什么？  什么场景下要设置一个较大的永久代空间？
        //小节
        //　由于对象进行了分代处理，因此垃圾回收区域、时间也不一样   垃圾回收的两种类型？  他们是什么？ 是怎么样的？

        //四. 垃圾收集器
        //如果说垃圾收集算法是内存回收的方法论，那么垃圾收集器就是内存回收的具体实现，思考思考 TODO
        //有几种垃圾收集器 ？作用于什么？特点？有什么联系 TODO:

        //五. 内存分配与回收策略
        // （逆向思维，先问题，再原理分析） 内存的分配是一成不变的吗？ 如果不是，和哪些有关系？ 内u才能分配过程？==>见1）
        //现在的商业虚拟机“一般”才用什么算法回收新生代？  HotSpotHotSpot默认Eden和Survivor的大小比例是？ 同上面的比例问题，不过这里描述的跟纤细
        //HotSpot虚拟机？ TODO：后续处理
        //哪些对象会进入老年代？
        //java垃圾回收的是谁提供的功能？  java垃圾回收是什么/定义？  五中的最后一段话  TODO:

        //六. Java中的内存泄露问题
        //为什么HashMap、Vector 等集合类的静态使用最容易出现内存泄露静？TODO:这些静态变量的生命周期和应用程序一致
        //哪些会导致内存泄漏问题？TODO:
        for (int i = 1; i < 100; i++) {
            Object o = new Object();
            v.add(o);
            o = null;
        }

        for (int i = 0; i < 100; i++) {
            Object o = new Object();
            Object b = o;
            o = null;
            v.add(b); //感觉这样也不行啊  TODO:后续处理
            // https://blog.csdn.net/u012516166/article/details/77014910
            // https://blog.csdn.net/riemann_/article/details/89426634
        }

        //七. 知识点补充
        //1、引用  1). 引用概述
        //1.2之前对引用的定义没看懂 TODO：再品品 ，哈哈是不是懂了一点
        //1.2的引用定义的缺点？ TODO:
        //1.2之后对引用进行了哪些扩充？
        //2). 引用的种类及其定义
        // 引用的种类及其定义 定义是什么。有什么特点？TODO:
        //2、方法区的回收 见内存模型我的问题记录  注意两个字  "必要“

        //ps：选着那种算法，其实就是什么场景下什么算法效率最高
        //六. Java中的内存泄露问题 TODO:后续处理

    }
}
