package com.jlxu.demo.thread.cache;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * 功能
 * 创建时间：2020年03月01日
 * 文件名称：UserCostStatComputerVersionTwo
 * 版本：1.0.0
 * 最后修改时间：2020/3/1 7:47
 *
 * @auther jlxu
 */
public class UserCostStatComputerVersionTwo implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // assume there is a long time compute...
        TimeUnit.SECONDS.sleep(3);
        return new BigInteger(arg);
    }
}
