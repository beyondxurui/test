package com.jlxu.demo.designmode.adapter.demo2;

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
public class Adapter2 implements Target2 {
    // 直接关联被适配类
    private Adeptee2 adeptee2;

    // 可以通过构造函数传入具体需要适配的被适配类对象
    public Adapter2(Adeptee2 adeptee2) {
        this.adeptee2 = adeptee2;
    }

    @Override
    public void request() {
        // 这里是使用委托的方式完成特殊功能
        adeptee2.specificRequest();
    }
}
