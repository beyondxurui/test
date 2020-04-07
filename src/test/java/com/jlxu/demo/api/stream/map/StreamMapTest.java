package com.jlxu.demo.api.stream.map;

import com.jlxu.demo.api.stream.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能：Stream对map的操作
 * 创建时间：2020年04月03日
 * 文件名称：StreamMapTest
 * 版本：1.0.0
 * 最后修改时间：2020/4/3 16:54
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class StreamMapTest {
    @Test
    public void streamMapTest() {//java.lang.Exception: Method streamMapTest() should be public
        //一、map排序
        Map<String, Integer> map = new HashMap<>();
        map.put("aaa", 123);
        map.put("bcd", 12);
        map.put("cdg", 13);
        map.put("ded", 125);
        map.put("egg", 2);
        map.put("fer", 23);
        Set<Map.Entry<String, Integer>> entries = map.entrySet(); //Stream操作不了Map 但是可以操作Collection,转就是了  TODO:

        //1）方式一
        //回顾 Collection FrameWork 见桌面图和源码类关系  回顾Map的数据结构
        //正向  ASC
        LinkedHashMap<String, Integer> sorted = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sorted);  //{egg=2, bcd=12, cdg=13, fer=23, aaa=123, ded=125}

        //反向  Desc
        LinkedHashMap<String, Integer> sorted11 = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sorted11);  //{ded=125, aaa=123, fer=23, cdg=13, bcd=12, egg=2}

        //2）方式二
        //正向 TODO: (e1, e2) -> e1
        LinkedHashMap<String, Integer> sorted2 = map.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sorted2);//{egg=2, bcd=12, cdg=13, fer=23, aaa=123, ded=125}

        //反向  TODO: (e1, e2) -> e2
        LinkedHashMap<String, Integer> sorted22 = map.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sorted22);  //{ded=125, aaa=123, fer=23, cdg=13, bcd=12, egg=2}

        //3）map的key单独排序
        Set<String> setKey = map.keySet();
        Object[] sorArr = setKey.toArray();
        Arrays.sort(sorArr);  //正序

        //二、Stream的异常问题
        //1）测试空集合
        List<Person> personList = new ArrayList<>();

        Stream<Person> wj = personList.stream().filter(p -> p.getName().equals("wj"));
        Map<Boolean, List<Person>> wj1 = personList.stream().collect(Collectors.groupingBy(e -> e.getName().equals("wj")));
        //int size = personList.stream().collect(Collectors.groupingBy(e -> e.getName().equals("wj"))).get("A").size();//java.lang.NullPointerException
        //TODO:get("A")调用后的对象为null ，get("A")调用前的map size为0
        long count = wj.count();
        System.out.println(count);

        //2）分组（更具经验猜想的写法）  已纠正，见下面的 TODO:
        List<Person> personList2 = Arrays.asList(new Person("we", 12)
                , new Person("wj", 15)
                , new Person("wj", 15)
                , new Person("wj2", 15)
                , new Person("wj", 15)
                , new Person("wj3", 15)
        );
        //name为A的集合数 TODO: 要加过滤的，不然返回的map的key会是Boolean类型
        Map<String, List<Person>> wj2 = personList2.stream().filter(e -> e.getName().equals("wj")).collect(Collectors.groupingBy(e -> e.getName()));
        //Map<Boolean, List<Person>> wj3 = personList2.stream().collect(Collectors.groupingBy(e -> e.getName().equals("wj")));
        int size2 = personList2.stream().filter(e -> e.getName().equals("wj")).collect(Collectors.groupingBy(e -> e.getName())).size();
        wj2.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
        //wj
        //[Person(name=wj, id=15), Person(name=wj, id=15), Person(name=wj, id=15)]
        System.out.println(size2); //1
        // TODO:
        //不指定就不用加过滤了  可见 StreamTest 中的 Map<Integer, List<Person>> listMap = list6.stream().collect(Collectors.groupingBy(Person::getId));
        //至于异常：集合可以为空，但是不能为null，要操作的数据（如获取集合对象中某个属性，还调用这个属性的方法时）不能能为null

    }
}
