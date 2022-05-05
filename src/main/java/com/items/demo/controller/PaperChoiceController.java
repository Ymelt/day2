package com.items.demo.controller;

import com.items.demo.entity.PaperChoice;
import com.items.demo.entity.PaperContent;
import com.items.demo.service.PaperChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/choice")
public class PaperChoiceController {
//    @Autowired
//    private PaperChoiceService paperChoiceService;
//
//    @RequestMapping("test")
//    public boolean save(@RequestBody PaperChoice paperChoice){
//        return paperChoiceService.save(paperChoice);
//    }
}
