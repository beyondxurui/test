package com.jlxu.demo.thread.three;

/**
 * 功能:多个线程可以同时进入多个加了锁的方法(this只能最多一个线程进入)
 * 版本权限：浙江省公众信息产业有限公司智慧旅游（事业）部
 * 创建时间：2020年02月29日
 * 文件名称：MsLunch
 * 版本：1.0.0
 * 最后修改时间：2020/2/29 21:23
 *
 * @auther jlxu
 */
public class MsLunch {
    private long c1 = 1;
    private long c2 = 1;
    private Object lock1 = new Object();/*锁*/
    private Object lock2 = new Object();/*锁*/

    public synchronized void ini1() {
        c1++;
    }

    public synchronized void ini2() {
        c2++;
    }
}
