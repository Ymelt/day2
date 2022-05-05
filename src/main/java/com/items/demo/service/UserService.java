package com.items.demo.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.demo.common.Constants;
import com.items.demo.common.Result;
import com.items.demo.entity.User;
import com.items.demo.mapper.UserMapper;
import com.items.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;


@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public Result login(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount());
        queryWrapper.eq("password",user.getPassword());
        User one = getOne(queryWrapper);
        if(one != null){
            String token = TokenUtils.getToken(one);
            return new Result(Constants.CODE_200,token,one);
        }
        return Result.error();
    }


    public Result userRegister(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount());
        User one = getOne(queryWrapper);
        if(one != null){
            return Result.error();
        }
        else if(save(user)){
            return Result.success(user);
        }
        return Result.error();
    }

}
