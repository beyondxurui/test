package com.jlxu.demo.jvm.lifecycle.demo6;

/**
 * 功能：初始化典型典型案例二  注：注意初始化典型典型案例一的区别
 * 创建时间：2020年04月05日
 * 文件名称：StaticTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 0:22
 *
 * @auther jlxu
 */
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
        //2
        //3
        //a=110 b=0
        //1
        //2
        //3
        //a=110 b=112
        //4
        //TODO：在同一个类加载器下，一个类型只会被初始化一次。所以，一旦开始初始化一个类型，无论是否完成，后续都不会再重新触发该类型的初始化阶段了(只考虑在同一个类加载器下的情形)。
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + " b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;
    static StaticTest st2 = new StaticTest();
}
