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
public class Course {
    private String courseName;
    private Integer code;

    public Course(String courseName, Integer code) {
        this.courseName = courseName;
        this.code = code;
    }
}
