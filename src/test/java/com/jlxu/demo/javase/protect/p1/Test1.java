package com.jlxu.demo.javase.protect.p1;

/**
 * 功能
 * 创建时间：2020年04月07日
 * 文件名称：Test1
 * 版本：1.0.0
 * 最后修改时间：2020/4/7 10:00
 *
 * @auther jlxu
 */
public class Test1 {
    public static void main(String[] args) {
        Father son1 = new Son1();
        son1.f();  // Complie OK
        //son1.clone(); //Compile OK   clone()' has protected access in 'java.lang.Object
        Father son11 = new Son11();
        son11.f();  // Complie OK
        //son11.clone(); //Compile OK   clone()' has protected access in 'java.lang.Object


        //protected的成员来自那里？
        // f() 来自父类  com.jlxu.demo.javase.protect.p1   clone()来自Object  java.lang
        //TODO：根据可见性：在同包和可见性可知


    }
}

class Father {
    protected void f() {   // 父类Father1中的protected方法
    }
}

class Son1 extends Father {

}

class Son11 extends Father {

}
