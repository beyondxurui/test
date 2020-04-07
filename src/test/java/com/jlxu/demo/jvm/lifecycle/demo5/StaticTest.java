package com.jlxu.demo.jvm.lifecycle.demo5;

/**
 * 功能：初始化典型典型案例一
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
        //4
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
    //TODO:在问题理解问题的基础上，不要忽略类加载阶段，这里尤其是准备阶段
}
