package com.jlxu.demo.thread.three;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能
 * 创建时间：2020年02月29日
 * 文件名称：ThreadCountingProcessor
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 18:21
 *
 * @auther jlxu
 * ps:业务处理
 */
@Slf4j
public class ThreadCountingProcessor implements CountingProcessor {

    private long count = 0;

    //    @Override
//    public void process() {
//        doProcess();/*业务逻辑*/
//        count++;/*版本一：线程不安全的*/
//    }
//    @Override
//    public synchronized void process() {/*版本二：线程安全的，整个方法*/
//        doProcess();/*业务逻辑*/
//        count++;/*版本一：线程不安全的*/
//    }
    public void process() {
        doProcess();/*业务逻辑*/
        synchronized (this) {/*版本三：线程安全的 ++操作*/   //ctrl+alt+t
            count++;
//            log.info("count ===> {}", count);
        }
    }

    private void doProcess() {
    }

    public long getCount() {
        return count;
    }
}
