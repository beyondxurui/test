package com.jlxu.demo.jvm.initializeAndInstantiation;

import com.jlxu.demo.jvm.initializeAndInstantiation.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：深入理解Java对象的创建过程：类的初始化与实例化
 * 创建时间：2020年04月05日
 * 文件名称：InitializeAndInstantiation
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 9:41
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class InitializeAndInstantiation {
    @Test
    public void initializeAndInstantiationTest() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //摘要：
        //一个对象在可以被使用之前必须要被正确地初始化    回顾类加载图即可
        //在实例化一个对象时，谁？首先会检查相关类型是否已经加载并初始化  如果没有，谁？立即进行“加载”并调用“类构造器”完成类的初始化 TODO:构造器和构造函数区别?  JVM操作构造器，构造函数是用来实例化对象的
        //类初始化过程"中"或完成后，一定去进行类的实例化吗？ 不会？那什么情况下会进行类哦实例化呢？
        //本文试图对JVM执行类初始化和实例化的过程做一个详细深入地介绍，以便从Java虚拟机的角度清晰解剖一个Java对象的创建过程。
        //那么 类的初始化时机呢？  TODO：什么情况下虚拟机需要开始加载一个类呢？虚拟机规范中并没有对此进行强制约束，这点可以交给虚拟机的具体实现来自由把握。

        //友情提示
        //一个Java对象的创建过程往往包括几个阶段？
        //该博文在类的初始化时机和初始化过程的础上，进一步阐述了一个Java对象创建的真实过程  TODO

        //一、Java对象创建时机
        //有哪些行为引起对象的创建呢？
        //1）使用new关键字创建对象  （无参和有参）
        Student student = new Student();
        //2）使用Class类的newInstance方法(反射机制)  这个newInstance方法调用无参的构造器创建对象
        Student student2 = (Student) Class.forName("com.jlxu.demo.jvm.initializeAndInstantiation.entity.Student").newInstance();
        //或
        Student student1 = Student.class.newInstance();  //TODO：这也是噢  newInstance
        //3）使用Constructor类的newInstance方法(反射机制)      该方法和Class类中的newInstance方法很像，但是更强大
        //TODO:通过这个newInstance方法调用有参数的和私有的构造函数
        /**
         * {@link com.jlxu.demo.jvm.initializeAndInstantiation.demo.Student }
         */
        //2,3使用的方式使用的是java的反射机制，事实上Class的newInstance方法内部调用的也是Constructor的newInstance方法
        //4). 使用Clone方法创建对象
        //clone回顾 ：为什么要使用克隆，浅克隆和深克隆的区别？ String在克隆中的特殊性？因为不可变对象 protected关键字：TODO:接下来处理
        /**
         * {@link com.jlxu.demo.jvm.initializeAndInstantiation.demo2.Student }
         */
        //5). 使用(反)序列化机制创建对象
        /**
         * {@link com.jlxu.demo.jvm.initializeAndInstantiation.demo3.Student }
         */
        //　从Java虚拟机层面看，除了使用new关键字创建对象的方式外，其他方式全部都是通过转变为invokevirtual指令直接创建对象的 TODO:不懂

        //二. Java 对象的创建过程
        //回顾初始化的时机？TODO；**
        //　当一个对象被创建“时”，谁干什么？虚拟机分配内存 分配内存干什么？如果有父类会干什么？分配内存的同时干什么？
        // 分后干什么？按照什么顺序？ TODO: Java虚拟机就会开始对新创建的对象按照程序猿的意志进行初始化
        //在Java对象初始化过程中，主要涉及三种执行对象初始化的结构
        //1、实例变量初始化与实例代码块初始化
        //声明实例变量的同时还可以干什么？
        // 实际上：TODO:实际上，如果我们对实例变量直接赋值或者使用实例代码块赋值，那么编译器会将其中的代码放到类的构造函数中去，并且这些代码会被放在对超类构造函数的调用语句之后(还记得吗？Java要求构造函数的第一条语句必须是超类构造函数的调用语句)，构造函数本身的代码之前
        /**
         * @see  com.jlxu.demo.jvm.initializeAndInstantiation.demo4.InstanceVariableInitializer
         * @see  com.jlxu.demo.jvm.initializeAndInstantiation.demo4.InstanceInitializer
         * @see  com.jlxu.demo.jvm.initializeAndInstantiation.demo4.InstanceInitializer2
         */
        //2、构造函数初始化
        //实例变量初始化与实例代码块初始化总是发生在构造函数初始化之前，那么我们下面着重看看构造函数初始化过程。 TODO:记住
        //为什么Java要求在实例化类之前，必须先实例化其超类? 以保证所创建实例的完整性 TODO:具体原因就是构造方式实现的
        //2-1、也没有显式调用超类的构造函数，那么编译器会为我们自动生成一个对超类构造函数的调用，比如：
        //public class ConstructorExample {}
        //对于上面代码中定义的类，我们观察编译之后的字节码，我们会发现编译器为我们生成一个构造函数，如下，
        //aload_0 TODO:
        //invokespecial   #8; //Method java/lang/Object."<init>":()V
        //return
        //　特别地，如果我们在一个构造函数中调用另外一个构造函数
        /**
         * @see com.jlxu.demo.jvm.initializeAndInstantiation.demo5.ConstructorExample
         */
        //3、 小结
        //都遵循如下顺序：先依次执行实例变量初始化和实例代码块初始化，再执行构造函数初始化。也就是说，编译器会将实例变量初始化和实例代码块初始化相关代码放到类的构造函数中去，
        // 并且这些代码会被放在对超类构造函数的调用语句之后，构造函数本身的代码之前。  TODO:
        //4、实例变量初始化、实例代码块初始化以及构造函数初始化综合实例
        /**
         * @see com.jlxu.demo.jvm.lifecycle.demo7.ConstructorExample
         */
        //TODO：后续回顾多态

        //三. 类的初始化时机与过程
        //类初始胡时机回顾   　类构造器<clinit>()与实例构造器<init>()的区别？ 作用的不同，一个时类信息，一个实例信息，
        //初始化和实例化顺序：源文件中出现的顺序决定的
        //在一个类的生命周期中，类构造器<clinit>()最多会被虚拟机调用一次，而实例构造器<init>()则会被虚拟机调用多次，只要程序员还在创建对象。

        //四. 总结
        //1、一个实例变量在对象初始化的过程中会被赋值几次？   TODO:博主的分析 ****
        //2、类的初始化过程与类的实例化过程的异同？   TODO:**
        //　类的初始化是指类"加载过程中"的"初始化阶段""对类变量按照程序猿的意图进行赋值"的"过程"；
        // 而类的实例化是指在"类完全加载到内存中后""创建对象"的"过程"。
        //3、假如一个类还未加载到内存中，那么在创建一个该类的实例时，具体过程是怎样的？  TODO:很经典
        // TODO: 我们知道，要想创建一个类的实例，必须先将该类加载到内存并进行初始化，也就是说，类初始化操作是在类实例化操作之前进行的，
        //  但并不意味着：只有类初始化操作结束后才能进行类实例化操作
        //总的来说，类实例化的一般过程是：父类的类构造器<clinit>() -> 子类的类构造器<clinit>() -> 父类的成员变量和实例代码块 -> 父类的构造函数 -> 子类的成员变量和实例代码块 -> 子类的构造函数。
        //ps:类初始化操作结束后才能进行类实例化操作对吗？ 不一定

        //ps
        //TODO：类的初始化时，类变量的初始值和实例化化对象时，实力变初始值的区别？  一个在准备阶段，一个见上面的Java 对象的创建过程的第一段总结
        //多态？
        //递归
        //protected
    }
}
