package com.items.demo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("sys_class_student")
public class ClassStudent {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;

    private int deleteTime;

    private String gradeNumber;

    private String theClassNumber;

    private String studentName;
}
