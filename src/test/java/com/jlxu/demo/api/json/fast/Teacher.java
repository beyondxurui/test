package com.jlxu.demo.api.json.fast;

import lombok.Data;

import java.util.List;

/**
 * 功能
 * 创建时间：2020年03月17日
 * 文件名称：Teacher
 * 版本：1.0.0
 * 最后修改时间：2020/3/17 17:32
 *
 * @auther jlxu
 */
@Data
public class Teacher {
    private String teacherName;
    private Integer teacherAge;
    private Course course;
    private List<Student> students;

    public Teacher(String teacherName, Integer teacherAge, Course course, List<Student> students) {
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
        this.course = course;
        this.students = students;
    }
}
