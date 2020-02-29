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
    }
}
