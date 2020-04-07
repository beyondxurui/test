package com.jlxu.demo.jvm.initializeAndInstantiation.demo4;

/**
 * 功能：实例变量初始化与实例代码块初始化
 * 创建时间：2020年04月05日
 * 文件名称：InstanceVariableInitializer
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 11:57
 *
 * @auther jlxu
 */
public class InstanceVariableInitializer {
    private int i = 1;
    private int j = i + 1;

    public InstanceVariableInitializer(int var) {
        System.out.println(i);
        System.out.println(j);
        this.i = var;
        System.out.println(i);
        System.out.println(j);
    }

    {
        j += 3;
    }

    public static void main(String[] args) {
        InstanceVariableInitializer ins = new InstanceVariableInitializer(8);
    }
}
