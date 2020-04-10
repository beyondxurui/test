package com.jlxu.demo.designmode.templateMethod.hook;

/**
 * 功能：含钩子方法的模板方法模式
 * 创建时间：2020年04月10日
 * 文件名称：HookTemplateMethod
 * 版本：1.0.0
 * 最后修改时间：2020/4/10 21:51
 *
 * @auther jlxu
 */
public class HookTemplateMethod {
    public static void main(String[] args) {
        HookAbstractClass hka = new HookConcreteClass();
        hka.templdateMethod();
        //抽象方法1的实现被调用...
        //钩子方法1被重写...
        //抽象方法2的实现被调用...
    }
}

//行钩子方法的抽象类
abstract class HookAbstractClass {
    public void templdateMethod() {//模板方法
        abstractMethod1();
        hookMethod1();
        if (hookMethod2()) {
            specificMethod();
        }
        abstractMethod2();
    }

    public void hookMethod1() {//钩子方法2

    }

    public boolean hookMethod2() {//钩子方法2
        return true;
    }


    protected void specificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    protected abstract void abstractMethod1();

    protected abstract void abstractMethod2();

}

//含钩子方法的具体子类
class HookConcreteClass extends HookAbstractClass {

    @Override
    protected void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    protected void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }

    public void hookMethod1() {
        System.out.println("钩子方法1被重写...");
    }

    public boolean hookMethod2() {
        return false;
    }
}
