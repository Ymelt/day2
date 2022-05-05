package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.demo.entity.PaperChoice;
import org.apache.ibatis.annotations.Param;

public interface PaperChoiceMapper extends BaseMapper<PaperChoice> {

    boolean deChoice(@Param("id") String id);
}
