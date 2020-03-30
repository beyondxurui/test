package com.jlxu.demo.genericity;

/**
 * 功能：泛型上下边界
 * 创建时间：2020年03月30日
 * 文件名称：Generic
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 20:12
 *
 * @auther jlxu
 */

public class Generic2<E extends Number> {
    public E key;//key这个成员变量的类型为T,T的类型由外部指定

    public Generic2(E key) {  //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public E getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}
