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

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/atStudent")
public class StudentController {

    @Resource
    private PaperStudentService paperStudentService;

    @Resource
    private PaperInfoService paperInfoService;

    @Resource
    private PaperContentMapper paperContentMapper;

    @Resource
    private PaperInfoMapper paperInfoMapper;

    @Resource
    private AnswerVoMapper answerVoMapper;

    @Resource
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
     * ???????????????????????????
     * @return
     */
    @RequestMapping("/content/allModule")
    public List<PaperContent> allModule(@RequestParam String paperId){
        return paperContentMapper.allModule(paperId);
    }

    /**
     * ?????????????????????????????????
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
     * ???????????????
     * @return
     */
    @RequestMapping("/answerShell")
    public List<AnswerVo> answerShell(@RequestParam String module,@RequestParam String paperId){
        return answerVoMapper.studentAnswerShell(module,paperId);
    }

    /**
     * ????????????????????????????????????
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
     * ??????????????????
     * @param paperId
     * @return
     */
    @RequestMapping("/history")
    public List<AnswerVo> history(@RequestParam String paperId){
        return answerVoMapper.shellHistory(paperId);
    }

    /**
     * ????????????????????????????????????
     * @param studentName
     * @return
     */
    @RequestMapping("/selectIds")
    public List<String> selectIds(@RequestParam String studentName){
        return paperStudentMapper.findIds(studentName);
    }
}
