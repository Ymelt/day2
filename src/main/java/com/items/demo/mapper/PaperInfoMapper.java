package com.items.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.demo.entity.PaperInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperInfoMapper extends BaseMapper<PaperInfo> {

    List<String> findId(@Param("courseName")String courseName);

    List<String> findIdForStu(@Param("name")String name);
}
