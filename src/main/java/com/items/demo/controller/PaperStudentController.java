package com.items.demo.controller;

import com.items.demo.entity.PaperStudent;
import com.items.demo.service.PaperStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paperStudent")
public class PaperStudentController {

    @Autowired
    private PaperStudentService paperStudentService;

    @RequestMapping("test")
    public boolean save(@RequestBody PaperStudent paperStudent){
        return paperStudentService.save(paperStudent);
    }
}
