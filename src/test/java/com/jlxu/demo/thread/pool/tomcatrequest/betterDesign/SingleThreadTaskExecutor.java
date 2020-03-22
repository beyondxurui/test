package com.jlxu.demo.thread.pool.tomcatrequest.betterDesign;

import java.util.concurrent.Executor;

/**
 * 功能 : 单线程服务器任务
 * 创建时间：2020年03月22日
 * 文件名称：Executor
 * 版本：1.0.0
 * 最后修改时间：2020/3/22 11:18
 *
 * @auther jlxu
 */
public class SingleThreadTaskExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}
