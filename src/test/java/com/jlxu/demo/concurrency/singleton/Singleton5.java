package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能: 单例模式 与 ThreadLocal
 * 创建时间：2020年03月14日
 * 文件名称：Singleton
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:17
 *
 * @auther jlxu
 */
@Slf4j
public class Singleton5 {

    private static final ThreadLocal<Singleton5> local = new ThreadLocal();

    // 私有的构造方法
    private Singleton5() {
    }

    //指向自己实例的私有静态引用，主动创建
    private static volatile Singleton5 singleton;

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton5 getSingleton() {
        //TODO:少加一层检查不行,不可以，hashCode值不一样
        if (local.get() == null) {
            synchronized (Singleton5.class) {//TODO:类锁该测试线程都可以进去的的因为调用的式类的方法
                if (singleton == null) {
                    singleton = new Singleton5();
                }
            }
            local.set(singleton);
        }
        return local.get();
    }
}
