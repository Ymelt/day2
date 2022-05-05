package com.items.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_account_role")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String account;

    private String password;

    private String role;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private long createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private long updateTime;

    private long deleteTime;

    @TableLogic //用来逻辑删除
    @TableField(fill = FieldFill.INSERT) //自动填充的注解
    private int deleted;
}
