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

public interface CourseMapper extends BaseMapper<Course> {

//    @Select("select distinct course_name from sys_course ")
   IPage<Course> selectMyPage(IPage<Course> page, @Param(Constants.WRAPPER) Wrapper<Course> queryWrapper);

   boolean deleteCourse(@Param("courseName") String courseName);

   List<String> checkTeacher(String courseName);

   boolean deleteClassCourse(@Param("courseName2") String courseName);

   boolean saveOnlyTeacher(@Param("teacherName3") String teacherName,@Param("courseName3") String courseName);

   boolean saveOnlyTeach(@Param("courseName5") String courseName,@Param("teacherName5") String teacherName);

   int selectThisCourse(@Param("courseName4") String courseName);

   boolean removeThisTeacher(@Param("id1") int id);

   int countFree(@Param("teacherName6") String teacherName);

   List<Course> onlyCourse();

   List<Course> arranged();
}
