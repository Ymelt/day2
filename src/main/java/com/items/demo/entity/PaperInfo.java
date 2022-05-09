package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("paper_info")
public class PaperInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String paperId;

    private String paperCourse;

    private String paperName;

    private String paperAuthor;

    private String paperApplyClass;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;
}
