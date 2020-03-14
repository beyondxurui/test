package com.jlxu.demo.concurrency.singleton;

/**
 * 功能:饿汉式单例
 * 创建时间：2020年03月14日
 * 文件名称：Singleton
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:17
 *
 * @auther jlxu
 */
public class Singleton {
    // 私有的构造方法
    private Singleton() {
    }

    //指向自己实例的私有静态引用，主动创建
    private static final Singleton singleton = new Singleton();

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton getSingleton() {
        return singleton;
    }
    //TODO:类加载的方式是按需加载，且加载一次
}
