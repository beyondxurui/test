package com.jlxu.demo.api.json.fast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：FastJson学习   https://segmentfault.com/a/1190000011212806
 * 创建时间：2020年03月17日
 * 文件名称：FastJsonTest
 * 版本：1.0.0
 * 最后修改时间：2020/3/17 14:21
 *
 * @auther ${许金李}
 */
@RunWith(SpringRunner.class)
@Slf4j
public class FastJsonTest {
    //定义”json格式“的”字符串”
    //json字符串-简单对象型
    private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String JSON_ARRAY_STR =
            "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String COMPLEX_JSON_STR =
            "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270}," +
                    "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    @Test
    public void fastJsonTest() {
        //一、源码分析
        //类实现和继承关系
        //1）
        //JSONArray extends JSON implements List<Object>, Cloneable, RandomAccess, Serializable {
        // 实现了List（Cloneable, RandomAccess, Serializable）
        //private final List<Object> list;
        // 继承了JSON   方法都是可以用的（和方法的区别：是否增强，是否重写）
        //2）
        //JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
        // 实现了Map（Cloneable, Serializable, InvocationHandler）
        // private static final int DEFAULT_INITIAL_CAPACITY = 16;
        //    private final Map<String, Object> map;
        // 继承了JSON   方法都是可以用的（和方法的区别：是否增强，是否重写）


        //二、FastJson的介绍
        // :  github地址：https://github.com/alibaba/fastjson

        //三、FastJson的特点
        //（ps从源码可得）
        //1）.FastJson数度快,无论序列化和反序列化,都是当之无愧的fast
        //2）.功能强大(支持普通JDK类包括任意Java Bean Class、Collection、Map、Date或enum)
        //3）.零依赖(没有依赖其它任何类库)

        //四、FastJson的简单说明:
        //FastJson对于json格式字符串的解析主要用到了下面三个类：
        //1）.JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换  TODO:
        //2）.JSONObject：fastJson提供的json对象
        //3）.JSONArray：fastJson提供json数组对象

        //五、FastJson的用法
        //1）1json格式的定义
        //{"name":"xx","age":[12,34],"dd":[{"age":23,"name":"sdd"}],"student":{"age":12},"name":"name"}
        //{} : ""必须  []:表示数据或者集合

        Map<String, String> map = new HashMap<>();
        map.put("s", "3");
        map.put("s1", "3");
        String jsonMap = JSON.toJSONString(map);
        log.info("map json string ===>{}", jsonMap);//{"s":"3","s1":"3"} String str = "{\"s\":\"3\",\"s1\":\"3\"}";
    }

    //五 2）、JSON格式字符串与JSON对象之间的转换

    //json字符串-简单对象型与JSONObject之间的转换
    @Test
    public void test1() {
        //json字符串-简单对象型与JSONObject之间的转换
        JSONObject json = JSON.parseObject(JSON_OBJ_STR);
        log.info("studentName：" + json.getString("studentName") + "===studentAge: " + json.getInteger("studentAge"));

        //JSONObject到json字符串-简单对象型的转换
        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        String s1 = JSONObject.toJSONString(jsonObject);
        String s2 = jsonObject.toJSONString();
        log.info("s1:{}", s1);
        log.info("s2:{}", s2);

    }

