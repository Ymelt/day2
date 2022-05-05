package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}
