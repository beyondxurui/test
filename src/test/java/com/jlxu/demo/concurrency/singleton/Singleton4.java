package com.jlxu.demo.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能: 懒汉式单例  内部类实现延迟加载
 * 创建时间：2020年03月14日
 * 文件名称：Singleton
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:17
 *
 * @auther jlxu
 */
@Slf4j
public class Singleton4 {
    // 私有的构造方法
    private Singleton4() {
    }

    //指向自己实例的私有静态引用，主动创建
    private static volatile Singleton4 singleton;

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton4 getSingleton() {
        //TODO:少加一层检查不行,不可以，hashCode值不一样

        if (singleton == null) {
            synchronized (Singleton4.class) {
                // 只需在第一次创建实例时才同步
                //TODO:??
                //为了在保证单例的前提下提高运行效率，我们需要对 singleton3 进行第二次检查，目的是避开过多的同步（因为这里的同步只需在第一次创建实例时才同步，一旦创建成功，以后获取实例时就不需要同步获取锁了）
                log.info("ss1");
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
