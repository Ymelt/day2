package com.items.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.entity.Course;
import com.items.demo.entity.PaperInfo;
import com.items.demo.entity.PaperStudent;
import com.items.demo.service.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class PaperInfoController {
//    @Autowired
//    private PaperInfoService paperInfoService;
//
//    @RequestMapping("save")
//    public boolean save(@RequestBody PaperInfo paperInfo){
//        return paperInfoService.save(paperInfo);
//    }
//
//    /**
//     * 分页查询
//     * @param pageNum
//     * @param pageSize
//     * @return
//     */
//    @RequestMapping("/page")
//    public IPage<PaperInfo> findPage(@RequestParam Integer pageNum,
//                                     @RequestParam Integer pageSize){
//        IPage<PaperInfo> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<PaperInfo> queryWrapper = new QueryWrapper<>();
////        queryWrapper.like("course_name",courseName);
//        return paperInfoService.page(page,queryWrapper);
//    }
}
