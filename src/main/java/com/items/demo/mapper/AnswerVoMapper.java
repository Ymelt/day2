package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.items.demo.entity.vo.AnswerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerVoMapper extends BaseMapper<AnswerVo> {
    List<AnswerVo> studentAnswerShell(@Param("module")String module ,@Param("paperId")String paperId);

    Integer anyThis(@Param("titleId")String titleId);

    Boolean judge();

    List<AnswerVo> shellHistory(@Param("paperId")String paperId);
}
