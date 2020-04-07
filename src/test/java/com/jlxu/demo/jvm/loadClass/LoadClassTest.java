package com.jlxu.demo.jvm.loadClass;

import com.jlxu.demo.jvm.loadClass.demo3.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：深入理解Java类加载器(一)：Java类加载原理解析
 * 创建时间：2020年04月05日
 * 文件名称：LoadClassTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 16:43
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class LoadClassTest {
    @Test
    public void LoadClassTest() {
        //摘要：
        //一个异常:java.lang.ClassNotFoundException,引出类加载机制
        //本文简述了JVM三种预定义类加载器，分别是什么？
        //并介绍和分析它们之间的关系和类加载所采用的双亲委派机制，给出并分析了与Java类加载原理相关的若干问题。

        //一、引子
        //为什么要学类加载器？ 排查程序出现的类加载失败等技术问题，对理解Java虚拟机的连接模型和Java语言的动态性都有很大帮助。

        //二. Java 虚拟机类加载器结构简述
        //1、JVM三种预定义类型类加载器
        // 三种加载加载的是那个地方的类？  TODO:
        //启动（Bootstrap）类加载器    它负责将JAVA_HOME/lib下面的核心类库或-Xbootclasspath选项指定的jar包等虚拟机识别的类库加载到内存中。  -Xbootclasspath：不懂
        //扩展（Extension）类加载器    它负责将JAVA_HOME /lib/ext或者由系统变量-Djava.ext.dir指定位置中的类库加载到内存中             系统变量-Djava.ext.dir 不懂
        //系统（System）类加载器       它负责将用户类路径(java -classpath或-Djava.class.path变量所指的目录，即当前类所在路径及其引用的第三方类库的路径
        //2、类加载双亲委派机制介绍和分析
        // 通过源码分析 系统类加载器和扩展类加载器把父类分别设置为标准扩展类加载器(ExtClassLoader)，父类加载器设置为null（null 本身就代表着引导类加载器）
        // TODO:源码是强制设置了，但是getSystemClassLoader()获得的是系统类加载器 但是呢？ 我的猜测 ==>  initSystemClassLoader()这个方法应该对胸痛类加载的变量做了处理，最后转换要的加载器
        System.out.println(LoadClassTest.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(LoadClassTest.class.getClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@4b85612c
        System.out.println(LoadClassTest.class.getClassLoader().getParent().getParent());//null

        //3、类加载双亲委派示例
        /**
         * @see  com.jlxu.demo.jvm.loadClass.demo.ClassLoaderTest
         */
        //博文中ClassNotFoundException异常的原因需要知道  标准扩展类加载器，没有加载到类/加载的类不存在（因为把xx/lib/ext下的类字节码移除了）
        //ps  为什么我的jdk按照目录下的lib下没有ext   TODO:因为ext在jre/lib下  而且看不到？可用代码看，如5.7）
        //案例三行代码的含义 TODO:后续看看

        //三. Java 程序动态扩展方式
        //1、反射 (调用java.lang.Class.forName(…)加载类)
        //运行时动态扩展java应用程序有哪些方式
        //栗子：加载数据库驱动   深入等到后面在处理
        //2、用户自定义类加载器
        //自定义类加载器的工作流程？ 命名空间
        //TODO：
        //1、首先检查请求的类型是否已经被这个类装载器装载到命名空间中了，如果已经装载，直接返回；否则转入步骤2；
        //2、委派类加载请求给父类加载器（“更准确的说应该是双亲类加载器”，真实虚拟机中各种类加载器最终会呈现树状结构），如果父类加载器能够完成，则返回父类加载器加载的Class实例；否则转入步骤3；
        //3、调用本类加载器的findClass（…）方法，“试图获取对应的字节码”。如果获取的到，则调用defineClass（…）导入类型到方法区；如果获取不到对应的字节码或者其他原因失败，
        // 向上抛异常给loadClass（…）， loadClass（…）转而调用findClass（…）方法处理异常，直至完成递归调用。
        //ps 总结： 见上面   代码层面没有总结：TODO:大前提一定要注意，用户自定义类加载器


        //四. 常见问题分析
        //1、由不同的类加载器加载的指定类还是相同的类型吗？
        /**
         * 注释：这个案例有问题，报错：Exception in thread "main" java.lang.NoClassDefFoundError: java/lang/Object
         * 后面看情况，要不要处理   TODO：问题找到了，是因为下面5中的1）
         * // TODO：而且题目是有特殊背景的（打jar包，，，，，一系列的操作）
         * @see com.jlxu.demo.jvm.loadClass.demo3.TestBean
         */
        //ps
        //双亲委派
        //debug  TODO:
        //String常用api
        //IO概念 流的概念  流读写操作的含义  字节，字节数据  TODO:
        //反射  如Class.for()  和  Class的实例的newInstance 的区别？  这个得看源码  TODO:
        //this  不是在那个类中指的就是那个类对象 TODO:
        //多层方法/递归调用时的  异常压栈  TODO:

        //2、在代码中直接调用Class.forName(String name)方法，到底会触发那个类加载器进行类加载行为？  调用类
        //Class.forName(String name)默认会使用调用类的类加载器来进行类加载

        //3、在编写自定义类加载器时，如果没有设定父加载器，那么父加载器是谁？  系统类加载类即应用类加载器
        //为什么是三个？ TODO:因为是双亲委派

        //4、在编写自定义类加载器时，如果将父类加载器强制设置为null，那么会有什么影响？如果自定义的类加载器不能加载指定类，就肯定会加载失败吗？
        //为什么是一个？ TODO:同上

        //3和4TODO: ps:前提都是不改变默认的委派逻辑

        //5、编写自定义类加载器时，一般有哪些注意点
        //1)、一般尽量不要覆写已有的loadClass(…)方法中的委派逻辑（Old Generation）
        //案例见 TestBean
        //2). 正确设置父类加载器
        //3). 保证findClass(String name)方法的逻辑正确性
        //TODO:事先尽量准确理解待定义的类加载器要完成的加载任务，确保最大程度上能够获取到对应的字节码内容。
        //TODO:现在会写自定义加载器的测试栗子了把

        //6、如何在运行时判断系统类加载器能加载哪些路径下的类？
        System.out.println("jlxu");
        TestBean tb = new TestBean();
        System.out.println(tb.getClass().getClassLoader());//1:    sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoader.getSystemClassLoader());//2:    sun.misc.Launcher$AppClassLoader@18b4aac2
        //URLClassLoader实例方法 getURLs()
        //4: 直接通过获取系统属性java.class.path来查看当前类路径上的条目信息 TODO:多品品
        System.out.println(System.getProperty("java.class.path"));//  太多
        //TODO:为什么还有idea的   如:   J:\java\soft\idea2018-3-6\lib\idea_rt.jar;
        //并且其加载路径就是用户类路径，包括当前类路径(TODO:ava_home/lib下的吗?)和引用的第三方类库的路径。
        //

        //7、如何在运行时判断标准扩展类加载器能加载哪些路径下的类？
        //栗子见 TestBean

        //五. 开发自己的类加载器
        //java.lan.ClassLoader里面Class<?> loadClass(String name)和 protected final Class<?> defineClass(byte[] b, int off, int len)的联系？ TODO:
        //上面两个方法的含义， loadClass:启动类的加载过程     defineClass:真正完成类的加载工作
        //                  前者称为一个类的定义加载器（defining loader），后者称为初始加载器（initiating loader）
        //类加载器会直接使用缓存的类的实例，而不会尝试再次加载
        //什么场景下需要自定义类加载器呢？TODO:
        //下面两种方式实现有什么特点 TODO:
        //1、文件系统类加载器
        /**
         *@see com.jlxu.demo.jvm.loadClass.demo4.FileSystemClassLoader
         */
        //2、网络类加载器
        /**
         * @see com.jlxu.demo.jvm.loadClass.demo5.client.CalculatorTest
         */

        //ps  以下所有的问题后续都要处理
        //1、第一个案例中流的问题 TODO:
        //2、 Class.forName(className)和newInstance的区别？ 表面就是：
        //3、Class.forName(className)和ClassLoader.loadClass(className)的区别
        //Class.forName(className)装载的class已经被初始化，  单参数默认初始化了， 多参数可以设置
        // 而 ClassLoader.loadClass(className)装载的class还没有被link，
        //https://blog.csdn.net/as403045314/article/details/101337322
        //4、有返回值得方法（return）  throws 能启动该什么作用？ 为什么？TODO:
        //5、File.separatorChar？
        //6、设计模式的单一自责原则
        //7 this   TODO:  this在那个类就是那个类的实例对象吗？
        //System.copyxxx ？TODO：
    }
}
