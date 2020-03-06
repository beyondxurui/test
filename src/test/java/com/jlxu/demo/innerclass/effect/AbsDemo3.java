package com.jlxu.demo.innerclass.effect;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年03月06日
 * 文件名称：AbsDemo
 * 版本：1.0.0
 * 最后修改时间：2020/3/6 12:31
 *
 * @auther jlxu
 */
@Slf4j
public abstract class AbsDemo3 {

    public AbsDemo3(String name, String city) {
        log.info(city);
    }

    abstract String getName();
}
