package com.jlxu.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能: 进球通知裁判测试类(主线程触发进球通知裁判线程)
 * 创建时间：2020年02月29日
 * 文件名称：Game
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 14:45
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class Game {

    @Test
    public void gaolTest() throws InterruptedException {
        // Game begun! Init goalNotifier thread
        GoalNotifier goalNotifier = new GoalNotifier();
        Thread goalNotifierThread = new Thread(goalNotifier);
        goalNotifierThread.start();
        Thread.sleep(3000);
        goalNotifier.setFlag(true);
        //补充：
        // 首先要解决的问题是，不加volatile之前，main函数明明调用了setGoal()方法，把goal改成了true，可为什么GoalNotifier线程里的goal还是false？
        //答案是，“主线程”里调用setGoal()方法修改的goal，和GoalNotifier线程里的goal，是两个副本。
        //What??? 变量还有副本？

    }
}
