package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_account_role")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String account;

    private String password;

    private String role;

    private int createTime;

    private int updateTime;

    private int deleteTime;
}
