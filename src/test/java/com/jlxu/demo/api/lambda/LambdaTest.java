package com.jlxu.demo.api.lambda;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jlxu.demo.api.lambda.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能：Lambda表达式  https://blog.csdn.net/justloveyou_/article/details/89066782
 * 创建时间：2020年03月24日
 * 文件名称：LambdaTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/24 10:51
 *
 * @auther jlxu
 */
@RunWith(SpringRunner.class)
@Slf4j
public class LambdaTest {
    @Test
    public void lambdaTest() {

        //一、函数式接口
        //函数式接口也是 java interface 的一种，但还需要满足：
        //一个函数式接口只有一个抽象方法(SAM，single abstract method)；
        //Object 类中的 public abstract method 不会被视为单一的抽象方法；
        //函数式接口可以有默认方法和静态方法；
        //函数式接口可以用@FunctionalInterface 注解进行修饰。
        //栗子  Comparator

        //函数式接口有什么用？
        //案例：我们要用Comparator实现排序算法
        //1：规规矩矩的写一个实现Comparator接口的java类去封装排序逻辑。若业务需要多种排序方式，那就得写多个类提供多种实现，而这些实现往往只需使用一次。。。。
        //2：另外一种聪明些的做法无外乎就是在需要的地方搞个匿名内部类去扛了。比如：
        List<Person> persons = new ArrayList<>();
        persons.addAll(Arrays.asList(new Person("adl", 36)
                , new Person("lbj", 35)
                , new Person("wd", 38)));
        Collections.sort(persons, new Comparator<Person>() {   //TODO:Collections的排序，这应该是一个排序算法，后续处理
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        //查看顺序
        for (Person person : persons) {
            System.out.println(person.toString());//小到大
        }
        //3：lambda实例化方式
        Comparator<Person> comparator = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
        Collections.sort(persons, comparator);

        //@FunctionalInterface 使用场景  看就懂了

        //二、lambda表达式
        //1）什么是lambda?
        //lambda表达式是实现函数式接口的一个快捷方式
        //最直观的栗子
        Runnable r = () -> {
            System.out.println("Hello World");
        };//=号的前面是变量的声明，后面是一个lambda表达式。我们直接将lambda表达式赋值为Runnable类型的task变量，就意味着：lambda表达式是实现函数式接口的一个快捷方式。理解这一点是至关重要的。
        //2）lambda表达式语法
        //可抽象表示为
        //(Type1 param1, Type2 param2, ..., TypeN paramN) -> {
        //      statment1;
        //      statment2;
        //      ...
        //      return statmentN;
        //}

        //以Predicate判断是否为成年人为例：
        Predicate<Person> predicate = (Person p1) -> {
            Integer age = p1.getAge();
            return age >= 18;
        };
        //和我们平时所写的lambda表达式比起来要繁琐一些
        //1参数类型省略
        Predicate<Person> predicate1 = (p1) -> {
            Integer age = p1.getAge();
            return age >= 18;
        };
        //2当lambda表达式的参数个数只有一个，可以省略小括号。
        Predicate<Person> predicate2 = p1 -> {
            Integer age = p1.getAge();
            return age >= 18;
        };
        //当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号。  这才横，不是吗
        Predicate<Person> predicate3 = p1 -> p1.getAge() > 18;

        //Lambda表达式眼中的外部世界
        //问题
        //是不是lambda表达式不能访问其外部变量？
        //lambda表达式其实是快速创建函数式接口实例的语法糖，匿名内部类所实现的实例都可以访问接口外部变量，那么lambda表达式肯定也是可以
        //TODO:事实上，不但可以，在java8中还做了一个小小的升级。看下面例子：
        String[] arr = {"a", "b", "c"};
        for (Integer i : Lists.newArrayList(1, 2, 3)) {
            System.out.println(i);
            Stream.of(arr).map(item -> Strings.padEnd(item, i, '*'))
                    .forEach(System.out::println);
        }

        //::的含义
        //https://blog.csdn.net/weixin_37770552/article/details/77905826
        //::的含义  TODO:  先简单的的，如文字栗子  难的后续看如代码栗子
        //表达式:
        //person -> person.getAge();  可以替换成   Person::getAge
        //表达式
        //() -> new HashMap<>();   可以替换成   HashMap::new
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        // .map映射   collection累加组合   Collectors.toList()：将输入元素新的Collector
        collected = collected.stream().map(string -> string.toUpperCase()).collect(Collectors.toList());
        System.out.println(collected);

        List<String> collected2 = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected2 = collected2.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));//注意发生的变化
        System.out.println(collected2);

        //Java8 预定义函数式接口及后面的， TODO:后续在看,目前知识大概的看了

        //问题
        //1）回顾匿名内部类
        //2）为什么匿名内部类访问外部类的变量要是final的？
        //TODO：对于问题1）2）见内部类2020-04-30提交记录====>内部类（更新）
        //ps
        //源码方法怎么看，TODO:
        // 方式一：进入看看，不行借组翻译软件，不行搜索

        //2020-03-30
        //TODO： 泛型  设计模式

        //lambda表达式的意义
        //https://www.jianshu.com/p/f02b04344365

    }

}
