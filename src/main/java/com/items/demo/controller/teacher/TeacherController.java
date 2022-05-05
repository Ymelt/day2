package com.items.demo.controller.teacher;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.common.Result;
import com.items.demo.entity.*;
import com.items.demo.mapper.*;
import com.items.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atTeacher")
public class TeacherController {
    /**
     * content
     */
    @Autowired
    private PaperContentService paperContentService;

    @Autowired
    private PaperContentMapper paperContentMapper;

    @Autowired
    private PaperStudentService paperStudentService;

    @Autowired
    private PaperInfoService paperInfoService;

    @Autowired
    private ClassStudentMapper classStudentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassStudentService classStudentService;

    @Autowired
    private ClassTeacherMapper classTeacherMapper;

    @Autowired
    private PaperInfoMapper paperInfoMapper;

    @Autowired
    private PaperChoiceService paperChoiceService;

    @Autowired
    private PaperChoiceMapper paperChoiceMapper;



    @RequestMapping("/content/test")
    public boolean save(@RequestBody PaperContent paperContent){
        return paperContentService.save(paperContent);
    }

    @RequestMapping("/content/all")
    public List<PaperContent> all(){
        return paperContentService.list();
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
    public List<PaperChoice> findChoices(@RequestParam String paperId,@RequestParam String titleId){
        return paperContentMapper.findChoices(paperId,titleId);
    }

    @RequestMapping("/saveTitle")
    public Result saveTitle(@RequestBody PaperContent paperContent){
        if(paperContentService.saveOrUpdate(paperContent)){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    /**
     * info
     */
    @RequestMapping("/selectCourse")
    public List<String> selectCourse(@RequestParam String teacherName){
        return classTeacherMapper.selectCourse(teacherName);
    }

    @RequestMapping("/selectId")
    public List<String> selectId(@RequestParam String courseName){
        return paperInfoMapper.findId(courseName);
    }

    @RequestMapping("/save")
    public Result save(@RequestBody PaperInfo paperInfo){
         if(paperInfoService.save(paperInfo)){
             return Result.success(paperInfo);
         }else{
             return Result.error();
         }
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/info/page")
    public IPage<PaperInfo> findInfoPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String courseName){
        IPage<PaperInfo> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PaperInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("paper_course",courseName);
        return paperInfoService.page(page,queryWrapper);
    }

    /**
     * paperStudent
     */


    @RequestMapping("/paperStudent/test")
    public boolean save(@RequestBody PaperStudent paperStudent){
        return paperStudentService.save(paperStudent);
    }


    @RequestMapping("/myStudents")
    public IPage<ClassStudent> myStudents(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String teacherName,
                                         @RequestParam(defaultValue = "") String studentName){
        IPage<ClassStudent> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ClassStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name",studentName);
        return classStudentMapper.selectMyStudent(page,queryWrapper,teacherName);

    }

    @RequestMapping("/removeStudent")
    public Result removeStudent(@RequestParam Integer id){
        if(classStudentService.removeById(id)){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    @RequestMapping("/selectFreeStudent")
    public List<String> selectFreeStudent(){
        return userMapper.selectFreeStudent();
    }

    @PostMapping("/addStudent")
    public Result addStudentToClass(@RequestBody  ClassStudent classStudent){
        if(classStudentService.saveOrUpdate(classStudent)){
            return Result.success(classStudent);
        }else{
            return Result.error();
        }
    }

    /**
     * 编辑选择题
     */
    @RequestMapping("/saveChoice")
    public Result saveChoice(@RequestBody PaperChoice paperChoice){
        if(paperChoiceService.saveOrUpdate(paperChoice)){
            return Result.success(paperChoice);
        }else{
            return Result.error();
        }
    }

    /**
     * 根据题号删除题目
     * @param id
     * @return
     */
    @RequestMapping("/deTitle/{id}")
    public Result deleteTitle(@PathVariable String id){
        if(paperContentService.removeById(id)){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    /**
     * 在选择内容弹框中删除单个选项
     * @param id
     * @return
     */
    @RequestMapping("/deOnlyChoice/{id}")
    public Result deleteChoice(@PathVariable String id){
        if(paperChoiceService.removeById(id)){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    /**
     * 在删除题目后选择题的选项也得删除
     */
    @RequestMapping("/deChoice/{id}")
    public Result deChoice(@PathVariable String id){
        if(paperChoiceMapper.deChoice(id)){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    /**
     * 删除模块按钮
     */
    @RequestMapping("/delModule/{module}")
    public Result delModule(@PathVariable String module){
        if(paperContentMapper.delModule(module) &&paperContentMapper.delModule2(module)){
            return Result.success();
        }else{
            return Result.error();
        }
    }
}
