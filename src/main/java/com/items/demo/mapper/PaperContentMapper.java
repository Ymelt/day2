package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.demo.entity.PaperChoice;
import com.items.demo.entity.PaperContent;
import com.items.demo.entity.vo.ContentVo;
import com.items.demo.entity.vo.Modules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperContentMapper extends BaseMapper<PaperContent> {

    List<ContentVo> allTitle();

    List<PaperContent> allModule(@Param("paperId") String paperId);

    List<PaperContent> titleIn(@Param("module1") String module);

    List<String> onlyModule();

//    List<PaperContent> titleInModule(@Param("module2") String module);
    List<PaperChoice> findChoices(@Param("paperId") String paperId,@Param("titleId") String titleId);

    boolean delModule(@Param("module2")String module);

    boolean delModule2(@Param("module3")String module);
}
