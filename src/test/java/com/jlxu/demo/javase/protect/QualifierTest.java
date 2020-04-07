package com.jlxu.demo.javase.protect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能：Java 访问权限控制：你真的了解 protected 关键字吗？
 * 创建时间：2020年04月07日
 * 文件名称：QualifierTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/7 9:41
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class QualifierTest {
    @Test
    public void qualifierTest() {
        //摘要：
        //类成员？  成员方法和成员变量 TODO:
        //对于类成员和类，能否被访问取决于什么？  TODO: private，无（包访问权限 ***），protected 和 public
        //哪些权限修饰符可以访问类（除内部类）？哪些权限修饰符可以修饰类成员访
        //本文重点说明了protected关键字的可见性内涵，并介绍了一些其他的修饰符。

        //一. Package
        //包名不重?  在一个项目中，不可以有相同的两个包名，也就是说，包名不能和项目中其他的包名重复，
        // 这里不但包括"自定义包名"也包括"项目所引用的类库的包名"
        //如自定义一个java.lang运行后会报错的： “java.lang.SecurityException: Prohibited package name: java.lang”
        //文件中包的位子？  package语句必须是文件中除注释外第一句

        //二. Java访问权限概述
        //需要注意的是  可以修饰类和内部类的权限修饰符的区别
        //public ：被public修饰的类成员能被所有的类直接访问；
        //private：被public修饰的类成员只能在定义它的类中被访问，其他类都访问不到。
        // TODO:特别地，我们一般建议将成员变量设为private的，并为外界提供 getter/setter 去对成员变量进行访问，这种做法充分体现了Java的封装思想；
        //包访问权限：包访问权限就是Java中的默认的权限，具有包访问权限的类成员只能被同一包中的类访问。

        //protected 关键字的真正内涵
        //在编程中，碰到涉及protected的调用时，首先要确定出该protected成员来自何方，其可见性范围是什么?
        //基类的protected成员是包内可见的，并且对子类可见；
        //若子类与基类不在同一包中，那么在子类中，子类实例可以访问其从基类继承而来的protected方法，
        // 而不能访问基类实例的protected方法
        // 栗子
        /**
         * @see com.jlxu.demo.javase.protect.p1.Test1
         * @see com.jlxu.demo.javase.protect.p22.Test2
         * @see com.jlxu.demo.javase.protect.p33.Test3
         * @see com.jlxu.demo.javase.protect.p7.MyObject7
         *
         */

        //四. 其他的修饰符
        //　　static：修饰变量和内部类("不能修饰常规类")，其中所修饰变量称为类变量或静态变量。
        //    静态变量是和类层次的变量,每个实例"共享这个静态变量"，在类加载时初始化。  TODO:JVM分析的适合以已经说了
        //　　final：被声明为final的变量必须在声明时给定初值（当然，空白final可以延迟到构造器中赋值），  TODO:遇到过好多次了
        //   而且被修饰的变量不能修改值。当修饰类时，该类不能派生出子类；
        //   修饰方法时，该方法不能被子类覆盖。
        //   若读者想对final关键字有一个更深刻的了解，请移步我的博文 《Java 继承、多态与类的复用》。 TODO:后续处理
        //　　abstract：修饰类和方法。当修饰类时，该类不能创建对象；修饰方法时，为抽象方法。类只要有一个abstract方法，类就必须定义为abstract，但abstract类不一定非要有abstract方法不可。

        //五. 总结  上面已经提到

    }
}
