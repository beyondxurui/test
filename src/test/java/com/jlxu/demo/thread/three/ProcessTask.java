package com.jlxu.demo.thread.three;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能:计数线程
 * 创建时间：2020年02月29日
 * 文件名称：ProcessTask
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 18:15
 *
 * @auther jlxu
 * ps：线程处理
 */
@Slf4j
@Data
public class ProcessTask implements Runnable {

    private long loopTime;

    private CountingProcessor countingProcessor;

    public ProcessTask(long loopTime, CountingProcessor countingProcessor) {//构造函数的用处(通常是初始化)
        this.loopTime = loopTime;
        this.countingProcessor = countingProcessor;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < loopTime) {
            countingProcessor.process();/*计数*/
//            log.info("i ===> {}", i);
            i++;
        }
        log.info("Finally, the count is {}", countingProcessor.getCount());
    }
}
