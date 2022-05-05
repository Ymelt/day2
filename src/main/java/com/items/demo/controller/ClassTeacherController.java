package com.items.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.common.Result;
import com.items.demo.entity.ClassTeacher;
import com.items.demo.service.ClassTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class ClassTeacherController {
//    @Autowired
//    private ClassTeacherService classTeacherService;
//
//    @RequestMapping("/page")
//    public IPage<ClassTeacher> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam(defaultValue = "") String gradeNumber,
//                                        @RequestParam(defaultValue = "") String classNumber){
//        IPage<ClassTeacher> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<ClassTeacher> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("grade_number",gradeNumber);
//        queryWrapper.like("the_class_number",classNumber);
//        return classTeacherService.page(page,queryWrapper);
//    }
//
//
//    @RequestMapping("save")
//    public Result save(@RequestBody ClassTeacher classTeacher){
//        if(classTeacherService.saveOrUpdate(classTeacher)){
//            return Result.success(classTeacher);
//        }
//        return Result.error();
//    }

}
