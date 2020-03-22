package com.jlxu.demo.thread.tomcatrequest.betterDesign;

import java.util.concurrent.Executor;

/**
 * 功能 : 多线程服务器任务（每个请求一个线程）
 * 创建时间：2020年03月22日
 * 文件名称：Executor
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 11:18
 *
 * @auther jlxu
 */
public class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();  //注意下 用的是start
    }
}
