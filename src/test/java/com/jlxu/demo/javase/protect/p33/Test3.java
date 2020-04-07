package com.jlxu.demo.javase.protect.p33;

import com.jlxu.demo.javase.protect.p3.MyObject3;

/**
 * 功能
 * 创建时间：2020年04月07日
 * 文件名称：Test3
 * 版本：1.0.0
 * 最后修改时间：2020/4/7 10:17
 *
 * @auther jlxu
 */
public class Test3 {
    public static void main(String[] args) throws CloneNotSupportedException {
        MyObject3 my = new MyObject3();
        my.clone();
        //和下面的案例优点像
        /**
         * @see com.jlxu.demo.javase.protect.p22.Test2
         */
    }
}
