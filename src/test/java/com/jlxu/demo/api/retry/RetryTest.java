package com.jlxu.demo.api.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能
 * 创建时间：2020年03月25日
 * 文件名称：RetryTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/25 11:58
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class RetryTest {
    @Test
    public void retryTest() {
        int count = 0;
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (j == 3) {
//                    break retry;
                    continue retry;
                }
                count++;
                log.info("count ===> {}", count);
            }
        }

        System.out.println("end");
    }
}
