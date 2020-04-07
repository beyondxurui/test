package com.jlxu.demo.javase.protect.p2;

/**
 * 功能
 * 创建时间：2020年04月07日
 * 文件名称：MyObject2
 * 版本：1.0.0
 * 最后修改时间：2020/4/7 10:09
 *
 * @auther jlxu
 */
public class MyObject2 {
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
