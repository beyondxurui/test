package com.jlxu.demo.jvm.initializeAndInstantiation.demo4;

/**
 * 功能：发生在实例变量i初始化之前和构造函数调用之前
 * 创建时间：2020年04月05日
 * 文件名称：InstanceInitializer2
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 12:09
 *
 * @auther jlxu
 */
public class InstanceInitializer2 {
    private int j = getIn();
    private int i = 1;

    public InstanceInitializer2() {
        i = 2;
    }

    private int getIn() {
        return i;
    }

    public static void main(String[] args) {
        InstanceInitializer2 in = new InstanceInitializer2();
        System.out.println(in.j);
        //0  这一动作发生在实例变量i初始化之前和构造函数调用之前
    }
}
