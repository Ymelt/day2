package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.items.demo.entity.ClassStudent;
import com.items.demo.entity.Course;
import com.items.demo.entity.vo.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassStudentMapper extends BaseMapper<ClassStudent> {

    IPage<ClassStudent> selectMyStudent(IPage<ClassStudent> page, @Param(Constants.WRAPPER) Wrapper<ClassStudent> queryWrapper, @Param("teacherName") String teacherName);
}
