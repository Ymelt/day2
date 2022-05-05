package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.demo.entity.PaperStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperStudentMapper extends BaseMapper<PaperStudent> {
    boolean deleteFirst(@Param("titleId")String titleId);

    List<String> findIds(@Param("studentName")String studentName);
}
