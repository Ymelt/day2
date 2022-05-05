package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("sys_course")
@NoArgsConstructor
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private int createTime;

    private int updateTime;

    private int deleteTime;

    private String courseName;

    private String courseTeacher;


    public Course(String courseName, String courseTeacher) {
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
    }
}
