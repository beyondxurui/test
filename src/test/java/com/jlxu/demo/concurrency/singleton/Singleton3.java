package com.jlxu.demo.concurrency.singleton;

/**
 * 功能: 懒汉式单例  内部类实现延迟加载
 * 创建时间：2020年03月14日
 * 文件名称：Singleton
 * 版本：1.0.0
 * 最后修改时间：2020/3/14 21:17
 *
 * @auther jlxu
 */
public class Singleton3 {
    // 私有的构造方法
    private Singleton3() {
    }

    //指向自己实例的私有静态引用，主动创建
    private static Singleton3 singleton;

    // 以自己实例为返回值的静态的公有方法，静态工厂方法
    public static Singleton3 getSingleton() {
        return Holder.singleton;
    }

    private static class Holder {
        private static Singleton3 singleton = new Singleton3();
        //调用getSingleton5()方法时，会触发Holder类的初始化。
        // 由于singleton5是Hold的类成员变量，
        // 因此在JVM调用Holder类的类构造器对其进行初始化时，
        // 虚拟机会保证一个类的类构造器在多线程环境中被正确的加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的类构造器，其他线程都需要阻塞等待，直到活动线程执行方法完毕。在这种情形下，其他线程虽然会被阻塞，但如果执行类构造器方法的那条线程退出后，其他线程在唤醒之后不会再次进入/执行类构造器，因为 在同一个类加载器下，一个类型只会被初始化一次，因此就保证了单例。
    }
}
