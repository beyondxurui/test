package com.jlxu.demo.equality;

/**
 * 功能：重写equals和不重写hashCode
 * 创建时间：2020年03月05日
 * 文件名称：Person5
 * 版本：1.0.0
 * 最后修改时间：2020/3/5 18:38
 *
 * @auther jlxu
 */
public class Person2 {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;
    private Integer age;

    public Person2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person5{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return name.equals(person2.name) &&
                age.equals(person2.age);
    }
}
