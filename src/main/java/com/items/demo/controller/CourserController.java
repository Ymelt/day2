package com.items.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.common.Constants;
import com.items.demo.common.Result;
import com.items.demo.entity.Course;
import com.items.demo.entity.vo.CourseVo;
import com.items.demo.mapper.CourseMapper;
import com.items.demo.mapper.CourseVoMapper;
import com.items.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourserController {
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private CourseMapper courseMapper;
//
//    @Autowired
//    private CourseVoMapper courseVoMapper;
//
//    @RequestMapping("/savecourse")
//    public Result saveCourse(@RequestBody Course course){
//        return courseService.saveCourse(course);
//    }
//
//    @RequestMapping("saveteacher")
//    public Result saveTeacher(@RequestBody Course course){
//        return courseService.saveTeacher(course);
//    }
//
//    @RequestMapping("saveOnlyTeach")
//    public Result saveOnlyTeach(@RequestParam String courseName,@RequestParam String teacherName){
//        if(StrUtil.isBlank(courseName)){
//            return Result.error(Constants.CODE_400,"输入课程参数为空");
//        }else if(courseMapper.countFree(teacherName)==0){
//            Course course = new Course(courseName,teacherName);
//             if(courseService.saveOrUpdate(course)){
//                 return Result.success(course);
//             }else{
//                 return Result.error(Constants.CODE_500,"添加失败");
//             }
//        }
//        else if(courseMapper.saveOnlyTeach(courseName,teacherName)){
//            return Result.success();
//        }else{
//            return Result.error(Constants.CODE_500,"修改失败");
//        }
//    }
//
//    /**
//     * 查询所有空闲老师但是不实现分页效果
//     * @return
//     */
//    @RequestMapping("OnlyTeacher")
//    public List<CourseVo> OnlyTeacher(){
//        return courseVoMapper.findFreeTeacher();
//    }
//
//    @RequestMapping("/allData")
//    public List<Course> allData(){
//        return courseMapper.arranged();
//    }
//
//
//    @RequestMapping("saveOnlyCourse")
//    public Result saveOnlyTeacher(@RequestParam String teacherName,@RequestParam String courseName){
//        if(StrUtil.isBlank(teacherName)){
//            return Result.error(Constants.CODE_400,"教师名输入为空");
//        }
//        else if(courseMapper.saveOnlyTeacher(teacherName, courseName)){
//            return Result.success();
//        }else{
//            return Result.error();
//        }
//    }
//
//
//    @RequestMapping("/all")
//    public IPage<Course> findAll(@RequestParam Integer pageNum,
//                                @RequestParam Integer pageSize,
//                                @RequestParam(defaultValue = "") String courseName){
//        IPage<Course> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("course_name",courseName);
//        return courseMapper.selectMyPage(page,queryWrapper);
//    }
//
//    @RequestMapping("/page")
//    public IPage<Course> findPage(@RequestParam Integer pageNum,
//                                  @RequestParam Integer pageSize,
//                                  @RequestParam(defaultValue = "") String courseName){
//        IPage<Course> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("course_name",courseName);
//        return courseService.page(page,queryWrapper);
//    }
//
//    @RequestMapping("/tandc")
//    public IPage<CourseVo> tandc(@RequestParam Integer pageNum,
//                                 @RequestParam Integer pageSize,
//                                 @RequestParam(defaultValue = "") String teacherName){
//        IPage<CourseVo> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
//        return courseVoMapper.selectTeacher(page,queryWrapper,teacherName);
//    }
//
//
//    @RequestMapping("freeTeacher")
//    public IPage<CourseVo> freeTeacher(@RequestParam Integer pageNum,
//                                 @RequestParam Integer pageSize){
//        IPage<CourseVo> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
//        return courseVoMapper.selectFreeTeacher(page,queryWrapper);
//    }
//
//
//    @RequestMapping("decourse1")
//    public Result deleteOnlyCourse(@RequestParam String courseName){
////        courseMapper.deleteCourse(courseName)&&
//        if(courseMapper.deleteCourse(courseName)){
//            return Result.success();
//        }
//        return Result.error();
//    }
//
//    @RequestMapping("decourse")
//    public Result deleteCourse(@RequestParam String courseName){
////        courseMapper.deleteCourse(courseName)&&
//        if(courseMapper.deleteCourse(courseName)&&courseMapper.deleteClassCourse(courseName)){
//            return Result.success();
//        }
//        return Result.error();
//    }
//
////    @RequestMapping("/test")
////    public Result selectTeacher(){
////        return Result.success(courseVoMapper.selectTeacher());
////    }
//    @RequestMapping("/test")
//    public String text(@RequestParam String courseName){
////        courseService.saveOrUpdate(course);
//
//        System.out.println(Arrays.toString(courseMapper.checkTeacher(courseName).toArray()));
////        System.out.println(courseMapper.checkTeacher(courseName) ==null);
//        for(String em :courseMapper.checkTeacher(courseName)){
//            if(StrUtil.isBlank(em)){
//                System.out.println("find it");
//                break;
//            }
//        }
//        return "true";
//    }
//
//    @RequestMapping("check")
//    public Result checkCourse(@RequestParam String courseName){
//        if(StrUtil.isBlank(courseName)){
//            return Result.error(Constants.CODE_400,"传入参数错误");
//        }else{
//            for(String em :courseMapper.checkTeacher(courseName)){
//                if(!em.equals("未安排")){
//                    return Result.error(Constants.CODE_600,"当前有老师在授课");
//                }
//            }
//            return Result.success();
//        }
//
//    }
//
//    @RequestMapping("/removeThisTeacher")
//    public Result removeThisTeacher(@RequestBody Course course){
//        return courseService.deleteTeacherCourse(course);
//    }
//
//    /**
//     * 找出所有开设课程
//     * @return
//     */
//    @RequestMapping("/onlyCourse")
//    public List<Course> onlyCourse(){
//        return courseMapper.onlyCourse();
//    }
}
