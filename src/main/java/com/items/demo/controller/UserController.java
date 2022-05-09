package com.items.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.items.demo.common.Constants;
import com.items.demo.common.Result;
import com.items.demo.entity.Course;
import com.items.demo.entity.User;
import com.items.demo.mapper.UserMapper;
import com.items.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/save")
    public Result register(@RequestBody User user){
        String account = user.getAccount();
        String password = user.getPassword();
        String username = user.getName();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) ||StrUtil.isBlank(account)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return userService.userRegister(user);

    }

    @RequestMapping("/modify")
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



    @RequestMapping("/login")
    public Result login(@RequestBody User user){
        String username = user.getAccount();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return userService.login(user);
    }


    @RequestMapping("/all")
    public IPage<User> findAll(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String teacherName  ){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role","老师");
        queryWrapper.like("name",teacherName);

        return userService.page(page,queryWrapper);
    }

    /**
     * 找到所有的老师
     * @return
     */
    @RequestMapping("/allTeacher")
    public List<User> findAllTeacher(){
        return userMapper.selectAllTeacher();
    }


    /**
     * 删除教师 teacher1 not finish
     * @param user
     * @return
     */
    @RequestMapping("/removeTeacher")
    public Result remove(@RequestBody User user){
        if(userService.removeById(user.getId())){
            return Result.success(user);
        }else{
            return Result.error(Constants.CODE_500,"删除失败");
        }

    }


    @RequestMapping("/username")
    public Result findByUsername(@RequestParam String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", username);
        User user = userService.getOne(queryWrapper);
        return Result.success(user);
    }


    /**
     * 测试list返回
     */
    @RequestMapping("/listTest")
    public Result listTest(){
        List<User> userList = new ArrayList<>();
        userList = userService.list();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("count",userList.size());
        map1.put("list",userList);
        System.out.println("***********************");
        System.out.println(map1);
        return new Result("200","成功返回",map1);
    }
}
