package com.jlxu.demo.thissuper;

import com.jlxu.demo.equality.Person5;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能 this关键字测试
 * 创建时间：2020年03月07日
 * 文件名称：ThisTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/7 10:48
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ThisTest {

    //测试类成员方法的中使用this是什么意思   测试原因：LinkedList的public V put(K key, V value)方法内e.recordAccess(this)方法中   remove();含义
    @Test
    public void thisTest() {
        List<Person5> personList = new ArrayList<>(4);
        personList = Arrays.asList(new Person5("dd"), new Person5("dd3"), new Person5("dd"));
        for (Person5 person : personList) {
            getPerson(this);
            break;
        }
    }

    private void getPerson(ThisTest thisTest) {//不用打印了就是当前类的引用
        log.info("这个this是那个呢 ===> {}", thisTest);//com.jlxu.demo.thissuper.ThisTest@78ac1102
        //ps TODO:当前类的引用
    }

}
