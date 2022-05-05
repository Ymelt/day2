package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_class_teacher")
public class ClassTeacher {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private int createTime;

    private int updateTime;

    private int deleteTime;

    private String gradeNumber;

    private String theClassNumber;

    private String classCourse;

    private String courseTeacher;
}
