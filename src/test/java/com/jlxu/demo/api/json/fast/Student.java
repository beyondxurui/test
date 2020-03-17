package com.jlxu.demo.api.json.fast;

import lombok.Data;

/**
 * 功能
 * 创建时间：2020年03月17日
 * 文件名称：Student
 * 版本：1.0.0
 * 最后修改时间：2020/3/17 17:05
 *
 * @auther jlxu
 */
@Data
public class Student {
    private String studentName;
    private Integer studentAge;

    public Student(String studentName, Integer studentAge) {
        this.studentName = studentName;
        this.studentAge = studentAge;
    }
}
