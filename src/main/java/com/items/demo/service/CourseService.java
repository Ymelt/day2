package com.items.demo.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.demo.common.Constants;
import com.items.demo.common.Result;
import com.items.demo.entity.Course;
import com.items.demo.mapper.CourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
public class CourseService extends ServiceImpl<CourseMapper, Course> {
    @Resource
    private CourseMapper courseMapper;

//    public List<Course> findAll(){
//        return courseMapper.selectMyPage(IPage<Course> page, @Param(Constants.WRAPPER) Wrapper<Course> queryWrapper)
//    }

    public Result saveCourse(Course course){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        String courseName = course.getCourseName();
        if (StrUtil.isBlank(courseName)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        queryWrapper.eq("course_name",courseName);
        queryWrapper.last("limit 1");
        Course one = getOne(queryWrapper);

        if(one != null){
            return Result.error(Constants.CODE_600,"参数已存在");
        }else if(save(course)){
            return Result.success(course);
        }
        return Result.error();
    }

    public Result saveTeacher(Course course){
        String courseName = course.getCourseName();
        String teacherName = course.getCourseTeacher();
        if (StrUtil.isBlank(courseName) || StrUtil.isBlank(teacherName)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        if(saveOrUpdate(course)){
            return Result.success(course);
        }
        return Result.error();
    }

    /**
     * 很明显写的不对，先不删了
     * @param course
     * @return
     */
    public Result saveOnlyTeacher(Course course){

        if(saveOrUpdate(course)){
            return Result.success(course);
        }
        return Result.error();
    }


    public Result deleteCourse( String courseName){
        if(StrUtil.isBlank(courseName)){
            return Result.error(Constants.CODE_400,"传入参数错误");
        }else if(courseMapper.deleteCourse(courseName)){
            return Result.success();
        }
        return Result.error();
    }

    public Result deleteTeacherCourse(Course course){
        if(courseMapper.selectThisCourse(course.getCourseName()) == 1){
            if(courseMapper.removeThisTeacher(course.getId())){
                return Result.success();
            }else{
                return Result.error(Constants.CODE_500,"修改课程授课老师错误");
            }
        }else{
            if(removeById(course.getId())){
                return Result.success();
            }else{
                return Result.error();
            }
        }
    }
}
