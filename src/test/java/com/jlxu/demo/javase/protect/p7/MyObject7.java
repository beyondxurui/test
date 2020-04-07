package com.jlxu.demo.javase.protect.p7;

import com.jlxu.demo.javase.protect.p33.Test3;

/**
 * 功能
 * 创建时间：2020年04月07日
 * 文件名称：MyObject7
 * 版本：1.0.0
 * 最后修改时间：2020/4/7 10:21
 *
 * @auther jlxu
 */
public class MyObject7 extends Test3 {
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        //test7.clone();  // Compile Error

        //和下面案例部分分析有点像
        /**
         * @see com.jlxu.demo.javase.protect.p1.Test1
         */
    }
}

class Test7 {

}
