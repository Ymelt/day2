package com.items.demo.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.common.Result;
import com.items.demo.entity.*;
import com.items.demo.entity.vo.AnswerVo;
import com.items.demo.mapper.AnswerVoMapper;
import com.items.demo.mapper.PaperContentMapper;
import com.items.demo.mapper.PaperInfoMapper;
import com.items.demo.mapper.PaperStudentMapper;
import com.items.demo.service.PaperInfoService;
import com.items.demo.service.PaperStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/atStudent")
public class StudentController {

    @Autowired
    private PaperStudentService paperStudentService;

    @Autowired
    private PaperInfoService paperInfoService;

    @Autowired
    private PaperContentMapper paperContentMapper;

    @Autowired
    private PaperInfoMapper paperInfoMapper;

    @Autowired
    private AnswerVoMapper answerVoMapper;

    @Autowired
    private PaperStudentMapper paperStudentMapper;

    @RequestMapping("/save")
    public Result saveRecord(@RequestBody PaperStudent paperStudent){

        if(answerVoMapper.anyThis(paperStudent.getPaperTitleId()) == 1){
            if(paperStudentMapper.deleteFirst(paperStudent.getPaperTitleId())){
                if(paperStudentService.saveOrUpdate(paperStudent)){
                    return Result.success(paperStudent);
                }else{
                    return Result.error();
                }
            }else{
                return Result.error();
            }

        }else{
            if(paperStudentService.saveOrUpdate(paperStudent)){
                return Result.success(paperStudent);
            }else{
                return Result.error();
            }
        }

    }


    @RequestMapping("/selectPapers")
    public IPage<PaperInfo> findPapers(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize
                                         ){
        IPage<PaperInfo> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PaperInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("paper_course",courseName);
        return paperInfoService.page(page,queryWrapper);
    }

    /**
     * 找到试卷的所有模块
     * @return
     */
    @RequestMapping("/content/allModule")
    public List<PaperContent> allModule(@RequestParam String paperId){
        return paperContentMapper.allModule(paperId);
    }

    /**
     * 找到对应模块的所有题目
     * @param module
     * @return
     */
    @RequestMapping("/content/titleIn")
    public List<PaperContent> titleIn(@RequestParam String module){
        return paperContentMapper.titleIn(module);
    }

    @RequestMapping("/content/only")
    public List<String> only(){
        return paperContentMapper.onlyModule();
    }

    @RequestMapping("/findChoices")
    public List<PaperChoice> findChoices(@RequestParam String paperId, @RequestParam String titleId){
        return paperContentMapper.findChoices(paperId,titleId);
    }

    @RequestMapping("/findIdForStu")
    public List<String> findIdForStu(@RequestParam String name){
        return paperInfoMapper.findIdForStu(name);
    }

    /**
     * 拿到所有的
     * @return
     */
    @RequestMapping("/answerShell")
    public List<AnswerVo> answerShell(@RequestParam String module,@RequestParam String paperId){
        return answerVoMapper.studentAnswerShell(module,paperId);
    }

    /**
     * 将试卷的作答情况进行批改
     */
    @RequestMapping("judge")
    public Result judge(){
        if(answerVoMapper.judge()){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    /**
     * 弄出历史试卷
     * @param paperId
     * @return
     */
    @RequestMapping("/history")
    public List<AnswerVo> history(@RequestParam String paperId){
        return answerVoMapper.shellHistory(paperId);
    }

    /**
     * 找出当前学生对应的试卷号
     * @param studentName
     * @return
     */
    @RequestMapping("/selectIds")
    public List<String> selectIds(@RequestParam String studentName){
        return paperStudentMapper.findIds(studentName);
    }
}
