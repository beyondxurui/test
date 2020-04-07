package com.jlxu.demo.jvm.loadClass.demo5.server;

import com.jlxu.demo.jvm.loadClass.demo5.client.ICalculate;

/**
 * 功能：模拟网络版本1  （接口为了方便直接使用客户端中的了）
 * 创建时间：2020年04月06日
 * 文件名称：Versioned
 * 版本：1.0.0
 * 最后修改时间：2020/4/6 22:43
 *
 * @auther jlxu
 */
public class CalculateBasic implements ICalculate {
    @Override
    public String calculate(String expresion) {
        return expresion;
    }

    @Override
    public String getVersion() {
        return "1";
    }
}
