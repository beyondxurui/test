package com.jlxu.demo.designmode.templateMethod.hook;

/**
 * 功能：抽象类
 * 创建时间：2020年04月10日
 * 文件名称：TemplateMethodPattern
 * 版本：1.0.0
 * 最后修改时间：2020/4/10 20:46
 *
 * @auther jlxu
 */
//抽象类
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass abs = new ConcreteClass();
        abs.templateMethod();
    }
}

//抽象类
abstract class AbstractClass {  //TODO：抽象类怎么定义？ 提示：需要加abstract 后面加上class

    //模板方法
    public void templateMethod() {
        specificMethod();
        abstractMethod1();
        abstractMethod2();
        //抽象方法中具体方法被调用
        //抽象方法1的实现被调用...
        //抽象方法2的实现被调用...
    }

    public void specificMethod() {
        System.out.println("抽象方法中具体方法被调用");
    }

    public abstract void abstractMethod1();

    public abstract void abstractMethod2();
}

class ConcreteClass extends AbstractClass {   //TODO:类继承抽象类要实现其中所有的抽象方法

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}
