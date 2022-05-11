package com.items.demo.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.common.Constants;
import com.items.demo.common.Result;
import com.items.demo.entity.ClassStudent;
import com.items.demo.entity.ClassTeacher;
import com.items.demo.entity.Course;
import com.items.demo.entity.User;
import com.items.demo.entity.vo.CourseVo;
import com.items.demo.mapper.CourseMapper;
import com.items.demo.mapper.CourseVoMapper;
import com.items.demo.mapper.UserMapper;
import com.items.demo.service.ClassStudentService;
import com.items.demo.service.ClassTeacherService;
import com.items.demo.service.CourseService;
import com.items.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private ClassStudentService classStudentService;

    @Resource
    private ClassTeacherService classTeacherService;

    @Resource
    private CourseService courseService;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseVoMapper courseVoMapper;

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;


    @RequestMapping("/student/page")
    public IPage<ClassStudent> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(defaultValue = "") String gradeNumber,
                                        @RequestParam(defaultValue = "") String classNumber,
                                        @RequestParam(defaultValue = "") String studentName){
        IPage<ClassStudent> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ClassStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("grade_number",gradeNumber);
        queryWrapper.like("the_class_number",classNumber);
        queryWrapper.like("student_name",studentName);
        return classStudentService.page(page,queryWrapper);
    }


    /**
     * classteacher
     */

    @RequestMapping("/teacher/page")
    public IPage<ClassTeacher> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(defaultValue = "") String gradeNumber,
                                        @RequestParam(defaultValue = "") String classNumber,
                                        @RequestParam(defaultValue = "") String course,
                                        @RequestParam(defaultValue = "") String teacherName){
        IPage<ClassTeacher> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ClassTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("grade_number",gradeNumber);
        queryWrapper.like("the_class_number",classNumber);
        queryWrapper.like("class_course",course);
        queryWrapper.like("course_teacher",teacherName);

        return classTeacherService.page(page,queryWrapper);
    }


    @RequestMapping("/teacher/save")
    public Result save(@RequestBody ClassTeacher classTeacher){
        if(classTeacherService.saveOrUpdate(classTeacher)){
            return Result.success(classTeacher);
        }
        return Result.error();
    }


    /**
     * course
     */



    @RequestMapping("/course/savecourse")
    public Result saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @RequestMapping("/course/saveteacher")
    public Result saveTeacher(@RequestBody Course course){
        return courseService.saveTeacher(course);
    }

    @RequestMapping("/course/saveOnlyTeach")
    public Result saveOnlyTeach(@RequestParam String courseName,@RequestParam String teacherName){
        if(StrUtil.isBlank(courseName)){
            return Result.error(Constants.CODE_400,"输入课程参数为空");
        }else if(courseMapper.countFree(teacherName)==0){
            Course course = new Course(courseName,teacherName);
            if(courseService.saveOrUpdate(course)){
                return Result.success(course);
            }else{
                return Result.error(Constants.CODE_500,"添加失败");
            }
        }
        else if(courseMapper.saveOnlyTeach(courseName,teacherName)){
            return Result.success();
        }else{
            return Result.error(Constants.CODE_500,"修改失败");
        }
    }

    /**
     * 查询所有空闲老师但是不实现分页效果
     * @return
     */
    @RequestMapping("/course/OnlyTeacher")
    public List<CourseVo> OnlyTeacher(){
        return courseVoMapper.findFreeTeacher();
    }

    @RequestMapping("/course/allData")
    public List<Course> allData(){
        return courseMapper.arranged();
    }


    @RequestMapping("/course/saveOnlyCourse")
    public Result saveOnlyTeacher(@RequestParam String teacherName,@RequestParam String courseName){
        if(StrUtil.isBlank(teacherName)){
            return Result.error(Constants.CODE_400,"教师名输入为空");
        }
        else if(courseMapper.saveOnlyTeacher(teacherName, courseName)){
            return Result.success();
        }else{
            return Result.error();
        }
    }


    @RequestMapping("/course/all")
    public IPage<Course> findAll(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String courseName){
        IPage<Course> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("course_name",courseName);
        return courseMapper.selectMyPage(page,queryWrapper);
    }

    @RequestMapping("/course/page")
    public IPage<Course> findCoursePage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String courseName){
        IPage<Course> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("course_name",courseName);
        return courseService.page(page,queryWrapper);
    }

    @RequestMapping("/course/tandc")
    public IPage<CourseVo> tandc(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String teacherName){
        IPage<CourseVo> page = new Page<>(pageNum,pageSize);
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        return courseVoMapper.selectTeacher(page,queryWrapper,teacherName);
    }


    @RequestMapping("/course/freeTeacher")
    public IPage<CourseVo> freeTeacher(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){
        IPage<CourseVo> page = new Page<>(pageNum,pageSize);
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        return courseVoMapper.selectFreeTeacher(page,queryWrapper);
    }


    @RequestMapping("/course/decourse1")
    public Result deleteOnlyCourse(@RequestParam String courseName){
//        courseMapper.deleteCourse(courseName)&&
        if(courseMapper.deleteCourse(courseName)){
            return Result.success();
        }
        return Result.error();
    }

    @RequestMapping("/course/decourse")
    public Result deleteCourse(@RequestParam String courseName){
//        courseMapper.deleteCourse(courseName)&&
        if(courseMapper.deleteCourse(courseName)&&courseMapper.deleteClassCourse(courseName)){
            return Result.success();
        }
        return Result.error();
    }

    //    @RequestMapping("/test")
