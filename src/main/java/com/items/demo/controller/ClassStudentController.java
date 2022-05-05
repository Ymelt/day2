package com.items.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.entity.ClassStudent;
import com.items.demo.entity.ClassTeacher;
import com.items.demo.service.ClassStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class ClassStudentController {
//    @Autowired
//    private ClassStudentService classStudentService;
//
//    @RequestMapping("/page")
//    public IPage<ClassStudent> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam(defaultValue = "") String studentName){
//        IPage<ClassStudent> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<ClassStudent> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("student_name",studentName);
//        return classStudentService.page(page,queryWrapper);
//    }


}
