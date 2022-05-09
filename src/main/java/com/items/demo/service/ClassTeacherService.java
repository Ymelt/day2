package com.items.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.demo.common.Constants;
import com.items.demo.common.Result;
import com.items.demo.entity.ClassTeacher;
import com.items.demo.mapper.ClassTeacherMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassTeacherService extends ServiceImpl<ClassTeacherMapper, ClassTeacher> {

    public Result saveGrade(ClassTeacher classTeacher){
        QueryWrapper<ClassTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("grade_number", classTeacher.getGradeNumber());
        queryWrapper.eq("the_class_number", classTeacher.getTheClassNumber());
        ClassTeacher one = getOne(queryWrapper);
        if(one != null) {
            return Result.error(Constants.CODE_600, "要创建的已存在");
        }
        if(save(classTeacher)){
            return Result.success(classTeacher);
        }else{
            return Result.error(Constants.CODE_500,"保存失败");
        }
    }
}
