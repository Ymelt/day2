package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("sys_course")
@NoArgsConstructor
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;

    private int deleteTime;

    private String courseName;

    private String courseTeacher;


    public Course(String courseName, String courseTeacher) {
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
    }
}
