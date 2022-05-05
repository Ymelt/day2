package com.items.demo.entity.vo;

import com.items.demo.entity.PaperContent;
import lombok.Data;

@Data
public class AnswerVo extends PaperContent {
    private String paperStudentAnswer;

    private String paperJudge;
}
