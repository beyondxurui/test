package com.jlxu.demo.concurrency.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能 synchronized方法
 * 创建时间：2020年03月10日
 * 文件名称：Test
 * 版本：1.0.0
 * 最后修改时间：2020/3/10 21:04
 *
 * @auther jlxu
 */
@Slf4j
public class Test2 {
    public static void main(String[] args) {
        final InsertData insertData = new InsertData();//共享资源访问地中写法（第一种参见com.jlxu.demo.concurrency.synchronize.Test中的）
        //TODO:final的原因：只能InsertData改变共享资源

        // 启动线程 1
        new Thread() {
            @Override
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();
        // 启动线程 1
        new Thread() {
            @Override
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();
        //(1)操作共享资源的类没加synchronized
        //21:51:37.406 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据0
        //21:51:37.406 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据0
        //21:51:37.413 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据1
        //21:51:37.413 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据1
        //21:51:37.414 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据2
        //21:51:37.414 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据2
        //21:51:37.414 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据3
        //21:51:37.414 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据3
        //21:51:37.414 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据4
        //21:51:37.414 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据4
        //（2）加了synchronized
        //21:53:43.306 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据0
        //21:53:43.314 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据1
        //21:53:43.314 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据2
        //21:53:43.314 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据3
        //21:53:43.314 [Thread-0] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-0插入数据4
        //21:53:43.321 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据0
        //21:53:43.322 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据1
        //21:53:43.322 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据2
        //21:53:43.324 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据3
        //21:53:43.324 [Thread-1] INFO com.jlxu.demo.concurrency.synchronize.InsertData - Thread-1插入数据4
    }
}

@Slf4j
class InsertData {
    //共享，可变资源
    private List<Integer> arrayList = new ArrayList<>();

    //对共享可变资源的访问
//    public void insert(Thread thread) {
    public synchronized void insert(Thread thread) {
        for (int i = 0; i < 5; i++) {
            log.info(thread.getName() + "插入数据" + i);
            arrayList.add(i);
        }
    }

}