package com.jlxu.demo.jvm.lifecycle.demo;

/**
 * 功能：通过子类引用父类的静态字段，不会导致子类初始化
 * 创建时间：2020年04月04日
 * 文件名称：NotInitialization
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 20:32
 *
 * @auther jlxu
 */
public class NotInitialization {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
        //SSClass
        //SClass init!
        //12
    }
}

class SSClass {
    static {
        System.out.println("SSClass");
    }
}

class SClass extends SSClass {
    static {
        System.out.println("SClass init!");
    }

    public static int value = 12;

    public SClass() {
        System.out.println("int SClass");
    }
}

class SubClass extends SClass {
    static {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass() {
        System.out.println("int SubClass");
    }
}
