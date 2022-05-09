package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("paper_choice")
public class PaperChoice {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String paperId;

    private String paperModule;

    private String paperTitleId;

    private String paperTitleOption;

    private String paperOptionContent;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;
}
