package com.jlxu.demo.equality;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能:Java 中的 ==, equals 与 hashCode 的区别与联系
 * 创建时间：2020年03月05日
 * 文件名称：EqualityTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 17:18
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class EqualityTest {

    //==测试
    @Test
    public void equalityTest() {
        //一 基本类型
        byte by = -12;
        byte by2 = -12;
        byte by3 = 12;
        byte by4 = -0;
        byte by5 = -128;
        log.info("by===by2 ===>{}", by == by2);//true
        log.info("by===by3 ===>{}", by == by3);//false
        log.info("b4===by5 ===>{}", by4 == by5);//false

        //引用类型
        //关系操作符判断的是左右两边操作数的内存地址是否相同。也就是说，若此时返回true,则该操作符作用的一定是同一个对象。
        //ps: String是引用类型
    }

    //equals测试
    @Test
    public void equalsTest() {
        //初衷 ： 判断两个对象的 content 是否相同
        //Object的equals方法比较的是引用所值对象再内存中的地址
        String str = new String("abc");
        String str2 = new String("abc");
        String str3 = null;
        str3.equals(str);//java.lang.NullPointerException   ps TODO:以后不要问自己这种问题了并且实践中注意 空指针异常
        log.info("str和str2是否相等", str.equals(str2));//true
        //为什么是true，因为String类重写equals方法(三步 （1）引用实现相等  （2）类型是否相等 （3）内容是否相等)
        //Java 中所有内置的类的 equals 方法的实现步骤均是如此，特别是诸如 Integer，Double 等包装器类。  ps:包装类不比较地址

        //TODO:其本意 是 比较两个对象的 content 是否相同

        //TODO:必要的时候，我们需要重写该方法，避免违背本意，且要遵循上述原则(对称性，自反性，类推性，一致性)
    }

    //测试hashCode
    @Test
    public void hashCodeTest() {
        //哈希概念
        //概念 ： Hash 就是把任意长度的输入(又叫做预映射， pre-image)，通过散列算法，变换成固定长度的输出(int)，该输出就是散列值。这种转换是一种  压缩映射，也就是说，散列值的空间通常远小于输入的空间。不同的输入可能会散列成相同的输出，从而不可能从散列值来唯一的确定输入值。
        // 简单的说，就是一种将任意长度的消息压缩到某一固定长度的消息摘要的函数。  ps TODO：czbk老师说过 code值是由地址值和equals决定的
        //应用–数据结构：数组（查快，增删慢）加链表（查慢，增删快）组合   查询快，增删也快
        //哈希表的实现：有拉链法
        //我们根据元素的自身特征把元素分配到不同的链表中去，也是根据这些特征，找到正确的链表，再从链表中找出这个元素。其中，将根据元素特征计算元素数组下标的方法就是散列法。
        //hashCode 的常规协定是：
        //要想进一步了解 hashCode 的作用，我们必须先要了解Java中的容器，因为 HashCode 只是在需要用到哈希算法的数据结构中才有用，比如 HashSet, HashMap 和 Hashtable。
        //那么, 这里就有一个比较严重的问题：要想保证元素不重复，可两个元素是否重复应该依据什么来判断呢？ 这就是 Object.equals  方法了。但是，如果每增加一个元素就检查一次，那么当元素很多时，后添加到集合中的元素比较的次数就非常多了。 也就是说，如果集合中现在已经有1000个元素，那么第1001个元素加入集合时，它就要调用1000次equals方法。这显然会大大降低效率。于是，Java采用了哈希表的原理。 这样，我们对每个要存入集合的元素使用哈希算法算出一个值，然后根据该值计算出元素应该在数组的位置。所以，当集合要添加新的元素时，可分为两个步骤：
        //先调用这个元素的 hashCode 方法，然后根据所得到的值计算出元素应该在数组的位置。如果这个位置上没有元素，那么直接将它存储在这个位置上；
        //如果这个位置上已经有元素了，那么调用它的equals方法与新元素进行比较：相同的话就不存了，否则，将其存在这个位置对应的链表中（Java 中 HashSet, HashMap 和 Hashtable的实现总将元素放到链表的表头）。
        //ps:性能
        //案例一（不重写equals和hashCode）
        Person person = new Person("ad", 3);
        log.info("person-1 ==>{}", person.hashCode());//1744189907
        Map hashMap = new HashMap<Object, Object>();
        hashMap.put(person, person);//存的是引用
        person.setAge(23);
        log.info("person-2 ==>{}", person.hashCode());//1744189907
        hashMap.put(person, person);//比较引用，被修改了，所以一样
        hashMap.forEach((k, v) -> {
            log.info("k,v==>{},{}", k.toString(), v.toString());
        });
        //k,v==>Person5{name='ad', age=23},Person5{name='ad', age=23}  只有一个
        // TODO:why?  因为HashMap底层key（这里的Person对象）的唯一性通过hashCode和equals确定改地，因为Person（默认继承Object）没重写hashCode和equals,所以相等

        //案例二(重写equals和不重写hashCode)
        Person2 person2 = new Person2("ad", 3);
        log.info("person2-1 ==>{}", person2.hashCode());//1704237553
        Map hashMap2 = new HashMap<Object, Object>();
        hashMap2.put(person2, person2);//存的是引用
        person2.setAge(23);
        log.info("person2-2 ==>{}", person2.hashCode());//1704237553
        hashMap2.put(person2, person2);//比较引用，被修改了，所以一样
        hashMap2.forEach((k, v) -> {
            log.info("k2,v2==>{},{}", k.toString(), v.toString());
        });
        //k2,v2==>Person5{name='ad', age=23},Person5{name='ad', age=23}   只有一个
        //ps:基本同上


        //案例三（重写equals和hashCode）
        Person3 person3 = new Person3("ad", 3);
        Person4 person5 = new Person4("ad", 23);
        log.info("person3-1 ==>{}", person3.hashCode());//person3-1 ==>97281
        Map hashMap3 = new HashMap<Object, Object>();
        hashMap3.put(person3, person3);
        person3.setAge(23);
        log.info("person3-2 ==>{}", person3.hashCode());//person3-2 ==>97301
        hashMap3.put(person3, person3);
        hashMap3.forEach((k, v) -> {
            log.info("k3,v3==>{},{}", k.toString(), v.toString());
        });
        //k3,v3==>Person5{name='ad', age=23},Person5{name='ad', age=23}
        //k3,v3==>Person5{name='ad', age=23},Person5{name='ad', age=23}
        //两个一样的？ TODO:说明存的是引用

        //案例四（不重写equals和重写hashCode）
        Person4 person4 = new Person4("ad", 3);
        Person4 person6 = new Person4("ad", 23);
        log.info("person4-1 ==>{}", person4.hashCode());//person4-1 ==>97281
        Map hashMap4 = new HashMap<Object, Object>();
        hashMap4.put(person4, person4);
        person4.setAge(23);
        log.info("person4-2 ==>{}", person4.hashCode());//person4-2 ==>97301
        hashMap4.put(person4, person4);
        hashMap4.forEach((k, v) -> {
            log.info("k4,v4==>{},{}", k.toString(), v.toString());
        });
        //k4,v4==>Person5{name='ad', age=23},Person5{name='ad', age=23}
        //k4,v4==>Person5{name='ad', age=23},Person5{name='ad', age=23}
        //两个一样的？TODO:说明存的是引用

        //String重写了hashCode和equals方法不用重写了
        //Java 中 HashSet, HashMap 和 Hashtable TODO:的equals和hashCode是否重写看 https://blog.csdn.net/xlgen157387/article/details/88087963
        //TODO: Set 存储的是不重复的对象，依据 hashCode 和 equals 进行判断 所以都要重写
    }


    //测试两个=一起使用什么意思  测试原因：LinkedHashMap源码中有这样的使用  栗子:   Entry<K,V> e = lastReturned = nextEntry;
    @Test
    public void test() {
        Person5 person2 = new Person5("ee1");
        Person5 person3 = new Person5("ee");
        Person5 p0 = person2 = person3;
        log.info("p0===>{}", p0);//p0===>Person5(name=ee)
        log.info("person2===>{}", person2);// person2===>Person5(name=ee)
        log.info("person3===>{}", person3);// person3===>Person5(name=ee)

        //ps： TODO: p0(引用)，p2(引用)，p3(引用)都执行一个对象：园p0所指的对象
    }
}
