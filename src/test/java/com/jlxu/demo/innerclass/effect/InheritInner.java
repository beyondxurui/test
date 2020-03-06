package com.jlxu.demo.innerclass.effect;

/**
 * 功能 内部类的继承
 * 创建时间：2020年03月06日
 * 文件名称：InheritInner
 * 版本：1.0.0
 * 最后修改时间：2020/3/6 12:56
 *
 * @auther jlxu
 */
public class InheritInner extends WithInner.Inner {
    //继承内部了是报错 No enclosing instance of type 'com.jlxu.demo.innerclass.effect.WithInner' is in scope
    //处理方式：把子类的无参构造注释就可以了
//    public InheritInner() {
//    }

    public InheritInner(WithInner withInner) {
        withInner.super();//TODO:无参构造？
    }
}
