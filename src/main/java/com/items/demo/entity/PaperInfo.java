package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}