    //json字符串(数组类型)与JSONArray之间的转换
    @Test
    public void test2() {
        //json字符串-数组类型到JSONArray的转换
        //方式一
        JSONArray jsonArray = JSONObject.parseArray(JSON_ARRAY_STR);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            log.info("studentName：" + jsonObject.getString("studentName") + "===studentAge: " + jsonObject.getInteger("studentAge"));
        }
        //方式二
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            log.info("studentName：" + jsonObject.getString("studentName") + "===studentAge: " + jsonObject.getInteger("studentAge"));

        }

        //JSONArray到json字符串-数组类型的转换
        JSONArray jsonArray2 = JSONObject.parseArray(JSON_ARRAY_STR);
        String s1 = jsonArray.toJSONString();
        String s2 = JSONArray.toJSONString(jsonArray2);
        log.info("s1: {}", s1);
        log.info("s2: {}", s2);
    }

    //复杂json格式字符串与JSONObject之间的转换
    @Test
    public void test3() {
        JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        log.info("teacherName: " + teacherName + "teacherAge: " + teacherAge);
        JSONObject jsonObject1 = jsonObject.getJSONObject("course");
        log.info("courseName: " + jsonObject1.getString("courseName") +
                " code: " + jsonObject1.getInteger("code")
        );
//        JSONArray jsonArray = JSONObject.parseArray(jsonObject.getString("students"));
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object o : jsonArray) {
            JSONObject jsonObject2 = (JSONObject) o;
            log.info("studentName: " + jsonObject2.getString("studentName") +
                    " studentAge: " + jsonObject2.getInteger("studentAge")
            );
        }


        //复杂JSONObject到json格式字符串的转换
        JSONObject jsonObject2 = JSONObject.parseObject(COMPLEX_JSON_STR);// TODO: COMPLEX_JSON_STR是json字符串
        String s1 = jsonObject2.toJSONString();
        String s2 = JSONObject.toJSONString(jsonObject);
        log.info("s1: {}", s1);
        log.info("s2: {}", s2);
    }

    //五 3）JSON格式字符串与javaBean之间的转换

    //json字符串-简单对象型与javaBean之间的转换
    @Test
    public void test4() {
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        String studentName = jsonObject.getString("studentName");
        Integer studentAge = jsonObject.getInteger("studentAge");
        Student student = new Student(studentName, studentAge);
        Student student1 = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {
        });
        Student student2 = JSONObject.parseObject(JSON_OBJ_STR, Student.class);
        log.info("\n" + "student: " + student.toString() +
                "\n" + "student1: " + student1.toString() +
                "\n" + "student2: " + student2.toString()
        );

        //JavaBean到json字符串-简单对象的转换
        Student student3 = new Student("sd", 23);
        String jsonString = JSONObject.toJSONString(student3);
        log.info("\n" + "student3: " + jsonString);
    }

    //json字符串-数组类型与javaBean之间的转换
    @Test
    public void test5() {
        //json字符串-数组类型到JavaBean_List的转换
        //方式一
        JSONArray jsonArray = JSONObject.parseArray(JSON_ARRAY_STR);
        List<Student> students = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String studentName = jsonObject.getString("studentName");
            Integer studentAge = jsonObject.getInteger("studentAge");
            Student student = new Student(studentName, studentAge);
            students.add(student);
        }
        log.info("\n" + "students: " + students);

        //方式二   TODO:JSONObject
        ArrayList<Student> students1 = JSONObject.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {
        });
        log.info("\n" + "students1: " + students1);

        //方式三
        List<Student> students2 = JSONObject.parseArray(JSON_ARRAY_STR, Student.class);
        log.info("\n" + "students2: " + students2);

        //JavaBean_List到json字符串-数组类型的转换
        List<Student> studentList = new ArrayList<>();
        Student student3 = new Student("sd2", 23);
        Student student4 = new Student("sd3", 23);
        studentList.add(student3);
        studentList.add(student4);
        String jsonStudents = JSONArray.toJSONString(studentList);  // TODO:JSONArray
        log.info("\n" + "jsonStudents: " + jsonStudents);
    }

    //复杂json格式字符串与与javaBean之间的转换

    @Test
    public void test6() {
        //复杂json格式字符串到JavaBean_obj的转换

        //方式一  使用TypeReference<T>
        Teacher teacher = JSONObject.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {
        });
        //方式二  使用Gson思想
        Teacher teacher1 = JSONObject.parseObject(COMPLEX_JSON_STR, Teacher.class);

        log.info("\n" + "teacher: " + teacher +
                "\n" + "teacher1: " + teacher1
        );

        //复杂JavaBean_obj到json格式字符串的转换
        Teacher teacher2 = JSONObject.parseObject(COMPLEX_JSON_STR, Teacher.class);
        String jsonString = JSONObject.toJSONString(teacher2);
        log.info("\n" + "jsonString: " + jsonString);
    }

    //javaBean与json对象间的之间的转换
    @Test
    public void test7() {
        //简单JavaBean_obj到json对象的转换

        Student student = new Student("lily", 12);
        //方式一
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(student);  //TODO:
        //方式二
        JSONObject jsonObject1 = JSON.parseObject(JSON.toJSONString(student)); //TODO:最常用的

        log.info("\n" + "jsonObject: " + jsonObject +
                "\n" + "jsonObject1: " + jsonObject1
        );
    }

    //JavaList与JsonArray之间的转换
    @Test
    public void test8() {
        //JsonArray到JavaList的转换

        Student student = new Student("lily", 12);
        Student studenttwo = new Student("lucy", 15);

        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(studenttwo);
        //方式一
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(students));//TODO: parseArray方法
        //方式二
        JSONArray jsonArray2 = (JSONArray) JSONArray.toJSON(student); //TODO: JSONArray

        log.info("\n" + "jsonArray: " + jsonArray +
                "\n" + "jsonArray2: " + jsonArray2
        );
    }

    //复杂JavaBean_obj与json对象之间的转换
    @Test
    public void test9() {
        //复杂JavaBean_obj到json对象的转换

        //已知复杂JavaBean_obj
        Student student = new Student("lily", 12);
        Student studenttwo = new Student("lucy", 15);

        List<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(studenttwo);
        Course course = new Course("english", 1270);

        Teacher teacher = new Teacher("crystall", 27, course, students);
        //方式一
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(teacher));
        //方式二
        JSONObject jsonObject2 = (JSONObject) JSONObject.toJSON(teacher);

        log.info("\n" + "jsonObject: " + jsonObject +
                "\n" + "jsonObject2: " + jsonObject2
        );
    }
    //ps  项目中问题记录
    //1）方法需要的是是Json字符串（如JSON.parseObject(String text)），如果不是json字符串，要把字符串对应的对象找出来，然后转为json字符串
    //2）JSON的实现类分两类  JSONObject JSONArray  区别：操作的是是否含义List/数组的json字符串
    //3）JSON对象转json字符串知道的由两种方式，JSON对象不是map结构，具体看json字符串的定义
    //4）JSON对象的字符串在编辑器（idea）中存在方式和JSON对象在debug不一样  TODO：
}
