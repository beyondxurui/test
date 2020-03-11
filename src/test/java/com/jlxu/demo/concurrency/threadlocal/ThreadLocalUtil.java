package com.jlxu.demo.concurrency.threadlocal;

/**
 * 功能
 * 创建时间：2020年03月11日
 * 文件名称：ThreadLocalUtil
 * 版本：1.0.0
 * 最后修改时间：2020/3/11 0:24
 *
 * @auther jlxu
 */
public class ThreadLocalUtil {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
