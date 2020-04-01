package com.jlxu.demo.api.stream;

import com.jlxu.demo.api.stream.entity.Person;
import com.jlxu.demo.classinit.A;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能：jdk8新特性   Stream   https://blog.csdn.net/y_k_y/article/details/84633001
 * 创建时间：2020年03月31日
 * 文件名称：StreamTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/31 15:50
 *
 * @auther
 */
@RunWith(SpringRunner.class)
@Slf4j
public class StreamTest {
    @Test
    public void test() {
        //ps 自己写的分组栗子
        Person person = new Person("sd", 23);
        Person person2 = new Person("sd3", 23);
        Person person3 = new Person("sd4", 23);
        Person person4 = new Person("sd3", 23);
        Person person5 = new Person("sd", 23);

        List<Person> persons = new ArrayList<>();
        persons.addAll(Arrays.asList(person, person2, person3, person4, person5));


        //分组
        //方式一  Map<String, List<Person>> listMap = persons.stream().collect(Collectors.groupingBy(Person::getName));
        Map<String, List<Person>> listMap = persons//方式二
                .stream()
                .collect(Collectors.groupingBy((Person p) -> p.getName()));

        listMap.forEach((k, v) -> {
            System.out.println("key is : " + k);
            System.out.println("value is : " + v.toString());
        });
        //ps:关于::查看 lambda包下
    }

    @Test
    public void streamTest() throws IOException {
        //一、概述
        //Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。
        // 使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。
        // 也可以使用 Stream API 来并行执行操作。  TODO:并行？
        // 简而言之，Stream API 提供了一种高效且易于使用的处理数据的方式。
        //特点：
        //        1 . 不是数据结构，不会保存数据。
        //        2. 不会修改原来的数据源，它会将操作后的数据保存到另外一个对象中。（保留意见：毕竟peek方法可以修改流中元素）
        //        3. 惰性求值，流在中间处理过程中，只是对操作进行了记录，并不会立即执行，需要等到执行终止操作的时候才会进行实际的计算。

        //二、分类  另有图片，见桌面图
        //    无状态：指元素的处理不受之前元素的影响；
        //    有状态：指该操作只有拿到所有元素之后才能继续下去。
        //    非短路操作：指必须处理所有元素才能得到最终结果；
        //    短路操作：指遇到某些符合条件的元素就可以得到最终结果，如 A || B，只要A为true，则无需判断B的结果。

        //三、具体用法
        //Stream
        //1.1）使用Collection下的 stream() 和 parallelStream() 方法
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("ddl", "dd", "21"));
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();
        //1.2）使用Arrays 中的 stream() 方法，将数组转成流
        String[] arr = new String[10];
        Stream<String> stream1 = Arrays.stream(arr);
        //1.3） 使用Stream中的静态方法：of()、iterate()、generate()
        Stream<Integer> stream2 = Stream.of(1, 23, 43, 3);
        Stream<Integer> stream3 = Stream.iterate(1, (x) -> x + 3).limit(8);
        stream3.forEach(System.out::println);
        Stream<Double> stream4 = Stream.generate(Math::random).limit(3);
        stream4.forEach(System.out::println);

        //1.4）使用 BufferedReader.lines() 方法，将每行内容转成流
        BufferedReader bf = new BufferedReader(new FileReader("J:\\java\\idea_workspace\\springboot_demo\\src\\test\\resources\\test.txt"));
        Stream<String> stream5 = bf.lines();  //TODO
        stream5.forEach(System.out::println);

        //1.5)使用 Pattern.splitAsStream() 方法，将字符串分隔成流
        Pattern pattern = Pattern.compile(",");
        Stream<String> stream6 = pattern.splitAsStream("d,dg,gg,e,e,rt,yu");
        stream6.forEach(System.out::println);

        //2流的中间操作
        //2.1）筛选与切片
        Stream<Integer> stream7 = Stream.of(1, 23, 3, 4, 5, 67, 5, 4, 78, 7)// filter：过滤流中的某些元素
                .distinct()//distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
                .skip(2)// skip(n)：跳过n元素，配合limit(n)可实现分页
                .limit(3);//limit(n)：获取n个元素
        Stream<Integer> stream8 = stream7.filter(s -> s > 3);
        stream8.forEach(System.out::println);
//        Stream<Integer> stream9 = stream8.distinct();
//        stream9.forEach(System.out::println);
        //后三个方法不能操作已经操作的流 TODO:  java.lang.IllegalStateException: stream has already been operated upon or closed

        //2.2）
        //map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        //flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        List<String> list1 = Arrays.asList("d,d,e", "1,23,4");
        Stream<String> stream9 = list1.stream().map(s -> s.replace(",", ""));
        stream9.forEach(System.out::println);
        Stream<String> stream10 = list1.stream().flatMap(s -> {
            String[] split = s.split(",");
            Stream<String> s1 = Arrays.stream(split);
            return s1;
        });
        stream10.forEach(System.out::println);

