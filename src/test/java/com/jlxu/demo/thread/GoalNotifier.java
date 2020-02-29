package com.jlxu.demo.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能:进球通知线程
 * 创建时间：2020年02月29日
 * 文件名称：GoalNotifier
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 14:35
 *
 * @auther jlxu
 */
@Slf4j
@Data
public class GoalNotifier implements Runnable {
//    public boolean isFlag() {
//        return flag;
//    }
//
//    public void setFlag(boolean flag) {
//        this.flag = flag;
//    }

    //    public boolean flag = false;
    public volatile boolean flag = false;

    @Override
    public void run() {
        while (true) {// 线程中用while(true)的原因 https://blog.csdn.net/qq_32451373/article/details/78041661
            if (flag) {
                log.info("Goal !!!");
                //tell the referee the ball is in
                //...
                setFlag(false);
            }
        }

    }
    //jvm内存模型
    //https://blog.csdn.net/justloveyou_/article/details/71189093
}
