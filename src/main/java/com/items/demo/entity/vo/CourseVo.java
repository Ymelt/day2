package com.items.demo.entity.vo;

import com.items.demo.entity.User;
import lombok.Data;

@Data
public class CourseVo extends User {

    private String courseName;

    private String courseTeacher;
}
