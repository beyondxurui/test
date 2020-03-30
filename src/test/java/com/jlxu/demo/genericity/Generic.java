package com.jlxu.demo.genericity;

/**
 * 功能：最简单的泛型类
 * 创建时间：2020年03月30日
 * 文件名称：Generic
 * 版本：1.0.0
 * 最后修改时间：2020/3/30 20:12
 *
 * @auther jlxu
 */
//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class Generic<E> {
    public E key;//key这个成员变量的类型为T,T的类型由外部指定

    public Generic(E key) {  //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public E getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}
