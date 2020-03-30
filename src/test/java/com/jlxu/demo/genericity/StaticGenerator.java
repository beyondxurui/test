package com.jlxu.demo.genericity;

/**
 * 功能：静态方法与泛型
 * 创建时间：2020年03月30日
 * 文件名称：StaticGenerator
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 22:11
 *
 * @auther jlxu
 */
public class StaticGenerator<T> {

    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（TODO:将这个方法定义成泛型方法）
     * * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     *
     * @param t
     * @param <T>
     */
    public static <T> void show(T t) {
        System.out.println(t.toString());
    }

//    public static void show(T t){//StaticGenerator cannot be refrenced from static context"
//        System.out.println(t.toString());
//    }
}
