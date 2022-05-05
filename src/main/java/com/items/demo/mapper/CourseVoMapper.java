package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.items.demo.entity.Course;
import com.items.demo.entity.vo.CourseVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseVoMapper extends BaseMapper<CourseVo> {
//    @Select("select r.name,c.course_name from sys_account_role r left join sys_course c on r.name = c.course_teacher where r.role = '老师'")
    IPage<CourseVo> selectTeacher(IPage<CourseVo> page,@Param(Constants.WRAPPER) Wrapper<CourseVo> queryWrapper,String tName);

    IPage<CourseVo> selectFreeTeacher(IPage<CourseVo> page,@Param(Constants.WRAPPER) Wrapper<CourseVo> queryWrapper);

    List<CourseVo> findFreeTeacher();
}
