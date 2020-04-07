package com.jlxu.demo.jvm.lifecycle.demo3;

/**
 * 功能：常量在编译阶段会存入"调用类"的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 * 创建时间：2020年04月04日
 * 文件名称：NotInitialization2
 * 版本：1.0.0
 * 最后修改时间：2020/4/4 20:39
 *
 * @auther jlxu
 */
public class NotInitialization3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.CONSTANT);
        //
        //分析：这是因为虽然在Java源码中引用了ConstClass类中的常量CONSTANT，但是编译阶段将此常量的值“hello world”存储到了NotInitialization常量池中，
        //     对常量ConstClass.CONSTANT的引用实际都被转化为NotInitialization类对自身常量池的引用了。
        //     也就是说，实际上NotInitialization的Class文件之中并没有ConstClass类的符号引用入口，这两个类在编译为Class文件之后就不存在关系了。
        //小技巧 嘿嘿  .TODO

    }
}

class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String CONSTANT = "hello world";
}
