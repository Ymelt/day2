package com.items.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.demo.entity.User;

import java.util.List;

/**
 * Mapper接口
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllTeacher();

    List<String> selectFreeStudent();
}
