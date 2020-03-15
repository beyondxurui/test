package com.jlxu.demo.thread.cache;

/**
 * 功能:每个要使用缓存功能的计算器，都要去实现这个接口
 * 创建时间：2020年03月01日
 * 文件名称：Computable
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 7:42
 *
 * @auther jlxu
 */
public interface Computable<A, V> {
    V compute(A arg) throws Exception;
}
