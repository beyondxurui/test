package com.jlxu.demo.jvm.memoryModel;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：JVM 内存模型
 * 创建时间：2020年04月04日
 * 文件名称：MemoryModelTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 18:43
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class MemoryModelTest {

    public void memoryModelTest() {
        //《JVM 内存模型概述》  回顾  怎么回顾（问题的方式）  TODO:
        //图解Java 垃圾回收机制
        //一. Java 虚拟机内存模型
        //内存模型介绍  JVM在执行程序的过程中，，为了   ，，分为，，，
        //线程私有的
        // 一个线程一个栈  程序计数器的租用？
        //https://www.cnblogs.com/shizhiyi/p/7857764.html  操作数栈
        //-Xms -Xmx -Xmn -Xss 含义    https://www.cnblogs.com/ceshi2016/p/8447989.html
        //栈？ 栈帧？ 栈帧中的局部变量表包含方法中和方法上的（***）    StackOverflowError 和 OutOfMemoryError？  栈帧和线程关系？  入栈？ 出栈？
        //本地方法栈   本地方法栈和JVM的联系   java虚拟机栈和虚拟机的联系     本地方法栈和java虚拟机栈区别？  对本地方法了解加深了吧（？）
        //             StackOverflowError 和 OutOfMemoryError
        //线程共享的
        //堆的定义？  为什么不需要程序代码显示释放？  为什么也称为GC堆？ （xx角度）分为xx代xx代？xx代又分为？ 分代收集算法？见垃圾回收章节
        // -Xmx -Xms的含义结合上面的地址？  什么情况OutOfMemoryError？  java堆可以是不连续的内存空间吗？
        //TLAB?  什么虚拟机在什么情况下，为什么要分配一块独立的空间TLAB? 其大小？  要加锁吗？（之前看够TL源码了）
        //Jvm在给xx的对象分配内存时  ？？==>"尽量"在TLAB上 为什么？
        // 下问（暂时不知道，后面看）中分配对象内存的性能和C基本是一样高效的，但如果对象过大的话则仍然是直接使用堆空间分配。
        //　　在下文中我们提到，虚拟机为新生对象分配内存时，需要考虑修改指针 (该指针用于划分内存使用空间和空闲空间) 时的线程安全问题？
        //为什么又线程安全问题？  TLAB起到了什么作用？
        //方法取  方法区和java堆的联系/相同点？ 方法区放在的是什么？存放的数据结构在什么期间生产的？（TODO:一定要区分方法区和栈，从线程是否共享和存放的是什么看）
        //”通常“方法区和永久代联系在一起？通常？什么情况下通常？  什么情况下抛出抛出 OutOfMemoryError ?
        // 1）运行时常量池和方法的关系？*   存放的时什么期间的什么？ * 什么也会抛出OutOfMemoryError 异常？ 为什么？
        //相对于Class常量池一个重要特征是？ 为什么叫运行时常量池？TODO:
        //  2）堆和方法区的区别?  从存放的数据结构和使用者的角度分析区别  TODO:
        // 方法区的回收
        //方法区的回收主要针对的是哪些？ 他们回收需要具备什么条件？是否苛刻？   博文中说：不使用了就一定回收 TODO:真的吗？满足什么条件
        // 他们的回收是一定吗？ 不是 是可以，那么场景需要虚拟机具备卸载的功能？ 为什么？

        //二. Java对象在虚拟机中的创建与访问定位
        //Java是一门面向对象的编程语言，在Java程序运行过程中无时无刻都有对象被创建和使用。在此，我们以最流行的HotSpot虚拟机以及常用的内存区域Java堆为例来探讨在虚拟机中对象的创建和对象的访问等问题。
        //1、对象在虚拟机中的创建过程
        //（1）中的两句话解释：第一句话是对第二句话的总结  TODO
        //（2）对象内存大小什么时候确定？谁分配？ 为什么？  见类加载  根据什么？一般来说内存分配有几种方式？ 方式的具体描述？
        //除了划分可用空间外，还需要考虑什么？解决这个问题有什么方案？   CAS?
        //（3）分配的初始化有几种不同   记住 后和时
        //（4）  上面(1,2,3)完成后，从虚拟机和java程序的视角看，有什么不同？TODO:
        //2、对象在虚拟机中的访问定位
        //创建对象是为了使用对象,那么谁同谁使用具体的对象呢？ TODO:
        //谁中的谁通过什么方式访问具体对象？  这些方式的描述？（见博文和桌面图）   这些方式的区别？

        //三. 内存异常产生情况分析
        //1、Java堆溢出 (OOM)  OutOfMemory
        //大对象？
        // List list=new ArrayList();   // 持有“大对象”的引用，防止垃圾回收
        //        while(true){
        //            int[] tmp = new int[10000000];  // 不断创建“大对象”
        //            list.add(tmp);
        //        }
        //如何处理 MemoryLeakor和OutOfMemoryError问题？  注：需要java内存模型和jcm垃圾回收的的支持 TODO:
        //2、虚拟机栈和本地方法栈溢出 (SOF/OOM)
        //虚拟机栈和本地方法栈区别？ 其实时回顾上面的线程私有内存区域
        //1）SOF  StackOverflow
        //什么时候抛出出StackOverflowError异常？==>如果线程请求的栈深度大于虚拟机栈允许的最大深度，将抛 -Xss？ 见上卖弄地址
        //public static void main(String[] args) {
        //          method();
        //    }
        //    // 递归调用导致 StackOverflowError
        //    public static void method(){
        //        method();
        //    }
        //案例SOF的原因是什么？怎么处理？
        //(2). OOM
        //该标题下？什么时候会抛出OutOfMemoryError？==>  如果虚拟机在拓展栈时无法申请到足够的内存空间
        //具体==>当Java 程序启动一个新线程时，若没有足够的空间为该线程分配Java栈  TODO：
        //一个线程Java栈的大小由-Xss设置决定 一个线程？ TODO:
        //ps:注意看这里大标题，===>虚拟机栈和本地方法栈溢出
        //3、方法区和运行时常量池溢出 (OOM)
        //运行时常量池溢出的情况： String.intern()是一个native方法  TODO:结合上下文品品的，再品品
        //String.intern()的作用？再jdk1.6及以前和jdk及之后有什么区别？TODO:
        //什么时候会抛出OutOfMemoryError异？
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);//true   字符串对象引用（第一次返回该字符串的引用）
        String str2 = new StringBuilder("java").toString();//相当于什么？TODO: *******   TODO:不可变对象回顾（六. 字符串常量池中的，尤其是情景1，2，3）
        System.out.println(str2.intern() == str2);//false   //动一次：字符串常量引用   第二是字符串对象引用
        String str3 = "dfg";
        String intern = str3.intern();
        System.out.println(intern == str3);//true   字符串常量引用

        //ps:  对上面解释的验证   得出结论：自己的验证是正确的
        String str4 = "abc";
        String str5 = new String("abc");
        String str6 = str4.intern();
        String str7 = str5.intern();

        System.out.println(str4 == str5);   //false
        System.out.println(str4 == str6);   //true
        System.out.println(str5 == str7);   //false


        //ps
        //不可变对象String回顾 （六）  已处理
        //内存泄漏参考地址 TODO: https://www.jianshu.com/p/54b5da7c6816   要结合内存模型和垃圾回收一起看
        //异常总结
        //1）-Xmx(最大堆容量) 和 -Xms(最小堆容量)    堆？  只要不断地创建对象，并且保证GC roots到对象之间有可达路径来避免垃圾回收机制清除这些对象 OOM:OutOfMemory
        //垃圾回收的 六. Java中的内存泄露问题 ，和这里优点像
        //2）-Xss 参数可以设置虚拟机栈大小      栈?：线程私有  那么？ 递归方法调用会导致栈溢出   SOF:StackOverflowError    虚拟机在拓展栈时无法申请到足够的内存空间，则抛出OOM:OutOfMemoryError异常
        //3）方法区和运行时常量池溢出 (OOM)  如不停的手动入池
        //TODO:JVM 的这两篇看一两次时不够的，还得继续看，找相关的文献看
    }
}
