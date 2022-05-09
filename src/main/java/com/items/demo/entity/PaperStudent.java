package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("paper_student")
public class PaperStudent {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String paperStudent;

    private String paperId;

    private String paperTitleId;

    private String paperStudentAnswer;

    private String paperJudge;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;
}
