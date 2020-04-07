package com.jlxu.demo.jvm.initializeAndInstantiation.demo4;

/**
 * 功能：特别需要注意的是，Java是按照编程顺序来执行实例变量初始化器和实例初始化器中的代码的，
 * 并且不允许顺序靠前的实例代码块初始化在其后面定义的实例变量
 * 创建时间：2020年04月05日
 * 文件名称：InstanceInitializer
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 12:00
 *
 * @auther jlxu
 */
public class InstanceInitializer {
    {
        j = 1;//TODO:难道jdk1.8做了优化
    } //

    private int i = 2;
    private int j;

    public InstanceInitializer() {
        System.out.println(i);
        System.out.println(j);
        int j = i;
        int i = 4;
        System.out.println(i);
        System.out.println(j);
        //2
        //1
        //4
        //2
    }

    public static void main(String[] args) {
        InstanceInitializer ins = new InstanceInitializer();
    }
}
