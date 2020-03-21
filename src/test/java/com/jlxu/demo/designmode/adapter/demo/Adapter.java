package com.jlxu.demo.designmode.adapter.demo;

/**
 * 功能
 * 创建时间：2020年03月21日
 * 文件名称：Adapter2
 * 版本：1.0.0
 * 最后修改时间：2020/3/21 16:03
 *
 * @auther jlxu
 */
// 适配器类，继承了被适配类，同时实现标准接口
public class Adapter extends Adeptee implements Target {
    @Override
    public void request() {
        super.specificRequest();
    }
}