        //2）.3 排序
        //sorted()：自然排序，流中元素需实现Comparable接口
        //sorted(Comparator com)：定制排序，自定义Comparator排序器
        List<String> list2 = Arrays.asList("aa", "bb", "cc", "dd");
        list2.stream().sorted().forEach(System.out::println);

        List<Person> list3 = Arrays.asList(new Person("aa", 12)
                , new Person("aa", 13)
                , new Person("bb", 13)
                , new Person("cc", 14)
                , new Person("dd", 15)
        );
        list3.stream().sorted((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return o1.getId() - o2.getId();
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }).forEach(System.out::println);

        //2.4 消费
        //peek：如同于map，能得到流中的每一个元素。
        //但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
        List<Person> list4 = Arrays.asList(new Person("aa", 12), new Person("bb", 13));
        list4.stream().peek(p -> p.setId(15)).forEach(System.out::println);//TODO:对于修改这个还可以啊

        //3. 流的终止操作
        //3.1） 匹配、聚合操作
        //allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
        //noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
        //anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
        //findFirst：返回流中第一个元素
        //findAny：返回流中的任意元素
        //count：返回流中元素的总个数
        //max：返回流中元素最大值
        //min：返回流中元素最小值
        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5);
        boolean b = list5.stream().allMatch(s -> s > 5);//false
        boolean b1 = list5.stream().noneMatch(s -> s > 7);//true
        boolean b2 = list5.stream().anyMatch(s -> s > 4);//true
        Integer integer = list5.stream().findFirst().get();//1
        Integer integer1 = list5.stream().findAny().get();
        System.out.println(integer1);
        long count = list5.stream().count();//5
        Integer integer2 = list5.stream().max(Integer::compareTo).get();//5
        Integer integer3 = list5.stream().min(Integer::compareTo).get();//1


        //3.2） 规约操作
        // Optional<T> reduce(BinaryOperator<T> accumulator)：
        // 第一次执行时，accumulator函数的第一个参数为流中的第一个元素，第二个参数为流中元素的第二个元素；
        // 第二次执行时，第一个参数为第一次函数执行的结果，第二个参数为流中的第三个元素；依次类推。

        //并行流的概念和Fork/Join TODO:后续看下
        //http://www.cocoachina.com/articles/30678#ForkJoin_681
        //https://www.jdon.com/performance/threadpool-forkjoin.html
        //https://blog.csdn.net/u014507083/article/details/70571502
        //https://www.jianshu.com/p/f777abb7b251

        //3.3） 收集操作  注：Collector和Collection不是一回事 同理   Collections和Collections
        //collect：接收一个Collector实例，将流中元素收集成另外一个数据结构。
        //Collector<T, A, R> 是一个接口，有以下5个抽象方法：
        //3.3.1） Collector 工具库：Collectors
        List<Person> list6 = Arrays.asList(new Person("dg", 13)
                , new Person("df", 13)
                , new Person("dh", 15));

        ////装成list
        //List<Integer> collect = list6.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<Integer> collect = list6.stream().map(Person::getId).collect(Collectors.toList());
        //转成set
        Set<Integer> set = list6.stream().map(Person::getId).collect(Collectors.toSet());
        //转成map,注:key不能相同，否则报错
        Map<String, Integer> map = list6.stream().collect(Collectors.toMap(Person::getName, Person::getId));
        //字符串count2分隔符连接
        String joinName = list6.stream().map(Person::getName).collect(Collectors.joining(",", "(", ")"));
        System.out.println(joinName);

        //聚合操作
        //1.总数
        Long count2 = list6.stream().collect(Collectors.counting());
        //2.最大id (最小的minBy同理)
        Integer maxId = list6.stream().map(Person::getId).collect(Collectors.maxBy(Integer::compare)).get();
        //TODO:compare 和compareTo的区别
        //3.所有人的id
        Integer idSum = list6.stream().collect(Collectors.summingInt(Person::getId));
        //4.平均id
        Double averageId = list6.stream().collect(Collectors.averagingInt(Person::getId));
        // 带上以上所有方法
        DoubleSummaryStatistics statistics = list6.stream().collect(Collectors.summarizingDouble(Person::getId));
        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());

        //分组
        Map<Integer, List<Person>> listMap = list6.stream().collect(Collectors.groupingBy(Person::getId));
        //多重分组
        Map<Integer, Map<String, List<Person>>> collect1 = list6.stream().collect(Collectors.groupingBy(Person::getId, Collectors.groupingBy(Person::getName)));

        //分区
        Map<Boolean, List<Person>> collect2 = list6.stream().collect(Collectors.partitioningBy(p -> p.getId() > 13));
        //规约  TODO:reducing方法，见上面，复杂的后续处理
        Integer integer4 = list6.stream().map(Person::getId).collect(Collectors.reducing(Integer::sum)).get();//40

        //3.3.2） Collectors.toList() 解析
        //TODO:

    }

}
