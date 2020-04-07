package com.jlxu.demo.jvm.initializeAndInstantiation.demo5;

/**
 * 功能：如果我们显式调用超类的构造函数，那么该调用必须放在构造函数所有代码的最前面，也就是必须是构造函数的第一条指令
 * 也没有显式调用超类的构造函数，那么编译器会为我们自动生成一个对超类构造函数的调用
 * 如果我们在一个构造函数中调用另外一个构造函数
 * 创建时间：2020年04月05日
 * 文件名称：ConstructorExample
 * 版本：1.0.0
 * 最后修改时间：2020/4/5 12:37
 *
 * @auther jlxu
 */
public class ConstructorExample {
    private int i;

    //正确的，编辑会在ConstructorExample(int i) 第一行加上  Java只允许在ConstructorExample(int i)内调用超类的构造函数
    public ConstructorExample() {
        // this(2);  //Call to 'this()' must be first statement in constructor body
        this(2);
        // this(2);  //Call to 'this()' must be first statement in constructor body
        //...
    }

    public ConstructorExample(int j) {
        //...
        this.i = j;
        //...

    }
}
