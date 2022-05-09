package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("paper_content")
public class PaperContent {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String paperId;

    private String paperTitleId;

    private String paperModule;

    private String paperTitleType;

    private String paperTitle;

    private String paperTitleAnswer;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;
}
