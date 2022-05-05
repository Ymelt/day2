package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}
