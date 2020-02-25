package com.jlxu.demo.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.jlxu.demo.response.Result;
import com.jlxu.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 功能 :api接口并发控制
 * 创建时间：2020年02月25日
 * 文件名称：ConcurrencyController
 * 版本：1.0.0
 * 最后修改时间：2020/2/25 11:20
 *
 * @auther jlxu
 */
@RestController
@RequestMapping("/concurrency")
@Slf4j
public class ConcurrencyController {

    private final Semaphore permit = new Semaphore(10, true);
    private final RateLimiter limiter = RateLimiter.create(5.0);

    @GetMapping("/semaphore")   //获取当前接口支持的并发量，设置参数，请求进来没处理的会等待前面的请求处理完
    public Result testSemaphore() {
        try {
            permit.acquire();
            log.info("处理请求，，，，，，，，，，");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("获取许可失败 ====> {}", e);
            e.printStackTrace();
            return new Result(HttpStatus.INTERNAL_SERVER_ERROR, "请求失败");
        } finally {/*释放资源*/
            permit.release();
        }
        return new Result(HttpStatus.OK);
    }


    @GetMapping("/rateLimiter1")//测试用的
    public Result testRateLimiter() {
        String start = DateUtils.getCurrentDateTime();
        RateLimiter rateLimiter = RateLimiter.create(1.0);/*这里的1表示每秒允许处理的量为1个*/
        for (int i = 0; i < 10; i++) {
            double waitTime = rateLimiter.acquire(i + 1);/*请求RateLimiter, 超过permits会被阻塞*/
            //以阻塞的方式获取令牌，随着i（相当于每秒的并发数）的增加，需要的令牌数增多，则需要等待的时间也增加。 参数要大于零
            log.info("cutTime=" + System.currentTimeMillis() + " call execute:" + i + " waitTime:" + waitTime);
        }
        String end = DateUtils.getCurrentDateTime();
        log.info("start time is ===> {}", start);
        log.info("end time is ===> {}", end);
        return new Result(HttpStatus.OK);
    }

    @GetMapping("/rateLimiter2")//实际开发可用
    public Result testRateLimiter2() {
        boolean flag = limiter.tryAcquire(1, 2, TimeUnit.SECONDS);
        if (flag) {
            log.info("处理请求，，，，，，，，，，");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("线程sleep方法执行失败 ===> {}", e);
                e.printStackTrace();
                return new Result(HttpStatus.INTERNAL_SERVER_ERROR, "请求失败");
            }
        } else {
            log.info("系统繁忙");
            return new Result(HttpStatus.FORBIDDEN, "系统繁忙");
        }
        return new Result(HttpStatus.OK);
    }

}
