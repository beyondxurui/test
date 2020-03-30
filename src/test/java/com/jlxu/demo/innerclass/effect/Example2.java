package com.jlxu.demo.innerclass.effect;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能 ：内部类拥有外围类的所有元素的访问权限
 * 创建时间：2020年03月05日
 * 文件名称：Example
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 23:26
 *
 * @auther jlxu
 */
@Slf4j
public class Example2 {
    private String name = "hello";
    private Integer i = 1;//默认是final的

    private class Inner {
        public Inner() {// //TODO:构造获取  如果是自己可能会写方法
            log.info(name);
//            log.info(i+1); //Error ：Cannot resolve method 'info(int)'
        }
    }

    public Inner getInnerInstance() {
        return new Inner();//推荐使用外部类getxxx()获取成员内部类对象
    }
}

