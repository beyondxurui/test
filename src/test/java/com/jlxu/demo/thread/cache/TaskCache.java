package com.jlxu.demo.thread.cache;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * 功能
 * 创建时间：2020年03月01日
 * 文件名称：TaskCache
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 7:11
 *
 * @auther jlxu
 */
@Data
@Slf4j
public class TaskCache implements Runnable {

    private UserCostStatComputeVersionOne userCostStatComputeVersionOne;
    private String userId;

    public TaskCache(UserCostStatComputeVersionOne userCostStatComputeVersionOne, String userId) {
        this.userCostStatComputeVersionOne = userCostStatComputeVersionOne;
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            BigInteger compute = userCostStatComputeVersionOne.compute(userId);
            log.info("cache ===> {}", compute);
        } catch (InterruptedException e) {
            log.error("线程任务执行失败 ===> {}", e);
            e.printStackTrace();
        }
    }
}
