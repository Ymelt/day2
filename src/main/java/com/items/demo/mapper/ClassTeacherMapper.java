package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.items.demo.entity.ClassTeacher;
import com.items.demo.entity.vo.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassTeacherMapper extends BaseMapper<ClassTeacher> {

    IPage<ClassTeacher> selectMyPage(IPage<CourseVo> page, @Param("gradeNumber") String gradeNumber,@Param("classNumber") String classNumber);

    List<String> selectCourse(@Param("teacherName") String teacherName);

}
