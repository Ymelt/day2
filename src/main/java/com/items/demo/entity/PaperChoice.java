package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}
