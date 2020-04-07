package com.jlxu.demo.javase.protect.p22;

import com.jlxu.demo.javase.protect.p2.MyObject2;

/**
 * 功能：
 * 创建时间：2020年04月07日
 * 文件名称：Test2
 * 版本：1.0.0
 * 最后修改时间：2020/4/7 10:10
 *
 * @auther jlxu
 */
public class Test2 extends MyObject2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        MyObject2 my = new MyObject2();
        //my.clone();   // Compile Error
        Test2 test2 = new Test2();
        test2.clone(); // Complie OK

        //Test2中不能访问基类MyObject2的protected方法clone()，继承来的可以
    }
}
