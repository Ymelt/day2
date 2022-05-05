package com.items.demo.controller;

import com.items.demo.entity.PaperContent;
import com.items.demo.entity.PaperStudent;
import com.items.demo.entity.vo.ContentVo;
import com.items.demo.entity.vo.Modules;
import com.items.demo.mapper.PaperContentMapper;
import com.items.demo.service.PaperContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class PaperContentController {
//    @Autowired
//    private PaperContentService paperContentService;
//
//    @Autowired
//    private PaperContentMapper paperContentMapper;
//
//    @RequestMapping("test")
//    public boolean save(@RequestBody PaperContent paperContent){
//        return paperContentService.save(paperContent);
//    }
//
//    @RequestMapping("all")
//    public List<PaperContent> all(){
//        return paperContentService.list();
//    }
//
//    /**
//     * 找到试卷的所有模块
//     * @return
//     */
////    @RequestMapping("allModule")
////    public List<PaperContent> allModule(){
////        return paperContentMapper.allModule();
////    }
//
//    /**
//     * 找到对应模块的所有题目
//     * @param module
//     * @return
//     */
//    @RequestMapping("titleIn")
//    public List<PaperContent> titleIn(@RequestParam String module){
//        return paperContentMapper.titleIn(module);
//    }
//
//    @RequestMapping("only")
//    public List<String> only(){
//        return paperContentMapper.onlyModule();
//    }
}