//    public Result selectTeacher(){
//        return Result.success(courseVoMapper.selectTeacher());
//    }
    @RequestMapping("/course/test")
    public String text(@RequestParam String courseName){
//        courseService.saveOrUpdate(course);

        System.out.println(Arrays.toString(courseMapper.checkTeacher(courseName).toArray()));
//        System.out.println(courseMapper.checkTeacher(courseName) ==null);
        for(String em :courseMapper.checkTeacher(courseName)){
            if(StrUtil.isBlank(em)){
                System.out.println("find it");
                break;
            }
        }
        return "true";
    }

    @RequestMapping("/course/check")
    public Result checkCourse(@RequestParam String courseName){
        if(StrUtil.isBlank(courseName)){
            return Result.error(Constants.CODE_400,"传入参数错误");
        }else{
            for(String em :courseMapper.checkTeacher(courseName)){
                if(!em.equals("未安排")){
                    return Result.error(Constants.CODE_600,"当前有老师在授课");
                }
            }
            return Result.success();
        }

    }

    @RequestMapping("/course/removeThisTeacher")
    public Result removeThisTeacher(@RequestBody Course course){
        return courseService.deleteTeacherCourse(course);
    }

    /**
     * 找出所有开设课程
     * @return
     */
    @RequestMapping("/course/onlyCourse")
    public List<Course> onlyCourse(){
        return courseMapper.onlyCourse();
    }

    /**
     * user
     */


    @RequestMapping("/user/save")
    public Result register(@RequestBody User user){
        String account = user.getAccount();
        String password = user.getPassword();
        String username = user.getName();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) ||StrUtil.isBlank(account)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return userService.userRegister(user);

    }

    @RequestMapping("/user/modify")
    public Result modify(@RequestBody User user){
        if (StrUtil.isBlank(user.getAccount()) || StrUtil.isBlank(user.getName()) ||StrUtil.isBlank(user.getPassword())){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        if(userService.saveOrUpdate(user)){
            return Result.success(user);
        }else{
            return Result.error();
        }

    }

    @RequestMapping("/user/login")
    public Result login(@RequestBody User user){
        String username = user.getAccount();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return userService.login(user);
    }


    @RequestMapping("/user/all")
    public IPage<User> findUserAll(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String teacherName  ){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role","老师");
        queryWrapper.like("name",teacherName);

        return userService.page(page,queryWrapper);
    }

    @RequestMapping("/user/allStudents")
    public IPage<User> findAllStudents(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String studentName  ){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role","学生");
        queryWrapper.like("name",studentName);

        return userService.page(page,queryWrapper);
    }

    /**
     * 找到所有的老师
     * @return
     */
    @RequestMapping("/user/allTeacher")
    public List<User> findAllTeacher(){
        return userMapper.selectAllTeacher();
    }


    /**
     * 删除教师 teacher1 not finish
     * @param user
     * @return
     */
    @RequestMapping("/user/removeTeacher")
    public Result remove(@RequestBody User user){
        if(userService.removeById(user.getId())){
            return Result.success(user);
        }else{
            return Result.error(Constants.CODE_500,"删除失败");
        }

    }


    @RequestMapping("/user/username")
    public Result findByUsername(@RequestParam String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", username);
        User user = userService.getOne(queryWrapper);
        return Result.success(user);
    }

    /**
     * 保存欲新增的年级段与班级
     */
    @RequestMapping("/saveGrade")
    public Result saveGrade(@RequestBody ClassTeacher classTeacher){

        if (StrUtil.isBlank(classTeacher.getTheClassNumber()) || StrUtil.isBlank(classTeacher.getGradeNumber())) {
            return Result.error(Constants.CODE_400, "填入参数为空");
        }
        return classTeacherService.saveGrade(classTeacher);
    }


    /**
     * 找出能上课的课程
     */
    @RequestMapping("/findArranged")
    public Result findArranged(){
        return courseService.findArranged();
    }

    /**
     * 安排班级的授课情况
     */
    @RequestMapping("/setCourse")
    public Result arrangeCourse(@RequestBody ClassTeacher classTeacher){
        if(classTeacherService.saveOrUpdate(classTeacher)){
            return Result.success(classTeacher);
        }else{
            return Result.error();
        }
    }

    /**
     * 找出所有空闲的学生
     */
    @RequestMapping("/findFreeStudent")
    public Result findFreeStudent(){
        return Result.success(userMapper.selectFreeStudent());
    }

    /**
     * 删除学生
     */
    @RequestMapping("/deleteStudent")
    public Result deleteStudent(@RequestParam Integer id){
        if(classStudentService.removeById(id)){
            return Result.success();
        }else{
            return Result.error();
        }
    }
}
