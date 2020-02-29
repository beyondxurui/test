package com.jlxu.demo.thread.three;

/**
 * 功能：计数处理器
 * 创建时间：2020年02月29日
 * 文件名称：CountingProcessor
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 18:18
 *
 * @auther jlxu
 * ps:面向对象的设计，业务接口
 */
public interface CountingProcessor {
    /**
     * 计数
     */
    void process();

    /**
     * 获取计数值
     *
     * @return 计数值
     */
    long getCount();
}
